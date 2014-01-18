package mains;

import java.io.IOException;

import bases.Base;
import bases.BaseMapListFactory;

import models.*;

import text2Model.T2M;

public class DataLoad {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Base b = BaseMapListFactory.createBase();
		T2M.file2Model("data/Electronics.txt",b);
		Product p = b.getProductById("B000060MQ");
		System.out.println(p.getTitle());
		System.out.println("nb reviews = "+b.nbReviews());
		System.out.println(b.getReviewByIndex(17).getSummary());
	}

}
