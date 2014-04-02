package gui.componants.sub;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class GraphInit extends JPanel {

	/**
	 * Graph Init SubPanel
	 */
	private static final long serialVersionUID = 1L;

	private JCheckBox exactDupe = new JCheckBox("Exact Duplicates");
	private JCheckBox nearDupe = new JCheckBox("Near Duplicates");
	private JCheckBox productbursts = new JCheckBox("Products Review Bursts");
	private JCheckBox userBursts = new JCheckBox("User Review Bursts");
	
	
	public GraphInit(){
		super();
		this.setLayout(new GridLayout(2,2));
		this.add(exactDupe);
		this.add(nearDupe);
		this.add(productbursts);
		this.add(userBursts);
	}
	
	public boolean getExactDupe(){
		return exactDupe.isSelected();
	}
	
	public boolean getNearDupe(){
		return nearDupe.isSelected();
	}
	
	public boolean getProductBursts(){
		return productbursts.isSelected();
		
	}
	public boolean getUserBursts(){
		return userBursts.isSelected();
	}
	
	

}
