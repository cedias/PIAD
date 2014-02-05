package tools;

import java.util.HashMap;

public class LettersCount  {

	HashMap<Integer, Integer> letters; //count
	HashMap<String, Integer> lexique; //lexique
	int size;
	static boolean singleLetters=false;


	public LettersCount(HashMap<String, Integer> lexique) {
		this.size = lexique.size();
		this.lexique = lexique;
		this.letters = new HashMap<Integer,Integer>(size/16);
	}

	public int add(String s){

			if(singleLetters == false && s.length()==1 )
				return 0; //ignore singles

			Integer i = lexique.get(s); //error prone, no tests


			Integer count = letters.get(i);

			if(null != count)
				count++;
			else
				count = 1;

			letters.put(i, count);
			return count;

	}

	public int getGramCount(String s ){
		Integer i = lexique.get(s); //error prone, no tests
		Integer count = letters.get(i);
		return count;
	}

	public int size(){
		return letters.size();
	}

	public double cosSimil(LettersCount lc){
		double res=0;
		double normA=0;
		double normB=0;
		int a;
		int b;


		for(int i=0;i<size;i++){
			a = this.get(i);
			b = lc.get(i);
			res+=(a*b);
			normA+=(a*a);
			normB+=(b*b);
		}

		normA = Math.sqrt(normA);
		normB = Math.sqrt(normB);
		res = res/(normA*normB);

		return res;

	}

	public int get(int i) {
		final Integer res = letters.get(i);
		return (res==null)? 0:res;
	}



}
