package mains;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import database.DB;

public class FindReviewBursts {

	/*
	 * burst produit/date
	 * 
	sql="SELECT r.product_id, TIME, COUNT( * ) AS nb_reviews"+
			"FROM reviews r"+
			"GROUP BY product_id, TIME"+
			"HAVING nb_reviews >10+
			"ORDER BY nb_reviews DESC"+
			"LIMIT 0 , 30";
			
			*/
	/*
	 * burst user/date
	SELECT r.user_id, TIME, COUNT( * ) AS nb_reviews
	FROM reviews r
	WHERE user_id <>  "unknown"
	GROUP BY user_id, TIME
	HAVING nb_reviews >10
	ORDER BY nb_reviews DESC 
	LIMIT 0 , 30
	
	*/
	
	
	public static int nb10 = 0;
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, SQLException, FileNotFoundException {
	
		ArrayList<Date> dates = new ArrayList<Date>();
		String currProduct ="";
		String exProd = "";
		Connection conn = DB.getConnection();
		double sumLen = 0;
		int count =0;
		
		/*product/time*/
		String sql = "SELECT product_id, time "
				+"FROM  `reviews` "
				+"ORDER BY  `product_id` ";
		
		/*user/time*/
		String sql2 = "SELECT user_id, time "
				+"FROM  `reviews` "
				+"ORDER BY  `user_id` ";
		
		
		    ResultSet rs = DB.getStreamingResultSet(sql, conn);
		    
		    	while(rs.next()){
		    		currProduct = rs.getString(1);
		    		
		    		if(!currProduct.equals(exProd) && exProd!=""){
		    			if(FindReviewBursts.hasBurst(dates,5))
		    				System.out.println(currProduct + " has a burst");
		    			dates = new ArrayList<Date>();
		    		}
		    		
		    		dates.add(rs.getDate(2));
		    		exProd = currProduct;
		    	}
		    
	
	}

	/*nb même jours > Param == burst*/
	private static boolean hasBurst(ArrayList<Date> dates, int param) {
		int count = 0;
		Collections.sort(dates);
		Date curr = dates.get(0);
		
		if(dates.size()<param)
			return false;
		
		
		for(int i=1;i<dates.size();i++){
			
			if(dates.get(i).equals(curr)){
				count++;
				
				if(count>=param)
					return true;
			}
			else
			{
			curr = dates.get(i);
			count=0;
			}
		}
		
		if(count>=param)
			return true;
		
		return false;
			
	}
	
}
