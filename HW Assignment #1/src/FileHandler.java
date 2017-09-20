import java.io.File;
import java.io.*;
import java.util.Scanner;

public class FileHandler {
	//DONT NEED THIS 

	//Private Variables
	File file;
	Scanner reader;
	
	//Constructor		??Poor programming to have a beefy constructor like this?
	public FileHandler(String fname) {
		//Constructor attempts to open the given text file
		Scanner reader = createReader(fname);
		reader.close();
	}
	
	//Helped method
	public Scanner createReader(String fname) {  //Does this need to be void?
		File file = new File(fname);
		Scanner input = null;

		try {
			input = new Scanner(file);	//Access Scanner functions of FileHandler obj by doing x.input.whatever() ??
		} catch (FileNotFoundException ex) {
			System.out.println("error u goob, no can do bc of " + ex.toString());
			//return null Substitute null with program close bc if there ain't a valid file then just give up 
			return null;
		}
	}
	
	
	
	
	/**
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
	*/
}
