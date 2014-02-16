package ihm;

import java.awt.GridLayout;

import javax.swing.JFrame;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	

	public MainFrame(){
		super();
		build();
	}
 
	private void build(){
		this.setLayout(new GridLayout(10, 1));
		addFrames();
		setTitle("Upload Utility");
		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	private void addFrames() {
		this.add(new DBConfig());
		
	}

}
