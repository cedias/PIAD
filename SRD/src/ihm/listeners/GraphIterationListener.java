package ihm.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.graph.ComputeHonestyScoreTask;
import tasks.graph.ComputeReliabilityScoreTask;
import tasks.graph.ComputeTrustinessScoreTask;

public class GraphIterationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		long start = System.currentTimeMillis();
		
		//compute honesty
		ComputeHonestyScoreTask hon = new ComputeHonestyScoreTask(25, 1);
		hon.run();
		
		System.out.println("end Honesty");
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
		
		//compute trust
		ComputeTrustinessScoreTask trust = new ComputeTrustinessScoreTask();
		trust.run();
		
		System.out.println("end trust");
		 end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
		
		//compute reliability
		ComputeReliabilityScoreTask rel = new ComputeReliabilityScoreTask();
		rel.run();
		
		System.out.println("end reliability");
		 end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
	}

	
	
}
