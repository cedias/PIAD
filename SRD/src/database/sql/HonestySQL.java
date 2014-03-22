package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HonestySQL {

public static PreparedStatement getUpdateHonestyStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE  `amazon`.`reviews` SET  `honesty_score` =  ? WHERE  `reviews`.`review_id` =?;";
		
		return conn.prepareStatement(sql);
	}
	
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
