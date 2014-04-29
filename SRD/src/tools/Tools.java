package tools;

import java.util.Arrays;
import java.util.Map;

/**
 * Different tools, static methods only
 * @author charles
 *
 */
public class Tools {

	/**
	 * Normalize a text (remove non-word character and n-spaces
	 * @param text
	 * @return
	 */
	public static String normalize(String text){
		text = text.toLowerCase();
		text = text.replaceAll("[\\W]", " ");
		text = text.replaceAll("\\s+", " ");
		text = text.trim();
		return text;

	}
	
	/**
	 * Return ngrams of a text
	 * @param text
	 * @param w
	 * @param resultSet
	 * @return
	 */
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
}

