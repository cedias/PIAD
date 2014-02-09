package mains;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.DB;

public class FindReviewBursts {

	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, SQLException, FileNotFoundException {
	
		ArrayList<Date> dates = new ArrayList<Date>();
		String currProduct ="";
		String exProd = "";
		Connection conn = DB.getConnection();
		String sql = "SELECT product_id, time"
				+"FROM  `reviews` "
				+"ORDER BY  `product_id` ";
		
		PreparedStatement stat = conn.prepareStatement(
		        sql,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		    stat.setFetchSize(Integer.MIN_VALUE);

		    ResultSet rs = stat.executeQuery();
		    	while(rs.next()){
		    		currProduct = rs.getString(1);
		    		
		    		if(currProduct != exProd){
		    			FindReviewBursts.hasBurst(dates);
		    			dates = new ArrayList<Date>();
		    		}
		    		
		    		dates.add(rs.getDate(2));
		    		exProd = currProduct;
		    	}
	
	}

	private static boolean hasBurst(ArrayList<Date> dates) {
		boolean hasBurst = false;
		//kernel density estimation
		
		for(int i=0;i<dates.size();i++){
			kj
		}
		return hasBurst;
			
	}
	
}
