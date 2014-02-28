package ihm;

import ihm.componants.DBConfig;
import ihm.componants.DBFileChooser;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;



	public MainFrame(){
		super();
		build();
	}

	private void build(){
		this.setLayout(new GridLayout(3, 1));
		addFrames();
		setTitle("Upload Utility");
		setSize(600,200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addFrames() {
		this.add(new DBConfig());
		this.add(new DBFileChooser());
		this.add(new JButton("upload"));

	}

}
