import java.io.File;
import java.io.*;
import java.util.Scanner;

public class IOTester {
	//TODO Method that returns scanner of first arg 									√√√√
	//TODO Method that returns a PrintWriter second arg								√√√√
	//TODO Method that Compares two files 
	
	//fields for bracket checking
	public static int numBraces = 0;
	
	//For reading
	public static Scanner openWords(String fname, PrintWriter output) {
		File file = new File(fname);
		Scanner input = null;

		try {							//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
			braceChecker(input);
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, no can do bc of " + ex.toString());
			output.print("Part 1: Unable to Open File \n\n"); //Skips a line and adds the blank file needed for step 2.
			
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
	
	
	//COMPARE TWO FILES
	public static void compareRead(String one, String two, PrintWriter output) {
		File first = new File(one);
		File second = new File(two);
		
		Scanner scanOne;
		Scanner scanTwo;
		
		String oneStr;	//Declares placeholder strings for each File>Scanner
		String twoStr;
		
		try {
			 scanOne = new Scanner(first); 
			 scanTwo = new Scanner(second);
		 } catch(FileNotFoundException ex) { 
			 output.print("Part 2 : unable to open file");
			 return;	
		 }
			
		while (scanOne.hasNext() ) {	//If one File>Scanner has stuff in it, try to compare to the other
			oneStr = scanOne.next();	//Creates strings from scanners from files passed in as arguments
			twoStr = scanTwo.next();
			
			for(int i = 0; i < oneStr.length(); i++) {  
	            if(oneStr.charAt(i) != twoStr.charAt(i) ) {	//If every character is not identical, then the files not
	            		output.print("Files Not Identical");
	            		scanOne.close();
	            		scanTwo.close();
	            		return;
	            }
			}
		}
		
		output.print("Files Identical");
		scanOne.close();
		scanTwo.close();
	}
	
	//Adlib reader
	public static void prompt(Scanner input) {
		//ArrayList responses = new ArrayList();
		
		//while(input.hasNext() )
			//find tags
			//store tags in var partOfSpeech;
			//System.out.println("Give a " + partOfSpeech);
			//response = kb.next()
			//responses.add(response);
	}
	
	/*Args in should be as follows:
	 * any.txt  	<-- This should be a .java 
	 * output.txt			
	 * file1.txt
	 * file2.txt
	 * compareResult.txt
	 * adlibIn.txt
	 * adlibOut.txt
	 */
	public static void main(String[] args) {
		if (args.length < 2) { // Need two args, input and output files
			System.out.println("No file given, not enough args");
			System.exit(1);
		}
	
	//PART ONE----------------------------------------------------------------
		//Creates PrintWriter to output to second file 
		PrintWriter out = openDictionary(args[1]);
		//Prints a blank line or an error message
		
		//Reads in first File
		Scanner in = openWords(args[0], out); // First argument passed in via command line
		
		//Checks if braces are balanced and modifies 2nd arg file accordingly
		if(in != null) {
			System.out.println("Braces Balanced : " + braceChecker(in) );
			in.reset();
			
			if(braceChecker(in))
				out.print("Braces Balanced\n");
			else 
				out.print("Braces Not Balanced\n");
			
			in.close();
		}
		
	//PART TWO----------------------------------------------------------------
		
		//Creates PrintWriter(s)? for the compare results for the 5th argument 
		PrintWriter compResult = openDictionary(args[4]);
		
		
		//TODO can I just reuse my openWords method? and then do compare on two scanners? 
				//Or should comp make a openWords-type method without the braces check
		//Creates Scanners for 3rd and 4th arguments
		//Scanner firstComp = openWords(args[2], compResult);	//TODO Can these share a Printwriter?  
		//Scanner secondComp = openWords(args[3], null);	//Or can I pass them null for the PrintWriter bc I don't want to checkBraces...

		compareRead(args[2], args[3], compResult);
		
		
	//PART THREE----------------------------------------------------------------
		Scanner adlib = openWords(args[2], out);
		prompt(adlib);
		
		
		out.close();
	}
}
