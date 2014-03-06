package threads;


import java.util.ArrayList;
import database.UpdateReviews;
import tools.LettersCount;


public class NearDupesMemThread extends Thread {

		ArrayList<LettersCount> reviews;
		final int index;
		UpdateReviews up;
		int window;
		double simil;



		public NearDupesMemThread(ArrayList<LettersCount> reviews,final int index, UpdateReviews up, int window,double simil) {
			this.reviews = reviews;
			this.index = index;
			this.up = up;
			this.window = window;
			this.simil = simil;
		}

		public void run(){
			 try{
				 if(index>=reviews.size())
					 return;


				 LettersCount lc2;
				 LettersCount lc = reviews.get(index);

				 int max = (index+window<reviews.size())? index+window : reviews.size();

				 for(int i=index+1;i<max;i++){
					lc2 = reviews.get(i);

					if(lc.cosSimil(reviews.get(i)) >= simil)
						 up.addNearDuplicate(lc.getId(), lc2.getId());
				 }

			 }catch(Exception e){
				System.out.println("[THREAD] ERROR:"+e.getMessage());
			 }
		 }



}
