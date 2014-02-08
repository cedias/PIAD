package mains;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import tools.LettersCount;
import tools.Tools;

public class LoadDataTextOnlyNoExact {

	public static void main(String[] args) throws IOException {

		HashMap<String,Integer> lexique = new HashMap<String,Integer>();
		BufferedReader br = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		String line;
		String data[];

		/*Populate lexique*/
		while((line=br.readLine())!=null){
			data = line.split(":->:");
			line = Tools.normalize(data[1]);
			Tools.toHashShingles(line, 1, lexique);
		}
		br.close();

		System.out.println("---END POPULATION---");


		LettersCount lc = new LettersCount(lexique,0); //!\INDEX 0 Careful
		LettersCount lc2 = new LettersCount(lexique,0);

		br = new BufferedReader(new FileReader("output/OPStripped_WED.txt"));
		line = br.readLine();
		data = line.split(":->:");
		line = Tools.normalize(data[1]);
		data = line.split(" ");

		for(int i =0; i<data.length;i++){
			lc.add(data[i]);
		}



		System.out.println("--START READING--");


		while((line=br.readLine())!=null){
			data = line.split(":->:");
			int nb = Integer.parseInt(data[0]);
			line = Tools.normalize(data[1]);
			data = line.split(" ");
			try {
				for(int i =0; i<data.length;i++){
					lc2.add(data[i]);
				}
				double res = lc.cosSimil(lc2);
					if(res>0.85)
						System.out.println("suspected duplicate: "+ nb);

			lc2 =  new LettersCount(lexique,0); //!\ Danger
			} catch (Exception e) {
				System.out.println(line);
				System.exit(1);
			}
		}



		br.close();





	}

}
