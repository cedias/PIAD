package upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.HashSet;


import database.DB;
public class TextFileUpload implements Uploader {
	int count=0;
	PrintWriter writerReviews;
	PrintWriter writerUsers;
	PrintWriter writerProducts;
	HashSet<String> users = new HashSet<String>(30000);
	HashSet<String> products = new HashSet<String>(30000);
	
	public TextFileUpload() throws IOException{
		this.writerReviews = new PrintWriter(new BufferedWriter(new FileWriter("temp/reviews.txt")));
		this.writerUsers =  new PrintWriter(new BufferedWriter(new FileWriter("temp/users.txt")));
		this.writerProducts =  new PrintWriter(new BufferedWriter(new FileWriter("temp/products.txt")));
	}
	
	@Override
	public int upload(String[] data) throws Exception {
		this.count++;
		String[] help = data[5].split("/");
		//r
		writerReviews.println(data[3]+"\t"+data[0]+"\t"+data[6]+"\t"+data[7]+"\t"+help[0]+"\t"+help[1]+"\t"+data[8]+"\t"+data[9]);
		
		if(products.add(data[0]))
			writerProducts.println(data[0]+"\t"+data[1]);
		
		if(users.add(data[3]))
			writerUsers.println(data[3]+"\t"+data[4]);
		
		return this.count;	
	}

	@Override
	public void flush() throws Exception {
		

	}

	@Override
	public void close() throws Exception {
		writerProducts.close();
		writerReviews.close();
		writerUsers.close();
		
		Connection conn = DB.getConnection();
		String workingDir = System.getProperty("user.dir");
		conn.createStatement().executeQuery("LOAD DATA CONCURRENT LOCAL INFILE \'"+workingDir+"/temp/reviews.txt\' INTO TABLE reviews (`user_id`, `product_id`, `score`, @var1, `helpfullness`, `nb_helpfullness`, `summary`, `text`) SET time = FROM_UNIXTIME(@var1)");
		conn.createStatement().executeQuery("LOAD DATA CONCURRENT LOCAL INFILE \'"+workingDir+"/temp/users.txt\' INTO TABLE users (`user_id`, `username`)");
		conn.createStatement().executeQuery("LOAD DATA CONCURRENT LOCAL INFILE \'"+workingDir+"/temp/products.txt\' INTO TABLE products (`product_id`, `product_name`)");
		conn.close();
		
		new File(workingDir+"/temp/reviews.txt").delete();
		new File(workingDir+"/temp/users.txt").delete();
		new File(workingDir+"/temp/products.txt").delete();
		
		
	}

}
