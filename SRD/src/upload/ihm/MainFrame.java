package upload.ihm;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;
import upload.ihm.listeners.UploadButtonListener;
import upload.ihm.listeners.UploadButtonListener2;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final DBConfig dbConf = new DBConfig();
	private final DBFileChooser dbFile = new DBFileChooser();
	private final JButton uploadButton = new JButton("Upload");
	private final JButton uploadButton2 = new JButton("Upload TEXT");



	public MainFrame(){
		super();
		build();
	}

	private void build(){
		this.setLayout(new GridLayout(4, 1));
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
		this.add(uploadButton2);
		this.uploadButton.addActionListener(new UploadButtonListener(dbConf,dbFile));
		this.uploadButton2.addActionListener(new UploadButtonListener2(dbConf,dbFile));

	}

}
