package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class LettersCount  {

	HashMap<Integer, Integer> letters;
	HashMap<String, Integer> lexique;
	int id;


	public LettersCount(HashMap<String, Integer> lexique,int id) {
		this.lexique = lexique;
		this.letters = new HashMap<Integer,Integer>(82);//nb de mot moyen par review.
		this.id = id;
	}

	public int get(int i) {
		return (!this.letters.containsKey(i))? 0 : this.letters.get(i);
	}

	public void add(String s){

			Integer i = lexique.get(s); //error prone, no tests
			Integer count = letters.get(i);

			if(null != count)
				count++;
			else
				count = 1;

			letters.put(i, count);
	}

	public double dotProduct(LettersCount lc){
		double sum = 0.0;

		 if (this.letters.size() <= lc.letters.size()) {
	            for (Integer i : this.letters.keySet())
	                if (lc.letters.containsKey(i))
	                	sum += this.letters.get(i) * lc.letters.get(i);
		 } else {
	            for (Integer i : lc.letters.keySet())
	                if (this.letters.containsKey(i))
	                	sum += this.letters.get(i) * lc.letters.get(i);
	        }
	        return sum;
	}

	public double norm(){
		return Math.sqrt(this.dotProduct(this));
	}

	public double cosSimil(LettersCount lc){
		return this.dotProduct(lc)/(this.norm()*lc.norm());

	}

	public String toString(){

		String s = "[";
		for (int i : this.letters.keySet()){
			s+= i+":"+this.letters.get(i)+" ; ";
		}
		return s.concat("]");
	}

	public int getId(){
		return this.id;
	}





}
