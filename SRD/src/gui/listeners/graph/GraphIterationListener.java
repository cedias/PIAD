package gui.listeners.graph;

import gui.componants.sub.GraphConfig;
import gui.componants.sub.GraphInit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		GraphIterationsTask t = new GraphIterationsTask(gc.getNbIterations(), gc.getWindowSize(), gc.getDiffScore());
		t.run();
	}

	
	
}
