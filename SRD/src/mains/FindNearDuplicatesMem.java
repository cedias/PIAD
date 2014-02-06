package mains;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.LinkedList;


import threads.NearDupesDBThread;
import threads.NearDupesMemThread;

import tools.LettersCount;
import tools.Tools;

import database.DB;

public class FindNearDuplicatesMem {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		HashMap<Integer,LettersCount> reviews = new HashMap<Integer,LettersCount>();

		/*creates lexicon*/
		Tools.populateLexicon("output/OPStripped_WED.txt", lexique, 1);

		BufferedReader br = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		String line;
		String data[];
		LettersCount lc;
		int id;
		
		

		/*create vectors*/	
			while((line=br.readLine())!=null){
				
				data = line.split(":->:");
				id = Integer.parseInt(data[0]);
				line = Tools.normalize(data[1]);
				data = line.split(" ");
				
				lc = new LettersCount(lexique);
				
				for(int i=0;i<data.length;i++){
					lc.add(data[i]);
				}
				reviews.put(id, lc);
				lc=null;
			}

			br.close();
		
			System.out.println("THREAD START");

			NearDupesMemThread th = new NearDupesMemThread(reviews, 1);
			NearDupesMemThread th2 = new NearDupesMemThread(reviews, 2);
			
			Long start = System.currentTimeMillis();
			th.start();
			th2.start();
			int i=3;
			int max=130000;
			
			while(i<= max){
				th.join();
				th2.join();

				th = new NearDupesMemThread(reviews,i);
				i++;
				th2 = new NearDupesMemThread(reviews, i);
				i++;
				th.start();
				th2.start();
			}
			Long end = System.currentTimeMillis();
			System.out.println("time: "+(end-start)/1000);
			System.out.println("Threads end");
	}
		
}
