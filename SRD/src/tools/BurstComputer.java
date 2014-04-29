package tools;

import java.util.ArrayList;


/**
 * BurstComputer: compute bursts based on three sigma rule.
 * @author charles
 *
 */
public class BurstComputer {

	private final double avg;
	private final double std;
	private final int eps;
	private final ArrayList<Integer> reviewPerDay = new ArrayList<Integer>();

	public BurstComputer(final double avg, final double std, final int eps) {
		super();
		this.avg = avg;
		this.std = std;
		this.eps = eps;
	}
	
	public void add(int i){
		this.reviewPerDay.add(i);
	}
	
	public int nbBursts(){
		int burstCount=0;
		
		for(Integer i:reviewPerDay){
			if((i-avg) > (eps*std)){
				burstCount++;
			}
		}
		
		return burstCount;
	}
	
	
	
}
