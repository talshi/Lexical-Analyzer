import java.util.Map;
import java.util.Scanner;

public class Parser {
	public static Map<Token, Integer> tokens;
	private static int charLocation;
	private static int lineNumber = 1;

	public static Token yylex(Scanner s) {
		charLocation = 0;
		String line = "";
		String lexema = "";
		char c;
		Token t = null;
		while((line = s.nextLine()) != "") {
			lineNumber++;
			charLocation = 0;
			while(charLocation != line.length()) {

				t = tokenize(lexema);
				
				// if tokenize return non-null value, add the token to the map
				if(t != null)
					tokens.put(t, lineNumber);
				
				// if tokenize return EOF, yylex finish working
				if(t.getLexema() == "EOF")
					break;
				if((c = getch(line)) != ' ')
					lexema += getch(line);
				
				
			}
		}
		return t;
	}

	private static char getch(String s) {
		char c = s.charAt(charLocation);
		charLocation++;
		return c;
	}
	
	private static Token tokenize(String token) {
		Token t = null;
		switch(token) {
		case "int":
			t = new Token("int",TokenTypeEnum.TokenType.INT);
			return t;
		case "function":
			t = new Token("function",TokenTypeEnum.TokenType.FUNC);
			break;
		case "main":
			t = new Token("main",TokenTypeEnum.TokenType.MAIN);
			break;
		case "if":
			t = new Token("if",TokenTypeEnum.TokenType.IF);
			break;
		case "then":
			t = new Token("then",TokenTypeEnum.TokenType.THEN);
			break;
		case "else":
			t = new Token("else",TokenTypeEnum.TokenType.ELSE);
			break;
		// case num: (TODO regular expression of number)
			
		// case ID: (TODO regular expression of identifier)
			
		// case FID: (TODO regular expression of function name)
			
		case "=":
			// TODO
			break;
		case "+":
			// TODO
			break;
		case "-":
			// TODO
			break;
		case "*":
			// TODO
			break;
		case "/":
			// TODO
			break;
		case "&&":
			// TODO
			break;
		case "||":
			// TODO
			break;
		case "==":
			// TODO
			break;
		case "!=":
			// TODO
			break;
		case "<=":
			// TODO
			break;
		case ">=":
			// TODO
			break;
		case "<":
			// TODO
			break;
		case ">":
			// TODO
			break;
		case ";":
			// TODO
			break;
		case "(":
			// TODO
			break;
		case ")":
			// TODO
			break;
		case "{":
			// TODO
			break;
		case "}":
			// TODO
			break;
		
		// TODO case CMNT (will return null)
		
		// TODO case White Spaces (will return null)
			
		// TODO case EOF
			
		
		}
		return null;
	}
}
