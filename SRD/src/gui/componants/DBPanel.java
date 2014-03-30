package gui.componants;


import gui.componants.sub.DBFileChooser;
import gui.listeners.db.UploadFastButtonListener;
import gui.listeners.db.UploadFullButtonListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;




public class DBPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final DBFileChooser dbFile = new DBFileChooser();
	private final JButton uploadFullButton = new JButton("Full Upload");
	private final JButton uploadFastButton = new JButton("Fast Upload");
	private final JButton exactDuplicatesButton = new JButton("Find Exact Duplicates");
	private final JButton nearDuplicatesButton = new JButton("Find Near Duplicates");kukgu
	private final JButton outliersButton = new JButton("Find Outliers");
	private final JButton productBursts = new JButton("Find Product Bursts");
	private final JButton userBursts = new JButton("Find User Bursts");
	private final JPanel top = new JPanel();
	private final JPanel bottom = new JPanel();
	private TitledBorder uploadBorder = new TitledBorder("Upload");
	private TitledBorder initBorder = new TitledBorder("Init");
	private DBConfig dbConf;


	public DBPanel(DBConfig dbConf){
		super();
		this.dbConf = dbConf;
		build();
		bind();
	}

	private void build(){
		this.setLayout(new GridLayout(2,1));
		
		/*-- TOP --*/
		this.add(top);
		top.setBorder(uploadBorder);
		top.setLayout(new GridLayout(3,1));
		
		top.add(dbFile);
		top.add(uploadFullButton);
		top.add(uploadFastButton);
		
		/*-- BOTTOM --*/
		this.add(bottom);
		bottom.setBorder(initBorder);
		bottom.setLayout(new GridLayout(5,1));
		bottom.add(exactDuplicatesButton);
		bottom.add(nearDuplicatesButton);
		bottom.add(outliersButton);
		bottom.add(productBursts);
		bottom.add(userBursts);
		
	}
	
	private void bind() {
		uploadFullButton.addActionListener(new UploadFullButtonListener(dbConf, dbFile));
		uploadFastButton.addActionListener(new UploadFastButtonListener(dbConf, dbFile));
		
	}


}
