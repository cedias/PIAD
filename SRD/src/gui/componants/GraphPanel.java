package gui.componants;

import gui.componants.sub.GraphConfig;
import gui.listeners.graph.GraphIterationListener;


import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphConfig gc = new GraphConfig();
	private JButton graphButton = new JButton("Launch Graph Algorithm");
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	
	
	
	public GraphPanel() {
		super();
		build();
		bind();
	}

	private void build() {
		//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(top);
		this.add(bottom);
		
		top.setLayout(new GridLayout(1, 2));
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		top.add(gc);
		bottom.add(graphButton);
		
	}
	
	private void bind() {
		this.graphButton.addActionListener(new GraphIterationListener(gc));
	}
	
	

}
