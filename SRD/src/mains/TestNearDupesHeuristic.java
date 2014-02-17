package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import tasks.FindNearDuplicatesExhaustiveTask;
import tasks.FindNearDuplicatesHeuristicTask;
import threads.NearDupesMemThread;
import tools.LettersCount;
import tools.Tools;

public class TestNearDupesHeuristic {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();
		String filename = "output/randomSet.txt";
		int nGramSize = 1;
		double sim = 0.90;
		int win = 250;
		LettersCount lc;
		
		//Bash to generate sample
		//shuf -n 10000 OPStripped_WED.txt > randomSet.txt

		
		/*creates lexicon*/
		Tools.populateLexicon(filename, lexique, nGramSize);

		/*create vectors*/
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while((line=br.readLine())!=null){

				String[] data = line.split(":->:");
				final int id = Integer.parseInt(data[0]);
				
				line = Tools.normalize(data[1]);
				lc = new LettersCount(lexique,id, nGramSize,line);
				reviews.add(lc);
				lc=null;
			}

			br.close();
				
			/*sorting*/
			Collections.sort(reviews);
			
			Thread t1 = new Thread(new FindNearDuplicatesExhaustiveTask(sim, reviews));
			Thread t2 = new Thread(new FindNearDuplicatesHeuristicTask(sim, win, reviews));
			t1.start();
			t2.start();
			t2.join();
			t1.join();
			
			
			
			
		

	}

}
