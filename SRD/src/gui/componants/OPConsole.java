package gui.componants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Console view Panel
 * @author charles
 */
public class OPConsole extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextArea console = new JTextArea(5, 50) ;
	private JScrollPane scrollPane = new JScrollPane(console);
	private Dimension size = new Dimension(500, 50);

	
	
	
	public OPConsole() {
		super();
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPane,BorderLayout.CENTER);
		
		console.setEditable(false);
		console.setAutoscrolls(true);
		this.printLineConsole("-Welcome-");
	}

	public void printLineConsole(String s){
		console.append(s+'\n');
	}

}
