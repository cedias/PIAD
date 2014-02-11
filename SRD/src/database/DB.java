package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/amazon", "amazon", "amazon");
		return connection;
	}
	
	public static ResultSet getStreamingResultSet(String sql, Connection conn) throws SQLException{
		
		PreparedStatement stat = conn.prepareStatement(
		        sql,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		
		    stat.setFetchSize(Integer.MIN_VALUE);
		    return stat.executeQuery();
	}

}
