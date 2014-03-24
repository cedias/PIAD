package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import tasks.BuildLexiconTask;
import tasks.FindNearDuplicatesTask;

public class NearDuplicateListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int win = 800;
		double sim = 0.9;
		int nGramSize = 1;
		HashMap<String, Integer> lexicon =  new HashMap<String,Integer>();
		BuildLexiconTask lex = new BuildLexiconTask(1, lexicon);
		lex.run();
		FindNearDuplicatesTask near = new FindNearDuplicatesTask(win, sim, nGramSize, lexicon);
		long start = System.currentTimeMillis();
		near.run();
		long end = System.currentTimeMillis();
		System.out.println("end: "+(end-start)/1000);

	}

}
