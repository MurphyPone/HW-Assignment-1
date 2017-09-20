import java.io.File;
import java.io.*;
import java.util.Scanner;

public class IOTester {
	//CONSOLE DRIVEN PROGRAM, NO NEED FOR O-O nonsense	//PUSHed AFTER SCHOOL
	//TODO Method that returns scanner of words.txt
	//TODO Method that returns a PrintWriter of ramblex dictionary.java
	//TODO NEED TO MAKE A FORMATTIG METHOD WHICH WILL BE SENT INTO THE RAMBLEX FILE
	
	//fields for bracket checking
	public static int numBraces = 0;
	
	//For reading
	public static Scanner openWords(String fname) {
		File file = new File(fname);
		Scanner input = null;

		try {	//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
		
			braceChecker(input);
			
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, no can do bc of " + ex.toString());
			//PROVIDE THIS MESSAGE TO THE OUTPUT FILE: "Part 1: Unable to Open File"
			return null;
		}
		return input;
	}
		
	//Character checker
	public static boolean braceChecker(Scanner input) {
        
		while(input.hasNext()) {
			if (braceChecker(input.next()) ) { 
				
			} else {
				
			}		
	
		
		for(int i = 0; i < input.length(); i++) {  //Modified from Stack
            if(input.charAt(i) == '{')
            	numBraces ++;
        	if(input.charAt(i) == '}')
        		numBraces --;
        } 

        if(numBraces != 0) {
        	//TODO WRITE Braces Balanced
			System.out.println("Braces Balanced");
        	return false;
        } else { 
        	//TODO WRITE Braces Not Balanced

        	return true;
        }
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
		Scanner oneSc;
		Scanner twoSc;	//Creates and deletes too many Scanners
		
		String oneStr;
		String twoStr;
		
		try {
			 oneSc = new Scanner(one); 
			 twoSc = new Scanner(two);
		 } catch(FileNotFoundException ex) { 
			 //TODO Write "Part 2 : unable to open file
			 System.out.println("Part 2 : unable to open file");
			 return;
		 }
			
		while (oneSc.hasNext() ) {
			oneStr = oneSc.next();	//Creates strings from scanners from files passed in as arguments
			twoStr = twoSc.next();
			
			for(int i = 0; i < oneStr.length(); i++) {  //Modified from Stack
	            if(oneStr.charAt(i) != twoStr.charAt(i) ) {	//If every character is not identical, then the files not
	            	//TODO Write "Files Not Identical"
	            	break;
	            }
			}
		}
		//TODO Write "Files Identical"
		oneSc.close();
		twoSc.close();
	}
	
	//CREATE ADLIB FROM 3RD ARGUMENT

	public static void main(String[] args) {
		if (args.length < 2) { // Need two args, input and output files
			System.out.println("No file given, not enough args");
			System.exit(1);
		}
		
		//Reads in first File
		Scanner in = openWords(args[0]); // First argument passed in via command line
		if (in == null)	
			System.exit(1); //If it doesn't exist, then exit 

		//Creates PrintWrite to output to second file
		PrintWriter out = openDictionary(args[1]);
		

		in.close();
		out.close();

	}
}
