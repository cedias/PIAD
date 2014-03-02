package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tools.Tools;

import database.DB;
import database.LexiconSQL;

public class BuildLexiconTask implements Runnable {
	
	private final int nGramSize;
	private final String sql = "SELECT  `text` FROM  `reviews` ";

	public BuildLexiconTask(int nGramSize){
		this.nGramSize = nGramSize;
	}
	
	@Override
	public void run() {
		
			try {
				
				int count = 0;
				int nbRev = 0;
				Map<String, Integer> lexicon = new HashMap<String,Integer>();
				Connection stream = DB.getConnection();
				Connection conn = DB.getConnection();
				conn.setAutoCommit(false);
				ResultSet reviews = DB.getStreamingResultSet(sql, stream);
				PreparedStatement st = LexiconSQL.getInsertLexiconGramStatement(conn);
				
				while(reviews.next()){
					String normText = Tools.normalize(reviews.getString(1));
					Tools.toHashShingles(normText, nGramSize, lexicon);
					
					
					if(count%1000==0)
						System.out.println(count);
						
					count++;
				}
				count = 0;
				System.out.println("---end dic, saving---");
				for(String key : lexicon.keySet()){
					if(key.hashCode()==1598)
						System.out.println(key);
						
					System.out.println(lexicon.get("0n"));
					System.out.println(lexicon.get("20"));
					LexiconSQL.insertGramBatch(st, key.hashCode() ,lexicon.get(key));
					count++;
					if(count%1000==0){
						st.executeBatch();
						System.out.println(count);
					}
				}
				
				
				stream.close();
				conn.commit();
				st.close();
				conn.close();
				
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
