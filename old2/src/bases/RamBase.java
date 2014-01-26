package bases;

import java.util.Collection;


import models.ReviewSmall;


public class RamBase implements Base {

	int nb = 0;
	Collection<ReviewSmall> base;
	int w;



	public RamBase(Collection<ReviewSmall> col, int w) {
		base=col;
		this.w=w;
	}

	public void addReview(String pid, String ptitle, String uid, int help,
			int nbhelp, float score, String timestamp, String summary,
			String text) {
		base.add(new ReviewSmall(nb,text, w));

	}

	public int size() {
		return nb;
	}

	public Collection<ReviewSmall> getBase(){
		return base;
	}



}
