package upload;


import java.io.File;
import java.util.Scanner;


public class UploadDataTask implements Runnable {

	String filename;
	Uploader uploader;
	

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
				//System.out.println("readin'");
				data[i] = line.split("^[\\w]*/[\\w]*:\\s")[1];
				
				if(data[i].equals("unknown"))
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
				
				if(!skip)
					 nb = uploader.upload(data);
				
				if(nb%1000 ==0 && nb!=0)
					System.out.println(nb); //TODO remove
				

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
}
