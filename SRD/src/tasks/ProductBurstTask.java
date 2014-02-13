package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import database.DB;

public class ProductBurstTask implements Runnable {

	final String sql = "SELECT r.product_id, r.time, COUNT( * ) AS nb_reviews "+
						"FROM reviews r " +
						"GROUP BY r.product_id, r.time " +
						"HAVING nb_reviews >= ? " +
						"ORDER BY nb_reviews DESC";

	final int max;
	final int window;

	public ProductBurstTask(int max, int window) {
		super();
		this.max = max;
		this.window = window;
	}

	@Override
	public void run() {
		Connection conn;
		try {
			conn = DB.getConnection();
			PreparedStatement st = DB.getStreamingStatement(sql, conn);
			st.setInt(1, max);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				String pid = rs.getString(1);
				Date date = rs.getDate(2);
				int nbReview = rs.getInt(3);
				System.out.println(pid.toString().concat(" ").concat(date.toString()).concat(" ").concat(""+nbReview));


			}


		}catch(Exception e){
			System.out.println("Erreur Product Burst Task");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

}
