package ihm.listeners;

import ihm.componants.DBConfig;
import ihm.componants.DBFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.LoadDataTask;

public class UploadButtonListener implements ActionListener {

	private final DBConfig conf;
	private final DBFileChooser fc;


	public UploadButtonListener(DBConfig conf, DBFileChooser fc) {
		super();
		this.conf = conf;
		this.fc = fc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		new LoadDataTask(fc.getFile().getAbsolutePath()).run();


	}

}
