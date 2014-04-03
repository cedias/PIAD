package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;
import database.UpdateProducts;
import tools.BurstComputer;

public class ProductBurstThread extends Thread{
	
	private final String product_id;
	private final int eps;
	private final UpdateProducts updater;
	
	/*Nb review per day*/
	final String sql_liste = "SELECT count(r.review_id) as ct FROM reviews r  WHERE r.product_id LIKE ? GROUP BY r.time";
	
	/*Avg and Std of review/day of product*/
	final String sql_stats = "SELECT AVG( v.ct ) AS moy, STD( v.ct ) AS var " +
			"FROM ( SELECT r.time AS TIME, COUNT( r.review_id ) AS ct " +
			"FROM reviews r " +
			"WHERE r.product_id LIKE ? " +
			"GROUP BY r.time ) v;";

	public ProductBurstThread(String product_id,int eps, UpdateProducts updater) {
		super();
		this.product_id = product_id;
		this.eps = eps;
		this.updater = updater;
	}

	@Override
	public void run() {
		
		Connection conn;
		ResultSet rs;
		BurstComputer burstComp;
		
		try {
			conn = DB.getConnection();
		
		
		PreparedStatement statsStatement = conn.prepareStatement(sql_stats);
		PreparedStatement listStatement = DB.getStreamingStatement(sql_liste, conn);
		
		//prepare queries
		statsStatement.setString(1, product_id);
		listStatement.setString(1, product_id);
		
		//get stats
		rs = statsStatement.executeQuery();
		rs.next();
		burstComp = new BurstComputer(rs.getDouble(1), rs.getDouble(2), eps);
		rs.close();
		statsStatement.close();
		
		//fetch list
		rs = listStatement.executeQuery();
		while(rs.next()){
			burstComp.add(rs.getInt(1));
		}
		int nb_bursts = burstComp.nbBursts();
		
		rs.close();
		listStatement.close();
		conn.close();
	
		updater.addBursts(product_id, nb_bursts);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
