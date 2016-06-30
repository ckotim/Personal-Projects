import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Markov {
	private static ArrayList<String> words = new ArrayList<String>();
	private static ArrayList<String> chain = new ArrayList<String>();

	public static void main(String args[]) {

		Scanner scin = new Scanner(System.in);
		System.out.print("Which file should be read? ");
		readFile(scin.next());
		System.out.print("How many words do you want to generate? ");
		int numWords = scin.nextInt();
		scin.nextLine();
		System.out.println("Enter the first word: ");
		String first = scin.nextLine();
		System.out.println("Enter the second word: ");
		String second = scin.nextLine();
		scin.close();
		chain.add(first);
		chain.add(second);

		for (int i = 0; i < numWords; i++) {
			String temp = calculateNext(first, second, words);
			chain.add(temp);
			first = second;
			second = temp;

		}
		String finalChain = "";
		for (int i = 0; i < chain.size(); i++) {
			finalChain += chain.get(i);
			finalChain += " ";
		}
		System.out.println(finalChain);

		
	}

	private static void readFile(String fileName) {
		Scanner sc;
		try {
			sc = new Scanner(new File(fileName));
			String temp;
			while (sc.hasNext()) {
				temp = sc.next();
				words.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static String calculateNext(String first, String second, ArrayList<String> text) {
		ArrayList<String> freq = new ArrayList<String>();
		String third = "NO_SUCH_WORDS_EXIST";
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).equalsIgnoreCase(first)) {
				try {
					if (words.get(i + 1).equalsIgnoreCase(second)) {
						freq.add(words.get(i + 2));
					}
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		if (freq.isEmpty()) {
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).equalsIgnoreCase(second)) {
					freq.add(words.get(i + 1));
				}
			}
		}
		if (freq.isEmpty()) {
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).equalsIgnoreCase(first)) {
					freq.add(words.get(i + 1));
				}
			}
		}
		if (freq.isEmpty()) {
			return third;
		}

		return returnHighestProbability(freq);
	}

	private static String returnHighestProbability(ArrayList<String> possible) {
		int temp = (int) Math.round((Math.random() * (possible.size() - 1)));
		Random rand = new Random(1000);
		temp = Math.abs(rand.nextInt() % (possible.size()));
		return possible.get(temp);
	}
}