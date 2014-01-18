package bases;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Product;
import models.Review;
import models.User;

public class BaseML implements Base {

	private Map<String,Product> products;
	private Map<String,User> users;
	private List<Review> reviews;


	public BaseML(Map<String, Product> products, Map<String, User> users,
			List<Review> reviews) {
		super();
		this.products = products;
		this.users = users;
		this.reviews = reviews;
	}

	public Product getProductById(String id){
		return products.get(id);
	}
	public User getUserById(String id){
		return users.get(id);
	}
	public Review getReviewByIndex(int i){
		return reviews.get(i);
	}

	public int nbReviews(){
		return reviews.size();
	}

	public Product AddProduct(String id, String title){

		if(id != "unknown"){
			Product p = new Product(id, title);
			products.put(id, p);
			return p;
		}
		return null;

	}

	public User addUser(String id, String username){
		if(id != "unknown"){
			User u = new User(id, username);
			users.put(id, u);
			return u;
		}
		return null;

	}

	public Review addReview(Product product, User user, Date date, int helpfulness,
			int nbHelpfulness, double score, String summary, String text){

		Review r = new Review(product, user, date, helpfulness, nbHelpfulness, score, summary, text);
		reviews.add(r);

		return r;

	}



}
