package gui.listeners.graph;

import gui.componants.sub.GraphConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tasks.graph.GraphIterationsTask;

public class GraphIterationListener implements ActionListener {
	GraphConfig gc;
	
	public GraphIterationListener(GraphConfig gc) {
		this.gc = gc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GraphIterationsTask t = new GraphIterationsTask(gc.getNbIterations(), gc.getWindowSize(), gc.getDiffScore());
		t.run();
	}

	
	
}
