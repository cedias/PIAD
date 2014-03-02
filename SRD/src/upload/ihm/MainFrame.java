package upload.ihm;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;
import upload.ihm.listeners.UploadButtonListener;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final DBConfig dbConf = new DBConfig();
	private final DBFileChooser dbFile = new DBFileChooser();
	private final JButton uploadButton = new JButton("Upload");



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
		this.add(dbConf);
		this.add(dbFile);
		this.add(uploadButton);
		this.uploadButton.addActionListener(new UploadButtonListener(dbConf,dbFile));

	}

}
