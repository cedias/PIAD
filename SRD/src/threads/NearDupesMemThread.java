package threads;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import tools.LettersCount;


public class NearDupesMemThread extends Thread {

	ArrayList<LettersCount> reviews;
	final int index;



	public NearDupesMemThread(ArrayList<LettersCount> reviews,final int index) {
		this.reviews = reviews;
		this.index = index;
	}




	public void run(){
		int count=0;
		 try{
			 LettersCount lc = reviews.get(index); //initial;
			 for(int i=index+1;i<reviews.size();i++){
				 if(lc.cosSimil(reviews.get(i))>= 0.9){
					 count++;
					 System.out.println("----");
					 System.out.println(lc.getId());
					 System.out.println(reviews.get(i).getId());
				 }


			 }
			 System.out.println("[THREAD"+index+"] finished, dupes= "+count);

		 }catch(Exception e){
			System.out.println("[THREAD] ERROR:"+e.getMessage());

		  }
	 }


}
