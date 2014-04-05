package database;

public class DBConfig {
	
	private static DBConfig instance = new DBConfig();
	
	private final String url = "localhost";
	private final String database = "amazon";
	private final String user = "amazon";
	private final String pass = "amazon";
	
	private DBConfig(){}

	
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
