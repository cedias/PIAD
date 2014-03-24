package scoring;

public class ReviewScoring {

	//nonsense ^^
	public static double computeReviewAgreement(Double agreement){
		return (2/(1+Math.pow(Math.E,(agreement*-1))))-1;
	}
	
	public static double computeReviewHonesty(double agreement, double reliability){
		return Math.abs(reliability)*agreement;
	}
}