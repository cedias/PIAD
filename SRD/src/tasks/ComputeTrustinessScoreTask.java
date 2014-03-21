package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;

public class ComputeTrustinessScoreTask implements Runnable {

	private final String sql = 	"SELECT r.user_id, SUM( r.honesty_score ) " 
								+ "FROM  `reviews` r "
								+ "GROUP BY r.user_id";
	
	@Override
	public void run() {
		Connection stream;
		int user_id;
		double sum_honesty;
		
		try {
			stream = DB.getConnection();
			ResultSet rs = DB.getStreamingResultSet(sql, stream);
			
			while(rs.next()){
				user_id = rs.getInt(1);
				sum_honesty = rs.getInt(2);
				
				goyoutwat
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
