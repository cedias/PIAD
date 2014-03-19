package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Static Methods to Insert or Update Reviews
 * @author Charles-Emmanuel Dias
 */
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
		 */
		String sql ="INSERT INTO `amazon`.`reviews` "
				+"(`review_id`, `user_id`, `product_id`, `score`, `time`, `helpfullness`, `nb_helpfullness`, `summary`, `text`)"
				+"VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
	}

	public static PreparedStatement getUpdateNearDuplicateStatement(Connection c) throws SQLException{
		/*
		 * 1: near_duplicate_id
		 * 2: review_id
		 */
		String sql = "UPDATE  `amazon`.`reviews` SET  `near_dup_id` = ? WHERE  `reviews`.`review_id` =?;";
		PreparedStatement st = c.prepareStatement(sql);
		return st;
	}



	public static void insertReviewBatch(PreparedStatement st, String uid, String pid, float score, String time, int help
				,int nbHelp, String summary, String text) throws ClassNotFoundException, SQLException{

			st.setString(1, uid);
			st.setString(2, pid);
			st.setFloat(3, score);
			st.setTimestamp(4,new Timestamp(Long.parseLong(time)*1000));
			st.setInt(5,help);
			st.setInt(6,nbHelp);
			st.setString(7,summary);
			st.setString(8,text);

			st.addBatch();
	}


	public static void addBatchNearDuplicateUpdate(PreparedStatement st, int reviewId, int duplicateId) throws SQLException{
		st.setInt(1, duplicateId);
		st.setInt(2, reviewId);
		st.addBatch();
	}


}