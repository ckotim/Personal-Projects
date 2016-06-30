package Markov;

import java.util.ArrayList;

public class Markov {
	private int order;
	private ArrayList<String> chain;
	private ArrayList<TextArray> texts;
	
	public Markov(int order, String[] files){
		this.order = order;
		this.texts = new ArrayList<TextArray>();
		for (int i = 0; i < files.length; i++) {
			this.texts.add(new TextArray(files[i]));
		}
	}
	
	public String calculateNext(int Order){
		String[] tempWords = 
		return "";
	}
	
	
}
