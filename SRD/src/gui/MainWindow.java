package gui;

import gui.componants.DBConfig;
import gui.componants.DBPanel;
import gui.componants.DBStats;
import gui.componants.GraphPanel;
import gui.componants.OPConsole;

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
	//private JPanel dbStat = new DBStats(); TODO
	private OPConsole console = new OPConsole(); 
	private JTabbedPane operations = new JTabbedPane(JTabbedPane.TOP);
	private JPanel dbOperations = new DBPanel(dbConf);
	private JPanel graphOperations = new GraphPanel();
	//private JToggleButton webServerButton = new JToggleButton("Web Server");
	private TitledBorder configBorder = new TitledBorder("Configuration"); 


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
		//this.add(dbStat); TODO 
		this.add(operations,BorderLayout.CENTER);
		
		//this.add(webServerButton,BorderLayout.SOUTH);
	
		this.add(console,BorderLayout.SOUTH); 
		console.append("Goddamned");
		
		

	}

}
