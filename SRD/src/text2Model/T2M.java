package text2Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class T2M {

	private static final int SIZE = 8000;
	static int i =0;
	public static void file2Model(String filename) throws IOException{

		FileInputStream fis = new FileInputStream(filename);
		byte[] barray = new byte[SIZE];
		int nRead;
		String toParse;

		while ( (nRead=fis.read( barray, 0, SIZE )) != -1 ){
			toParse = new String(barray);
			parseReview(toParse);
		}
	}

	private static void parseReview(String toParse){
		Scanner sc = new Scanner(toParse);
		String line="";

		while(sc.hasNextLine()){
	     line = sc.nextLine();
		//System.out.println(line); //each line
		}
	}
}
