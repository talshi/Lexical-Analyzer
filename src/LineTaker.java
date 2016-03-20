
public class LineTaker {

	private static String line = "";
	private static int lineNumber = 1;
	
	public static String getLine() { return line; }
	public static void setLine(String line) { LineTaker.line = line; }
	
	public static int getLineNumber() { return lineNumber; }
	public static void setLineNumber(int lineNumber) { LineTaker.lineNumber = lineNumber; }
	public static void increaseLineNumber() { LineTaker.lineNumber++; }
	
	
}
