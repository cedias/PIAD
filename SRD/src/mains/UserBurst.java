package mains;

import tasks.UserBurstTask;

public class UserBurst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserBurstTask ub = new UserBurstTask(10, 1);
		long start = System.currentTimeMillis();
		ub.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);

	}

}
