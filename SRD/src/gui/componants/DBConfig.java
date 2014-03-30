package gui.componants;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DBConfig extends JPanel{

	private static final long serialVersionUID = 1L;
	private FlowLayout layout = new FlowLayout();
	private final int width = 10;

	private JLabel databaseLabel = new JLabel("Database:");
	private JLabel userLabel = new JLabel("User:");
	private JLabel passLabel = new JLabel("Password:");

	private JTextField databaseField = new JTextField(width);
	private JTextField userField = new JTextField(width);
	private JPasswordField passField = new JPasswordField(width);

	public DBConfig(){
		super();
		build();
	}

	private void build(){
		this.setLayout(layout);
		this.add(databaseLabel);
		this.add(databaseField);

		this.add(userLabel);
		this.add(userField);

		this.add(passLabel);
		this.add(passField);

	}

	public String getDatabase(){
		return databaseField.getText();
	}
	public String getUser(){
		return userField.getText();
	}
	public String getPassword(){
		return passField.getPassword().toString(); //Unsecure, see JPasswordField.getPassword Doc
	}

}
