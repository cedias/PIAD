package upload.ihm.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import upload.BatchUpload;
import upload.UploadDataTask;
import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;

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


		try {
			UploadDataTask uploader;
			uploader = new UploadDataTask(fc.getFile().getAbsolutePath(), new BatchUpload(1000));
			Long start = System.currentTimeMillis();
			uploader.run();
			Long end = System.currentTimeMillis();
			System.out.println("length: "+ (end-start)/1000);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


	}

}
