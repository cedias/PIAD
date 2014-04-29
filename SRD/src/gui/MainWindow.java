package gui;


import gui.componants.DBPanel;
import gui.componants.GraphPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * main GUI window
 * @author charles
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane operations = new JTabbedPane(JTabbedPane.TOP);
	private JPanel dbOperations = new DBPanel();
	private JPanel graphOperations = new GraphPanel();


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
		

	}

}
