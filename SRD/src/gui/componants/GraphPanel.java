package gui.componants;

import gui.componants.sub.GraphConfig;
import gui.componants.sub.GraphInit;
import gui.listeners.graph.GraphIterationListener;


import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GraphPanel extends JPanel {

	/**
	 * Graph Tab Panel
	 */
	private static final long serialVersionUID = 1L;
	private GraphConfig gc = new GraphConfig();
	private GraphInit gi = new GraphInit();
	private JButton graphButton = new JButton("Launch Graph Algorithm");
	private TitledBorder initBorder = new TitledBorder("Graph Initialisation");
	private TitledBorder graphBorder = new TitledBorder("Graph Parameters");
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	
	
	
	public GraphPanel() {
		super();
		build();
		bind();
	}

	private void build() {
		this.setLayout(new GridLayout(2,1));
		this.add(top);
		this.add(bottom);
		
		top.setBorder(initBorder);
		top.setLayout(new GridLayout());
		top.add(gi);
		
		bottom.setBorder(graphBorder);
		bottom.setLayout(new FlowLayout());
		bottom.add(gc);
		bottom.add(graphButton);
		
	
		
		
	}
	
	private void bind() {
		this.graphButton.addActionListener(new GraphIterationListener(gi,gc));
	}
	
	

}
