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

/**
 * Find exact duplicates from the database
 * @author charles
 *
 */
public class FindExactDuplicatesTask implements Runnable {

	private final int nGramSize;
	private final String sql = "SELECT review_id , text FROM reviews ";
	Map<String, Integer> lexicon = null;
	Map<Integer,Integer> dupe = new HashMap<Integer,Integer>();

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
					int key = normText.hashCode();
					
					if(lexicon != null)
						Tools.toHashShingles(normText, nGramSize, lexicon);
					
					if(dupe.containsKey(key)){
						DuplicateSQL.insertExactDuplicateBatch(insertion, dupe.get(key), review_id);
					}
					else
					{
						dupe.put(key, review_id);
					}
					
					if(count%10000==0){
						System.out.println("Exact: "+count);
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
