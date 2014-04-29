package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import tools.LettersCount;
import tools.Tools;
import database.DB;
import database.sql.CosSimSQL;

/**
 * Populate the cos_simil field in the database, needed for near duplicates
 * @author charles
 *
 */
public class CosSimilBuild implements Runnable {

	private final String sql = "SELECT  `review_id`,`text` FROM  `reviews` ";
	private final HashMap<String,Integer> lexicon;
	private final int nGramSize;

	public CosSimilBuild(int nGramSize,HashMap<String,Integer> lexicon ){
		this.lexicon = lexicon;
		this.nGramSize = nGramSize;
	}
	
	@Override
	public void run() {
		
			try {
				Connection conn = DB.getConnection();
				conn.setAutoCommit(false);
				Connection stream = DB.getConnection();
				ResultSet reviewStream = DB.getStreamingResultSet(sql, stream);
				PreparedStatement st = CosSimSQL.getInsertCosSimilStatement(conn);
				
				
				while(reviewStream.next()){
					int reviewId = reviewStream.getInt(1);
					String normText = Tools.normalize(reviewStream.getString(2));
					
					double cosSimil = new LettersCount(lexicon, reviewId,nGramSize ,normText).cosSimilIdent();
					CosSimSQL.insertBatchCosSimil(st, reviewId, cosSimil);
				}
				
				st.executeBatch();
				conn.commit();
				conn.close();
				stream.close();
				
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
