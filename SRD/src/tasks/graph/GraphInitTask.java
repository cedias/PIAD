package tasks.graph;

import java.util.HashMap;
import java.util.Map;

import tasks.BuildLexiconTask;
import tasks.CosSimilBuild;
import tasks.FindExactDuplicatesTask;
import tasks.FindNearDuplicatesTask;
import tasks.ProductBurstTask;

public class GraphInitTask implements Runnable {

	final private boolean exactDupes;
	final private boolean nearDupes;
	final private boolean reviewBursts;
	final int nGramSize;
	final int windowND;
	final double cosSimil;
	final int minReviews;
	final int eps;
	

	public GraphInitTask(boolean exactDupes, boolean nearDupes,
			boolean reviewBursts, int nGramSize, int windowND, double cosSimil,
			int minReviews, int eps) {
		super();
		this.exactDupes = exactDupes;
		this.nearDupes = nearDupes;
		this.reviewBursts = reviewBursts;
		this.nGramSize = nGramSize;
		this.windowND = windowND;
		this.cosSimil = cosSimil;
		this.minReviews = minReviews;
		this.eps = eps;
	}


	@Override
	public void run() {
		long start = System.currentTimeMillis();
		
		System.out.println("Exact");
		if(exactDupes)
			new Thread(new FindExactDuplicatesTask()).start();
		System.out.println("Burst");
		if(reviewBursts)
			new Thread(new ProductBurstTask(minReviews,eps)).start();
		System.out.println("ND");
		if(nearDupes){
			HashMap<String, Integer> lexicon = new HashMap<String,Integer>(100000);
			System.out.println("LEX");
			new BuildLexiconTask(nGramSize, lexicon).run();
			System.out.println("cossim");
			new CosSimilBuild(nGramSize, lexicon).run();
			System.out.println("D");
			new FindNearDuplicatesTask(windowND, cosSimil, nGramSize, lexicon).run();
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("end: "+(end-start)/1000);
			
		

	}

}
