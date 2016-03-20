import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;

public class ParserMain {
	public static void main(String[] args) {
		String[] input = args[0].split("/");
		String[] inputFileName = input[input.length-1].split("\\.");
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
		
		File file = new File(inputFileName[0] + ".token");
		try {
			if(!file.createNewFile()) {
			System.err.println("File already exist!");
//			System.exit(0);
			}
		} catch (IOException e) {
			System.err.println("Error creating output file!");
			System.exit(0);
		}
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e1) {
			System.err.println("Error creating writer object!");
			System.exit(0);
		}
		
		Parser p = new Parser();
		Token t = null;
		do {
			t = p.yylex(s);
//			try {
//				writer.write(t.toString()); // TODO check if works
//			} catch (IOException e) {
//				System.err.println("Error writing to file!");
//				System.exit(0);
//			}
		} while(s.hasNext()); // until EOF
		for (Map.Entry<Token, Integer> entry : p.tokens.entrySet()) {
			try {
				System.out.println("writing to file... " + "[" + entry.toString() + "]");
				writer.write(entry.toString());
			} catch (IOException e) {
				System.err.println("Error writing to file!");
				System.exit(0);
			}
		}
		try {
			writer.close();
		} catch (IOException e1) {
			System.err.println("Error closing writer!");
			System.exit(0);
		}
	}
}
