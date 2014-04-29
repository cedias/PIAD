package tools;

/**
 * The agreement Object
 * @author charles
 *
 */
public class Agreement{
	final int reviewId;
	final float score;
	final double trust;
	double agreement;
	
	public Agreement(int reviewId, float score, double trust) {
		super();
		this.reviewId = reviewId;
		this.score = score;
		this.trust = trust;
	}

	public int getReviewId() {
		return reviewId;
	}

	public float getScore() {
		return score;
	}

	public double getTrust() {
		return trust;
	}
	
	public double getAgreement() {
		return agreement;
	}
	
	public void setAgreement(double agreement){
		this.agreement = agreement;
	}
	
	
	
	
}
