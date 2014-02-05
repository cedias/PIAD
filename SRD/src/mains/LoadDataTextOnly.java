package mains;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;


import database.DB;
import database.ReviewSQL;

public class LoadDataTextOnly {


	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		Long start = System.currentTimeMillis();
		String filename = "OPStripped";
		HashMap<String,Integer> reviews = new HashMap<String,Integer>();
		BufferedReader br = new BufferedReader(new FileReader("data/OPStripped.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output/"+filename+"_WED.txt")));
		String line;
		String data[];
		int id = 1;
		int dupes=0;
		Connection conn = DB.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = ReviewSQL.getInsertReviewStatement(conn);

		while((line=br.readLine())!=null){
			data = line.split("review/text: ");

			if(reviews.containsKey(data[1]))
			{
				dupes++;
				ReviewSQL.insertReviewBatchExactDupe(st,data[1], reviews.get(data[1]) );
			}
			else
			{
				reviews.put(data[1], id);
				bw.write(id +":->:"+ data[1]);
				bw.newLine();
				ReviewSQL.insertReviewBatchExactDupe(st,data[1], 0 );

			}
			id++;

			if(id%1000==0){
				st.executeBatch();
				System.out.println(id);
			}
		}
		st.executeBatch();
		st.close();
		conn.commit();
		conn.close();
		br.close();
		bw.close();

		Long end = System.currentTimeMillis();
		System.out.println(dupes +" exact duplicates on "+(id-1) +" reviews loaded in "+ (end-start)/1000 + " seconds");




	}

}
