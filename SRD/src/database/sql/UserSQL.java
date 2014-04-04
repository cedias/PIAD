package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSQL {

	public static PreparedStatement getInsertReviewStatement(
			Connection c) throws SQLException {
		/*
		 *1.User id
		 *2.User username
		 *3.User duplicate reviews count
		 *4.User burst count
		 *5.User trustiness score
		 *
		 *NB: INSERT IGNORE Statement: if key already exists, doesn't insert anything.
		 */
		String sql ="INSERT IGNORE INTO users" +
				" (`user_id`, `username`, `nb_duplicates`, `nb_bursts`)" +
				" VALUES (?, ?, ?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
		
	}

	public static void insertUserBatch(PreparedStatement stUsers,
			String user_id, String username) throws SQLException {
		
			stUsers.setString(1, user_id);
			stUsers.setString(2, username);
			stUsers.setInt(3, 0);
			stUsers.setInt(4, 0);
			
			stUsers.addBatch();
		
	}
	

}
