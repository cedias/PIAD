package tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import threads.NearDupesMemThread;
import tools.DBBuffer;
import tools.LettersCount;
import tools.Tools;
import database.DB;
import database.UpdateReviews;

public class FindNearDuplicatesTask implements Runnable {

	private static final int MAXLC = 50000;
	private final int win;
	private final double sim;
	private final int nGramSize;
	HashMap<String,Integer> lexicon;


	public FindNearDuplicatesTask(int win, double sim, int nGramSize, HashMap<String,Integer> lexicon) {
		super();
		this.win = win;
		this.sim = sim;
		this.nGramSize = nGramSize;
		this.lexicon = lexicon;
	}

	@Override
	public void run() {
		try{
			System.out.println("-----Start ND--------");
			final String sql = "SELECT review_id, text FROM `reviews` WHERE exact_dup_id IS NULL ORDER BY cos_simil_ident DESC";
			
			NearDupesMemThread th1;
			NearDupesMemThread th2;
			NearDupesMemThread th3;
			NearDupesMemThread th4;
			Connection conn = DB.getConnection();
			UpdateReviews up = new UpdateReviews(conn);
			
			Connection stream = DB.getConnection();
			ResultSet reviewStream = DB.getStreamingResultSet(sql, stream);
			ArrayList<LettersCount> vectors= new ArrayList<LettersCount>();
			LettersCount lc;
			
			int currentLC = 0;
			
			
			while(currentLC<100000){
				
				System.out.println("---FetchingBuffer---");
				while(reviewStream.next() && vectors.size()<MAXLC){
					int reviewId = reviewStream.getInt(1);
					String normText = Tools.normalize(reviewStream.getString(2));
					lc = new LettersCount(lexicon, reviewId, nGramSize, normText);
					vectors.add(lc);
					if(vectors.size()%10000==0)
					System.out.println(vectors.size());
				}
				//MAXLC in vectors
				System.out.println("---LookingForDupes---");
				for(int i=0;i<MAXLC-win;i=i+4){
					th1 = new NearDupesMemThread(vectors, i, up, win, sim);
					th1.start();
					th2 = new NearDupesMemThread(vectors, i+1, up, win, sim);
					th2.start();
					th3 = new NearDupesMemThread(vectors, i+2, up, win, sim);
					th3.start();
					th4 = new NearDupesMemThread(vectors, i+3, up, win, sim);
					th4.start();
					th1.join();
					th2.join();
					th3.join();
					th4.join();
					currentLC+=4;
					if(currentLC%100==0)
						System.out.println(currentLC);
				}
				System.out.println("---CopyingBuffer---");
				vectors = new ArrayList<LettersCount>(vectors.subList(MAXLC-win, MAXLC-1));
				
				
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
