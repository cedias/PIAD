package mains;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import database.DB;

public class FindReviewBursts {

	public static int nb10 = 0;
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, SQLException, FileNotFoundException {
	
		ArrayList<Date> dates = new ArrayList<Date>();
		String currProduct ="";
		String exProd = "";
		Connection conn = DB.getConnection();
		double sumLen = 0;
		int count =0;
		
		String sql = "SELECT user_id, time "
				+"FROM  `reviews` "
				+"ORDER BY  `user_id` ";
		
		PreparedStatement stat = conn.prepareStatement(
		        sql,
		        ResultSet.TYPE_FORWARD_ONLY,
		        ResultSet.CONCUR_READ_ONLY);
		
		    stat.setFetchSize(Integer.MIN_VALUE);
		    ResultSet rs = stat.executeQuery();
		    
		    	while(rs.next()){
		    		currProduct = rs.getString(1);
		    		
		    		if(!currProduct.equals(exProd)){
		    			System.out.println(FindReviewBursts.hasBurst(dates,10));
		    			sumLen+=dates.size();
		    			dates = new ArrayList<Date>();
		    			
		    			count++;
		    		}
		    		
		    		dates.add(rs.getDate(2));
		    		exProd = currProduct;
		    	}
		    	sumLen+=dates.size();
    			System.out.println(FindReviewBursts.hasBurst(dates,10));
    			System.out.println(nb10);
		    
	
	}

	private static boolean hasBurst(ArrayList<Date> dates, int window) {
		boolean hasBurst = true;
		
		if(dates.size()<window)
			return false;
		
		
		for(int i=0;i<dates.size()-window;i++){
			for(int j=i;j<i+window;j++){
				dates.get(j).getTime();
			}
			
		}
		nb10++;
		return hasBurst;
			
	}
	
}
