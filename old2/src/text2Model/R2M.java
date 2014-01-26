package text2Model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import bases.Base;

public class R2M {

	public static void reviews2Model(String filename, Base b) throws IOException{
		File file = new File(filename);
		BufferedReader reader = new BufferedReader(new FileReader(file));

	    String line;
	    String[] data;

	    while ((line = reader.readLine()) != null) {
	       data = line.split("review/text: ");
	      // b.addReview(null, null, null, 0, 0, 0, null, null, data[1]);

	    }
	    reader.close();

	}




}
