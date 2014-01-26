package models;

import java.util.Date;

public class Review {

	private Product product;
	private User user;
	private Date date;
	private int helpfulness;
	private int nbHelpfulness;
	private double score;
	private String summary;
	private String text;

	public Review(Product product, User user, Date date, int helpfulness,
			int nbHelpfulness, double score, String summary, String text) {
		super();
		this.product = product;
		this.user = user;
		this.date = date;
		this.helpfulness = helpfulness;
		this.nbHelpfulness = nbHelpfulness;
		this.score = score;
		this.summary = summary;
		this.text = text;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHelpfulness() {
		return helpfulness;
	}
	public void setHelpfulness(int helpfulness) {
		this.helpfulness = helpfulness;
	}
	public int getNbHelpfulness() {
		return nbHelpfulness;
	}
	public void setNbHelpfulness(int nbHelpfulness) {
		this.nbHelpfulness = nbHelpfulness;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


}
