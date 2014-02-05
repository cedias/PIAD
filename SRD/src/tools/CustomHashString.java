package tools;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CustomHashString {

	String s;
	public static MessageDigest md;

	public CustomHashString(String s) {
		super();
		this.s = s;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.hashCode() == hashCode();
	}
	
	public String getString() {
		return s;
	}

	public void setString(String s) {
		this.s = s;
	}

	public static void setMd(MessageDigest md) {
		CustomHashString.md = md;
	}

	@Override
	public int hashCode() {
			return new BigInteger(md.digest(s.getBytes())).intValue();

	}

	@Override
	public String toString() {
		return s.toString();
	}
	
	
	
	


}
