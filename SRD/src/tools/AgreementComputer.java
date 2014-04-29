package tools;

import java.util.ArrayList;

import scoring.ReviewScoring;

/**
 * AgreementComputer: computes the agreement of an array list of agreement
 * @author charles
 *
 */
public class AgreementComputer {
	
	final String productId;
	ArrayList<Agreement> agreements = new ArrayList<Agreement>();
	final int windowSize;
	final double diffScore;
	
	public AgreementComputer(String productId, int windowSize, double diffScore) {
		super();
		this.productId = productId;
		this.windowSize = windowSize;
		this.diffScore = diffScore;
	}
	
	public void addAgreement(Agreement ag){
		agreements.add(ag);
	}
	
	public String getProductId(){
		return productId;
	}

	public ArrayList<Agreement> compute() {
		double same = 0;
		double diff = 0;
		
		
		for(int i=0;i<agreements.size();i++){
				for(int j=((i-windowSize>=0)?i-windowSize:0);j<((i+windowSize<=agreements.size())?i+windowSize:agreements.size());j++){
					
					if(Math.abs(agreements.get(i).getScore()-agreements.get(j).getScore())<diffScore)
						same+=agreements.get(i).getTrust();
					else
						diff+=agreements.get(i).getTrust();
				}
				
				this.agreements.get(i).setAgreement(ReviewScoring.computeReviewAgreement(same-diff));
				
		}
		
		return agreements;
	}


	
}
