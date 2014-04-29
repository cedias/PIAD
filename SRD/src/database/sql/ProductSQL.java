package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Product PreparedStatement class
 * @author charles
 *
 */
public class ProductSQL {

	/**
	 * Get insert product PreparedStatement
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getInsertReviewStatement(
			Connection c) throws SQLException {
		
		/*
		 *1.Product id
		 *2.Product duplicate reviews count
		 *3.Product burst count
		 *4.Product reliability score
		 *
		 *NB: INSERT IGNORE Statement: if key already exists, doesn't insert anything.
		 */
		
		String sql ="INSERT IGNORE INTO products " +
				"(`product_id`,`product_name`)" +
				" VALUES (?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}

	/**
	 * Configure insert Product PreparedStatement
	 * @param stProducts
	 * @param product_id
	 * @param product_name
	 * @throws SQLException
	 */
	public static void insertProductBatch(PreparedStatement stProducts,
			String product_id, String product_name) throws SQLException {
		
		stProducts.setString(1, product_id);
		stProducts.setString(2, product_name);
		
		stProducts.addBatch();
		
	}

	/**
	 * get update product nb bursts PreparedStatement
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getUpdateNBBurstsStatement(Connection c) throws SQLException {
		/*
		 *1.Product Nb_Bursts
		 *2.Product ID
		 */
		
		String sql = "UPDATE  products SET  `nb_bursts` = ? WHERE  `products`.`product_id` =  ?";
		PreparedStatement st = c.prepareStatement(sql);
		
		return st;
	}

	/**
	 * configure update product nb bursts PreparedStatement
	 * @param st
	 * @param product_id
	 * @param nb_bursts
	 * @throws SQLException
	 */
	public static void addBatchNBBurstsUpdate(PreparedStatement st,
			String product_id, int nb_bursts) throws SQLException {
		
		st.setInt(1, nb_bursts);
		st.setString(2, product_id);
		st.addBatch();
	}


}
