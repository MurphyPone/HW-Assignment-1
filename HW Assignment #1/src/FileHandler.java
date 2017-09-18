import java.io.*;

public class FileHandler {
	//Take a file name 
	private String path; 
	private File file;
	
	public FileHandler() {
		path = "/../Datawords.text"; //..means go up a dir
		file = new File(path);
	}

}

/*READING FROM A TEXT FILE
 * try {
 * 	input = new Scanner(file);  Instead of reading from kb, reads from file
 * } catch {FileNotFoundException ex) {
 * 	System.out.println("** CANNOT OPEN " + path + " ***);
 * } 
 */
