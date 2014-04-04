package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrustinessSQL {
	
	public static PreparedStatement getUpdateTrustinessStatement(Connection conn) throws SQLException{
		
	final String sql = 	"UPDATE  users SET  `trust_score` =? WHERE  `users`.`user_id` =?;";
	return conn.prepareStatement(sql);
	
	}

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
