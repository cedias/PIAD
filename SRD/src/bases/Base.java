package bases;

import java.util.Date;

import models.Product;
import models.Review;
import models.User;

public interface Base {

	Product AddProduct(String productId, String productName);

	User addUser(String userId, String username);

	Review addReview(Product prod, User user, Date date, int parseInt,
			int parseInt2, double parseDouble, String string, String string2);

	Product getProductById(String string);

	int nbReviews();

	Review getReviewByIndex(int i);


}
