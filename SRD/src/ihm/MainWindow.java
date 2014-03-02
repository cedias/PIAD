package ihm;


import ihm.listeners.LexiconButtonListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;
import upload.ihm.listeners.UploadButtonListener;



public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton uploadButton = new JButton("Build Lexicon");



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
		this.add(uploadButton);
		this.uploadButton.addActionListener(new LexiconButtonListener());

	}

}
