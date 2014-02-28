package tools;

import java.util.HashMap;

import interfaces.Eater;
import interfaces.Feeder;

public class LexiconCreator implements Feeder,Eater {

	private final HashMap<String,Integer> lexicon = new HashMap<String,Integer>();
	private final int w;
	private Eater e;

	public LexiconCreator(int w) {
		this.w = w;
	}

	public void eat(int id,String text) {
		Tools.toHashShingles(text, w, lexicon);

		if(e!=null){
			e.eat(id,text);
		}
	}

	public int getShingleSize(){
		return w;
	}

	public HashMap<String,Integer> getLexicon() {
		return lexicon;
	}

	@Override
	public void registerEater(Eater e) {
		this.e = e;

	}

}
