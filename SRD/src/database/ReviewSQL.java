package database;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


public class ReviewSQL {

	public static PreparedStatement getInsertReviewStatement(Connection c) throws ClassNotFoundException, SQLException{
		String sql ="INSERT INTO `amazon`.`reviews` "
				+"(`review_id`, `user_id`, `product_id`, `score`, `time`, `helpfullness`, `nb_helpfullness`, `summary`, `text`, `is_exact_duplicate`, `exact_duplicate_id`)"
				+"VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
			
			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}
	
	public static void insertReviewBatch(PreparedStatement st, String uid, String pid, float score, String time, int help
				,int nbHelp, String summary, String text, boolean isExactDup, int dupeId) throws ClassNotFoundException, SQLException{
		
			st.setString(1, uid);
			st.setString(2, pid);
			st.setFloat(3, score);
			st.setString(4,time);
			st.setInt(5,help);
			st.setInt(6,nbHelp);
			st.setString(7,summary);
			st.setString(8,text);
			st.setBoolean(9,isExactDup);
			st.setInt(10,dupeId);
			st.addBatch();
			
	}
		

	public static void insertReviewBatch(PreparedStatement st,String text, boolean isExactDup, int dupeId) throws SQLException, ClassNotFoundException{
		st.setNull(1, Types.INTEGER);
		st.setNull(2, Types.INTEGER);
		st.setNull(3, Types.FLOAT);
		st.setNull(4, Types.DATE);
		st.setNull(5, Types.INTEGER);
		st.setNull(6, Types.INTEGER);
		st.setNull(7, Types.BLOB);
		st.setString(8,text);
		st.setBoolean(9,isExactDup);
		st.setInt(10,dupeId);
		st.addBatch();
	}

}