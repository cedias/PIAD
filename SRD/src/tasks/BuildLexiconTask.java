package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import tools.Tools;
import database.DB;

/**
 * Create the ngram-lexicon
 * @author charles
 *
 */
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
				int cpt=0;
				Connection stream = DB.getConnection();
				ResultSet reviews = DB.getStreamingResultSet(sql, stream);
				
				while(reviews.next()){
					String normText = Tools.normalize(reviews.getString(1));
					Tools.toHashShingles(normText, nGramSize, lexicon);
					cpt++;
					if(cpt%10000==0)
						System.out.println("Lexicon:"+cpt);
				}
				
				stream.close();
				
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
