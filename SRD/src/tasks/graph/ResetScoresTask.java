package tasks.graph;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DB;

/**
 * Task that resets all scores.
 * @author charles
 *
 */
public class ResetScoresTask implements Runnable {

	@Override
	public void run() {
		String sqlProduct ="UPDATE products SET reliability_score =1";
		String sqlUser ="UPDATE users SET trust_score =1";
		String sqlReview ="UPDATE reviews SET HONESTY_score =1";
		try {
		Connection c = DB.getConnection();
		
		Statement st = c.createStatement();
			st.executeUpdate(sqlReview);
			st.executeUpdate(sqlUser);
			st.executeUpdate(sqlProduct);
			st.close();
			System.out.println("Score resetted");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error in Reset Score Task");
			e.printStackTrace();
		}
		

	}
}
