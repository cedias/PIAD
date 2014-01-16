package models;

import java.util.Map;

public class Base {

	Map<String,Product> products;
	Map<String,User> users;
	Map<String,Review> reviews;


	public Base(Map<String, Product> products, Map<String, User> users,
			Map<String, Review> reviews) {
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
	public Review getReviewById(String id){
		return reviews.get(id);
	}


}
