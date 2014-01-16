package mains;

import java.io.IOException;

import text2Model.T2M;

public class DataLoad {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		T2M.file2Model("data/Electronics.txt");

	}

}
