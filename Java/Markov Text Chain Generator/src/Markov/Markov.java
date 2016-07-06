package Markov;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class Markov {
	//variables
	private ArrayList<String> chain;
	private TextArray files;
	private long seed;
	private Random numGen;
	private String startText;

	
	//constructor with seed
	public Markov(TextArray files, long seed) {
		this.chain = new ArrayList<String>();
		this.files = files;
		this.numGen = new Random(seed);
	}
	
	//constructor with default seed
	public Markov(TextArray files) {
		this(files, 1122334455);
	}

	//randomly picks input file from multiple
	public int pickFile(int[] textFiles) {
		return numGen.nextInt(textFiles.length - 1);
	}

	//calculates next word 
	private String calculateNextWord(int order, int fileIndex, ArrayList<String> startText) {
		// check if preconditions are true
		if (startText.size() < order) {
			return calculateNextWord(startText.size(), fileIndex, startText);
		}
		
		// declare/instantiates variables
		ArrayList<String> previous = new ArrayList<String>();
		ArrayList<String> file = files.getFile(fileIndex);
		ArrayList<String> possible = new ArrayList<String>();
		boolean brokenString = false;

		// pulls seed words from start text to match order
		for (int i = 0; i < order; i++) {
			previous.add(0, startText.get(startText.size() - 1 - i));
		}
		System.out.println(previous.toString());

		// Iterates over file word by word
		for (int i = 0; i < file.size(); i++) {
			// If word matches first seed word
			if (file.get(i).equalsIgnoreCase(previous.get(0))) {
				brokenString = false;
				// checks to see if there is a word to pull, if not it continues
				try {
					file.get(i + order);
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
				// checks that the words also match the rest of the seed words
				for (int j = 0; j < order; j++) {
					// if string doesn't match seed string, breaks loop and
					// moves on
					if (!file.get(j + i).equalsIgnoreCase(previous.get(j))) {
						brokenString = true;
						break;
					}
				}
				// if string didn't break, adds
				if (!brokenString) {
					possible.add(file.get(i + order));
				}
			}

		}
		
		//checks to see if possible words is empty, if so it returns this method with a decremented order
		if (possible.isEmpty()) {
			System.out.println("Order " + order + " didn't work");
			return calculateNextWord(order - 1, fileIndex, startText);
		}
		
		return possible.get(numGen.nextInt(possible.size()));

	}

	public ArrayList<String> parseString(String string) {
		this.startText = string;
		ArrayList<String> strings = new ArrayList<String>();
		String[] splitString = string.split(" ");
		for (int i = 0; i < splitString.length; i++) {
			strings.add(splitString[i]);
		}
		return strings;
	}
	
	public void calculateNext(int order, int numWords, int fileIndex, ArrayList<String> startText){
		//checks precondition
		if (order <= 0) {
			System.out.println("Those words don't exist in input file!");
			throw new NoSuchElementException();
		}
		
		//variables
		ArrayList<String> previous = startText;
		chain.clear();
		
		//iterates for numWords 
		for (int i = 0; i < numWords; i++) {
			String temp = calculateNextWord(order, fileIndex, previous);
			chain.add(temp);
			previous.add(temp);
			if (previous.size() > order) {
				previous.remove(0);
			}
		}
	}
	
	public String getChain(){
		return this.chain.toString();
	}
	
	public String toString(){
		String temp = startText;
		for (int i = 0; i < chain.size(); i++) {
			temp += " ";
			temp += chain.get(i);
		}
		return temp;
	}
	
	
	

}
