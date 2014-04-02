package gui;

import gui.componants.DBConfig;
import gui.componants.DBPanel;
import gui.componants.GraphPanel;
import gui.componants.OPConsole;
import gui.componants.sub.DBStats;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Console;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;




public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private DBConfig dbConf = new DBConfig();

	private OPConsole console = new OPConsole(); 
	private JTabbedPane operations = new JTabbedPane(JTabbedPane.TOP);
	private JPanel dbOperations = new DBPanel(dbConf);
	private JPanel graphOperations = new GraphPanel();

	private TitledBorder configBorder = new TitledBorder("Configuration"); 
	private TitledBorder consoleBorder = new TitledBorder("Console"); 


	public MainWindow(){
		super();
		build();
	}

	private void build(){

		operations.addTab("Database", dbOperations);
		operations.addTab("Graph", graphOperations);
	

		
		this.setLayout(new BorderLayout());
		addFrames();
		setTitle("Spam Review");
		setSize(600,700);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(600,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addFrames() {
		this.add(dbConf,BorderLayout.NORTH);
		dbConf.setBorder(configBorder);
	
		this.add(operations,BorderLayout.CENTER);
		

		this.add(console,BorderLayout.SOUTH);
		console.setBorder(consoleBorder);
		

	}

}
