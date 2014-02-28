package mains;

import tasks.FindNearDuplicatesTask;
import tasks.LoadDataTask;
import tools.LexiconCreator;
import tools.ReviewVectorCreator;

public class UploadDataLexRev {


	public static void main(String[] args) {
		String file = "data/Office_Products.txt";
		LexiconCreator lex = new LexiconCreator(1);
		ReviewVectorCreator vect = new ReviewVectorCreator(lex);


		Long start = System.currentTimeMillis();
		LoadDataTask loader = new LoadDataTask(file);
		loader.registerEater(lex);
		lex.registerEater(vect);
		loader.run();
		System.out.println("lex size:"+lex.getLexicon().size());
		System.out.println("vect size:"+vect.getReviews().size());
		FindNearDuplicatesTask near = new FindNearDuplicatesTask(700, 0.9, 1, lex.getLexicon(), vect.getReviews());
		near.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
	}

}
