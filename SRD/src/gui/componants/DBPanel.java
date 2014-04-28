package gui.componants;


import gui.componants.sub.DBFileChooser;
import gui.listeners.db.UploadFastButtonListener;
import gui.listeners.db.UploadFullButtonListener;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;



/**
 * Database Tab Panel (Upload)
 * @author charles
 *
 */
public class DBPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final DBFileChooser dbFile = new DBFileChooser();
	private final JButton uploadFullButton = new JButton("Full Upload");
	private final JButton uploadFastButton = new JButton("Fast Upload");
	private final JPanel bottom = new JPanel();
	private TitledBorder uploadBorder = new TitledBorder("Upload");



	public DBPanel(){
		super();
		build();
		bind();
	}

	private void build(){
		this.setLayout(new GridLayout(2,1));
		
		this.add(bottom);
		bottom.setBorder(uploadBorder);
		bottom.setLayout(new GridLayout(3,1));
		bottom.add(dbFile);
		bottom.add(uploadFullButton);
		bottom.add(uploadFastButton);
		
	}
	
	private void bind() {
		uploadFullButton.addActionListener(new UploadFullButtonListener(dbFile));
		uploadFastButton.addActionListener(new UploadFastButtonListener(dbFile));
	}


}
