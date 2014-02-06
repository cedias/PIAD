package mains;

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


		Tools.populateLexicon("output/OPStripped_WED.txt", lexique, 1);
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
		ndt.join();
		ndt2.join();
		ndt3.join();
		ndt4.join();

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



	}

}
