package upload;


import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import tools.Tools;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

/**
 * Upload Task Class
 */
public class UploadDataTask implements Runnable {

	String filename;
	Uploader uploader;
	private HashSet<Integer> dupeSet = new HashSet<Integer>(1000000); //check if review already exists
	private HashMap<Integer,String> titleDupe = new HashMap<Integer,String>(200000);
	

	public UploadDataTask(String filename,Uploader uploader) {
		this.filename=filename;
		this.uploader = uploader;
	}

	@Override
	public void run() {

		try{
		Scanner sc = new Scanner(new File(filename));

		String line = "";
		String[] data = new String[10];
		int i = 0;
		int nb = 0;
		int skipCpt = 0;
		boolean skip = false;


		while(sc.hasNextLine()){
			line = sc.nextLine();
			
			
			try {
				data[i] = line.split("^[\\w]*/[\\w]*:\\s")[1];
				
				if( i!=2 && data[i].equals("unknown")) //price is useless
					throw new UnknownFieldException();
				
			} catch (ArrayIndexOutOfBoundsException | UnknownFieldException e) {
				/*Skipping review if field empty*/
				
					while(i<9){
						sc.nextLine(); //skip left lines
						i++;
					}
					
					skipCpt++;
					skip=true;
			}
			
			
			i++;

			if(i==10){
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
				 *data[9]: Review text
				 */
				
				/* Test for duplicity in review set */
				if(!skip){
					/*title/pid dupe*/
					int code = normalizeTitle(data[1]).hashCode(); //title to int
					
					if(code != 0 ){
						/*normalized title isn't "" */
					
						if(!titleDupe.containsKey(code)){
							//add new title to hashmap
							titleDupe.put(code, data[0]);
						}
						else
						{
							if(!data[0].equals(titleDupe.get(code)))
								data[0] = titleDupe.get(code);
							
						}
					
						/* dupe user/reviewtext */
						 code =  data[0].concat(data[9]).hashCode();
						
						if(dupeSet.add(code) == false){
							skip = true;
							skipCpt++;
						}
					}
					
					else{
						/*Code is 0, title is ""*/
						skip = true;
						skipCpt++;
					}
					
				}
				
				
				
				
				if(!skip)
					 nb = uploader.upload(data);
				
				if(nb%10000 ==0 && nb!=0)
					System.out.println(nb); 
				

				if(sc.hasNextLine()){
					sc.nextLine();
					i=0;
					skip = false;
				}
			}

		}
		sc.close();
		uploader.close(); //flush and closes uploader
		System.out.println("Uploaded "+nb+" reviews, skipped "+skipCpt+" reviews.");
		return;
		
		}catch(Exception e){
			System.out.println("EXCEPTION upload task:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	/**
	 * Sometimes title are the same with different case or added [text] or (text), we'll delete those. 
	 * @param title
	 * @return
	 */
	private String normalizeTitle(String title)
	{
		
		return title.replaceAll("(\\[.*\\]|\\(.*\\))", "") 
				.toLowerCase()
				.replaceAll("[\\W]", " ")
				.replaceAll("\\s+", " ")
				.trim();
	
	}
}
