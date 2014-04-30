package mains;

import tasks.graph.GraphInitTask;
import tasks.graph.GraphIterationsTask;

public class DefaultGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Init*/
		GraphInitTask init = new GraphInitTask(true, true, true,1,1000, 0.9, 50, 3,25,3);
		
		init.run();
		
		/*Iteration Task*/
		GraphIterationsTask t = new GraphIterationsTask(6,25,3);
		t.run();

	}

}
