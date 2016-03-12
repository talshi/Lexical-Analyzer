import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ParserMain {
	public static void main(String[] args) {
		
		// if args has no arguments
		if(args.length == 0) {
			System.out.println("No file to work with.");
			System.exit(0);
		}
		
		// read file
		Scanner s = null;
		try {
			s = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to read from file.");
			System.exit(0);
		}
		
		while(Parser.yylex(s) != "EOF") {
			// TODO
		}
	}
}
