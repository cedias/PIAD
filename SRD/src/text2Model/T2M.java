package text2Model;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import bases.Base;


public class T2M {

	static boolean stop = false;
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
		String[] help = data[5].split("/");

		b.addReview(
				b.AddProduct(data[0], data[1]),
				b.addUser(data[3], data[4]),
				new Date(Long.parseLong(data[7])),
				Integer.parseInt(help[0]),
				Integer.parseInt(help[1]),
				Double.parseDouble(data[6]),
				data[8],
				data[9]);


	}



}
