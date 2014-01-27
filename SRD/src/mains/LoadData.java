package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import database.DB;
import database.ReviewSQL;

public class LoadData {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		HashMap<String,Integer> reviews = new HashMap<String,Integer>();
		BufferedReader br = new BufferedReader(new FileReader("data/OPStripped.txt"));
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
				System.out.println("Exact duplicate: "+id+" "+reviews.get(data[1]));
				dupes++;
				
				ReviewSQL.insertReviewBatch(st,data[1], true, reviews.get(data[1]) );
			}
			else
			{
				reviews.put(data[1], id);
				ReviewSQL.insertReviewBatch(st,data[1], false, 0 );
				
			}
			id++;
			
			if(id%1000==0){
				st.executeBatch();
			}
		}
		st.executeBatch();
		st.close();
		conn.commit();
		conn.close();
		
		System.out.println(dupes +" exact duplicates on "+id +" reviews");
		br.close();
		
		
		

	}

}
