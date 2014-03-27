package upload.ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import upload.BatchUpload;
import upload.TextFileUpload;
import upload.UploadDataTask;
import upload.ihm.componants.DBConfig;
import upload.ihm.componants.DBFileChooser;

public class UploadButtonListener2 implements ActionListener {

	private final DBConfig conf;
	private final DBFileChooser fc;


	public UploadButtonListener2(DBConfig conf, DBFileChooser fc) {
		super();
		this.conf = conf;
		this.fc = fc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {


		UploadDataTask uploader;
		try {
			uploader = new UploadDataTask(fc.getFile().getAbsolutePath(), new TextFileUpload());
		
		Long start = System.currentTimeMillis();
		uploader.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
