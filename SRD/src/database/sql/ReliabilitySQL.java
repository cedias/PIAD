package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReliabilitySQL {

	
	public static PreparedStatement getUpdateReliabilityStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE  `amazon`.`products` SET  `reliability_score` =? WHERE  `products`.`product_id` =?;";
		return conn.prepareStatement(sql);
	
	}

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
