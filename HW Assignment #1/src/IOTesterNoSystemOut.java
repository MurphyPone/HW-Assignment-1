/**
 * Class Description: 	This class is intended to interpret both user data and data gathered 
 * 						from files passed in through the command line. Specific tasks include:
 * 						 	1. Brace checking for .java files
 * 						  	2. File comparison
 * 						  	3. Replacing keywords from a given file with words provided 
 * 								by the user
 * @author MurphyP1
 * @date 9/26/17
 */

import java.io.*;
import java.util.*;

public class IOTesterNoSystemOut {	
	
	public static int numBraces = 0; //Field for brace checking
	
	/**
	 * Returns a Scanner from the given filename String depending on whether of not 
	 * that file exists
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method openWords
	 * 
	 * @param fname the name of the file to open with a Scanner 
	 * @param fileNum a number which helps identify what part of the program an error occurs in
	 * 
	 * @return a Scanner to grab data from the files passed in via the command line
	 */
	//For reading
	public static Scanner openWords(String fname, PrintWriter output, int fileNum) {	
		File file = new File(fname);
		Scanner input = null;

		try {							//Check to see if the requested input file exists in the given directory
			input = new Scanner(file);	//If so, create a new Scanner to grab the data
		} catch (FileNotFoundException ex) {	//Else, print to console the reason why and output the reason why & part of program getting stuck
			output.print("Part " + fileNum + ": Unable to Open File\n");
			return null;
		}
		return input;
	}
	
