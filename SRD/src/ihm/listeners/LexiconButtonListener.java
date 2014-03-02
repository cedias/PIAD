package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.BuildLexiconTask;

public class LexiconButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		BuildLexiconTask builder = new BuildLexiconTask(1);
		builder.run();

	}

}
