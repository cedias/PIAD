package mains;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import bases.BaseFactory;
import bases.RamBase;
import models.*;
import text2Model.R2M;


public class DataLoad {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {


		RamBase b = (RamBase) BaseFactory.createRamBase(new HashSet<ReviewSmall>(),5);
		long startTime = System.nanoTime();
		R2M.reviews2Model("data/testReviewsStripped.txt", b);
		long endTime = System.nanoTime();

		long duration = endTime - startTime;

		System.out.println("data in base:"+ b.size()+" read in: "+duration);
/*
		 HashSet<ReviewSmall> base = (HashSet<ReviewSmall>) b.getBase();



		for(ReviewSmall r : base){
			for(ReviewSmall r2 : base){
				if(r.equals(r2) && r.getId()!=r2.getId()){
					System.out.println("duplicate: "+r.getId()+" = "+r2.getId());
				}
			}
		}

*/




	}

}
