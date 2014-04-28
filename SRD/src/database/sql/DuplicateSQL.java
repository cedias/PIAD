package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Exact Duplicate Review Statement
 * @author charles
 *
 */
public class DuplicateSQL {

	/**
	 * Get the statement
	 * @param c Connection
	 * @return PreparedStatement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static PreparedStatement getInsertExactDuplicateStatement(Connection c) throws ClassNotFoundException, SQLException{
		/*
		 * 1:	dupe_id
		 * 2:	orig_id
		 */
		String sql ="UPDATE reviews SET exact_dup_id=  ? , honesty_score = -1 WHERE review_id = ?;";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}
	
	/**
	 * Configure the statement and insert it to batch
	 * @param st PreparedStatement
	 * @param id1 int
	 * @param id2 int 
	 * @return PreparedStatement 
	 * @throws SQLException
	 */
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
