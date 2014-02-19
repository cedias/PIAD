package tasks;

import java.util.ArrayList;
import tools.LettersCount;

public class FindNearDuplicatesHeuristicTask implements Runnable {
	
	private final double sim;
	private final int win;
	public volatile int nbDup;
	private ArrayList<LettersCount> reviews;
	
	

	public FindNearDuplicatesHeuristicTask(double sim,int win, ArrayList<LettersCount> reviews) {
		super();
		this.sim = sim;
		this.win = win;
		this.reviews = reviews;
	}

	@Override
	public void run() {
		try{
		
			LettersCount lc;
			LettersCount lc2;
			
			
			int count = 0;
			/*Searching for dupes*/
			for(int i=0;i<reviews.size();i++)
			{
				lc = reviews.get(i);
				int max = (i+win<reviews.size())? i+win : reviews.size();
				
				for(int j=i+1;j<max;j++)
				{
					lc2 = reviews.get(j);
					if(lc2.cosSimil(lc) >= sim && i!=j){
						//System.out.printf("[H] ids: (%d;%d) =>  list place: (%d;%d) => cosSimilIdent(%f;%f)\n",
							//	lc.getId(), lc2.getId(), i,j , lc.cosSimilIdent(), lc2.cosSimilIdent());
						count++;
					}
					
				}
			}
			
			System.out.println("["+win+"]Heuristic search: "+count+ " duplicates");
			this.nbDup = count;
			
		} catch(Exception e){
			System.out.println("EXCEPTION find near duplicates task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	

}
