package ihm.listeners;

import ihm.componants.DBConfig;
import ihm.componants.DBFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import upload.UploadDataTask;

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


		
		UploadDataTask uploader = new UploadDataTask(fc.getFile().getAbsolutePath());
		
		Long start = System.currentTimeMillis();
		uploader.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);


	}

}
