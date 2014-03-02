package upload.ihm.componants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class DBFileChooser extends JComponent{

	private static final long serialVersionUID = 1L;
	final JFileChooser fc = new JFileChooser();
	final JButton chooseFile = new JButton("Choose File");
	final JTextField filename = new JTextField(30);
	File choosed =null;
	JComponent ref = this;

	public DBFileChooser(){
		super();
		build();
	}

	private void build(){
		this.setLayout(new FlowLayout());
		this.add(filename);
		filename.setEnabled(false);
		this.add(chooseFile);

		chooseFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(ref);
				if ( returnVal == JFileChooser.APPROVE_OPTION) {
		            choosed = fc.getSelectedFile();
		            filename.setText(choosed.getName());
		        }


			}
		});

	}

	public File getFile(){
		return this.choosed;
	}



}
