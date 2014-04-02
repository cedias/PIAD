package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.sql.ProductSQL;

public class UpdateProducts {

	int count = 0;
	Connection conn;
	PreparedStatement st;

	public UpdateProducts(Connection conn) throws SQLException {
		this.conn = conn;
		st = ProductSQL.getUpdateNBBurstsStatement(conn);
	}


	synchronized public void addBursts(String product_id, int nb_bursts) throws SQLException{

		ProductSQL.addBatchNBBurstsUpdate(st, product_id, nb_bursts);
		count++;
		if(count>=1000){
			st.executeBatch();
			count=0;
		}
	}

	synchronized public void flushBatch() throws SQLException{
		st.executeBatch();
		st.close();
	}

}
	
	
