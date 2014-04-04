package gui;


import gui.componants.DBPanel;
import gui.componants.GraphPanel;
import gui.componants.OPConsole;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;




public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private OPConsole console = new OPConsole(); 
	private JTabbedPane operations = new JTabbedPane(JTabbedPane.TOP);
	private JPanel dbOperations = new DBPanel();
	private JPanel graphOperations = new GraphPanel();

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
	
		this.add(operations,BorderLayout.CENTER);
		

		this.add(console,BorderLayout.SOUTH);
		console.setBorder(consoleBorder);
		

	}

}
