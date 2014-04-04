package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import tools.Tools;
import database.DB;
import database.sql.DuplicateSQL;

public class FindExactDuplicatesTask implements Runnable {

	private final int nGramSize;
	private final String sql = "SELECT review_id , text FROM reviews ";
	Map<String, Integer> lexicon = null;
	Map<String,Integer> dupe = new HashMap<String,Integer>();

	public FindExactDuplicatesTask(int nGramSize,Map<String, Integer> lexicon){
		this.nGramSize = nGramSize;
		this.lexicon = lexicon;
	}
	
	public FindExactDuplicatesTask(){
		this.nGramSize=0;
	}
	
	@Override
	public void run() {
		
			try {
				
				int count = 0;
				Connection stream = DB.getConnection();
				Connection conn = DB.getConnection();
				conn.setAutoCommit(false);
				ResultSet reviews = DB.getStreamingResultSet(sql, stream);
				PreparedStatement insertion = DuplicateSQL.getInsertExactDuplicateStatement(conn);
				
				
				while(reviews.next()){
					int review_id = reviews.getInt(1);
					String normText = Tools.normalize(reviews.getString(2));
					
					if(lexicon != null)
						Tools.toHashShingles(normText, nGramSize, lexicon);
					
					if(dupe.containsKey(normText)){
						DuplicateSQL.insertExactDuplicateBatch(insertion, dupe.get(normText), review_id);
					}
					else
					{
						dupe.put(normText, review_id);
					}
					
					if(count%1000==0){
						System.out.println(count);
						insertion.executeBatch();
					}
						
					count++;
				}
				insertion.executeBatch();
				conn.commit();
				insertion.close();
				conn.close();
				stream.close();
				
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
