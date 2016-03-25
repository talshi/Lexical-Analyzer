import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private Token token = null;
	
	public Token yylex(Scanner s) {
		String lexema = "";
		char c;
		
		if(LineTaker.getLine() == "") {
			LineTaker.setLine(s.nextLine());
		}
		else if(isEndOfLine()) {
			newLine(s);
		}
			
		while(!isWhiteSpace()) {
			while(!isEndOfLine()) {
				lexema = "";
				while(!isEndOfLine() && !Character.isSpaceChar(c = CharTaker.getch(LineTaker.getLine()))) {
					lexema += c;
				}
				
				System.out.println("(DEBUG)token: " + lexema);
				token = tokenize(lexema);
				
				// if tokenize return non-null value, add the token to the map
				if(token != null && token.getType() != Token.TokenType.CMMNT) {
					return token;
				}
				else if(token != null && token.getType() == Token.TokenType.CMMNT) {
					newLine(s);
					continue; // ignore comments
				}
				else {
					// back to last recognized lexema 
					while(lexema.length() > 0) {
						System.out.println("(DEBUG)removing last char.");
						lexema = CharTaker.removeLastChar(lexema);
						token = tokenize(lexema);
						if(token != null) {
							return token;
						}
						else if(lexema.isEmpty()) {
							newLine(s);
							return null;
						}
					}
				}
			}
		}
		return token;
	}
	
	private Token tokenize(String token) {
		Token t = null;
		switch(token) {
		case "int":
			t = new Token("int", Token.TokenType.INT, LineTaker.getLineNumber());
			break;
		case "function":
			t = new Token("function", Token.TokenType.FUNC, LineTaker.getLineNumber());
			break;
		case "main":
			t = new Token("main", Token.TokenType.MAIN, LineTaker.getLineNumber());
			break;
		case "if":
			t = new Token("if", Token.TokenType.IF, LineTaker.getLineNumber());
			break;
		case "then":
			t = new Token("then", Token.TokenType.THEN, LineTaker.getLineNumber());
			break;
		case "else":
			t = new Token("else", Token.TokenType.ELSE, LineTaker.getLineNumber());
			break;
		case "=":
			if((CharTaker.popch()) == '=' ) {
				CharTaker.increaseCharLocation();
				t = new Token("REL", Token.TokenType.REL, "==", LineTaker.getLineNumber());
			}
			else {
				t = new Token("ASSIGN", Token.TokenType.ASSIGN, LineTaker.getLineNumber());
			}
			break;
		case "+":
			t = new Token("PLUS", Token.TokenType.PLUS, "+", LineTaker.getLineNumber());
			break;
		case "-":
			t = new Token("MINUS", Token.TokenType.MINUS, "-", LineTaker.getLineNumber());
			break;
		case "*":
			t = new Token("MULT", Token.TokenType.MULT, "*", LineTaker.getLineNumber());
			break;
		case "/":
			if(CharTaker.popch() != '*' && CharTaker.popch() != '/') {
				t = new Token("DIV", Token.TokenType.DIV, "/", LineTaker.getLineNumber());
			}
			else if(isCMNT(token)) {
				t = new Token("CMMNT", Token.TokenType.CMMNT, LineTaker.getLineNumber());
			}
			break;
		case "&&":
			t = new Token("AND", Token.TokenType.AND, LineTaker.getLineNumber());
			break;
		case "||":
			t = new Token("OR", Token.TokenType.OR, LineTaker.getLineNumber());
			break;
		case "!=":
			t = new Token("REL", Token.TokenType.REL, "!=", LineTaker.getLineNumber());
			break;
		case "<=":
			t = new Token("REL", Token.TokenType.REL, "<=", LineTaker.getLineNumber());
			break;
		case ">=":
			t = new Token("REL", Token.TokenType.REL, ">=", LineTaker.getLineNumber());
			break;
		case "<":
			t = new Token("REL", Token.TokenType.REL, "<", LineTaker.getLineNumber());
			break;
		case ">":
			t = new Token("REL", Token.TokenType.REL, ">", LineTaker.getLineNumber());
			break;
		case ";":
			t = new Token("SC", Token.TokenType.SC, LineTaker.getLineNumber());
			break;
		case "(":
			t = new Token("LP", Token.TokenType.LP, LineTaker.getLineNumber());
			break;
		case ")":
			t = new Token("RP", Token.TokenType.RP, LineTaker.getLineNumber());
			break;
		case "{":
			t = new Token("LC", Token.TokenType.LC, LineTaker.getLineNumber());
			break;
		case "}":
			t = new Token("RC", Token.TokenType.RC, LineTaker.getLineNumber());
			break;

		default:
			if(isNum(token)) {
				t = new Token(token, Token.TokenType.NUM, token, LineTaker.getLineNumber());
			}
			else if(isID(token)) {
				t = new Token(token, Token.TokenType.ID, token, LineTaker.getLineNumber());
			}
			else if(isFID(token)) {
				t = new Token(token, Token.TokenType.FID, token, LineTaker.getLineNumber());
			}
			else if(isCMNT(token)) {
				System.out.println("(DEBUG)Found Comment. Ignoring.");
				t = new Token("CMMNT", Token.TokenType.CMMNT, LineTaker.getLineNumber());
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
		String id = "[a-z][a-zA-Z0-9]*";
		Pattern idPattern = Pattern.compile(id);
		Matcher m = idPattern.matcher(token);
		if(m.matches()) {
			return true;
		}
		return false;
	}
	
	private boolean isFID(String token) {
		String func = "[A-Z][a-zA-Z0-9]*";
		Pattern funcPattern = Pattern.compile(func);
		Matcher m = funcPattern.matcher(token);
		if(m.matches()) {
			return true;
		}
		return false;
	}

	private boolean isCMNT(String token) {
		String cmnt = "(//.*)|(/\\*(.*)|(\t\n\f\r)*\\*/)|(/\\*[^/])";
		Pattern cmntPattern = Pattern.compile(cmnt);
		Matcher m = cmntPattern.matcher(token);
		if(m.matches()) {
			return true;
		}
		return false;
	}
	
	private void newLine(Scanner s) {
		LineTaker.setLine(s.nextLine());
		LineTaker.increaseLineNumber();
		CharTaker.zeroLocation();
	}
	
	private boolean isEndOfLine() {
		return CharTaker.getCharLocation() >= LineTaker.getLine().length();
	}
	
	private boolean isWhiteSpace() {
		return LineTaker.getLine().trim().isEmpty();
	}
	
	// TODO case of EOF
	
}
