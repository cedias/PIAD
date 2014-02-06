package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Tools {

	public static String normalize(String text){
		text = text.toLowerCase();
		text = text.replaceAll("[\\W]", " ");
		text = text.replaceAll("\\s+", " ");
		text = text.trim();
		return text;

	}

		public static Map<String,Integer> toHashShingles(String text,int w, Map<String,Integer> resultSet){

			String[] array = text.split(" ");

			for(int i=0;i<=array.length-w;i++){
				String[] temp = Arrays.copyOfRange(array, i, i+w);

				String toHash =temp[0];
				for(int j=1;j<temp.length;j++){
					toHash = toHash.concat(" ").concat(temp[j]);
				}

				if(!resultSet.containsKey(toHash))
					resultSet.put(toHash, resultSet.size());

			}


			return resultSet;

		}

		public static void populateLexicon(final String filename,final Map<String,Integer> lexique, final int nGramSize) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		String data[];

			/*creates lexicon*/
			while((line=br.readLine())!=null){
				data = line.split(":->:");
				line = Tools.normalize(data[1]);
				Tools.toHashShingles(line, nGramSize, lexique);
			}

			br.close();
		}
}

