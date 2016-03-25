import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
		
		// open file
		File file = new File(inputFileName[0] + ".token");
		try {
			if(!file.createNewFile()) {
			System.err.println("File already exist!");
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
		
		// find tokens and save them to hashmap
		do {
			t = p.yylex(s);
			if(t == null) continue;
			System.out.println("writing to file... " + "[" + t.toString() + "]");
			try {
				writer.write(t.toString());
				writer.write("\n");
			} catch (IOException e) {
				System.err.println("Error writing to file!");
				System.exit(0);
			}
		} while(s.hasNext()); // until EOF

		// close writer
		try {
			writer.close();
		} catch (IOException e1) {
			System.err.println("Error closing writer!");
			System.exit(0);
		}
	}
}
