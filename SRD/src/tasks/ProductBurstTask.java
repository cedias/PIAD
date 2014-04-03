package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import threads.ProductBurstThread;
import tools.BurstComputer;
import upload.Uploader;

import database.DB;
import database.UpdateProducts;

public class ProductBurstTask implements Runnable {

	/*Products with More than X reviews per day*/
	final String sql_suspects = "SELECT DISTINCT r.product_id "+
						"FROM reviews r " +
						"GROUP BY r.product_id, r.time " +
						"HAVING COUNT( r.review_id ) >= ? " +
						"ORDER BY r.product_id DESC";
	
	/*Nb review per day*/
	final String sql_liste = "SELECT count(r.review_id) as ct FROM reviews r  WHERE r.product_id LIKE ? GROUP BY r.time";
	
	/*Avg and Std of review/day of product*/
	final String sql_stats = "SELECT AVG( v.ct ) AS moy, STD( v.ct ) AS var " +
			"FROM ( SELECT r.time AS TIME, COUNT( r.review_id ) AS ct " +
			"FROM reviews r " +
			"WHERE r.product_id LIKE ? " +
			"GROUP BY r.time ) v;";

	final int min;
	final int eps;

	public ProductBurstTask(int min, int eps) {
		super();
		this.min = min;
		this.eps = eps;
	}

	@Override
	public void run() {
		Connection conn,conn2;
		
		try {
			conn = DB.getConnection();
			conn2 = DB.getConnection();
			PreparedStatement st = DB.getStreamingStatement(sql_suspects, conn);

			UpdateProducts updater = new UpdateProducts(conn2);
			ProductBurstThread th;
			st.setInt(1, min);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				String pid = rs.getString(1);
				
				
				
				th = new ProductBurstThread(pid, eps, updater);
				th.start(); /*could be parallelized*/
				th.join();
				
				System.out.println(pid);
				

			}
			updater.flushBatch();


		}catch(Exception e){
			System.out.println("Erreur Product Burst Task");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}
	
}