	/**
	 * Returns a boolean based on the syntactical validity of the order and number of braces 
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method braceChecker
	 * 
	 * @param input the Scanner whose braces are checked
	 * 
	 * @return true of the braces are valid according Java syntax, false otherwise
	 */
	//Brace checker
	public static boolean braceChecker(Scanner input) {
        String temp;	//Grabs the current line of the input 
        //Running difference between { and }
        while(input.hasNext()) {	//checks if input == null when called
			temp = input.next();
			for(int i = 0; i < temp.length(); i++) {	//Iterates through the current line of input
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
	
	/**
	 * Returns a PrintWriter object to write to the output file
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method writeWords
	 * 
	 * @param fname a String to create a file from
	 * 
	 * @return a PrintWriter if the file exists and can be opened, otherwise returns null 
	 */
	//For writing
	public static PrintWriter writeWords(String fname)  {
		File file = new File(fname);
		PrintWriter output = null;

		try {	//Check to see if the requested file exists/can be opened
			output = new PrintWriter(file);	//If so, create a new PrintWriter to modify the contents of the file
		} catch (FileNotFoundException ex) {	//Should not be necessary to include some error output to file bc the PrintWriter should not get created if the file does not exist
			return null;
		}
		return output;
	}
	
	/**
	 * Analyzes the differences, if any, between two files and writes the result to the output file
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method compare
	 * 
	 * @param one the name of the first file to be created for comparison
	 * @param two the name of the second file to be created for comparison
	 * @param output the PrintWriter object to write the results
	 * 
	 * @return void
	 */
	//COMPARE TWO FILES
	public static void compare(String one, String two, PrintWriter output) {
		Scanner scanOne = openWords(one, output, 2);	//These share fileNum 2 because this is the second part of the program
		Scanner scanTwo = openWords(two, output, 2);
		String strOne;
		String strTwo;
			
		//If they both exist and have nextLines 
		if(scanOne != null && scanTwo != null) {
			while (scanOne.hasNext()  && scanTwo.hasNext() ) {	//If both File>Scanners have stuff in them, try to compare to the other
				strOne = scanOne.next();	//Creates strings from Scanners from Files from Strings passed in as arguments
				strTwo = scanTwo.next();
				
				for(int i = 0; i < strOne.length(); i++) {//TODO TEST HERE IF FILE 2 IS LONGER THAN FILE1 WHAT HAPPENS   
					try {
						if(strOne.charAt(i) != strTwo.charAt(i) ) {	//If every character is not identical, then output not false
							output.print("Files Not Identical\n");
		            		scanOne.close();
		            		scanTwo.close();
		            		return;
			            }
					} catch(StringIndexOutOfBoundsException e) {//Should account for the error above?!!!??
						output.print("Files Not Identical\n");
	            		scanOne.close();
	            		scanTwo.close();
	            		return;
					}
				}
			} 
			if(scanOne.hasNext() || scanTwo.hasNext()) {
				output.print("Files Not Identical\n");
			} else {
				output.print("Files Identical \n");
			}
			
			//Scanners must close within this brace as this is the umbrella of existence.
			scanOne.close();
			scanTwo.close();
			
		} else { //Both files not exist and have next then --Instead, check each case
			//TODO Cannot close null, may cause errors
			if(scanOne != null)
				scanOne.close();
			if(scanTwo != null)
				scanTwo.close();
			return; 
		}
	}
	
	/**
	 * Gets the the user's input if a file of words is not supplied
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method prompt
	 * 
	 * @param input the Scanner which searches for tags, prompting user input
	 * @param kb the Scanner which gets user input from the keyboard
	 * @param output the PrintWriter object to write the results
	 * 
	 * @return an ArrayList containing strings to replace the words within the tags of the input file
	 */
	//Adlib reader
	public static ArrayList<String> prompt(Scanner input, Scanner kb, PrintWriter output) {
		ArrayList<String> responses = new ArrayList<String>();
		
		//Grab part of speech
		while(input.hasNextLine() ) {	//Look for the next line
			String curLine = input.nextLine();
			String partOfSpeech;		//Contents of the tag
			String response;			
			
			for(int i = 0; i < curLine.length(); i++) {
				if(curLine.charAt(i) == '<') {	//Doesn't account for lonely <s 
					partOfSpeech = curLine.substring(curLine.indexOf("<") + 1, curLine.indexOf(">"));	//TODO I feel like this should error out, put it doesn't...
					curLine = curLine.substring( curLine.indexOf(">")+1); //cuts off everything before the closing >
					
					System.out.println("\nGive a " + partOfSpeech);
					response = kb.nextLine();	
					
					responses.add(response);	//Inserts response into ArrayList of responses
				}
			}
		}
		return responses;
	}
	
	/**
	 * Writes the new 'adlib' to the output file replacing the tags with either the user's input, or the prepared file 
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method writeAdlib
	 * 
	 * @param words the ArrayList of words to replace the tags
	 * @param input the Scanner which finds the tags 
	 * @param output the PrintWriter object to write the results
	 * 
	 * @return void
	 */
	//Adlib Writer				
	public static void writeAdlib(ArrayList<String> words, Scanner input, PrintWriter output) {
		int i = 0;	//Keeps track of current word

		if(input != null) {
			while(input.hasNextLine() ) {	//Look for the next line
				String curLine = input.nextLine(); 	
				
				while(curLine.indexOf('<') != -1 && curLine.indexOf('>') != -1) { //While there is a valid tag in the line
					if(i < words.size() ) {	//Checks to see if there are more words to fill
						curLine = curLine.substring(0,curLine.indexOf('<')) + words.get(i) + curLine.substring(curLine.indexOf('>') +1 );
						i++;	 //Goes to next word in user responses
					} else { //IF there are fewer responses than tags
						curLine = curLine.substring(0,curLine.indexOf('<')) + "*NO RESPONSE GIVEN*" + curLine.substring(curLine.indexOf('>') +1 );
					}
				}	
				output.println(curLine);
			}
		}  
		return;
	}

	/**
	 * Executes all of the previously declared methods in a particular order
	 * 
	 * @author MurphyP1
	 * @date 9/26/17
	 * @method main
	 * 
	 * @param args the names of the files to be passed in through the command line
	 * 
	 * @return void
	 */
	
	/*Args in should in the following order:
	 * arg0.java				//For checking braces and compare
	 * file1.txt				//For testing compare
	 * adlibIn.txt			//Input file for the adlib
	 * AdlibWords.txt 		//Optional file containing prepared responses to adlibIn.txt
	 * output.txt			//The output file
	 */
	public static void main(String[] args) {
		PrintWriter out = writeWords("output.txt"); 
		Scanner preppedWordsFile = null;
	
	//PART ONE----------------------------------------------------------------
		Scanner in = openWords(args[0], out, 1); //Reads in first File
		
		//Checks if braces are balanced and modifies output.txt accordingly
		if(in != null) {
			if(braceChecker(in))	//Can only call braceChecker once per Scanner otherwise have to redefine to reset point to beginning of file
				out.print("Braces Balanced\n");
			else 
				out.print("Braces Not Balanced\n");
			in.close();
		}
		
	//PART TWO----------------------------------------------------------------
		out.print("\n");

	//PART THREE----------------------------------------------------------------
		//Compares given files 
		compare(args[0], args[1], out);	//Modify args[X] to select which files to compare 
		
	//PART FOUR ----------------------------------------------------------------
		out.print("\n");

	//PART FIVE----------------------------------------------------------------
		//5.b
		Scanner adlib;
		
		if (args.length > 3) { // If there is file of prepared words give
			preppedWordsFile = openWords(args[3], out, 3);
			
			if(preppedWordsFile != null) {	
				ArrayList<String> preppedWords = new ArrayList<String>();		//Initializes ArrayList of prepped words
				
				while(preppedWordsFile.hasNextLine()) {
					preppedWords.add(preppedWordsFile.nextLine());	//Grabs words from file and adds them to preppedWords
				}
				
				adlib = openWords(args[2], out, 3);					//initializes the input to search for tags

				writeAdlib(preppedWords, adlib, out);				//Writes to file using prepared words
			} else {
				out.close();
				System.exit(1);
			}
		} else if(args.length < 4) { 
			//IF there are no preppedWords, then get user input
			Scanner keyboard = new Scanner(System.in);	//Create Scanner from keyboard
			adlib = openWords(args[2], out, 3);			//Create a scanner from the file with tags
		
			ArrayList<String> words = prompt(adlib, keyboard, out);	//Gets user input for tags
			
			adlib = openWords(args[2], out, 3);	//Resets Adlib Scanner		
			
			writeAdlib(words, adlib, out);	//Writes to output using user input
			
			//Housekeeping
			adlib.close();
			keyboard.close();
		}
		
	//Housekeeping
		out.close();
	}
}
