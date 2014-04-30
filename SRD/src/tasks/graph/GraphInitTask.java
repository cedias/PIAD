package tasks.graph;

import java.util.HashMap;

import database.sql.HonestySQL;

import tasks.BuildLexiconTask;
import tasks.CosSimilBuild;
import tasks.FindExactDuplicatesTask;
import tasks.FindNearDuplicatesTask;
import tasks.ProductBurstTask;

/**
 * Graph init task
 * @author charles
 *
 */
public class GraphInitTask implements Runnable {

	final private boolean exactDupes;
	final private boolean nearDupes;
	final private boolean reviewBursts;
	final int nGramSize;
	final int windowND;
	final double cosSimil;
	final int minReviews;
	final int eps;
	final int windowSize;
	final double diffScore;
	

	public GraphInitTask(boolean exactDupes, boolean nearDupes,
			boolean reviewBursts, int nGramSize, int windowND, double cosSimil,
			int minReviews, int eps,int windowSize, double diffScore) {
		super();
		this.exactDupes = exactDupes;
		this.nearDupes = nearDupes;
		this.reviewBursts = reviewBursts;
		this.nGramSize = nGramSize;
		this.windowND = windowND;
		this.cosSimil = cosSimil;
		this.minReviews = minReviews;
		this.eps = eps;
		this.windowSize = windowSize;
		this.diffScore = diffScore;
	}


	@Override
	public void run() {
		long start = System.currentTimeMillis();
		
		System.out.println("Clearing previous scores");
		new ResetScoresTask().run();
		
		ComputeHonestyScoreTask hon = new ComputeHonestyScoreTask(windowSize, diffScore);
		
		if(exactDupes)
			new Thread(new FindExactDuplicatesTask()).start();
		
		if(reviewBursts)
			new Thread(new ProductBurstTask(minReviews,eps)).start();
		;
		if(nearDupes){
			HashMap<String, Integer> lexicon = new HashMap<String,Integer>(100000);
			new BuildLexiconTask(nGramSize, lexicon).run();
			new CosSimilBuild(nGramSize, lexicon).run();
			new FindNearDuplicatesTask(windowND, cosSimil, nGramSize, lexicon).run();
		}
		
		//compute trust
		ComputeTrustinessScoreTask trust = new ComputeTrustinessScoreTask();
		trust.run();
		
		//compute reliability
		ComputeReliabilityScoreTask rel = new ComputeReliabilityScoreTask();
		rel.run();
		
		long end = System.currentTimeMillis();
		System.out.println("end: "+(end-start)/1000);
			
		

	}

}
