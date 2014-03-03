package ihm;


import ihm.listeners.CosSimListener;
import ihm.listeners.ExactDuplicateListener;
import ihm.listeners.LexiconButtonListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;
import upload.ihm.listeners.UploadButtonListener;



public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton lexiconButton = new JButton("Build Lexicon");
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
		this.add(lexiconButton);
		this.lexiconButton.addActionListener(new LexiconButtonListener());
		
		this.add(exactDuplicates);
		this.exactDuplicates.addActionListener(new ExactDuplicateListener());
		
		this.add(cosSim);
		this.cosSim.addActionListener(new CosSimListener());

	}

}
