package upload;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Scanner;
import database.DB;
import database.sql.ProductSQL;
import database.sql.ReviewSQL;
import database.sql.UserSQL;

public class UploadDataTask implements Runnable {

	String filename;
	Uploader uploader;

	public UploadDataTask(String filename,Uploader uploader) {
		this.filename=filename;
		this.uploader = uploader;
	}

	@Override
	public void run() {

		try{
		Scanner sc = new Scanner(new File(filename));

		String line = "";
		String[] data = new String[10];
		int i = 0;
		


		while(sc.hasNextLine()){
			line = sc.nextLine();
			try {
				data[i] = line.split("^*/*: ")[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				/*field empty*/
				data[i] = "unknown";
			}
			i++;

			if(i==10){
				/*
				 *data[0]: Product id
				 *data[1]: Product title
				 *data[2]: Product price
				 *data[3]: User id
				 *data[4]: User profilename
				 *data[5]: Review helpfulness
				 *data[6]: Review score
				 *data[7]: Review timestamp
				 *data[8]: Review summary
				 *data[9]: Review text
				 */
				

				int nb = uploader.upload(data);
				
				if(nb%1000 ==0 && nb!=0)
					System.out.println(nb); //TODO remove
				

				if(sc.hasNextLine()){
					sc.nextLine();
					i=0;
				}
			}

		}
		sc.close();
		uploader.close(); //flush and closes uploader
		return;
		
		}catch(Exception e){
			System.out.println("EXCEPTION upload task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
