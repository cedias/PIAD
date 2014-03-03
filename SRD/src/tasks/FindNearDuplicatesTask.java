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
			final String sql = "SELECT review_id, text FROM `reviews` WHERE exact_dup_id IS NULL ORDER BY cos_simil_ident DESC";
			
			NearDupesMemThread th1;
			Connection conn = DB.getConnection();
			UpdateReviews up = new UpdateReviews(conn);
			
			Connection stream = DB.getConnection();
			ResultSet reviewStream = DB.getStreamingResultSet(sql, stream);
			ArrayList<LettersCount> vectors= new ArrayList<LettersCount>();
			LettersCount lc;
			DBBuffer<LettersCount> buffer = new DBBuffer<>(50000);
			int currentLC = 0;
			
			for(int i=0;i<10000;i++){
				th1 = new NearDupesMemThread(lexicon, i, up, win, sim);
				th1.run();
				System.out.println(i);
			}
			
			/*
			while(reviewStream.next()){
				int reviewId = reviewStream.getInt(1);
				String normText = Tools.normalize(reviewStream.getString(2));
				lc = new LettersCount(lexicon, reviewId, nGramSize, normText);
				vectors.add(lc);
				
				if(currentLC+win <= vectors.size()){
					th1 = new NearDupesMemThread(vectors, currentLC, up, win, sim);
					th1.run();
				}
				
				
			}
			*/
			
				
				conn.close();

		} catch(Exception e){
			System.out.println("EXCEPTION find near duplicates task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}



}
