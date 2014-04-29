package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User trustiness statements class
 * @author charles
 *
 */
public class TrustinessSQL {
	
	/**
	 * get update statement
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getUpdateTrustinessStatement(Connection conn) throws SQLException{
		
	final String sql = 	"UPDATE  users SET  `trust_score` =? WHERE  `users`.`user_id` =?;";
	return conn.prepareStatement(sql);
	
	}

	/**
	 * configure and add statement to batch
	 * @param st
	 * @param score
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement insertBatchUpdateTrustiness(PreparedStatement st, double score,String user_id) throws SQLException{
		/*
		 * 1: reliability_score
		 * 2: user_id_id
		 */
		st.setDouble(1, score);
		st.setString(2, user_id);
		st.addBatch();
		return st;
	}

}
