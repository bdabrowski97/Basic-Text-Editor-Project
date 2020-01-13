package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javafx.scene.control.TextArea;

public class Markov {
	
	public static void randomize(TextArea ta, String starterWord, int printThisMany) {
		int masterCount = 0; //keeps track of number of masterWords
		int bbWordCount = 0; // DEBUG bb count
		
		StringTokenizer stringtoken = new StringTokenizer(ta.getText()); // turns text into tokens
		String[] allWords = new String[stringtoken.countTokens()]; // array to store them all
		
		for (int i = 0; i < allWords.length; i++) { // put all tokens into array
			if (stringtoken.hasMoreTokens()) {
				allWords[i] = stringtoken.nextToken();
			}
			
		}
		HashMap<String,LinkedList<String>> uniqueWords = new HashMap<String, LinkedList<String>>(allWords.length); // hashmap for words and their bb lists
		
		for (int i = 0; i < allWords.length;i++) {
			String newWord = allWords[i];
			String nextWord = null;
			try{
				nextWord = allWords[i+1];
			} catch (ArrayIndexOutOfBoundsException wordNotFound) {
				// wordNotFound.printStackTrace();
			}
			if (uniqueWords.containsKey(newWord)) { // if unique word is found, copy the word after it to its linkedList
				try {
					uniqueWords.get(newWord).add(nextWord);
					bbWordCount++;
				} catch (ArrayIndexOutOfBoundsException error) {
					// error.printStackTrace();
				}
				
			}
			else { // if unique word not found, add the unique word to the hashmap
				uniqueWords.put(allWords[i], new LinkedList<String>());
				try {
					uniqueWords.get(allWords[i]).add(allWords[i+1]);
				} catch (ArrayIndexOutOfBoundsException wordNotFound2) {
					// wordNotFound2.printStackTrace();
				}
				masterCount++;
			}
		}
		
		boolean continueTheProcess = true;
		
		//GENERATING RANDOM PARAGRAPHS/////////
		String key = null;
		if (uniqueWords.containsKey(starterWord)) {
			key = starterWord;
		}
		if (!uniqueWords.containsKey(starterWord)) {
			AlertBox.display("error", "Word does not exist in text area");
			continueTheProcess = false;
		}
		
		int words = 0;
		int random = (int) (Math.random() * (masterCount)) +1 ; 
		
		if (continueTheProcess == true) {
			ta.clear();
			while (words != printThisMany) {
				if (uniqueWords.get(key) == null) {
					break;
				}
				ta.appendText(key + " "); // print the word
				if (uniqueWords.get(key).size() == 0) { // if no bb words for this word, stop the process STILL ERRORS OUT***** PRINTS NULL SOMETIMES, DOESNT DETECT ABSENCE OF WORDS
					ta.appendText("\n\n");
					ta.appendText("NO BABY WORDS AVAILABLE FOR CURRENT WORD");
					break;
				}
				
				words++; // add to word count
				System.out.println("words " + words);
				random = (int) (Math.random() * uniqueWords.get(key).size()); // generate random number from current key's list
				key = uniqueWords.get(key).get(random); // using random int, find the next word to use from the key's list, then change the current key to that word
			}
		}
		
		/*
			//from starting word, we get a random word from its bblist
			//then we print that word, and find its entry in the masterlist
			//we search for it in the master list, then draw a random number from its bbamount
			//we go thru its bblist that many times as the random number, then get that word and print it
			//we then find that word in the master list and repeat the process
		
		*/
		
	}
	
	
	
}
