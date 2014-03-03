package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import tools.Tools;
import database.DB;


public class BuildLexiconTask implements Runnable {
	
	private final int nGramSize;
	private final String sql = "SELECT  `text` FROM  `reviews` ";
	Map<String, Integer> lexicon;

	public BuildLexiconTask(int nGramSize,Map<String, Integer> lexicon){
		this.nGramSize = nGramSize;
		this.lexicon = lexicon;
	}
	
	@Override
	public void run() {
		
			try {
				
				int count = 0;
				Connection stream = DB.getConnection();
				ResultSet reviews = DB.getStreamingResultSet(sql, stream);
				
				while(reviews.next()){
					String normText = Tools.normalize(reviews.getString(1));
					Tools.toHashShingles(normText, nGramSize, lexicon);
					
					if(count%1000==0)
						System.out.println(count);
						
					count++;
				}
				
				stream.close();
				
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
