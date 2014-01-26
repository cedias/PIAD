package bases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;


import mining.Tools;
import models.ReviewSmall;


public class RamBase implements Base {

	int nb = 0;
	LinkedList<ReviewSmall> base;
	int w;



	public RamBase(int w) {
		base = new LinkedList<ReviewSmall>();
		this.w=w;
	}

	public void addReview(String pid, String ptitle, String uid, int help,
			int nbhelp, float score, String timestamp, String summary,
			String text) {
			new ReviewSmall(nb, text, w);
	}


	public int size() {
		return nb;
	}

	public List<ReviewSmall> getBase(){
		return base;
	}



}
