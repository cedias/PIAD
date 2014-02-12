package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DB;

public class FindOutlierTask implements Runnable {

	final String sql = 	"SELECT r.product_id, count(*) as nb_reviews, AVG(r.score) as moyenne, STD_DEV(r.score) as ecart"+
						"FROM reviews r"+
						"GROUP BY r.product_id"+
						"HAVING nb_reviews > ?"+
						"ORDER BY nb_reviews DESC";
	
	final int sigma = 3;
	final int minReviews;	
	
	public FindOutlierTask(int minReviews) {
		super();
		this.minReviews = minReviews;
	}

	@Override
	public void run() {
		Connection conn;
		try {
			conn = DB.getConnection();
			PreparedStatement st = DB.getStreamingStatement(sql, conn);
			st.setInt(1, 10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				System.out.println("hello");
			}
			
			
		}catch(Exception e){
			System.out.println("Erreur Score Outlier Task");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
	}

}
