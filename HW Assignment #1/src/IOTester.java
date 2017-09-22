import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOTester {
	//TODO Method that returns scanner of first arg 									√√√√
	//TODO Method that returns a PrintWriter second arg								√√√√
	//TODO Method that Compares two files //Works but probably not air tight
	//TODO create an adlib
	
	//fields for bracket checking
	public static int numBraces = 0;
	
	//For reading
	public static Scanner openWords(String fname, int fileNum) {	
		File file = new File(fname);
		Scanner input = null;

		try {							//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, no can do bc of " + ex.toString());			
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
			System.out.println("Failed to be able to write to " + fname + "bc of " + ex.toString());
			return null;
		}
		return output;
	}
	
	
	//COMPARE TWO FILES
	public static void compareRead(String one, String two, PrintWriter output) {
		Scanner scanOne = openWords(one, 1);
		Scanner scanTwo = openWords(two, 2);
		String strOne;
		String strTwo;
		
		//TODO truncate this to use openWords
		if(scanOne.hasNext() || scanTwo.hasNext() ) {	
			while (scanOne.hasNext()  && scanTwo.hasNext() ) {	//If one File>Scanner has stuff in it, try to compare to the other
					strOne = scanOne.next();	//Creates strings from scanners from files passed in as arguments
					strTwo = scanTwo.next();
					
					for(int i = 0; i < strOne.length(); i++) {  
			            if(strOne.charAt(i) != strTwo.charAt(i) ) {	//If every character is not identical, then the files not
			            		output.print("Files Not Identical\n");
			            		scanOne.close();
			            		scanTwo.close();
			            		return;
			            }
					}
				}
		}
			
		output.print("Files Identical \n");	//TODO \n isn't working here?
		scanOne.close();
		scanTwo.close();
	}
	
	
	//Adlib reader
	public static ArrayList<String> prompt(Scanner input, Scanner kb, PrintWriter output) {
		ArrayList<String> responses = new ArrayList<String>();
		
		//Grab part of speech
		while(input.hasNextLine() ) {	//Look for the next line
			String curLine = input.nextLine();
			String partOfSpeech;
			String response;
			
			for(int i = 0; i < curLine.length(); i++) {
				if(curLine.charAt(i) == '<') {
					partOfSpeech = curLine.substring(curLine.indexOf("<") + 1, curLine.indexOf(">"));
					curLine = curLine.substring( curLine.indexOf(">")+1); //cuts off everything before the first > hopefully
					
					System.out.println("\nGive a " + partOfSpeech);
					response = kb.next();
					
					responses.add(response);	//Inserts response into arraylist of responses
				}
			}
		}
		//Prints contents
		for(String r : responses)
			System.out.println("PartOfSpeech : " + r);
		
		return responses;
	}
	
	//Adlib Writer
	public static void writeAdlib(ArrayList<String> words, Scanner input, PrintWriter output) {
			//Writes contents
			for(String r : words)
				while(input.hasNextLine() ) {	//Look for the next line
					String curLine = input.nextLine();
					
					while(curLine.indexOf('<') != -1 && curLine.indexOf('>') != -1) { //Need this for something
						curLine = curLine.substring(0,curLine.indexOf('<')) + r + curLine.substring(curLine.indexOf('>') +1 );
					}
					output.println(curLine);
				}
		}

	/*Args in should be as follows:
	 * arg0.java
	 * output.txt			
	 * file1.txt		//for testing compare
	 * file2.txt		//for testing compare
	 * adlibIn.txt
	 * 
	 *
	 *///TODO clean this up, needs to only take 3 arguments
	public static void main(String[] args) {
		if (args.length < 4) { // Need two args, input and output files
			System.out.println("No file given, not enough args");
			System.exit(1);
		}
		
		for(int i = 0; i < args.length; i++) 
			System.out.println("arg " + i + " = " + args[i]);
	
	//PART ONE----------------------------------------------------------------
		//Creates PrintWriter to output to second file 
		PrintWriter out = openDictionary(args[1]);
		//Prints a blank line or an error message
		
		//Reads in first File
		Scanner in = openWords(args[0], 1); // First argument passed in via command line
		
		//Checks if braces are balanced and modifies 2nd arg file accordingly
		if(in != null) {
			if(braceChecker(in))	//Can only call braceChecker once per Scanner bc you can't reset the pointer.
				out.print("Braces Balanced\n");
			else 
				out.print("Braces Not Balanced\n");
			in.close();
		}
		
	//PART TWO----------------------------------------------------------------
		out.print("\n");

	//PART THREE----------------------------------------------------------------
		//Creates Scanners for 3rd and 4th arguments
		compareRead(args[2], args[3], out);
		
	//PART FOUR ----------------------------------------------------------------
		out.print("\n");

	//PART THREE----------------------------------------------------------------
		Scanner keyboard = new Scanner(System.in);
				
		Scanner adlib = openWords(args[4], 3);
		
		ArrayList<String> words = prompt(adlib, keyboard, out);
		
		//Need to recreate adlib to re-go through each line
		Scanner adlibIN = openWords(args[4], 3);

		//TODO does adlib need to be reset in order to write?
		writeAdlib(words, adlibIN, out);
		
		
	//Housekeeping
		out.close();
		adlib.close();
		adlibIN.close();
		keyboard.close();
	}
}
