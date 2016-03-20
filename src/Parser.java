import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	public Map<Token, Integer> tokens;
	private int charLocation = 0;
	private int lineNumber = 0;
	private Token token = null;

	public Parser() {
		tokens = new HashMap<Token, Integer>();
	}
	
	public Token yylex(Scanner s) {
		String line = "";
		String lexema = "";
		char c;
		
		while(s.hasNext()) { // TODO check condition
			line = s.nextLine();
			lineNumber++;
			charLocation = 0;
			while(charLocation < line.length()) {
				lexema = "";
				while(charLocation < line.length() && !Character.isSpaceChar(c = getch(line))) {
					lexema += c;
				}
				
				System.out.println(lexema);
				token = tokenize(lexema);
				
				// if tokenize return non-null value, add the token to the map
				if(token != null) {
					System.out.println("Token: " + token.toString() + " lineNumber: " + lineNumber);
					tokens.put(token, lineNumber);
				}
				else {
					// back to last recognized lexema 
					while(lexema.length() > 0 && lexema != "") {
						lexema = lexema.substring(0, lexema.length()-1);
						charLocation--;
						token = tokenize(lexema);
						if(token != null) {
							tokens.put(token, lineNumber);
							break;
						}
					}
				}
			}
		}
		return token;
	}

	private char getch(String s) {
		char c = '\u0000'; // null char
		if(charLocation < s.length()) {
			c = s.charAt(charLocation);
			charLocation++;
		}
		return c;
	}
	
	private char popch(String s) {
		char c = '\u0000';
		if(charLocation < s.length())
			c = s.charAt(charLocation);
		return c;
	}
	
	private Token tokenize(String token) {
		Token t = null;
		switch(token) {
		case "int":
			t = new Token("int", Token.TokenType.INT);
			break;
		case "function":
			t = new Token("function", Token.TokenType.FUNC);
			break;
		case "main":
			t = new Token("main", Token.TokenType.MAIN);
			break;
		case "if":
			t = new Token("if", Token.TokenType.IF);
			break;
		case "then":
			t = new Token("then", Token.TokenType.THEN);
			break;
		case "else":
			t = new Token("else", Token.TokenType.ELSE);
			break;
		case "=":
			char c;
			if((c = popch(token)) == '=' ) {
				t = new Token("REL", Token.TokenType.REL);
			}
			else {
				t = new Token("ASSIGN", Token.TokenType.ASSIGN);
			}
			break;
		case "+":
			t = new Token("PLUS", Token.TokenType.PLUS);
			break;
		case "-":
			t = new Token("MINUS", Token.TokenType.MINUS);
			break;
		case "*":
			t = new Token("MULT", Token.TokenType.MULT);
			break;
		case "/":
			t = new Token("DIV", Token.TokenType.DIV);
			break;
		case "&&":
			t = new Token("AND", Token.TokenType.AND);
			break;
		case "||":
			t = new Token("OR", Token.TokenType.OR);
			break;
		case "!=":
			t = new Token("REL", Token.TokenType.REL);
			break;
		case "<=":
			t = new Token("REL", Token.TokenType.REL);
			break;
		case ">=":
			t = new Token("REL", Token.TokenType.REL);
			break;
		case "<":
			t = new Token("REL", Token.TokenType.REL);
			break;
		case ">":
			t = new Token("REL", Token.TokenType.REL);
			break;
		case ";":
			t = new Token("SC", Token.TokenType.SC);
			break;
		case "(":
			t = new Token("LP", Token.TokenType.LP);
			break;
		case ")":
			t = new Token("RP", Token.TokenType.RP);
			break;
		case "{":
			t = new Token("LC", Token.TokenType.LC);
			break;
		case "}":
			t = new Token("RC", Token.TokenType.RC);
			break;
		case "":
			break;
		// TODO case EOF
		
		default:
			if(isNum(token)) {
				t = new Token(token, Token.TokenType.NUM);
			}
			else if(isID(token)) {
				t = new Token(token, Token.TokenType.ID);
			}
			else if(isFID(token)) {
				t = new Token(token, Token.TokenType.FID);
			}
			return t;
			
		}
		return t;
	}

	private boolean isNum(String token) {
		String num = "0|[1-9][0-9]*";
		Pattern numPattern = Pattern.compile(num);
		Matcher m = numPattern.matcher(token);
		if(m.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean isID(String token) {
		String id = "[a-z][a-zA-Z0-9]";
		Pattern idPattern = Pattern.compile(id);
		Matcher m = idPattern.matcher(token);
		if(m.find()) {
			return true;
		}
		return false;
	}
	
	private boolean isFID(String token) {
		String func = "[A-Z][a-zA-Z0-9]";
		Pattern funcPattern = Pattern.compile(func);
		Matcher m = funcPattern.matcher(token);
		if(m.find()) {
			return true;
		}
		return false;
	}

	public String toString() {
		String output = "";
		for (Map.Entry<Token, Integer> entry : tokens.entrySet()) {
			output += entry.getKey().toString() + " ; " + entry.getValue();
		}
		return output;
	}
}
