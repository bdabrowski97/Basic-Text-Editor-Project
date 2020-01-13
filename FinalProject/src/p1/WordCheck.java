package p1;

import java.util.StringTokenizer;
import java.util.regex.*;

import javafx.scene.control.TextArea;

public class WordCheck {
	public static int wordCount(TextArea ta) {
		if (ta.getText() == null || ta.getText().isEmpty()) {
            return 0;
        }
		else {
			StringTokenizer count = new StringTokenizer(ta.getText());
        	return  count.countTokens();
		}
	}
	
	public static int wordCount(String str) {
		if (str.equals(null) || str.isEmpty()) {
			return 0;
		}
		else {
			StringTokenizer count = new StringTokenizer(str);
			return count.countTokens();
		}
	}
}
