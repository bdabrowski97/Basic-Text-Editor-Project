package p1;
import java.text.DecimalFormat;
import java.util.regex.*;

import javafx.scene.control.TextArea;

public class FleschCheck {
	
	public static int avgSentenceLength(TextArea ta) {
		int sentences = SentenceCheck.sentenceCount(ta);
		if (sentences == 0) {
			return 0;
		}
		else {
			return WordCheck.wordCount(ta) / SentenceCheck.sentenceCount(ta);
		}
	}
	
	public static double calculateFleschScore(TextArea ta) { 
		
		String strToCheck = ta.getText();
		String strToCheck2 = ta.getText();
		String vowels  = "[aeiouAEIOU]+"; 
		String endingE = "(e)\b";
		
		Pattern checkEndingE = Pattern.compile(endingE);
		Pattern checkVowels = Pattern.compile(vowels);
		
		Matcher endingEMatcher = checkEndingE.matcher(strToCheck);
		Matcher vowelMatcher = checkVowels.matcher(strToCheck2);
	
		double syllablePts = 0;
		
		while(vowelMatcher.find()) {
			if (vowelMatcher.group().length() != 0) {
				syllablePts++;
			}
		}
		while(endingEMatcher.find()) {
			if (endingEMatcher.group().length() != 0) {
				syllablePts--;
			}
		}
	
		double flesch = 0;
		try {
			double a = 1.015 * (WordCheck.wordCount(ta)/SentenceCheck.sentenceCount(ta));
			double b = 84.6 *  ((syllablePts)/(WordCheck.wordCount(ta)));
			flesch = 206.835 - a - b;
		} catch (ArithmeticException mathError) {
			flesch = 0;
		}
		
		return flesch;
		
	}
	
public static double calculateFleschScore(String str) { 
		
		String strToCheck = str;
		String strToCheck2 = str;
		String vowels  = "[aeiouAEIOU]+"; 
		String endingE = "(e)\b";
		
		Pattern checkEndingE = Pattern.compile(endingE);
		Pattern checkVowels = Pattern.compile(vowels);
		
		Matcher endingEMatcher = checkEndingE.matcher(strToCheck);
		Matcher vowelMatcher = checkVowels.matcher(strToCheck2);
	
		double syllablePts = 0;
		
		while(vowelMatcher.find()) {
			if (vowelMatcher.group().length() != 0) {
				syllablePts++;
			}
		}
		while(endingEMatcher.find()) {
			if (endingEMatcher.group().length() != 0) {
				syllablePts--;
			}
		}
	
		double flesch = 0;
		try {
			double a = 1.015 * (WordCheck.wordCount(str)/SentenceCheck.sentenceCount(str));
			double b = 84.6 *  ((syllablePts)/(WordCheck.wordCount(str)));
			flesch = 206.835 - a - b;
		} catch (ArithmeticException mathError) {
			flesch = 0;
		}
		
		return flesch;
		
	}
	
	
	public static void syllableCount(TextArea ta, boolean viewInConsole) {
		String strToCheck = ta.getText();
		String vowels  = "[aeiouAEIOU]+"; 
		Pattern checkVowels = Pattern.compile(vowels);
		Matcher vowelMatcher = checkVowels.matcher(strToCheck);
	
		
		int syllablePts = 0;
		
		while(vowelMatcher.find()) {
			if (vowelMatcher.group().length() != 0) {
				syllablePts++;
			}
		}
		if (viewInConsole) {
			System.out.println("syllablePts: " + syllablePts);
		}
	}
}
