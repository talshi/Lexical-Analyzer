
public class Token {
	private String lexema;
	private TokenTypeEnum type;
	private int location;
	
	public Token(String lexema, TokenTypeEnum type, int location) {
		this.lexema = lexema;
		this.type = type;
		this.location = location;
	}
	
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public TokenTypeEnum getType() {
		return type;
	}
	public void setType(TokenTypeEnum type) {
		this.type = type;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
}
