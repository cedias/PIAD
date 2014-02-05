package tools;

import java.util.HashMap;

public class IdentityLettersCount extends LettersCount {

	public IdentityLettersCount(HashMap<String, Integer> lexique) {
		super(lexique);
	}

	@Override
	public int get(int i) {
		return 1;
	}
	@Override
	public int getGramCount(String s ){
		return 1;
	}



}
