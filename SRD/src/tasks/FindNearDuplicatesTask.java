package tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import threads.NearDupesMemThread;
import tools.LettersCount;
import tools.Tools;
import database.DB;
import database.UpdateReviews;

public class FindNearDuplicatesTask implements Runnable {
	
	private final int win;
	private final double sim;
	private final int nGramSize;
	private final String filename;
	

	public FindNearDuplicatesTask(int win, double sim, int nGramSize, String filename) {
		super();
		this.win = win;
		this.sim = sim;
		this.nGramSize = nGramSize;
		this.filename = filename;
	}

	@Override
	public void run() {
		try{
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();
		
		Connection conn = DB.getConnection();
		UpdateReviews up = new UpdateReviews(conn);
		
		LettersCount lc;
		
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
				
			/*sorting*/
			Collections.sort(reviews);
			
			System.out.println("loading done - searching");

			NearDupesMemThread th1;
			NearDupesMemThread th2;
			NearDupesMemThread th3;
			NearDupesMemThread th4;
			
			
			/*Searching for dupes*/
			for(int i=0;i<reviews.size();i++)
			{
				th1 = new NearDupesMemThread(reviews, i,up, win, sim);
				th1.start();
				th2 = new NearDupesMemThread(reviews, i+1,up,win, sim);
				th2.start();
				th3 = new NearDupesMemThread(reviews, i+2,up, win, sim);
				th3.start();
				th4 = new NearDupesMemThread(reviews, i+3,up, win, sim);
				th4.start();

				th1.join();
				th2.join();
				th3.join();
				th4.join();
				if(i%1000==0)
					System.out.println(i);
			}
			
			up.flushBatch();
			conn.close();
			
		} catch(Exception e){
			System.out.println("EXCEPTION find near duplicates task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	

}
