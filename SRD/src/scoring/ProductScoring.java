package scoring;

public class ProductScoring {
	
	public static double computeReliability(double theta){
		return (2/(1+Math.pow(Math.E,(theta*-1))))-1;
	}

}
