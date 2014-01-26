package bases;

import java.util.Collection;

import models.ReviewSmall;

public class BaseFactory {

	public static Base createRamBase(Collection<ReviewSmall> col, int w){
		return new RamBase(col,w);
	}



}
