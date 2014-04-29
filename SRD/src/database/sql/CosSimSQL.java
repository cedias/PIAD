package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cosinus Similarity statements.
 * @author charles
 *
 */
public class CosSimSQL {

	/**
	 * Get insert cos_sim statement
	 * @param conn Connection
	 * @return PreparedStatement the insert statement
	 * @throws SQLException
	 */
	public static PreparedStatement getInsertCosSimilStatement(Connection conn) throws SQLException{
		
		final String sql = 	"UPDATE reviews SET  cos_simil_ident =  ? WHERE  review_id =?;";
		
		return conn.prepareStatement(sql);
	}
	
	/**
	 * Configure the insert CosSimilStatement and adds it to batch.
	 * @param st PreparedStatement
	 * @param reviewId int
	 * @param cosSimil double
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public static PreparedStatement insertBatchCosSimil(PreparedStatement st, int reviewId,double cosSimil) throws SQLException{
		st.setInt(2, reviewId);
		st.setDouble(1, cosSimil);
		st.addBatch();
		return st;
	}
	
}
