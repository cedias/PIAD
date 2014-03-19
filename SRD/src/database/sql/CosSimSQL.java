package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CosSimSQL {

	public static PreparedStatement getInsertCosSimilStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE  `amazon`.`reviews` SET  `cos_simil_ident` =  ? WHERE  `reviews`.`review_id` =?;";
		
		return conn.prepareStatement(sql);
	}
	
	public static PreparedStatement insertBatchCosSimil(PreparedStatement st, int reviewId,double cosSimil) throws SQLException{
		st.setInt(2, reviewId);
		st.setDouble(1, cosSimil);
		st.addBatch();
		return st;
	}
	
}
