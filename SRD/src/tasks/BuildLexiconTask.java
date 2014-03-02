package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.Tools;

import database.DB;

public class BuildLexiconTask implements Runnable {
	
	private final String sql = "SELECT  `text` FROM  `reviews` ";

	@Override
	public void run() {
		
			try {
				Connection conn = DB.getConnection();
		
				ResultSet reviews = DB.getStreamingResultSet(sql, conn);
				
				while(reviews.next()){
					String normText = Tools.normalize(reviews.getString(1));
					
					
				}
				
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
