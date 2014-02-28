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
	HashMap<String,Integer> lexique;
	ArrayList<LettersCount> reviews;


	public FindNearDuplicatesTask(int win, double sim, int nGramSize, String filename) {
		super();
		this.win = win;
		this.sim = sim;
		this.nGramSize = nGramSize;
		this.filename = filename;
	}

	public FindNearDuplicatesTask(int win, double sim, int nGramSize, HashMap<String,Integer> lexique,ArrayList<LettersCount> reviews ) {
		super();
		this.win = win;
		this.sim = sim;
		this.nGramSize = nGramSize;
		this.lexique = lexique;
		this.reviews = reviews;
		this.filename = "";
	}

	@Override
	public void run() {
		try{

			/*creates lexicon*/
			if(lexique == null){
				this.lexique = new HashMap<String,Integer>();
				Tools.populateLexicon(filename, lexique, nGramSize);

			}

			/*create vectors*/
			if(reviews == null){

				this.reviews = new ArrayList<LettersCount>();
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String line;
				LettersCount lc;
				while((line=br.readLine())!=null){

					String[] data = line.split(":->:");
					final int id = Integer.parseInt(data[0]);

					line = Tools.normalize(data[1]);
					lc = new LettersCount(lexique,id, nGramSize,line);
					reviews.add(lc);
					lc=null;
				}

				br.close();
			}
			System.out.println("lex size:"+lexique.size());
			System.out.println("vect size:"+reviews.size());
			/*Starts here with constructor N°2*/

				/*sorting*/
				Collections.sort(reviews);

				System.out.println("loading done - searching");

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
