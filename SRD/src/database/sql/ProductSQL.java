package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductSQL {

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
		
		String sql ="INSERT IGNORE INTO `amazon`.`products` " +
				"(`product_id`,`product_name`, `nb_duplicates`, `nb_bursts`)" +
				" VALUES (?, ?, ?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}

	public static void insertProductBatch(PreparedStatement stProducts,
			String product_id, String product_name) throws SQLException {
		
		stProducts.setString(1, product_id);
		stProducts.setString(2, product_name);
		stProducts.setInt(3, 0);
		stProducts.setInt(4, 0);
		
		stProducts.addBatch();
		
	}


}
