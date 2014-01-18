package bases;

import java.util.ArrayList;
import java.util.HashMap;

import models.Product;
import models.Review;
import models.User;

public class BaseMapListFactory {

	public static BaseML createBase(){

		return new BaseML(new HashMap<String,Product>(), new HashMap<String,User>(), new ArrayList<Review>());
	}

}
