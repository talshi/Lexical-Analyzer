
public class Token {
	
	public enum TokenType {
		INT,
		FUNC,
		MAIN,
		IF,
		THEN,
		ELSE,
		NUM,
		ID,
		FID,
		ASSIGN,
		PLUS,
		MINUS,
		MULT,
		DIV,
		AND,
		OR,
		REL,
		SC, // semicolon
		LP, // left parentheses
		RP, // right parentheses
		LC, // left curly parentheses
		RC, // right curly parentheses
		CMMNT,
		WHITE,
		EOF
	}
	
	private String lexema;
	private TokenType type;
	private String attribute;
	private int lineNumber;
	
	public Token(String lexema, TokenType type, int lineNumber) {
		this.lexema = lexema;
		this.type = type;
		this.attribute = "";
		this.lineNumber = lineNumber;
	}

	public Token(String lexema, TokenType type, String attribute, int lineNumber) {
		this.lexema = lexema;
		this.type = type;
		this.attribute = attribute;
		this.lineNumber = lineNumber;
	}
	
	public String getLexema() { return lexema; }
	public void setLexema(String lexema) { this.lexema = lexema; }
	
	public TokenType getType() { return type; }
	public void setType(TokenType type) { this.type = type; }
	
	public String getAttribute() { return attribute; }
	public void setAttribute(String attribute) { this.attribute = attribute; }

	public int getLineNumber() { return lineNumber; }
	public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

	public String toString() {
		if(attribute != "")
			return type + ";" + attribute + ";" + lineNumber;
		else
			return type + ";" + ";" + lineNumber;
	}
}
