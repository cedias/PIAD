package mains;

import tasks.ProductBurstTask;

public class ProductBurst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProductBurstTask pb = new ProductBurstTask(10, 1); //more than 10 reviews/day
		long start = System.currentTimeMillis();
		pb.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);

	}

}
