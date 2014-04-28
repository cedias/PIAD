package scoring;


/**
 * The trustiness formula0
 * @author charles
 *
 */
public class UserScoring {
	
	public static double computeTrustiness(double sum_honesty){
		
		return (2/(1+Math.pow(Math.E,(sum_honesty*-1))))-1;
		
	}

}
