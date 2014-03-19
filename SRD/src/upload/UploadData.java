package upload;


public class UploadData {


	public static void main(String[] args) {
		String file = "data/Office_Products.txt";
		Long start = System.currentTimeMillis();
		UploadDataTask loader = new UploadDataTask(file);
		loader.run();
		Long end = System.currentTimeMillis();
		System.out.println("length: "+ (end-start)/1000);
	}

}
