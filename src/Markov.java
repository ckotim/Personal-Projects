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
		try {
			Scanner scan = new Scanner(new File(args[0]));
			readFile(scan);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scin = new Scanner(System.in);
		String first = scin.nextLine();
		String second = scin.nextLine();
		chain.add(first);
		chain.add(second);
		
		for (int i = 0; i < 75; i++) {
			String temp = calculateNext(first, second, words);
			chain.add(temp);
			System.out.println(chain);
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

	private static void readFile(Scanner sc) {
		String temp;
		while (sc.hasNext()) {
			temp = sc.next();
			//System.out.println(temp);
			words.add(temp);
		}
	}

	private static String calculateNext(String first, String second, ArrayList<String> text) {
		ArrayList<String> freq = new ArrayList<String>();
		String third = "";
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
			return third;
		}

		System.out.println(freq.toString());
		
		return returnHighest(freq);
	}

	private static String returnHighest(ArrayList<String> possible){
		Map<Integer, String> markovFreq = new TreeMap<Integer, String>();
		int num = 0;
		int max = 0;
		
		possible.sort(null);
		//System.out.println(possible.toString());
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
	
//	private static String returnHighest(ArrayList<String> possible) {
//		possible.sort(null);
//		String current = "";
//		String best = "";
//		int max = 0;
//		int total = 0;
//		for (int i = 0; i < possible.size(); i++) {
//			if (current.equalsIgnoreCase(possible.get(i)) && i != possible.size() - 1)
//				total++;
//			else {
//				if (total > max) {
//					best = possible.get(i - 1);
//				}
//				current = possible.get(i);
//				continue;
//			}
//			if (total > max) {
//				best = possible.get(i);
//			}
//			System.out.println("Best is : " + best);
//		}
//		return best;
//	}
	
	private static String returnFirst(ArrayList<String> possible){
		possible.sort(null);
		return possible.get(0);
	}
	

}
