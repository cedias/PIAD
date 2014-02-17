package tasks;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Scanner;
import tools.Tools;
import database.DB;
import database.ReviewSQL;

public class LoadDataTask implements Runnable{
	
	String filename;

	public LoadDataTask(String filename) {
		this.filename=filename;
	}
	
	@Override
	public void run() {
		
		try{
		Scanner sc = new Scanner(new File(filename));
		HashMap<String,Integer> reviews = new HashMap<String,Integer>();
		
		
		String line = "";
		String[] data = new String[10];
		String[] help;
		int i = 0;
		int id = 1;
		int idDupe = 0;
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
				String normText = Tools.normalize(data[9]);
				if(reviews.containsKey(normText))
				{
					idDupe = reviews.get(normText);
				}
				else
				{
					reviews.put(normText, id);
					idDupe=0;

				}

				help = data[5].split("/");
				
				ReviewSQL.insertReviewBatchExactDupe(
						st,
						data[3],
						data[0],
						Float.parseFloat(data[6]),
						data[7],
						Integer.parseInt(help[0]),
						Integer.parseInt(help[1]),
						data[8],
						data[9],
						idDupe
				);

				if(sc.hasNextLine()){
					sc.nextLine();
					i=0;
					id++;
				}
			}
			
			if(id%1000==0 && i==0){
				st.executeBatch();
			}
		}
		
		st.executeBatch();
		conn.commit();
		st.close();
		sc.close();
		return;
		
		}catch(Exception e){
			System.out.println("EXCEPTION upload task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}