package tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import threads.NearDupesMemThread;
import tools.LettersCount;
import tools.Tools;
import database.DB;
import database.UpdateReviews;

/**
 * Finds near duplicates in the database.
 * @author charles
 *
 */
public class FindNearDuplicatesTask implements Runnable {

	private static final int MAXLC = 100000;
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
			
			Connection stream = DB.getConnection();
			ResultSet reviewStream = DB.getStreamingResultSet(sql, stream);
			ArrayList<LettersCount> buffer= new ArrayList<LettersCount>();
			
			while(fillBuffer(MAXLC,reviewStream,buffer)){
				//MAXLC in vectors
				System.out.println("---LookingForDupes---");
				findNearDuplicates(win,buffer);
				
				System.out.println("---CopyingBuffer---");
				buffer = new ArrayList<LettersCount>(buffer.subList(buffer.size()-win, buffer.size()-1));	
			}
			
			findNearDuplicates(0,buffer);

		} catch(Exception e){
			System.out.println("EXCEPTION find near duplicates task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean fillBuffer(int max,ResultSet reviewStream, ArrayList<LettersCount> buffer) throws SQLException{
		System.out.println("---Filling Buffer---");
		
		while(reviewStream.next()){
			
			buffer.add(new LettersCount(
						lexicon,
						reviewStream.getInt(1),
						nGramSize,
						Tools.normalize(reviewStream.getString(2))
					));
			
			if(buffer.size()>=max)
				return true;
		}
		
		return false;
	}
	private void findNearDuplicates(int stop,ArrayList<LettersCount> buffer) throws ClassNotFoundException, SQLException, InterruptedException {
		System.out.println("---LookingForDupes---");
		NearDupesMemThread th1;
		NearDupesMemThread th2;
		NearDupesMemThread th3;
		NearDupesMemThread th4;	
		Connection conn = DB.getConnection();
		UpdateReviews up = new UpdateReviews(conn);
		
		
		for(int i=0;i<buffer.size()-stop;i=i+4){
			
			th1 = new NearDupesMemThread(buffer, i, up, win, sim);
			th1.start();
			th2 = new NearDupesMemThread(buffer, i+1, up, win, sim);
			th2.start();
			th3 = new NearDupesMemThread(buffer, i+2, up, win, sim);
			th3.start();
			th4 = new NearDupesMemThread(buffer, i+3, up, win, sim);
			th4.start();
			
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			
			if(i%1000==0)
				System.out.println("Near:"+ i);
		}
		up.flushBatch();
		conn.close();
		
	}



}
