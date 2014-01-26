package text2Model;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import bases.Base;


public class T2M {


	public static Base file2Model(String filename, Base b) throws IOException{

		Scanner sc = new Scanner(new File(filename));
		String line = "";
		String[] data = new String[10];
		int i = 0;


		while(sc.hasNextLine()){
			line = sc.nextLine();

			try {
				data[i] = line.split("^*/*: ")[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				data[i] = "unknown";
			}


			i++;

			if(i==10){
				addData(data,b);

				if(sc.hasNextLine()){
					sc.nextLine();

					i=0;
				}
			}
		}

		sc.close();
		return b;
	}

	public static void addData(String[] data, Base b) {
		/*
		 *data[0]: Product id
		 *data[1]: Product title
		 *data[2]: Product price
		 *data[3]: User id
		 *data[4]: User profilename
		 *data[5]: Review helpfulness
		 *data[6]: Review score
		 *data[7]: Review timestamp
		 *data[8]: Review summary
		 *data[9]: Revuew text
		 */

		String[] help = data[5].split("/");
		b.addReview(data[0],
					data[1],
					data[3],
					Integer.parseInt(help[0]),
					Integer.parseInt(help[1]),
					Float.parseFloat(data[6]),
					data[7],
					data[8],
					data[9]
					);



	}



}
