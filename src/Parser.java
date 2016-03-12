import java.util.Map;
import java.util.Scanner;

public class Parser {
//	private Enum TokenType;
	// TODO build a map of reservedWords
//	public Map<String, TokenType> reservedWords;
	private static int charLocation;
	private int lineNumber;
	
	public static String yylex(Scanner s) {
		charLocation = 0;
		char c;
		String lexema = "";
		while((c = getch(s)) != ' ') { // while char is not space
			lexema += c;
			
			// TODO check if lexema is in reservedWord
			
		}
		// TODO create new Token object and write it to file
		return lexema;
	}
	
	public static char getch(Scanner s) {
		char c = s.next().charAt(charLocation);
		charLocation++;
		return c;
	}
}
