package upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DB;
import database.sql.ProductSQL;
import database.sql.ReviewSQL;
import database.sql.UserSQL;

/**
 * Uploader using batch of INSERT queries
 * @author charles
 *
 */
public class BatchUpload implements Uploader {
	
	int count = 0;
	int maxCount;
	Connection connReviews;
	Connection connUsers;
	Connection connProducts;
	PreparedStatement stReviews; 
	PreparedStatement stProducts;
	PreparedStatement stUsers;

	public BatchUpload(int maxCount) throws ClassNotFoundException, SQLException{
		this.maxCount = maxCount;
		
		this.connReviews = DB.getConnection();
		this.connUsers = DB.getConnection();
		this.connProducts = DB.getConnection();
		connReviews.setAutoCommit(false);
		connUsers.setAutoCommit(false);
		connProducts.setAutoCommit(false);
		
		this.stReviews = ReviewSQL.getInsertReviewStatement(connReviews);
		this.stProducts = ProductSQL.getInsertReviewStatement(connProducts);
		this.stUsers = UserSQL.getInsertReviewStatement(connUsers);
		
	}
	
	@Override
	public int upload(String[] data) throws SQLException, NumberFormatException, ClassNotFoundException  
	{
		/*
		 *data[0]: Product id
		 *data[1]: Product title
		 *data[2]: Product price
		 *data[3]: User id
		 *data[4]: User profilename
		 *data[5]: Review helpfulness
		 *data[6]: Review score
		 *data[7]: Review timestamp
		 *data[8]: Review summary
		 *data[9]: Review text
		 */
		
		String[] help;
		
		help = data[5].split("/");
		
		
		ReviewSQL.insertReviewBatch(
				stReviews,
				data[3],
				data[0],
				Float.parseFloat(data[6]),
				data[7],
				Integer.parseInt(help[0]),
				Integer.parseInt(help[1]),
				data[8],
				data[9]
		);
		
		ProductSQL.insertProductBatch(stProducts,data[0],data[1]);
		UserSQL.insertUserBatch(stUsers,data[3],data[4]);
		count++;
		
		if(count%maxCount == 0)
			this.flush();
		
		return this.count;
		
	}

	@Override
	public void flush() throws SQLException {
		this.stProducts.executeBatch();
		this.stReviews.executeBatch();
		this.stUsers.executeBatch();
	}
	
	public void close() throws SQLException{
		this.flush();
		
		this.connReviews.commit();
		this.connUsers.commit();
		this.connProducts.commit();
		
		this.stProducts.close();
		this.stReviews.close();
		this.stUsers.close();
		this.connProducts.close();
		this.connReviews.close();
		this.connUsers.close();
	}

}
