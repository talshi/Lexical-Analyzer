
public class CharTaker {

	private static int charLocation;
	
	public static int getCharLocation() { return charLocation; }
	public static void setCharLocation(int charLocation) { CharTaker.charLocation = charLocation; }

	public static char getch(String s) {
		char c = '\u0000'; // null char
		if(charLocation < s.length()) {
			c = s.charAt(charLocation);
			increaseCharLocation();
		}
		return c;
	}
	
	public static char popch() {
		char c = '\u0000';
		if(charLocation < LineTaker.getLine().length())
			c = LineTaker.getLine().charAt(charLocation);
		return c;
	}
	
	public static String removeLastChar(String str) {
		decreaseCharLocation();
		return str.substring(0, str.length()-1);
	}
	
	public static void increaseCharLocation() { CharTaker.charLocation++; }
	
	public static void decreaseCharLocation() { CharTaker.charLocation--; }
	
	public static void zeroLocation() { CharTaker.charLocation = 0; }
	
}
