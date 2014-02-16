package ihm;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DBConfig extends JPanel{

	private static final long serialVersionUID = 1L;
	private GridLayout layout = new GridLayout(2,3);
	
	private JLabel tableLabel = new JLabel("Table:");
	private JLabel userLabel = new JLabel("User:");
	private JLabel passLabel = new JLabel("Password:");
	
	private JTextField tableField = new JTextField("");
	private JTextField userField = new JTextField("");
	private JTextField passField = new JTextField("");
	
	public DBConfig(){
		super();
		build();
	}
 
	private void build(){
		
		this.setLayout(layout);
		this.add(tableLabel);
		this.add(userLabel);
		this.add(passLabel);
		this.add(tableField);
		this.add(userField);
		this.add(passField);
		
		layout.setHgap(5);
		
		
	}

}
