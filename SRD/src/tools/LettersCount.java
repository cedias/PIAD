package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class LettersCount implements Comparable<LettersCount> {

	HashMap<Integer, Integer> letters;
	HashMap<String, Integer> lexique;
	int sumComp = -1;
	double norm =-1;
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
		if(this.norm==-1)
			this.norm = Math.sqrt(this.dotProduct(this));
		
		return this.norm;
	}

	public double cosSimil(LettersCount lc){
		return this.dotProduct(lc)/(this.norm()*lc.norm());
	}
	
	public int sum(){
		if (this.sumComp!=-1)
			return this.sumComp;
		
		int sum=0;
		for(Integer i: this.letters.values())
			sum+=i;
		this.sumComp=sum;
		return this.sumComp;
			
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
	
	public double cosSimilIdent(){
		return this.sum()/(this.norm()*Math.sqrt(this.lexique.size()));
	}

	@Override
	public int compareTo(LettersCount lc) {
		return Double.compare(this.cosSimilIdent(), lc.cosSimilIdent());
	}





}
