package tasks.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import scoring.ProductScoring;
import database.DB;
import database.sql.ReliabilitySQL;

public class ComputeReliabilityScoreTask implements Runnable {

	String sql = 	"SELECT p.product_id, SUM((u.trust_score) * ( r.score -3 )) AS theta "+
					"FROM reviews r, users u, products p "+
					"WHERE r.user_id = u.user_id "+
					"AND r.product_id = p.product_id "+
					"AND u.trust_score >0 "+
					"GROUP BY product_id "+
					"ORDER BY product_id; ";
			
		
	@Override
	public void run() {
		System.out.println("Computing Reliability");
		Connection stream;
		Connection upstream;
		PreparedStatement st;
		
		String product_id;
		double theta;
		double reliability_score;
		int count = 0;
		
		try {
			stream = DB.getConnection();
			upstream = DB.getConnection();
			upstream.setAutoCommit(false);
			ResultSet rs = DB.getStreamingResultSet(sql, stream);
			st = ReliabilitySQL.getUpdateReliabilityStatement(upstream);
			
			while(rs.next()){
				product_id = rs.getString(1);
				theta = rs.getDouble(2);
				reliability_score = ProductScoring.computeReliability(theta); 
				ReliabilitySQL.insertBatchUpdateReliability(st, reliability_score, product_id);
				
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
			System.out.println("Error Compute Reliability: "+e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
