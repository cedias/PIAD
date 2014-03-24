package upload;

import java.sql.SQLException;


public class UploadData {


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String file = "data/Office_Products.txt";
		Long start = System.currentTimeMillis();
		UploadDataTask loader = new UploadDataTask(file, new BatchUpload(1000));
		loader.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
	}

}
