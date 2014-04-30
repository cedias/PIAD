package tasks.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import scoring.UserScoring;


import database.DB;
import database.sql.TrustinessSQL;

public class ComputeTrustinessScoreTask implements Runnable {

	private final String sql = 	"SELECT r.user_id, SUM( r.honesty_score ) " 
								+ "FROM  `reviews` r "
								+ "GROUP BY r.user_id";
	
	@Override
	public void run() {
		System.out.println("Computing Trustiness");
		Connection stream;
		Connection upstream;
		PreparedStatement st;
		
		String user_id;
		double sum_honesty;
		double trust_score;
		int count = 0;
		
		try {
			stream = DB.getConnection();
			upstream = DB.getConnection();
			upstream.setAutoCommit(false);
			ResultSet rs = DB.getStreamingResultSet(sql, stream);
			st = TrustinessSQL.getUpdateTrustinessStatement(upstream);
			
			while(rs.next()){
				user_id = rs.getString(1);
				sum_honesty = rs.getDouble(2);
				trust_score = UserScoring.computeTrustiness(sum_honesty); 
				TrustinessSQL.insertBatchUpdateTrustiness(st, trust_score, user_id);
				
				if(count%1000==0){
					st.executeBatch();
				}
			}
			st.executeBatch();
			upstream.commit();
			st.close();
			upstream.close();
			rs.close();
			stream.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error Compute Trustiness: "+e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
