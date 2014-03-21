package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.ComputeAgreementScoreTask;
import tasks.ComputeHonestyScoreTask;

public class GraphIterationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		long start = System.currentTimeMillis();

		//ComputeAgreementScoreTask ag = new ComputeAgreementScoreTask(25, 1);
		//ag.run();
		
		//compute honesty
		ComputeHonestyScoreTask hon = new ComputeHonestyScoreTask(25, 1);
		hon.run();
		
		//compute trust
		//compute reliability
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
	}

	
	
}
