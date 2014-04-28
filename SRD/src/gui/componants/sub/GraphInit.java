package gui.componants.sub;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * Graph init subpanel (with parameters)
 * @author charles
 *
 */
public class GraphInit extends JPanel {

	/**
	 * Graph Init SubPanel
	 */
	private static final long serialVersionUID = 1L;

	private JCheckBox exactDupe = new JCheckBox("Exact Duplicates");
	private JCheckBox nearDupe = new JCheckBox("Near Duplicates");
	private JCheckBox bursts = new JCheckBox("Review Bursts");
	private NearDupeSubPan ndsp = new NearDupeSubPan(); 
	private JPanel left = new JPanel();
	private JPanel right = new JPanel();
	

	
	public GraphInit(){
		super();
		build();
		bind();
		
	}
	
	private void build() {
		this.setLayout(new GridLayout(1,2));
		this.add(left);
		this.add(right);
		left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));
		left.add(exactDupe);
		left.add(nearDupe);
		left.add(bursts);
		right.add(ndsp);
		ndsp.setOptionsBurst(false);
		ndsp.setOptionsNearDup(false);
		
	}
	
	private void bind() {
		nearDupe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ndsp.setOptionsNearDup(nearDupe.isSelected());
				
			}
		});
		
		bursts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ndsp.setOptionsBurst(bursts.isSelected());
				
			}
		});
		
	}

	public boolean getExactDupe(){
		return exactDupe.isSelected();
	}
	
	public boolean getNearDupe(){
		return nearDupe.isSelected();
	}
	
	public boolean getBursts(){
		return bursts.isSelected();
		
	}

	public int getNGram() {
		return ndsp.getNGram();
	}

	public int getWindow() {
		return ndsp.getWindow();
	}

	public int getMinReviews() {
		return ndsp.getMinReviews();
	}

	public int getSigma() {
		return ndsp.getSigma();
	}
	
	public float getCosSim() {
		return ndsp.getCosSim();
	}
	
	
	
	

}
