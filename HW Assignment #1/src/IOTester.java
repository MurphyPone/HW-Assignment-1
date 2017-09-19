import java.io.File;
import java.io.*;
import java.util.Scanner;

public class IOTester {
	// Method that returns scanner of words.txt
	// Method that returns a PrintWriter of ramblex dictionary.java

	// NEED TO MAKE A FORMATTIG METHOD WHICH WILL BE SENT INTO THE RAMBLEX FILE

	public static Scanner openWords(String fname) {
		File file = new File(fname);
		Scanner input = null;

		try {
			input = new Scanner(file);
			//CHECK FOR BALANCED BRACES
		} catch (FileNotFoundException ex) {
			System.out.println("error u goob, no can do bc of " + ex.toString());
			//PROVIDE THIS MESSAGE TO THE OUTPUT FILE: "Part 1: Unable to Open File"
			return null;
		}
		
		return input;//
	}

	public static PrintWriter openDictionary(String fname) {
		File file = new File(fname);
		PrintWriter output = null;

		try {
			output = new PrintWriter(file);
		} catch (FileNotFoundException ex) {
			System.out.println("error u goob, no can do bc of " + ex.toString());
			return null;
		}
		return output;
	}

	public static void writeEntry(Scanner input, PrintWriter output) {
		//DISPLAY A BLANK LIN IN THE OUTPUT FILE
		while (input.hasNextLine()) {
			String word = input.nextLine().toUpperCase(); // Smart Programming!

			if (word.length() >= 3 && word.length() <= 5) {
				output.println("\t\t\"" + word + "\",");
			}
		}
	}
	
	public static void writeEntryHeader(PrintWriter output) {
		output.println("public class RamblexDictionary { \n\tprivate String[] words = {");
	}
	public static void writeEntryFooter(PrintWriter output) {
		output.println("\t};\n}");
	}
	
	
	//COMPARE TWO FILES
	
	//CREATE ADLIB FROM 3RD ARGUMENT

	public static void main(String[] args) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		if (args.length < 2) { // Need two args, input and output files
			System.out.println("No file given, not enough args");
			System.exit(1);
		}
		Scanner in = openWords(args[0]); // First argument passed in via command line
		if (in == null)
			System.exit(1);
		;

		PrintWriter out = openDictionary(args[1]);

		writeEntryHeader(out);
		writeEntry(in, out);
		writeEntryFooter(out);
		
		in.close();
		out.close();
=======
		System.out.println("I am a nothingburger");
>>>>>>> branch 'master' of https://github.com/MurphyPone/HW-Assignment-1
	}
}
