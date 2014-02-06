package threads;


import java.util.HashMap;
import tools.LettersCount;


public class NearDupesMemThread extends Thread {

	HashMap<Integer, LettersCount> vectors;
	LettersCount lc;
	int id;



	public NearDupesMemThread(HashMap<Integer, LettersCount> reviews, int id) {
		this.lc = reviews.get(id);
		this.vectors = reviews;
		this.id = id;
	}




	public void run(){

		 try{
			
			 for(LettersCount lc2 :vectors.values()){
				 if(lc.cosSimil(lc2)>0.9){
					 System.out.println("DUPE");
				 }
				 
			 }
			 System.out.println("[THREAD"+id+"] finished"); 
			 
		 }catch(Exception e){
			System.out.println("[THREAD] ERROR:"+e.getMessage());

		  }
	 }


}
