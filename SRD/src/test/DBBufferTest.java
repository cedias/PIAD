package test;

import tools.DBBuffer;

public class DBBufferTest {

	
	public static void main(String[] args){
		
		DBBuffer<String> buftest = new DBBuffer<String>(5);
		for(int i=0;i<50;i++){
			buftest.addBuffer(""+i);
			System.out.println(buftest);
			
		}
		
	}
}
