package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import com.planetj.math.rabinhash.PJLProvider;

import tools.CustomHashString;

import database.DB;
import database.ReviewSQL;

public class LoadDataTextOnlyRabin {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		HashMap<CustomHashString,Integer> reviews = new HashMap<CustomHashString,Integer>();
		BufferedReader br = new BufferedReader(new FileReader("data/OPStripped.txt"));
		MessageDigest md = MessageDigest.getInstance("RHF64", new PJLProvider());
		String line;
		CustomHashString.setMd(md);
		CustomHashString text;
	
		String data[];
		int id = 1;
		int dupes=0;
		Connection conn = DB.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement st = ReviewSQL.getInsertReviewStatement(conn);
		
		while((line=br.readLine())!=null){
			data = line.split("review/text: ");
			
			text = new CustomHashString(data[1]);
			
			if(reviews.containsKey(text))
			{
				dupes++;
				ReviewSQL.insertReviewBatchExactDupe(st,data[1], reviews.get(text) );
			}
			else
			{
				reviews.put(text, id);
				ReviewSQL.insertReviewBatchExactDupe(st,data[1], 0 );
				
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
