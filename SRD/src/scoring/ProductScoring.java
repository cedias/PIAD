package scoring;

/**
 * The Reliability Formula
 * @author charles
 *
 */
public class ProductScoring {
	
	public static double computeReliability(double theta){
		return (2/(1+Math.pow(Math.E,(theta*-1))))-1;
	}

}
