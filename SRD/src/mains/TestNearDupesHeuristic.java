package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import tasks.FindNearDuplicatesExhaustiveTask;
import tasks.FindNearDuplicatesHeuristicTask;
import tools.LettersCount;
import tools.Tools;

public class TestNearDupesHeuristic {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String filename = "output/randomSet.txt";
		int nGramSize = 1;
		double sim = 0.90;
		int win = 100;
		LettersCount lc;
		
		int nbPass = 10;
		int nbLines = 10000;
		
		for(int i=0;i<nbPass;i++){
			
			HashMap<String,Integer> lexique = new HashMap<String,Integer>();
			ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();
		
			Process p = Runtime.getRuntime().exec("shuf -n "+nbLines+" -o ./output/randomSet.txt ./output/OPStripped_WED.txt");
		    p.waitFor();
		 
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
				
				System.out.println("-----Try "+i+" -----");
				FindNearDuplicatesExhaustiveTask t1 = new FindNearDuplicatesExhaustiveTask(sim, reviews);
				t1.run();
				int nbDupeEx = t1.nbDup;
				int nbDupHe = -1;
				
				for(int window=win;window<(reviews.size()/2);window=window+100){
					
					if(nbDupeEx == nbDupHe)
						break;
					
					FindNearDuplicatesHeuristicTask t2 = new FindNearDuplicatesHeuristicTask(sim, window, reviews);
					t2.run();
					nbDupHe = t2.nbDup;
				}
		}
			
			
			
			
		

	}

}
