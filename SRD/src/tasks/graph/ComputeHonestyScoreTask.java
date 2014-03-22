package tasks.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import scoring.ReviewScoring;
import tools.Agreement;
import tools.AgreementComputer;

import database.DB;
import database.sql.HonestySQL;

public class ComputeHonestyScoreTask implements Runnable {

	int windowSize;
	double diffScore;
	final String sql = "SELECT r.review_id, r.product_id, r.score, u.trust_score, p.reliability_score FROM reviews r, users u, products p where r.user_id = u.user_id AND r.product_id = p.product_id order by r.product_id;";
	AgreementComputer agComp = null;
	
	public ComputeHonestyScoreTask(int windowSize, double diffScore) {
		super();
		this.windowSize = windowSize;
		this.diffScore = diffScore;
	}

	public void run() {
		
		int count=0;
		int review_id;
		String product_id;
		float score;
		double trustiness;
		double reliability;
		Connection stream;
		Connection upstream;
		PreparedStatement upstreamStatement;
		ArrayList<Agreement> reviews;
		
		try {
			
			stream = DB.getConnection();
			upstream = DB.getConnection();
			upstream.setAutoCommit(false);
			upstreamStatement = HonestySQL.getUpdateHonestyStatement(upstream);
			
			
			
			ResultSet rs= DB.getStreamingResultSet(sql, stream);

			/*
			 *1:review_id : int
			 *2:product_id : String
			 *3:review_score :float 
			 *4:user_trust_score : double 
			 *5:product_reliability_score : double
			 */
			
			//init
			rs.next();
			review_id = rs.getInt(1);
			product_id = rs.getString(2);
			score = rs.getFloat(3);
			trustiness = rs.getDouble(4);
			reliability = rs.getDouble(5);
			
			agComp = new AgreementComputer(product_id, windowSize, diffScore);
			agComp.addAgreement(new Agreement(review_id, score, trustiness));
			
			while(rs.next()){
				
				review_id = rs.getInt(1);
				product_id = rs.getString(2);
				score = rs.getFloat(3);
				trustiness = rs.getDouble(4);
				reliability = rs.getDouble(5);
				
				if(!product_id.equals(agComp.getProductId()))
				{
					reviews = agComp.compute();
					count = upload(count, upstreamStatement, reviews, reliability);
					agComp = new AgreementComputer(product_id, windowSize, diffScore);
				}
				
				agComp.addAgreement(new Agreement(review_id, score, trustiness));
					
			
			}
			
			reviews = agComp.compute();
			upload(count, upstreamStatement, reviews, reliability);
			upstreamStatement.executeBatch();
			upstream.commit();
			upstreamStatement.close();
			upstream.close();
			rs.close();
			stream.close();
			
		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error Compute Agreement Score Task: " +e.getMessage());
			e.printStackTrace();
		}	
	}

	private int upload(int count, PreparedStatement upstreamStatement, ArrayList<Agreement> reviews,double reliability) throws SQLException {
		
		for(Agreement ag: reviews){
			double score = ReviewScoring.computeReviewHonesty(ag.getAgreement(),reliability);
			HonestySQL.insertBatchUpdateHonesty(upstreamStatement, score, ag.getReviewId());
			count++;
			
			if(count>=1000){
				upstreamStatement.executeBatch();
				count=0;
			}
		}
		return count;
	}
	

}
