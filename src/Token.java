
public class Token {
	private String lexema;
	private TokenTypeEnum.TokenType type;
	private int location;
	
	public Token(String lexema, TokenTypeEnum.TokenType type) {
		this.lexema = lexema;
		this.type = type;
	}
	
//	public Token(String lexema, TokenTypeEnum.TokenType type, int location) {
//		this.lexema = lexema;
//		this.type = type;
//		this.location = location;
//	}
	
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public TokenTypeEnum.TokenType getType() {
		return type;
	}
	public void setType(TokenTypeEnum.TokenType type) {
		this.type = type;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
}
