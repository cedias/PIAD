package tasks;

import java.util.ArrayList;
import tools.LettersCount;

public class FindNearDuplicatesExhaustiveTask implements Runnable {
	
	private final double sim;
	private ArrayList<LettersCount> reviews;
	

	public FindNearDuplicatesExhaustiveTask(double sim, ArrayList<LettersCount> reviews) {
		super();
		this.sim = sim;
		this.reviews = reviews;
	}

	@Override
	public void run() {
		try{
		int count = 0;
		
		LettersCount lc;
		LettersCount lc2;
		
			
			
			/*Searching for dupes*/
			for(int i=0;i<reviews.size();i++)
			{
				lc = reviews.get(i);
				for(int j=i+1;j<reviews.size();j++)
				{
					lc2 = reviews.get(j);
					if(lc2.cosSimil(lc) >= sim && i!=j){
						System.out.printf("[E] ids: (%d;%d) =>  list place: (%d;%d) => cosSimilIdent(%f;%f)\n",
								lc.getId(), lc2.getId(), i,j , lc.cosSimilIdent(), lc2.cosSimilIdent());
						count++;
					}
				}
				if(i%1000==0)
					System.out.printf("[E] number: %d \n",i);
			}
			
			System.out.println("Exhaustive search: "+count+ " duplicates");
			
		} catch(Exception e){
			System.out.println("EXCEPTION find near duplicates task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	

}
