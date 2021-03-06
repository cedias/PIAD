package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Reliability PreparedStatement Class
 * @author charles
 *
 */
public class ReliabilitySQL {

	/**
	 * Get Update product Reliability statement
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getUpdateReliabilityStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE products SET  `reliability_score` = ? WHERE `product_id` = ?;";
		return conn.prepareStatement(sql);
	
	}

	/**
	 * configure Update product Reliability statement
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
public static PreparedStatement insertBatchUpdateReliability(PreparedStatement st, double score,String product_id) throws SQLException{
	/*
	 * 1: reliability_score
	 * 2: user_id_id
	 */
	st.setDouble(1, score);
	st.setString(2, product_id);
	st.addBatch();
	return st;
}

	
}
