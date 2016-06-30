package Markov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextArray {
	private Scanner scan;
	private ArrayList<String> words;

	public TextArray(String fileName) {
		try {
			this.scan = new Scanner(new File(fileName));
			this.words = new ArrayList<String>();
			while (scan.hasNext()) {
				this.words.add(scan.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}

	}

	public String get(int index) {
		return this.words.get(index);
	}

	public ArrayList<String> getArray() {
		return this.words;
	}

	public int size() {
		return this.words.size();
	}

	public String toString() {
		return this.words.toString();
	}

	public boolean equals(Object o) {
		if (o instanceof TextArray) {
			TextArray text = (TextArray) o;
			if (this.words.equals(text.getArray())) {
				return true;
			}
		}
		return false;
	}
}
