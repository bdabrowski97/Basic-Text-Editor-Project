package p1;

import javafx.scene.control.TextArea;

public class SentenceCheck {
	public static int sentenceCount(TextArea ta) {
		if (ta.getText() == null || ta.getText().isEmpty()) {
           return 0;
        }
		else {
			 String[] count = ta.getText().split("[.?!]+");
			 return  count.length;
		}
	}
	
	public static int sentenceCount(String str) {
		if (str == null || str.isEmpty()) {
           return 0;
        }
		else {
			 String[] count = str.split("[.?!]+");
			 return  count.length;
		}
	}
	
	public static String[] returnSentenceArray(TextArea ta) {
		if (ta.getText() == null || ta.getText().isEmpty()) {
	           return null;
	       }
		else {
			String[] count = ta.getText().split("[.?!]+");
			return count;
		}
	}
}
