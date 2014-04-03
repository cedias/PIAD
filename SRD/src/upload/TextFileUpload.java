package upload;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
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
	StringBuilder reviewsBuilder = new StringBuilder();
	StringBuilder productsBuilder = new StringBuilder();
	StringBuilder usersBuilder = new StringBuilder();
	private final String fs = "\t";
	private final String ls = "\n";
	
	public TextFileUpload() throws IOException{
		this.writerReviews = new PrintWriter(new BufferedWriter(new FileWriter("temp/reviews.txt")));
		this.writerUsers =  new PrintWriter(new BufferedWriter(new FileWriter("temp/users.txt")));
		this.writerProducts =  new PrintWriter(new BufferedWriter(new FileWriter("temp/products.txt")));
	}
	
	@Override
	public int upload(String[] data) throws Exception {
		this.count++;
		String[] help = data[5].split("/");
		
		reviewsBuilder.delete(0, reviewsBuilder.length());
		usersBuilder.delete(0, usersBuilder.length());
		productsBuilder.delete(0,productsBuilder.length());
		
		reviewsBuilder.append(data[3]).append(fs)
			.append(data[0]).append(fs)
			.append(data[6]).append(fs)
			.append(data[7]).append(fs)
			.append(help[0]).append(fs)
			.append(help[1]).append(fs)
			.append(data[8]).append(fs)
			.append(data[9]);
		
		writerReviews.println(reviewsBuilder.toString());
		
		if(products.add(data[0]))
			writerProducts.println(productsBuilder.append(data[0]).append(fs).append(data[1]));
		
		if(users.add(data[3]))
			writerUsers.println(usersBuilder.append(data[3]).append(fs).append(data[4]));
		
		return this.count;	
	}

	@Override
	public void flush() throws Exception {
		//no need;
		return;
	}

	@Override
	public void close() throws Exception {
		writerProducts.close();
		writerReviews.close();
		writerUsers.close();
		
		Connection conn = DB.getConnection();
		String workingDir = System.getProperty("user.dir");
		conn.createStatement().executeQuery("LOAD DATA LOCAL INFILE \'"+workingDir+"/temp/reviews.txt\' INTO TABLE reviews FIELDS ESCAPED BY '¤' (`user_id`, `product_id`, `score`, @var1, `helpfullness`, `nb_helpfullness`, `summary`, `text`) SET time = FROM_UNIXTIME(@var1) ");
		conn.createStatement().executeQuery("LOAD DATA LOCAL INFILE \'"+workingDir+"/temp/users.txt\' INTO TABLE users FIELDS ESCAPED BY '¤' (`user_id`, `username`) ");
		conn.createStatement().executeQuery("LOAD DATA LOCAL INFILE \'"+workingDir+"/temp/products.txt\' INTO TABLE products FIELDS ESCAPED BY '¤' (`product_id`, `product_name`) ");
		conn.close();
		
		
		new File(workingDir+"/temp/reviews.txt").delete();
		new File(workingDir+"/temp/users.txt").delete();
		new File(workingDir+"/temp/products.txt").delete();
		
		
		
	}

}
