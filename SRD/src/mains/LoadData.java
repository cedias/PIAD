package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import database.DB;

public class LoadData {

	
	public static void main(String[] args) throws IOException {
		
		HashMap<String,Integer> reviews = new HashMap<String,Integer>();
		BufferedReader br = new BufferedReader(new FileReader("data/OPStripped.txt"));
		String line;
		String data[];
		boolean added;
		int id = 1;
		int dupes=0;
		
		while((line=br.readLine())!=null){
			data = line.split("review/text: ");
			
			if(reviews.containsKey(data[1]))
			{
				System.out.println("Exact duplicate: "+id+" "+reviews.get(data[1]));
				dupes++;
			}
			else
			{
				reviews.put(data[1], id);
			}
			id++;
		}
		
		System.out.println(dupes +" exact duplicates on "+id +" reviews");
		br.close();
		
		try{
		Connection c = DB.getConnection();
		Statement st = c.createStatement();
		st.execute("TEST");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
