package mains;

import java.sql.SQLException;

import upload.BatchUpload;
import upload.UploadDataTask;

public class DefaultUpload {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			UploadDataTask uploader;
			uploader = new UploadDataTask("reviews.txt", new BatchUpload(1000));
			Long start = System.currentTimeMillis();
			uploader.run();
			Long end = System.currentTimeMillis();
			System.out.println("length: "+ (end-start)/1000);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
