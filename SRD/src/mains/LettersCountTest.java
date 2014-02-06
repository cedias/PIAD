package mains;

import java.util.HashMap;

import tools.LettersCount;
import tools.Tools;

public class LettersCountTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "this is a quite spectacular test string already normalized";
		String s2 = "this is, a quite spectacular! test string, already? normalized !%% wwo";
		HashMap<String,Integer> lexique = new HashMap<String,Integer>();


		s1 = Tools.normalize(s1);
		s2 = Tools.normalize(s2);

		System.out.println("S1 == S2: " + s1.equals(s2));
		Tools.toHashShingles(s1, 1, lexique);
		Tools.toHashShingles(s2, 1, lexique);
		System.out.println("Lexique == 9: " + (lexique.size()==s1.length()));


		String[] splitted = s1.split(" ");

		LettersCount lc1 = new LettersCount(lexique);
		LettersCount lc2 = new LettersCount(lexique);

		for(int i =0; i<splitted.length;i++){
			lc1.add(splitted[i]);
		}

		splitted = s2.split(" ");
		for(int i =0; i<splitted.length;i++){
			lc2.add(splitted[i]);
		}
		System.out.println(lc2.cosSimil(lc2));
		System.out.println(lc1.cosSimil(lc1));
		System.out.println(lc2.cosSimil(lc1));
		System.out.println(lc1.cosSimil(lc2));


	}

}
