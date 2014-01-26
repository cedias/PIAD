package mains;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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


		RamBase b = (RamBase) BaseFactory.createRamBase(8);
		long startTime = System.nanoTime();
		R2M.reviews2Model("data/testReviewsStripped.txt", b);
		long endTime = System.nanoTime();

		long duration = endTime - startTime;

		System.out.println("data in base:"+ b.size()+" read in: "+duration*Math.pow(10, -9)+" sec");





	}

}
