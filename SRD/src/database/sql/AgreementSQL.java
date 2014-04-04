package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgreementSQL {

	

	public static PreparedStatement getUpdateAgreementScoreStatement(Connection c) throws ClassNotFoundException, SQLException{
		/*
		 * 1:	agreement score
		 * 2:	review id
		 */
		String sql = "UPDATE reviews SET  agreement_score = ? WHERE  review_id =?;";


		PreparedStatement st = c.prepareStatement(sql);
		return st;
	}
	
	public static void updateAgreementScoreBatch(PreparedStatement st, double score ,int review_id) throws SQLException{
		
		st.setDouble(1, score);
		st.setInt(2, review_id);
		st.addBatch();
		
	}
	
	
}


