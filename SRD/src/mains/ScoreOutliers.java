package mains;

import tasks.FindOutlierTask;

public class ScoreOutliers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindOutlierTask out = new FindOutlierTask(10,3); //more than 10 reviews, 3 sigmas
		long start = System.currentTimeMillis();
		out.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);


	}

}
