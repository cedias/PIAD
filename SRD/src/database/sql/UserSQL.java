package database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User SQL PreparedStatement
 * @author charles
 *
 */
public class UserSQL {

	/**
	 * get insert user PreparedStatement
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getInsertReviewStatement(
			Connection c) throws SQLException {
		/*
		 *1.User id
		 *2.User username
		 *5.User trustiness score
		 *
		 *NB: INSERT IGNORE Statement: if key already exists, doesn't insert anything.
		 */
		String sql ="INSERT IGNORE INTO users" +
				" (`user_id`, `username`)" +
				" VALUES (?, ?);";


			PreparedStatement st = c.prepareStatement(sql);
			return st;
		
	}

	/**
	 * configure and add to batch insert user PreparedStatement
	 * @param stUsers
	 * @param user_id
	 * @param username
	 * @throws SQLException
	 */
	public static void insertUserBatch(PreparedStatement stUsers,
			String user_id, String username) throws SQLException {
		
			stUsers.setString(1, user_id);
			stUsers.setString(2, username);
			
			stUsers.addBatch();
		
	}
	

}
