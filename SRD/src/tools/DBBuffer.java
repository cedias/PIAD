package tools;

import java.util.ArrayList;

public class DBBuffer<T> {
	
	public final int size;
	public int startCursor = 1;
	public int endCursor = 0;
	public int startIndex = 0;
	public int endIndex = 0;
	public int count = 0;
	private ArrayList<T> buf;
	
	public DBBuffer(int size) {
		super();
		this.size = size;
		this.buf = new ArrayList<T>(size);
	}
	
	public void addBuffer(T obj){
		if(count<size){
			buf.add(endIndex, obj);
		}
		else{
			buf.set(endIndex, obj);
			if(endIndex==startIndex){
				startIndex = (startIndex+1)%size;
				startCursor++;
			}
		}
	
		endIndex = (endIndex+1)%size;
		endCursor++;
		
		
		count = count+1;
	}
	
	public T get(int i){
		return buf.get(i);
	}
	
	
	public int getStartCursor() {
		return startCursor;
	}

	public void setStartCursor(int startCursor) {
		this.startCursor = startCursor;
	}

	public int getEndCursor() {
		return endCursor;
	}

	public void setEndCursor(int endCursor) {
		this.endCursor = endCursor;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public String toString(){
		String res="[";
		for(int i=0;i<buf.size();i++){
				res+=buf.get(i).toString()+" - ";
		}
		res+="X]"+"EI:"+endIndex+" SI:"+startIndex+" SC:"+startCursor+" EC:"+endCursor+" count:"+count;
		return res;
	}
	

}
