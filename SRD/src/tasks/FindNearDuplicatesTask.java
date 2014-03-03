package tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
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


			Connection stream = DB.getConnection();
			ResultSet reviewStream = DB.getStreamingResultSet(sql, stream);

				NearDupesMemThread th1;
				NearDupesMemThread th2;
				NearDupesMemThread th3;
				NearDupesMemThread th4;
				Connection conn = DB.getConnection();
				UpdateReviews up = new UpdateReviews(conn);


				/*Searching for dupes*/
				for(int i=0;i<reviews.size();i++)
				{
					th1 = new NearDupesMemThread(reviews, i,up, win, sim);
					th1.start();
					i++;
					th2 = new NearDupesMemThread(reviews, i,up,win, sim);
					th2.start();
					i++;
					th3 = new NearDupesMemThread(reviews, i,up, win, sim);
					th3.start();
					i++;
					th4 = new NearDupesMemThread(reviews, i,up, win, sim);
					th4.start();

					th1.join();
					th2.join();
					th3.join();
					th4.join();

					if(i%1001==0)
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
