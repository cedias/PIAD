package gui.componants.sub;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NearDupeSubPan extends JPanel {

	private static final long serialVersionUID = 1L;


	private JTextField cosSimil = new JTextField("0.9");
	private JTextField window = new JTextField("1000");
	private JTextField nGram = new JTextField("1");
	
	private JTextField minRev = new JTextField("50");
	private JTextField sigma = new JTextField("3");
	
	private JLabel labelCosSim = new JLabel("cossim:");
	private JLabel labelWindow = new JLabel("window:");
	private JLabel labelNGram = new JLabel("nGram:");
	private JLabel labelminRev = new JLabel("minRev:");
	private JLabel labelSigma = new JLabel("sigma:");
	
	
	public NearDupeSubPan() {
		this.setLayout(new GridLayout(5,2));
		
		this.add(labelNGram);
		this.add(nGram);
		
		this.add(labelCosSim);
		this.add(cosSimil);
		
		this.add(labelWindow);
		this.add(window);
		
		this.add(labelSigma);
		this.add(sigma);
		
		this.add(labelminRev);
		this.add(minRev);
	}
	
	public void setOptionsNearDup(boolean b){
			cosSimil.setEnabled(b);
			nGram.setEnabled(b);
			window.setEnabled(b);
		
	}
	
	public void setOptionsBurst(boolean b){
			sigma.setEnabled(b);
			minRev.setEnabled(b);
	}
	
	public float getCosSim(){
		float f =  Float.parseFloat(cosSimil.getText());
		return f;
	}
	
	public int getNGram(){
		int i = Integer.parseInt(nGram.getText());
		return i;
	}
	
	public int getWindow(){
		int i = Integer.parseInt(window.getText());
		return i;
	}
	
	public int getMinReviews(){
		int i = Integer.parseInt(minRev.getText());
		return i;
	}
	
	public int getSigma(){
		int i = Integer.parseInt(sigma.getText());
		return i;
	}
	
}
