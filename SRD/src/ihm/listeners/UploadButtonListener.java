package ihm.listeners;

import ihm.componants.DBConfig;
import ihm.componants.DBFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tasks.FindNearDuplicatesTask;
import tasks.LoadDataTask;
import tools.LexiconCreator;
import tools.ReviewVectorCreator;

public class UploadButtonListener implements ActionListener {

	private final DBConfig conf;
	private final DBFileChooser fc;


	public UploadButtonListener(DBConfig conf, DBFileChooser fc) {
		super();
		this.conf = conf;
		this.fc = fc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {


		LexiconCreator lex = new LexiconCreator(1);
		ReviewVectorCreator vect = new ReviewVectorCreator(lex);
		LoadDataTask loader = new LoadDataTask(fc.getFile().getAbsolutePath());
		FindNearDuplicatesTask near = new FindNearDuplicatesTask(700, 0.9, 1, lex.getLexicon(), vect.getReviews());

		loader.registerEater(lex);
		lex.registerEater(vect);

		Long start = System.currentTimeMillis();
		loader.run();
		near.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);


	}

}
