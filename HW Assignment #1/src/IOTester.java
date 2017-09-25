/**
 * Class Description: 	This class is intended to interpret both user data and data gathered 
 * 						from files passed in through the command line. Specific tasks include:
 * 						 	1. Brace checking for .java files
 * 						  	2. File comparison
 * 						  	3. Replacing keywords from a given file with words provided 
 * 								by the user
 * @author MurphyP1
 * @date 9/24/17
 */

import java.io.*;
import java.util.*;

public class IOTester {	
	
	//Field for bracket checking
	public static int numBraces = 0;
	
	/**
	 * Returns a Scanner from the given filename String depending on whether of not 
	 * that file exists
	 * 
	 * @author MurphyP1
	 * @date 9/24/17
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
		} catch (FileNotFoundException ex) {	//TODO Else, print to console the reason why and create that file with the error message inside
			System.out.println("error u goob, cannot open " + fname);		
			output.print("Part " + fileNum + ": Unable to Open File\n");
			return null;
		}
		return input;
	}
	
	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: braceChecker
	 * 
	 * @param: input
	 * @return: boolean
	 */
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
	
	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: openDictionary
	 * 
	 * @param: fname
	 * @return: PrintWriter
	 * @throws FileNotFoundException 
	 */
	//For writing
	public static PrintWriter openDictionary(String fname) throws NullPointerException {
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
	
	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: compareRead
	 * 
	 * @param: one, two, output
	 * @return: void
	 */
	//COMPARE TWO FILES
	public static void compareRead(String one, String two, PrintWriter output) {
		Scanner scanOne = openWords(one, output, 2);	//These share fileNum 2 because this is the second part of the program
		Scanner scanTwo = openWords(two, output, 2);
		String strOne;
		String strTwo;
												//They have to exist too
		if( (scanOne != null && scanTwo != null) && (scanOne.hasNext() && scanTwo.hasNext()) ) {	
			while (scanOne.hasNext()  && scanTwo.hasNext() ) {	//If one File>Scanner has stuff in it, try to compare to the other
				strOne = scanOne.next();	//Creates strings from scanners from files passed in as arguments
				strTwo = scanTwo.next();
				
				for(int i = 0; i < strOne.length(); i++) {  
					try {
						if(strOne.charAt(i) != strTwo.charAt(i) ) {	//If every character is not identical, then the files not
							output.print("Files Not Identical\n");
		            		scanOne.close();
		            		scanTwo.close();
		            		return;
			            }
					} catch(StringIndexOutOfBoundsException e) {
						output.print("Files Not Identical\n");
						System.out.println(e + "Therefore they ain't the same");
		            		scanOne.close();
		            		scanTwo.close();
		            		return;
					}
				}
			}
			//HAve to be in here bc this is the umbrella of existence
			scanOne.close();
			scanTwo.close();
			//This is not the best way to deal with this
		} else { //Both files not exist and have next then
			return; //Breakout bc openwords already printed the "Unable nonsense
		}
			output.print("Files Identical \n");	//Now this isn't printing
			//If it makes it all they way through the if, then it should print
			//Otherwise if one doesn't exist the only output should be "Part 2: Unable to Open File";
	}
	
	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: prompt
	 * 
	 * @param: input, kb, output
	 * @return: ArrayList<String>
	 */
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
					response = kb.nextLine();	//TODO -- (FIXED?) ONLY ACCEPTS 1 WORD RESPONSE
					
					responses.add(response);	//Inserts response into arraylist of responses
				}
			}
		}
		//Prints contents for testing purposes
		for(String r : responses)
			System.out.println("PartOfSpeech : " + r);
		
		return responses;
	}
	
	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: writeAdlib
	 * 
	 * @param: words, input, output
	 * @return: void
	 */
	//Adlib Writer				
	public static void writeAdlib(ArrayList<String> words, Scanner input, PrintWriter output) {
		int i = 0;	//Keeps track of current word
		//TODO check size --Don;t remember what I meant????
		//TODO CHECK IF IT EXISTS --if adlibin.txt DNE, then you need to figure that out asap.
		while(input.hasNextLine() ) {	//Look for the next line
			String curLine = input.nextLine(); 	
		
			//TODO DOESNT CHANGE TAGS WITH SPACES  --Maybe because it only 
			while(curLine.indexOf('<') != -1 && curLine.indexOf('>') != -1) { //While there is a valid tag in the line
				if(i < words.size() ) {	//Checks to see if there are more words to fill
					curLine = curLine.substring(0,curLine.indexOf('<')) + words.get(i) + curLine.substring(curLine.indexOf('>') +1 );
					i++;	 //Goes to next word in user responses
				} else { //IF there are fewer responses than tags
					curLine = curLine.substring(0,curLine.indexOf('<')) + "*NO RESPONSE GIVEN*" + curLine.substring(curLine.indexOf('>') +1 );
				}
				//output.println(curLine);
			}	
			output.println(curLine);
		}
	}

	/**
	 * @author MurphyP1
	 * date 9/24/2017
	 * @method: main
	 * 
	 * @param: args
	 * @return: void
	 */
	/*Args in should be as follows:
	 * arg0.java
	 * file1.txt		//for testing compare
	 * adlibIn.txt
	 * AdlibWords.txt 
	 * output.txt	
	 */
	public static void main(String[] args) {
		PrintWriter out; 
		
		if (args.length < 4) { // If there is no preppedWords file given
			//Creates PrintWriter to output to second file 
			out = openDictionary(args[3]);	//output.txt is 4th argument
		} else {	// If there is a 4th argument passed
			out = openDictionary(args[4]);	//output.txt is 5th argument
		}
		
		for(int i = 0; i < args.length; i++) 
			System.out.println("args[" + i + "] = " + args[i]);
	
	//PART ONE----------------------------------------------------------------
		//Reads in first File
		Scanner in = openWords(args[0], out, 1); // First argument passed in via command line
		
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
		compareRead(args[0], args[1], out);	//Modify args[X] to compare 
		
	//PART FOUR ----------------------------------------------------------------
		out.print("\n");

	//PART FIVE----------------------------------------------------------------
		//5.b
		Scanner preppedWordsFile = openWords(args[3], out, 3);
		Scanner adlib;
		
		if(preppedWordsFile != null) {
			ArrayList<String> preppedWords = new ArrayList<String>();		//Initializes arraylist
			
			while(preppedWordsFile.hasNextLine()) {
				preppedWords.add(preppedWordsFile.nextLine());	//Grabs words from file and adds them to arraylist
			}
			
			adlib = openWords(args[2], out, 3);						//initializes the input to search for tags

			writeAdlib(preppedWords, adlib, out);				//Writes to file using prepared words
			
		} else {		//IF there are no preppedWords, then get user input
			Scanner keyboard = new Scanner(System.in);
			adlib = openWords(args[2], out, 3);
		
			ArrayList<String> words = prompt(adlib, keyboard, out);	//Gets user input for tags
			
			adlib = openWords(args[2], out, 3);	//Resets Adlib scanner		
			
			writeAdlib(words, adlib, out);	//Writes to output using user input
			
			//Housekeeping
			adlib.close();
			keyboard.close();
		}
		
	//Housekeeping
		out.close();
	}
}
