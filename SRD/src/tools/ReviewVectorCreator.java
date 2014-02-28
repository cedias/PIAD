package tools;

import java.util.ArrayList;

import interfaces.Eater;

public class ReviewVectorCreator implements Eater {

	private final ArrayList<LettersCount> reviews = new ArrayList<LettersCount>();
	LexiconCreator lexique;
	private final int w;

	public ReviewVectorCreator(LexiconCreator lexique) {
		this.lexique = lexique;
		this.w = lexique.getShingleSize();
	}

	public void eat(int id,String text) {

		reviews.add(new LettersCount(lexique.getLexicon(), id, w, text));

	}

	public int getShingleSize(){
		return w;
	}

	public ArrayList<LettersCount> getReviews() {
		return reviews;
	}

}
