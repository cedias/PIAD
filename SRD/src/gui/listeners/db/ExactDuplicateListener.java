package gui.listeners.db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.FindExactDuplicatesTask;

public class ExactDuplicateListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		FindExactDuplicatesTask dupes = new FindExactDuplicatesTask();
		dupes.run();
	}

}
