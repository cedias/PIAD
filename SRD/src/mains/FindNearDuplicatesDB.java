package mains;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;


import threads.NearDupesDBThread;

import tools.Tools;

import database.DB;

public class FindNearDuplicatesDB {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		Connection conn1 = DB.getConnection();
		Connection conn2 = DB.getConnection();
		Connection conn3 = DB.getConnection();
		Connection conn4 = DB.getConnection();
		/*
		Connection conn5 = DB.getConnection();
		Connection conn6 = DB.getConnection();
		Connection conn7 = DB.getConnection();
		Connection conn8 = DB.getConnection();
		Connection conn9 = DB.getConnection();
		Connection conn10 = DB.getConnection();
		 */


		BufferedReader br = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		String line;
		String data[];

		/*creates lexicon*/
		while((line=br.readLine())!=null){
			data = line.split(":->:");
			line = Tools.normalize(data[1]);
			Tools.toHashShingles(line, 1, lexique);
		}

		br.close();
		int i = 5;
		int max =  128973;


		NearDupesDBThread ndt = new NearDupesDBThread(conn1, 1, lexique);
		NearDupesDBThread ndt2 = new NearDupesDBThread(conn2, 2,  lexique);
		NearDupesDBThread ndt3 = new NearDupesDBThread(conn3,3,lexique);
		NearDupesDBThread ndt4 = new NearDupesDBThread(conn4, 4, lexique);


		ndt.start();
		ndt2.start();
		ndt3.start();
		ndt4.start();

		System.out.println("Threads launched");

		while(i<= max){
			ndt.join();
			ndt2.join();
			ndt3.join();
			ndt4.join();

			ndt = new NearDupesDBThread(conn1, i, lexique);
			i++;
			ndt2 = new NearDupesDBThread(conn2, i,  lexique);
			i++;
			ndt3 = new NearDupesDBThread(conn3,i,lexique);
			i++;
			ndt4 = new NearDupesDBThread(conn4, i, lexique);
			i++;

			ndt.start();
			ndt2.start();
			ndt3.start();
			ndt4.start();

		}
		/*
		ndt5.start();
		ndt6.start();
		ndt7.start();
		ndt8.start();
		ndt9.start();
		ndt10.start();
		*/


/*
		/*create lc1
		LettersCount lc = new LettersCount(lexique);
		text = Tools.normalize(text);
		data = text.split(" ");

		for(int i =0; i<data.length;i++){
			lc.add(data[i]);
		}


		/*search through all others

		LettersCount lc2 = null;
		double simil = 0;
		String[] splitted;

		PreparedStatement stat = conn.prepareStatement(
		        sql2,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		    stat.setFetchSize(Integer.MIN_VALUE);

		    rs = stat.executeQuery();
		    while (rs.next()) {
		    	lc2 = new LettersCount(lexique);
		    	int currId = rs.getInt(1);
				String currText = rs.getString(2);

				/*
				 if(currId == id){

					continue;
				}

				currText = Tools.normalize(currText);
				splitted = currText.split(" ");

				for(int i =0; i<splitted.length;i++){
					lc2.add(splitted[i]);
				}

				simil = lc.cosSimil(lc2);



				if(simil>0.8 && simil !=1){
					System.out.println(text);
					System.out.println(currText);
					System.out.println(simil);
					System.exit(1);
				}


				lc2 = null;


		    }
		    System.out.println("end");


		    rs.close();
		    stat.close();
		    conn.close();
*/


	}

}
