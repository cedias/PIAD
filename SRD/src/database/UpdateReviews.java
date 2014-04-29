package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.sql.ReviewSQL;

/**
 * Concurrent update reviews
 * @author charles
 *
 */
public class UpdateReviews {

	int count = 0;
	Connection conn;
	PreparedStatement st;

	public UpdateReviews(Connection conn) throws SQLException {
		this.conn = conn;
		st = ReviewSQL.getUpdateNearDuplicateStatement(conn);
	}


	synchronized public void addNearDuplicate(int reviewId, int duplicateId) throws SQLException{

		ReviewSQL.addBatchNearDuplicateUpdate(st, reviewId, duplicateId);
		count++;
		if(count>=1000){
			st.executeBatch();
			count=0;
		}
	}

	synchronized public void flushBatch() throws SQLException{
		st.executeBatch();
		st.close();
	}

}
