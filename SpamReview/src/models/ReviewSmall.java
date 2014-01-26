package models;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import mining.Tools;


public class ReviewSmall {

	Set<Integer> shingles;
	int reviewId;

	public ReviewSmall(int id,String reviewText,int w){

			reviewId = id;
			shingles = createSet(reviewText,w);

	}

	private HashSet<Integer> createSet(String reviewText, int w) {
		/**
		 * /!\ use Arrays.hashcode() and not a.hashcode
		 * ps: double hashing ?
		 */
		HashSet<Integer> resultSet = new HashSet<Integer>() ;
		reviewText = Tools.normalize(reviewText);
		String[] array = reviewText.split(" ");
		String[] temp;


		for(int i=0;i<array.length-w;i++){
			temp = Arrays.copyOfRange(array, i, i+w);
			resultSet.add(Arrays.hashCode(temp));
		}


		return resultSet;
	}

	public boolean equals(ReviewSmall r2) {
		HashSet<Integer> compareI= new HashSet<Integer>(shingles);
		compareI.retainAll(r2.shingles);

		HashSet<Integer> compareU= new HashSet<Integer>(shingles);
		compareU.addAll(r2.shingles);

		float union = compareU.size();
		float inter = compareI.size();

		if(inter/union >= 0.90)
			return true;
		return false;


	}


	public int getId() {
		return reviewId;
	}




}
