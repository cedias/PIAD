package gui.listeners.db;

import gui.componants.sub.DBFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import upload.TextFileUpload;
import upload.UploadDataTask;

/**
 * ActionListener for fast upload button
 * @author charles
 *
 */
public class UploadFastButtonListener implements ActionListener {

	private final DBFileChooser fc;


	public UploadFastButtonListener( DBFileChooser fc) {
		super();
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
