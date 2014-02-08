package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;


public class ReviewSQL {

	public static PreparedStatement getInsertReviewStatement(Connection c) throws ClassNotFoundException, SQLException{
		/*
		 * 1:	user_id
		 * 2:	product_id
		 * 3:	score
		 * 4:	time
		 * 5:	helpfullness
		 * 6:	nb_helpfullness
		 * 7:	summary
		 * 8:	text
		 * 9:	exact_duplicate_id
		 * 10:	near_duplicate_id
		 */
		String sql ="INSERT INTO `amazon`.`reviews` "
				+"(`review_id`, `user_id`, `product_id`, `score`, `time`, `helpfullness`, `nb_helpfullness`, `summary`, `text`, `exact_duplicate_id`,`near_duplicate_id`)"
				+"VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}



	public static void insertReviewBatchExactDupe(PreparedStatement st, String uid, String pid, float score, String time, int help
				,int nbHelp, String summary, String text, int dupeId) throws ClassNotFoundException, SQLException{

			st.setString(1, uid);
			st.setString(2, pid);
			st.setFloat(3, score);
			st.setTimestamp(4,new Timestamp(Long.parseLong(time)*1000));
			st.setInt(5,help);
			st.setInt(6,nbHelp);
			st.setString(7,summary);
			st.setString(8,text);
			st.setNull(10, Types.INTEGER);

			if(dupeId!=0)
				st.setInt(9,dupeId);
			else
				st.setNull(9, Types.INTEGER);


			st.addBatch();

	}



	public static void insertReviewBatchExactDupe(PreparedStatement st,String text, int dupeId) throws SQLException, ClassNotFoundException{
		st.setNull(1, Types.INTEGER);
		st.setNull(2, Types.INTEGER);
		st.setNull(3, Types.FLOAT);
		st.setNull(4, Types.DATE);
		st.setNull(5, Types.INTEGER);
		st.setNull(6, Types.INTEGER);
		st.setNull(7, Types.BLOB);
		st.setString(8,text);
		st.setNull(10, Types.INTEGER);

		if(dupeId!=0)
			st.setInt(9,dupeId);
		else
			st.setNull(9, Types.INTEGER);

		st.addBatch();
	}

	public static void insertReviewBatchNearDupe(PreparedStatement st, String uid, String pid, float score, String time, int help
			,int nbHelp, String summary, String text, int dupeId) throws ClassNotFoundException, SQLException{

		st.setString(1, uid);
		st.setString(2, pid);
		st.setFloat(3, score);
		st.setTimestamp(4,new Timestamp(Long.parseLong(time)*1000));
		st.setInt(5,help);
		st.setInt(6,nbHelp);
		st.setString(7,summary);
		st.setString(8,text);
		st.setNull(9, Types.INTEGER);

		if(dupeId!=0)
			st.setInt(10,dupeId);
		else
			st.setNull(10, Types.INTEGER);

		st.addBatch();

}



public static void insertReviewBatchNearDupe(PreparedStatement st,String text, int dupeId) throws SQLException, ClassNotFoundException{
	st.setNull(1, Types.INTEGER);
	st.setNull(2, Types.INTEGER);
	st.setNull(3, Types.FLOAT);
	st.setNull(4, Types.DATE);
	st.setNull(5, Types.INTEGER);
	st.setNull(6, Types.INTEGER);
	st.setNull(7, Types.BLOB);
	st.setString(8,text);
	st.setNull(9, Types.INTEGER);

	if(dupeId!=0)
		st.setInt(10,dupeId);
	else
		st.setNull(10, Types.INTEGER);

	st.addBatch();
}

public static PreparedStatement getUpdateNearDuplicateStatement(Connection c) throws SQLException{
	String sql = "UPDATE  `amazon`.`reviews` SET  `near_duplicate_id` = ? WHERE  `reviews`.`review_id` =?;";
	PreparedStatement st = c.prepareStatement(sql);
	return st;
}

public static void addBatchNearDuplicateUpdate(PreparedStatement st, int reviewId, int duplicateId) throws SQLException{
	st.setInt(1, duplicateId);
	st.setInt(2, reviewId);
	st.addBatch();
}


}