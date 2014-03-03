package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import tasks.BuildLexiconTask;
import tasks.CosSimilBuild;

public class CosSimListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		HashMap<String, Integer> lexicon = new HashMap<String,Integer>();
		BuildLexiconTask lex = new BuildLexiconTask(1, lexicon);
		CosSimilBuild cs = new CosSimilBuild(1,lexicon);
		System.out.println("hey");
		lex.run();
		cs.run();

	}

}
