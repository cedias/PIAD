package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DB;

public class ComputeAgreementScoreTask implements Runnable {

	int windowSize;
	double diffScore;
	final String sql = "SELECT r.review_id, r.product_id, r.score, u.trust_score FROM reviews r, users u where r.user_id = u.user_id order by r.product_id";
	ArrayList<Integer> reviews_id = new ArrayList<Integer>();
	ArrayList<String> products_id = new ArrayList<String>();
	ArrayList<Float> scores = new ArrayList<Float>();
	ArrayList<Double> trustinesses = new ArrayList<Double>();
	int nbReviews = 0;
	
	
	public ComputeAgreementScoreTask(int windowSize, double diffScore) {
		super();
		this.windowSize = windowSize;
		this.diffScore = diffScore;
	}

	public void run() {
		
		
		int review_id;
		String product_id;
		float score;
		double trustiness;
		
		
		
		Connection c;
		try {
			c = DB.getConnection();
			ResultSet rs= DB.getStreamingResultSet(sql, c);

			/*
			 *1:review_id : int
			 *2:product_id : String
			 *3:review_score :float 
			 *4:user_trust_score : double 
			 */
			
			while(rs.next()){
				
				review_id = rs.getInt(1);
				product_id = rs.getString(2);
				score = rs.getFloat(3);
				trustiness = rs.getDouble(4);
				compute(review_id,product_id,score,trustiness);
				
			}
			
		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error Compute Agreement Score Task: " +e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
	private void compute(int rId,String pId, float score, double trust){
			if(rId != cu
		
	}

}
