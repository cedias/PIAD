package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Singleton Configuration of the database.
 * @author charles
 *
 */
public class DBConfig {
	
	private static DBConfig instance = new DBConfig();
	
	private  String url;
	private  String database;
	private  String user;
	private  String pass;
	
	private DBConfig(){
		try {
			String line;
			Scanner sc = new Scanner(new File("config/database.conf"));
			sc.nextLine(); //comment
			line = sc.nextLine(); //url
			url = line.split("=")[1];
			line = sc.nextLine(); //database
			database = line.split("=")[1];
			line = sc.nextLine(); //user
			user = line.split("=")[1];
			line = sc.nextLine(); //password
			pass = line.split("=")[1];
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error in file database.conf, using defaults");
			
			url = "localhost";
			database = "amazon";
			user = "amazon";
			pass = "amazon";
			
			e.printStackTrace();
		}
		
	}

	
	public String getUrl() {
		return url;
	}


	public String getDatabase() {
		return database;
	}


	public String getUser() {
		return user;
	}


	public String getPass() {
		return pass;
	}


	public static synchronized DBConfig getInstance(){
		return instance;
	}
	
	
	
	
}
