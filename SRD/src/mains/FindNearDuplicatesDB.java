package mains;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import threads.NearDupesDBThread;
import tools.LettersCount;
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

		String s1="i have had this system for two years and have also experienced what some may call quirks in its operation several reviewers have stated what i believe to be reasonable explanations for the difficulties others have experienced it is important for people to keep in mind that with any multi function compact electronic device there are times when a strict adherence to defined procedures will be required for the unit to carry out a desired function this is not your father s desk phone i have had to refer to the manual on several occassions and found it helpful and easy to use on one occassion it was necessary for me to use customer sevice to re register the system and their instructions were very clear in solving the problem which has not recurred i can tell you that compared to re formatting a hard drive and reloading operating systems and software that process was like brushing my teeth in short i am very pleased with the system it has fully met my expectations and i would recommend it to anyone who is comfortable with electronics";
		String s2="i bought this case about 3 years ago when i graduated with my m arch degree i keep it in a small pocket inside my purse which is rather protected and over time it has deteriorated the pin that is in the hinge constantly slips out and i have to push it back in i find it difficult to open with such a small tab and the clear cover acrylic has cracked in several places i find it quite awkward to hand out cards at a moment s notice because i have to turn it just right in order to get a good grip to open it nice idea but i ll be sticking to something that lasts a bit longer and is easier and quicker to open let your business card tell people what you do not your case";
		String[] splitted = s1.split(" ");

		LettersCount lc1 = new LettersCount(lexique);
		LettersCount lc2 = new LettersCount(lexique);

		for(int i =0; i<splitted.length;i++){
			lc1.add(splitted[i]);
		}
		splitted = s2.split(" ");
		for(int i =0; i<splitted.length;i++){
			lc2.add(splitted[i]);
		}
		System.out.println(lc2.cosSimil(lc1));
		System.out.println(lc1.cosSimil(lc2));

		System.exit(1);

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


	}

}
