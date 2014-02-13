package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DB;

public class FindOutlierTask implements Runnable {

	// NB: Index on product_id needed.
	final String sql2 =	"SELECT r.review_id,g.nb_reviews,g.pid, r.score, g.moyenne,g.ecart FROM "+
						"(SELECT r.product_id as pid, count(*) as nb_reviews, AVG(r.score) as moyenne, STDDEV(r.score) as ecart "+
						"FROM reviews r "+
						"GROUP BY r.product_id "+
						"HAVING nb_reviews >= ? "+
						"ORDER BY nb_reviews DESC "+
						") g , reviews r "+
						"WHERE r.product_id = g.pid "+
						"AND r.score-g.moyenne > ?*g.ecart;";

	final int sigma;
	final int minReviews;

	public FindOutlierTask(int minReviews, int sigma) {
		super();
		this.minReviews = minReviews;
		this.sigma = sigma;
	}

	@Override
	public void run() {
		Connection conn;
		String s = " ";
		try {
			conn = DB.getConnection();
			PreparedStatement st = DB.getStreamingStatement(sql2, conn);
			st.setInt(1, minReviews);
			st.setInt(2, sigma);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				//r.review_id,g.nb_reviews,g.pid, r.score, g.moyenne,g.ecart
				int reviewId = rs.getInt(1);
				int nbReview = rs.getInt(2);
				String pid = rs.getString(3);
				double score = rs.getDouble(4);
				double moyScore = rs.getDouble(5);
				double ecartT = rs.getDouble(6);
				System.out.println(reviewId+s+nbReview+s+pid+s+score+s+moyScore+s+ecartT);
			}


		}catch(Exception e){
			System.out.println("Erreur Score Outlier Task");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

}
