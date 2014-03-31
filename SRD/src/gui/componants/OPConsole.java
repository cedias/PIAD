package gui.componants;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OPConsole extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea console = new JTextArea(5, 100) ;
	
	
	
	public OPConsole() {
		super();
		this.add(console);
		console.append("Hello SRD");
	}



	@Override
	public void update(Observable o, Object arg) {
		console.append(arg.toString());
	}
	
	public void append(String text)
	{
		console.append(text);
	}
	

}
