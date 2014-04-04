package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DuplicateSQL {

	public static PreparedStatement getInsertExactDuplicateStatement(Connection c) throws ClassNotFoundException, SQLException{
		/*
		 * 1:	dupe_id
		 * 2:	orig_id
		 */
		String sql ="UPDATE reviews SET exact_dup_id=  ? , honesty_score = -1 WHERE review_id = ?;";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}
	
	public static PreparedStatement insertExactDuplicateBatch(PreparedStatement st, int id1,int id2) throws SQLException{
		
		if(id1<id2)
		{
			/*id1 < id2 -> id2 WHERE*/
			st.setInt(1,id1);
			st.setInt(2,id2);
		}
		else
		{
			/*id1 > id2 -> id1 WHERE*/
			st.setInt(2, id1);
			st.setInt(1, id2);
		}
		st.addBatch();
		return st;
	}
}
