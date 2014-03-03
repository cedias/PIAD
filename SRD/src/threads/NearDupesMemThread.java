package threads;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import database.DB;
import database.UpdateReviews;

import tools.LettersCount;
import tools.Tools;


public class NearDupesMemThread extends Thread {

	ArrayList<LettersCount> reviews;
	final int offset;
	UpdateReviews up;
	int window;
	double simil;
	private final String sql = "SELECT review_id, text FROM `reviews` WHERE exact_dup_id IS NULL ORDER BY cos_simil_ident DESC LIMIT ?,?;";
	private int nGram;
	private HashMap<String, Integer> lexicon;



	public NearDupesMemThread(HashMap<String, Integer> lexicon,final int offset, UpdateReviews up, int window,double simil) {
		this.lexicon = lexicon;
		this.offset = offset;
		this.up = up;
		this.window = window;
		this.simil = simil;
	}

	public void run(){
		 try{
			 Connection conn = DB.getConnection();
			 PreparedStatement st = DB.getStreamingStatement(sql, conn);
			 st.setInt(1, offset);
			 st.setInt(2, window);
			 ResultSet stream = st.executeQuery();
			 stream.next();
			 int origId = stream.getInt(1);
			 LettersCount origLC = new LettersCount(lexicon,nGram, origId,Tools.normalize(stream.getString(2)));
			 
			 
			 while(stream.next()){
				 int dupeId = stream.getInt(1);
				 LettersCount dupeLC = new LettersCount(lexicon,nGram, origId,Tools.normalize(stream.getString(2)));
				 
				 if(origLC.cosSimil(dupeLC) >= simil)
				 {
					System.out.println("dupe: "+origId+"-"+dupeId);
				 }
			 }
			 
			 

			 
			 
			 conn.close();
		 }catch(Exception e){
			System.out.println("[THREAD] ERROR:"+e.getMessage());
		 }
	 }


}
