import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
		

		// System.out.println(words.toString());
	}

	private static void readFile(String fileName) {
		Scanner sc;
		try {
			sc = new Scanner(new File(fileName));
			String temp;
			while (sc.hasNext()) {
				temp = sc.next();
				//System.out.println(temp);
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

		//System.out.println(freq.toString());
		
		return returnHighestProbability(freq);
	}

	private static String returnHighest(ArrayList<String> possible){
		Map<Integer, String> markovFreq = new TreeMap<Integer, String>();
		int num = 0;
		int max = 0;
		
		possible.sort(null);
		String temp = possible.get(0);
		
		for (int i = 0; i < possible.size(); i++) {
			if (temp.equals(possible.get(i))) {
				num++;
			}
			else{
				if (max < num) {
					max = num;
				}
				markovFreq.put(num, possible.get(i - 1));
				temp = possible.get(i);
				num = 0;
				i--;
				continue;
			}
			if (i == possible.size() - 1) {
				if (max < num) {
					max = num;
				}
				markovFreq.put(num, possible.get(i));
			}

	
			
			
			//System.out.println(markovFreq.toString());
				
		}
		System.out.println(markovFreq.toString());
		return markovFreq.get(max);
	}
	
	private static String returnHighestProbability(ArrayList<String> possible){
		possible.sort(null);
		//System.out.println(possible);
		int temp = (int)Math.round((Math.random() * (possible.size() - 1)));
		//System.out.println(temp);
		return possible.get(temp);
	}
	

	
	private static String returnFirst(ArrayList<String> possible){
		possible.sort(null);
		return possible.get(0);
	}
	

}
