package mains;

import tasks.graph.GraphInitTask;

public class testFullInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GraphInitTask(true, true, true, 1, 1000, 0.9, 15, 3).run();

	}

}
