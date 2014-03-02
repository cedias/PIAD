package upload;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Scanner;
import database.DB;
import database.ReviewSQL;

public class UploadDataTask implements Runnable {

	String filename;

	public UploadDataTask(String filename) {
		this.filename=filename;
	}

	@Override
	public void run() {

		try{
		Scanner sc = new Scanner(new File(filename));

		String line = "";
		String[] data = new String[10];
		String[] help;
		int i = 0;
		int id = 1;
		Connection conn = DB.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = ReviewSQL.getInsertReviewStatement(conn);


		while(sc.hasNextLine()){
			line = sc.nextLine();
			try {
				data[i] = line.split("^*/*: ")[1];
			} catch (ArrayIndexOutOfBoundsException e) {
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
				

				help = data[5].split("/");

				ReviewSQL.insertReviewBatch(
						st,
						data[3],
						data[0],
						Float.parseFloat(data[6]),
						data[7],
						Integer.parseInt(help[0]),
						Integer.parseInt(help[1]),
						data[8],
						data[9]
				);

				if(sc.hasNextLine()){
					sc.nextLine();
					i=0;
					id++;
				}
			}

			if(id%1000==0 && i==0){
				System.out.println(id);
				st.executeBatch();

				if(id%100000==0)
					conn.commit();
			}
		}

		st.executeBatch();
		conn.commit();
		st.close();
		sc.close();
		System.out.println("Loading Finished");
		return;

		}catch(Exception e){
			System.out.println("EXCEPTION upload task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}