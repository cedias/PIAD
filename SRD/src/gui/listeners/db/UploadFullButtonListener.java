package gui.listeners.db;


import gui.componants.DBConfig;
import gui.componants.sub.DBFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import upload.BatchUpload;
import upload.UploadDataTask;

public class UploadFullButtonListener implements ActionListener {

	private final DBConfig conf;
	private final DBFileChooser fc;


	public UploadFullButtonListener(gui.componants.DBConfig dbConf, DBFileChooser fc) {
		super();
		this.conf = dbConf;
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
