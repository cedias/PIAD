package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DB;

public class ProductBurstTask implements Runnable {

	final String sql = "SELECT r.product_id, r.time, COUNT( * ) AS nb_reviews"+
						"FROM reviews r" +
						"GROUP BY r.product_id, r.time" +
						"HAVING r.nb_reviews > ?" +
						"ORDER BY r.nb_reviews DESC";
	
	final int max;
	final int window;
	
	public ProductBurstTask(int max, int window) {
		super();
		this.max = max;
		this.window = window;
	}

	@Override
	public void run() {
		Connection conn;
		try {
			conn = DB.getConnection();
			PreparedStatement st = DB.getStreamingStatement(sql, conn);
			st.setInt(1, 10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				System.out.println("hello");
				
			}
			
			
		}catch(Exception e){
			System.out.println("Erreur Product Burst Task");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
	}

}
