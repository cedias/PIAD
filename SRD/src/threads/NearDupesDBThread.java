package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import tools.LettersCount;
import tools.Tools;


public class NearDupesDBThread extends Thread {

	Connection conn;
	HashMap<String,Integer> lexicon;
	int id;

	public NearDupesDBThread(Connection conn, int id, HashMap<String, Integer> lexicon){
		this.conn = conn;
		this.id = id;
		this.lexicon = lexicon;
	}

	 public void run(){

		 try{

			String sqlBase = "SELECT  `review_id` ,  `text` "+
							 "FROM  `reviews` "+
							 "WHERE  `exact_duplicate_id` IS NULL";

			String sql2 = sqlBase + " LIMIT "+(id-1)+" , 100000000";

			 String sql =	sqlBase + " LIMIT "+(id-1)+" , 1";

				Statement st =  conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				int idReview = rs.getInt(1);
				String text = rs.getString(2);
				st.close();
				rs.close();


			/*create lc1*/
			LettersCount lc = new LettersCount(lexicon);
			text = Tools.normalize(text);
			String[] data = text.split(" ");

			for(int i =0; i<data.length;i++){
				lc.add(data[i]);
			}


			/*search through all others*/

			LettersCount lc2 = null;
			double simil = 0;
			String[] splitted;
			int nearDupes=0;

			PreparedStatement stat = conn.prepareStatement(
			        sql2,
			        ResultSet.TYPE_FORWARD_ONLY,
			        ResultSet.CONCUR_READ_ONLY);
			    stat.setFetchSize(Integer.MIN_VALUE);

			     rs = stat.executeQuery();
			    while (rs.next()) {
			    	lc2 = new LettersCount(lexicon);
			    	int currId = rs.getInt(1);
					String currText = rs.getString(2);


					 if(currId == idReview)
						continue;


					currText = Tools.normalize(currText);
					splitted = currText.split(" ");

					for(int i =0; i<splitted.length;i++){
						lc2.add(splitted[i]);
					}

					simil = lc2.cosSimil(lc);



					if(simil>0.9){
						nearDupes++;
						System.out.println("------");
						System.out.println(idReview);
						System.out.println(text);
						System.out.println(currId);
						System.out.println(currText);
						System.out.println("------");
					}

					lc2 = null;


			    }
			    System.out.println("[THREAD "+id+"]  idReview = "+idReview+" has near dupes = "+nearDupes);


			    rs.close();
			    stat.close();

		 }catch(Exception e){
			 e.printStackTrace();
			System.out.println("[THREAD "+id+"] ERROR:"+e.getMessage());

		  }
	 }


}
