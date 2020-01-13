package p1;

public class OneLoop {
	public static void analyze(String stringNormal, boolean displayResults) {
		
		String string = stringNormal.toLowerCase();
		
		int words = 0;
		int sentences = 0;
		int syllables = 0;
		
		try {
			for (int j = 0; j < string.length();j++) {
				char current = string.charAt(j);
				char next = string.charAt(j+1);
				
				if (Character.isDigit(current)) {
					continue;
				}
				if (Character.isLetter(current) && (Character.isWhitespace(next) || next == '"' || next == '-')) {
					words++;
				}
				if (current ==',' && (Character.isWhitespace(next) || next == '\'')) {
					words++;
				}
				if (current == '.' && (Character.isWhitespace(next) || next == '"')) {
					sentences++;
				}
				if (current == '?' && (Character.isWhitespace(next) || next == '"')) {
					sentences++;
				}
				if (current == '!' && (Character.isWhitespace(next) || next =='"')) {
					sentences++;
				}
				
				if (j>1) {
					if (current == 'e' && Character.isWhitespace(next)) {
						; // nop
					}
					if ((current == 'a' || current == 'i' || current == 'o' || current == 'u') && (next == 'a' || next == 'i' || next == 'o' || next == 'u')) {
						syllables++;
						continue;
					}
					
					if ((current == 'a' || current == 'i' || current == 'o' || current == 'u')) {
						syllables++;
					}
					if ((current == '\'') && (next == 's')) {
						syllables++;
					}
					if ((current == 's') && (next == '\'')) {
						syllables++;
					}
					
					
				}
				
			}
		} catch (StringIndexOutOfBoundsException error) {
			; // nop
		}
		
		if (displayResults) {
			System.out.println("Words (OneLoop) " + words);
			System.out.println("Sentences (OneLoop) " + sentences);
			System.out.println("Syllables (OneLoop) " + syllables);
		}
	
		
		
	}

	

	
}
