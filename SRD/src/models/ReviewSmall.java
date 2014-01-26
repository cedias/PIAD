package models;


import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import mining.Tools;


public class ReviewSmall {

	HashSet<String> shingles;
	int reviewId;

	public ReviewSmall(int id,String reviewText,int w){
		try {
			reviewId = id;
			reviewText = Tools.normalize(reviewText);
			shingles = (HashSet<String>) Tools.toHashShingles(reviewText, w, new HashSet<String>());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean equals(ReviewSmall r2) {
		HashSet<String> compareI= new HashSet<String>(shingles);
		compareI.retainAll(r2.shingles);

		HashSet<String> compareU= new HashSet<String>(shingles);
		compareU.addAll(r2.shingles);

		float union = compareU.size();
		float inter = compareI.size();

		if(inter/union >= 0.90)
			return true;
		return false;


	}


	public String getText() {
		return shingles.toString();
	}


	public int getId() {
		return reviewId;
	}




}
