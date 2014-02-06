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


		Tools.populateLexicon("output/OPStripped_WED.txt", lexique, 1);

		BufferedReader br = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		String line;
		String data[];

			/*creates lexicon*/
			while((line=br.readLine())!=null){
				data = line.split(":->:");
				line = Tools.normalize(data[1]);

				for

			}

			br.close();
		}





	}

}
