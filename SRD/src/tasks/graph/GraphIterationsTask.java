package tasks.graph;

public class GraphIterationsTask implements Runnable {
	
	final int nbIter;
	final int windowSize;
	final double diffNote;

	public GraphIterationsTask(int nbIter, int windowSize, double diffNote) {
		super();
		this.nbIter = nbIter;
		this.windowSize = windowSize;
		this.diffNote = diffNote;
	}

	@Override
	public void run() {
		
		for(int i=0; i<nbIter;i++){	
			System.out.println("Iteration "+(i+1)+" on "+nbIter);
			//compute honesty
			ComputeHonestyScoreTask hon = new ComputeHonestyScoreTask(windowSize, diffNote);
			hon.run();
			
			//compute trust
			ComputeTrustinessScoreTask trust = new ComputeTrustinessScoreTask();
			trust.run();
			
			//compute reliability
			ComputeReliabilityScoreTask rel = new ComputeReliabilityScoreTask();
			rel.run();
			
		}
	}
	
	

}
