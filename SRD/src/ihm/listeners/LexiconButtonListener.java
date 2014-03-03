package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import tasks.BuildLexiconTask;

public class LexiconButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Map<String,Integer> lexicon = new HashMap<String,Integer>();
		BuildLexiconTask builder = new BuildLexiconTask(1,lexicon);
		builder.run();
		

	}

}
