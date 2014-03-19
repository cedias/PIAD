package ihm;


import ihm.listeners.CosSimListener;
import ihm.listeners.ExactDuplicateListener;
import ihm.listeners.LexiconButtonListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;




public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton nearDuplicates = new JButton("Find near Duplicates");
	private final JButton exactDuplicates = new JButton("Find Exact Duplicates");
	private final JButton cosSim = new JButton("Build CosSim");



	public MainWindow(){
		super();
		build();
	}

	private void build(){
		this.setLayout(new GridLayout(3, 1));
		addFrames();
		setTitle("Choose Task");
		setSize(600,200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addFrames() {
		this.add(exactDuplicates);
		this.exactDuplicates.addActionListener(new ExactDuplicateListener());
		
		this.add(cosSim);
		this.cosSim.addActionListener(new CosSimListener());
		
		/*needs cosSim*/
		this.add(nearDuplicates);
		this.exactDuplicates.addActionListener(new ExactDuplicateListener());
		

	}

}