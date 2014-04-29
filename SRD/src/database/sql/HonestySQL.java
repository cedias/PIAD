package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Honesty statements
 * @author charles
 *
 */
public class HonestySQL {

/**
 * Get Update Honesty PreparedStatement
 * @param conn
 * @return
 * @throws SQLException
 */
public static PreparedStatement getUpdateHonestyStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE  reviews SET honesty_score = ? WHERE  review_id=?;";
		
		return conn.prepareStatement(sql);
	}
	
/**
 * Configure Update Honesty PreparedStatement
 * @param st
 * @param score
 * @param review_id
 * @return
 * @throws SQLException
 */
	public static PreparedStatement insertBatchUpdateHonesty(PreparedStatement st, double score,int review_id) throws SQLException{
		/*
		 * 1: reliability_score
		 * 2: review_id_id
		 */
		st.setDouble(1, score);
		st.setInt(2, review_id);
		st.addBatch();
		return st;
	}
}
