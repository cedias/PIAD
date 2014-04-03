package gui.componants.sub;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GraphConfig extends JPanel{

	/**
	 * Graph Config SubPanel
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JLabel nbIterLabel = new JLabel("Number of Algorithm Iterations");
	private JSlider nbIter = new JSlider(1, 10,4);
	private JLabel windowLabel = new JLabel("Agreement's Window Size");
	private JSlider window = new JSlider(1, 50,25);
	private JLabel diffLabel = new JLabel("Agreement's Score Difference");
	private JSlider diff = new JSlider(1,5);
	
	public GraphConfig(){
		this.setLayout(new GridLayout(3, 2));
		
		this.add(nbIterLabel);
		this.add(nbIter);
		
		this.add(windowLabel);
		this.add(window);
		
		this.add(diffLabel);
		this.add(diff);
	}
	
	public int getNbIterations(){
		return nbIter.getValue();
	}
	public int getWindowSize(){
		return window.getValue();
	}
	public double getDiffScore(){
		return diff.getValue();
	}
	
	
}
