package mains;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


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
		ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();

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

				lc = new LettersCount(lexique,id);

				for(int i=0;i<data.length;i++){
					lc.add(data[i]);
				}
				reviews.add(lc);
				lc=null;
			}

			br.close();

			System.out.println("THREAD START");
			NearDupesMemThread th1;
			NearDupesMemThread th2;
			NearDupesMemThread th3;
			NearDupesMemThread th4;



			Long start = System.currentTimeMillis();

			for(int i=0;i<reviews.size();i=i+4)
			{
				th1 = new NearDupesMemThread(reviews, i);
				th1.start();
				th2 = new NearDupesMemThread(reviews, i+1);
				th2.start();
				th3 = new NearDupesMemThread(reviews, i+2);
				th3.start();
				th4 = new NearDupesMemThread(reviews, i+3);
				th4.start();

				th1.join();
				th2.join();
				th3.join();
				th4.join();
			}

			Long end = System.currentTimeMillis();
			System.out.println("time: "+(end-start)/1000);
			System.out.println("Threads end");
	}

}
