package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import tools.LettersCount;
import tools.Tools;


public class NearDupesMemThread extends Thread {

	HashMap<String,Integer> lexicon;
	HashMap<Integer, LettersCount> list;
	LettersCount lc;
	int start;



	public NearDupesMemThread(HashMap<String, Integer> lexique,
			HashMap<Integer, LettersCount> reviews, int i) {
		this.lexicon = lexique;
		this.lc = reviews.get(i);
		this.list = reviews;
		this.start = i+1;
	}




	public void run(){

		 try{
			 int count=0;
			int nearDupes=0;
			for(int i =start;i<list.size();i++){

				if(!list.containsKey(i))
					continue;

				 if(lc.cosSimil(list.get(i))>0.8)
					 nearDupes++;

				 count++;

				 if(count%10000==0)
					 System.out.println("[THREAD] "+count);
			 }


			    System.out.println("[THREAD]   has near dupes = "+nearDupes);




		 }catch(Exception e){
			System.out.println("[THREAD] ERROR:"+e.getMessage());

		  }
	 }


}
