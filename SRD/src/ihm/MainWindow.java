package ihm;


import ihm.listeners.CosSimListener;
import ihm.listeners.ExactDuplicateListener;
import ihm.listeners.GraphIterationListener;
import ihm.listeners.NearDuplicateListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;




public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton nearDuplicates = new JButton("Find near Duplicates");
	private final JButton exactDuplicates = new JButton("Find Exact Duplicates");
	private final JButton cosSim = new JButton("Build CosSim");
	private final JButton graph = new JButton("Launch Graph Iteration");



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
		
		
		this.add(nearDuplicates);
		this.nearDuplicates.addActionListener(new NearDuplicateListener());
		
		
		this.add(graph);
		this.graph.addActionListener(new GraphIterationListener());
		

	}

}
