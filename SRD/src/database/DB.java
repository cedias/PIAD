package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database connection class
 * @author charles
 *
 */
public class DB {

	/**
	 * Get a Connection Object
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		DBConfig dbConf = DBConfig.getInstance();
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://"+dbConf.getUrl()+"/"+dbConf.getDatabase(), dbConf.getUser(),dbConf.getPass());
		return connection;
	}
	
	/**
	 * Get a streaming ResultSet from a sql review.
	 * @param sql
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getStreamingResultSet(String sql, Connection conn) throws SQLException{
		
		PreparedStatement stat = conn.prepareStatement(
		        sql,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		
		    stat.setFetchSize(Integer.MIN_VALUE);
		    return stat.executeQuery();
	}
	
	/**
	 * Get a streaming PreparedStatement from a sql review
	 * @param sql
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getStreamingStatement(String sql, Connection conn) throws SQLException{
		
		PreparedStatement stat = conn.prepareStatement(
		        sql,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		
		    stat.setFetchSize(Integer.MIN_VALUE);
		    return stat;
	}

}
