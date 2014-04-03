package gui.listeners.graph;

import gui.componants.sub.GraphConfig;
import gui.componants.sub.GraphInit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.graph.GraphInitTask;
import tasks.graph.GraphIterationsTask;

public class GraphIterationListener implements ActionListener {
	GraphConfig gc;
	GraphInit gi;
	
	public GraphIterationListener(GraphInit gi, GraphConfig gc) {
		this.gc = gc;
		this.gi = gi;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*Init*/
		GraphInitTask init = new GraphInitTask(gi.getExactDupe(), gi.getNearDupe(), gi.getBursts(),
				gi.getNGram(), gi.getWindow(), gi.getCosSim(), gi.getMinReviews(), gi.getSigma());
		
		init.run();
		
		/*Iteration Task*/
		GraphIterationsTask t = new GraphIterationsTask(gc.getNbIterations(), gc.getWindowSize(), gc.getDiffScore());
		t.run();
	}

	
	
}
