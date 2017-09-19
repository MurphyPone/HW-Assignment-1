import java.io.File;
import java.io.*;
import java.util.Scanner;

public class FileHandler {
	// Method that returWns scanner of words.txt
	// Method that returns a PrintWriter of ramblex dictionary.java

	// NEED TO MAKE A FORMATTIG METHOD WHICH WILL BE SENT INTO THE RAMBLEX FILE

	public Scanner openWords(String fname) {
		File file = new File(fname);
		Scanner input = null;

		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("error u goob, no can do bc of " + ex.toString());
			return null;
		}
		
		return input;//
	}
	
	public FileHandler(String myPath) {
		path = myPath ; //..means go up a dir
		file = new File(path);
	}

	public PrintWriter openDictionary(String fname) {
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

	public void writeEntry(Scanner input, PrintWriter output) {
		while (input.hasNextLine()) {
			String word = input.nextLine().toUpperCase(); // Smart Programming!

			if (word.length() >= 3 && word.length() <= 5) {
				output.println("\t\t\"" + word + "\",");
			}
		}
	}
	
	public void writeEntryHeader(PrintWriter output) {
		output.println("public class RamblexDictionary { \n\tprivate String[] words = {");
	}
	public void writeEntryFooter(PrintWriter output) {
		output.println("\t};\n}");
	}
}
