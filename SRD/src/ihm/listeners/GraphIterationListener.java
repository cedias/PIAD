package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.ComputeAgreementScoreTask;

public class GraphIterationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ComputeAgreementScoreTask ag = new ComputeAgreementScoreTask(25, 1);
		ag.run();
		//compute honesty
		//compute trust
		//compute reliability
		
	}

	
	
}
