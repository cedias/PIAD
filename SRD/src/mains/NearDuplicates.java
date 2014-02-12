package mains;

import tasks.FindNearDuplicatesTask;

public class NearDuplicates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int win = 100;
		double sim = 0.9;
		int nGramSize = 1;
		String filename = "output/OPStripped_WED.txt";
		FindNearDuplicatesTask near = new FindNearDuplicatesTask(win, sim, nGramSize, filename);
		
		long start = System.currentTimeMillis();
		near.run();
		long end = System.currentTimeMillis();
		System.out.println("end: "+(end-start)/1000);

	}

}
