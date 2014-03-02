package tools.eaters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.Eater;

public class TextCreator implements Eater {

	private final String filename;
	private BufferedWriter writer = null;

	public TextCreator(String filename){
		this.filename = filename;
	}

	public void start() throws IOException{
		this.writer = new BufferedWriter(new FileWriter(new File("output/"+filename)));
	}

	public void stop() throws IOException{
		this.writer.close();
	}
	@Override
	public void eat(int id, String text) throws Exception {
		try {
			writer.write(id +":->:"+ text);
			writer.newLine();
		} catch (IOException e) {
			System.err.println("TextCreator Writer ERROR, Maybe missing .start() call ?");
			throw e;
		}

	}

}
