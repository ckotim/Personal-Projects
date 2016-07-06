package Markov;

import java.util.ArrayList;

public class Main {
	private static ArrayList<String> words;

	public static void main(String[] args) {
		TextArray text = new TextArray(".txt files/");

		System.out.println(text.toString());
		
		Markov markov = new Markov(text);
		markov.calculateNext(15, 500, 7, markov.parseString("PROLOGUE"));
		System.out.println(markov.toString());

	}

}
