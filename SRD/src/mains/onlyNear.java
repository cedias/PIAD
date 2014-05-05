package mains;

import java.util.HashMap;

import tasks.BuildLexiconTask;
import tasks.FindNearDuplicatesTask;

public class onlyNear {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Integer> lex = new HashMap<String,Integer>(50000);
		new BuildLexiconTask(1, lex).run();
		new FindNearDuplicatesTask(1000, 0.9, 1, lex).run();
	}

}
