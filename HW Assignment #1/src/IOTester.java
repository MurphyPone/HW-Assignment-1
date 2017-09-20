import java.io.File;
import java.io.*;
import java.util.Scanner;

public class IOTester {
	//CONSOLE DRIVEN PROGRAM, NO NEED FOR O-O nonsense
	//TODO Method that returns scanner of words.txt
	//TODO Method that returns a PrintWriter of ramblex dictionary.java
	//TODO NEED TO MAKE A FORMATTIG METHOD WHICH WILL BE SENT INTO THE RAMBLEX FILE

	//For reading
	public static Scanner openWords(String fname) {
		File file = new File(fname);
		Scanner input = null;

		try {	//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
			//TODO CHECK FOR BALANCED BRACES
			
			while(input.hasNext()) {
				if (bracketChecker(input.next()) ) { 
					//TODO WRITE Brackets Balanced
				} else {
					//TODO WRITE Brackets Not Balanced
				}
			}
			
			
			//FROM StackOverflow
				//while(input.hasNext())
				//	ArrayList.add(input.next()) --Automatically uses whitespace as delimiter 
				//								-- Might cause problems bc of things like "Foo(){" no whitespace, and would not return tru 
			
				//for(i = 0; i <ArraList.length(); i++ {
				//	if(ArraList.get(i).contains("}")
				//		numOpen ++;
				//}
			
			//If(#"{" chars == #"}" chars)
			//	Write "Braces Balanced"
			//Else
			//	Write "Braces Not Balanced"
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, no can do bc of " + ex.toString());
			//PROVIDE THIS MESSAGE TO THE OUTPUT FILE: "Part 1: Unable to Open File"
			return null;
		}
		return input;
	}
		
	//Character checker
	public static boolean bracketChecker(String input) {
		int numOpen = 0;	//Make these fields
		int numClose = 0;	//won't keep track of whole file
		
        for(int i = 0; i < input.length(); i++) //Modified from Stack
            if(input.charAt(i) == '{')
            	numOpen ++;
        	if(input.charAt(i) == '}')
        		numClose ++;

        if(numOpen == numClose)
        	return true;
        else 
        	return false;
	}

	//For writing
	public static PrintWriter openDictionary(String fname) {
		File file = new File(fname);
		PrintWriter output = null;

		try {	//Check to see if the requested input file exists in the given directory
			output = new PrintWriter(file);	//If so, create a new PrintWriter to modify the contents of the file
		} catch (FileNotFoundException ex) {	//Should not be necessary to include some error output to file bc the PrintWriter should not get created if the file does not exist
			System.out.println("error u goob, no can do bc of " + ex.toString());
			return null;
		}
		return output;
	}

	public static void writeEntry(Scanner input, PrintWriter output) {
		//TODO 2. DISPLAY A BLANK LINE IN THE OUTPUT FILE
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
	public static void compare(File one, File two) {
		/* Try/Catch{ //TODO Write "Part 2 : unable to open file 
		 * 
		 * if(one.length != two.length)
		 * 	return false
		 * else 
		 * 	while(one.hasNext())
		 * 		if( one.next() != two.next() )
		 * 			//TODO Write "Files Not Identical"
		 * 		else 
		 * 			//TODO Write "Files Identical"
		 */
		
		
	}
	
	//CREATE ADLIB FROM 3RD ARGUMENT

	public static void main(String[] args) {
		if (args.length < 2) { // Need two args, input and output files
			System.out.println("No file given, not enough args");
			System.exit(1);
		}
		
		Scanner in = openWords(args[0]); // First argument passed in via command line
		if (in == null)
			System.exit(1);

		PrintWriter out = openDictionary(args[1]);

		writeEntryHeader(out);
		writeEntry(in, out);
		writeEntryFooter(out);
		
		in.close();
		out.close();

	}
}
