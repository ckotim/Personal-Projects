package Markov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//Stores the contents of text files and allows access to the contents.
public class TextArray {
	//Declaring variables
	private Scanner scan;
	private ArrayList<ArrayList<String>> files;
	private ArrayList<String> fileNames;
	private String path;

	
	//constructor 
	public TextArray(String path){
		this.path = path;
		files = new ArrayList<ArrayList<String>>();
		fileNames = new ArrayList<String>();
		File directory = new File(path);
		for (int i = 0; i < directory.list().length; i++) {
			if (directory.list()[i].matches("settings.txt")) {
				continue;
			}
			this.addFile(directory.list()[i]);
		}
	}
	
	
	//adds a file and its contents
	public void addFile(String fileName){
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
			this.scan = new Scanner(new File(path + fileName));
			while (scan.hasNext()) {
				temp.add(scan.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		fileNames.add(fileName);
		files.add(temp);
	}
	
	
	//returns a file contents
	public ArrayList<String> getFile(int index){
		return files.get(index);
	}
	
	
	//writes out each file and index on a new line
	public String toString() {
		String temp = "";
		for (int i = 0; i < fileNames.size(); i++) {
			temp += String.format("%d - %s\n", i + 1, fileNames.get(i));
		}
		return temp;
	}
	
	//equals
	public boolean equals(Object o) {
		if (o instanceof TextArray) {
			TextArray textArray = (TextArray) o;
			if (this.files.equals(textArray.files) && this.fileNames.equals(textArray.fileNames)) {
				return true;
			}
		}
		return false;
	}
}
