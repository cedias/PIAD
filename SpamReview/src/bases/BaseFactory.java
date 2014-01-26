package bases;

import java.util.Collection;

import models.ReviewSmall;

public class BaseFactory {

	public static Base createRamBase(int w){
		return new RamBase(w);
	}



}
