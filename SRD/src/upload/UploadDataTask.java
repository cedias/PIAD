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
		
		Connection connReviews = DB.getConnection();
		Connection connUsers = DB.getConnection();
		Connection connProducts = DB.getConnection();
		connReviews.setAutoCommit(false);
		connUsers.setAutoCommit(false);
		connProducts.setAutoCommit(false);
		PreparedStatement stReviews = ReviewSQL.getInsertReviewStatement(connReviews);
		PreparedStatement stProducts = ProductSQL.getInsertReviewStatement(connProducts);
		PreparedStatement stUsers = UserSQL.getInsertReviewStatement(connUsers);


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
				

				help = data[5].split("/");

				ReviewSQL.insertReviewBatch(
						stReviews,
						data[3],
						data[0],
						Float.parseFloat(data[6]),
						data[7],
						Integer.parseInt(help[0]),
						Integer.parseInt(help[1]),
						data[8],
						data[9]
				);
				
				ProductSQL.insertProductBatch(stProducts,data[0],data[1]);
				UserSQL.insertUserBatch(stUsers,data[3],data[4]);
				

				if(sc.hasNextLine()){
					sc.nextLine();
					i=0;
					id++;
				}
			}

			if(id%1000==0 && i==0){
				System.out.println(id);
				stReviews.executeBatch();
				stUsers.executeBatch();
				stProducts.executeBatch();
			}
		}

		stReviews.executeBatch();
		stProducts.executeBatch();
		stUsers.executeBatch();
		
		
		connReviews.commit();
		connUsers.commit();
		connProducts.commit();
		
		stReviews.close();
		stProducts.close();
		stUsers.close();
		
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
