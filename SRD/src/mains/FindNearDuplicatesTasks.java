package mains;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.DB;
import database.UpdateReviews;
import threads.NearDupesMemTask;
import threads.NearDupesMemThread;
import tools.LettersCount;
import tools.Tools;


public class FindNearDuplicatesTasks {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		/*Parameters*/
		int win = 1000;
		double sim = 0.90;
		int nGramSize = 1;
		String filename = "output/OPStripped_WED.txt";
		
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();
		
		Connection conn = DB.getConnection();
		UpdateReviews up = new UpdateReviews(conn);
		
		LettersCount lc;
		
		long start = System.currentTimeMillis();
		
		/*creates lexicon*/
		Tools.populateLexicon(filename, lexique, nGramSize);

		/*create vectors*/
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while((line=br.readLine())!=null){

				String[] data = line.split(":->:");
				final int id = Integer.parseInt(data[0]);
				
				line = Tools.normalize(data[1]);
				lc = new LettersCount(lexique,id, nGramSize,line);
				reviews.add(lc);
				lc=null;
			}

			br.close();
			
				System.out.println("gogo");
				
			/*sorting*/
			Collections.sort(reviews);

			
			
			
			ExecutorService es = Executors.newFixedThreadPool(4);
			
			for(int i=0;i<reviews.size()-1;i++)
				es.execute(new NearDupesMemTask(reviews, i+1,up,win, sim));
			
			up.flushBatch();
			conn.close();
			
			long end = System.currentTimeMillis();
			System.out.println("end: "+(end-start)/1000);
			
	}

}
