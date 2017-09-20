import java.io.File;
import java.io.*;
import java.util.Scanner;

public class IOTester {
	//TODO Method that returns scanner of first arg 									√√√√
	//TODO Method that returns a PrintWriter second arg								√√√√
	//TODO Method that Compares two files 
	
	//fields for bracket checking
	public static int numBraces = 0;
	public static String argStatus; //This is poor programming
	
	//For reading
	public static Scanner openWords(String fname) {
		File file = new File(fname);
		Scanner input = null;

		try {	//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
			braceChecker(input);
			argStatus = "\n .\n";
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, no can do bc of " + ex.toString());
			/////ISSSUE HERE argStatus = "Part 1: Unable to Open File \n\n"; //Skips a line and adds the blank file needed for step 2.
			return null;
		}
		return input;
	}
		
	//Brace checker
	public static boolean braceChecker(Scanner input) {
        String temp;	//Grabs the current line of the input 
		
        //Running difference between { and }
        while(input.hasNext()) {
			temp = input.next();
			
			for(int i = 0; i < temp.length(); i++) {  
				//If numBraces ever dips below 0, then they ain't balanced!
				if(numBraces >= 0 ) { 
		            if(temp.charAt(i) == '{')
		            		numBraces ++;			//So smart, thanks Aidan
		            if(temp.charAt(i) == '}')
		        			numBraces --;
				} else
					return false;
	        }
		}

        if(numBraces != 0) 
        		return false;
        else 
	        	return true;
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
			 System.out.print("Part 2 : unable to open file");
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
		
		
		//Creates PrintWriter to output to second file --Created before in bc in writes to output.txt according to contents of first arg
		PrintWriter out = openDictionary(args[1]);
		//Prints a blank line or an error message
		out.println(argStatus);

		
		//Reads in first File
		Scanner in = openWords(args[0]); // First argument passed in via command line
		
		//Checks if braces are balanced and modifies 2nd arg file accordingly
		System.out.println("Braces Balanced : " + braceChecker(in) );
		if(braceChecker(in))
			out.print("Braces Balanced\n");
		else 
			out.print("Braces Not Balanced\n");
		
		if (in == null)	//Is this necessary (try/catch in openWords does the same?)
			System.exit(1); 

		

		in.close();
		out.close();

	}
}
