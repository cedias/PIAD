package mining;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;
import com.planetj.math.rabinhash.PJLProvider;

public class Tools {

	public static String normalize(String text){
		text = text.toLowerCase();
		text = text.replaceAll("[\\W]", " ");
		text = text.replaceAll("\\s+", " ");
		text = text.trim();
		return text;

	}

	public static Set<String> toHashShingles(String text,int w, Set<String> resultSet) throws NoSuchAlgorithmException{

		String[] array = text.split(" ");

		MessageDigest md = MessageDigest.getInstance("RHF64",new PJLProvider());
		for(int i=0;i<array.length-w;i++){
			String[] temp = Arrays.copyOfRange(array, i, i+w);
			String toHash =temp[0];
			for(int j=1;j<temp.length;j++){
				toHash+=" "+temp[j];
			}

			resultSet.add(new String(md.digest(toHash.getBytes())));
		}


		return resultSet;

	}

	public static void stripFileGrep(File f, String newName){

	}

}
