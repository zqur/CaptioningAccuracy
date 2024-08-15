//Zain Qureshi
//CMSC 331 - SP22
//FileIO class for Captioning project
import java.io.*;
import java.util.*;

public class FileIO {
	
	private Scanner scan;
	private Vector<Word> wordList;
	
	//default constructor for FileIO obj
	public FileIO() {
		wordList = new Vector<Word>();
		scan = null;
	}
	//parameters - String fileName
	//Attempts to open text file, returns True if successful
	public boolean openTextFile(String fileName) {
		boolean fileOpen = false;

		try {
			//updating Scanner for the file
			scan = new Scanner(new FileReader(fileName));
			fileOpen = true;
		}catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
			System.exit(0);
		}
		return fileOpen;
	}
	//parameters - String searchWord
	//Searches Vector for a match of searchWord, returns True if found.
	public boolean wordExists(String searchWord) {
		boolean exists = false;
		
		for(int i = 0; i < wordList.size(); i++) {

			if (wordList.elementAt(i).getValue().equalsIgnoreCase(searchWord)) {
				return true;
			}
		}
		return exists;
	}

	//parameters - String fileName
	//scans lines from the open text file and formats the tokenized string values
	public Vector<Word> readTextFile(String fileName){
		String currLine;
		String tempString = "";
		
		if (openTextFile(fileName)) {
			//Continue scanning until last line of file
			while (scan.hasNextLine()) {
				//update currLine to the current line being read by scanner and then tokenize it 
				currLine = scan.nextLine();
				StringTokenizer lineTokens = new StringTokenizer(currLine);
				
				//continue until there are no more tokens for the current line
				while (lineTokens.hasMoreTokens()) {
					
					//cast the current token to String so we can work with it
					String currValue = (String)lineTokens.nextToken();
					currValue.toLowerCase();
					
					//spaces, numbers and values with leading hyphen (-->) are invaluable and filtered out here
					if (currValue != " " && currValue.charAt(0) != '-' && !Character.isDigit(currValue.charAt(0))){
						
						//StringBuilder object lets us further evaluate current string
						StringBuilder newString = new StringBuilder(currValue);
						
						for(int i = 0; i < newString.length(); i++) {
							//filter out , ? . [ ] and deletes them from current string
							if (newString.charAt(i) == ',' || newString.charAt(i) == '?' || newString.charAt(i) == '.' || newString.charAt(i) == '[' || newString.charAt(i) == ']') {
								newString.deleteCharAt(i);
							}
						}
						//extra loop so we can delete trailing comma at end of current string
						//unsure why but the above loop wouldn't delete commas if they proceeded a period
						for(int i = 0; i < newString.length(); i++) {
							if (newString.charAt(i) == ',') {
								newString.deleteCharAt(i);
							}
						}
						//convert new string builder to a String and make it lowercase
						tempString = newString.toString().toLowerCase();
						
						//check if word already exists in Vector
						if (!wordExists(tempString)) {
							//if not, add newly initiated Word object with our new String value
							wordList.add(new Word(tempString));

							//need to find the index of the new word we added so we can later update the count
							int indexOfWord = 0;
							for (int i = 0; i < wordList.size(); i++) {
								if(tempString.equals(wordList.elementAt(i).getValue())) {
									indexOfWord = i;
								}
							}
							//update count for word respective to what file it was scanned in from
							if (fileName == "PT1.txt") {
								wordList.elementAt(indexOfWord).setCountPT(wordList.elementAt(indexOfWord).getCountPT() + 1);
							}else if (fileName == "YT1.txt") {
								wordList.elementAt(indexOfWord).setCountYT(wordList.elementAt(indexOfWord).getCountYT() + 1);
							}
							//collections will sort our word objects because we have the overriden compareTo method
							Collections.sort(wordList);
						}else {
							
							//word already exists in vector so we find index
							int indexOfWord = 0;
							
							for (int i = 0; i < wordList.size(); i++) {
								if(tempString.equals(wordList.elementAt(i).getValue())) {
									indexOfWord = i;
								}
							}
							//update the count respective to what file it was scanned in from
							if (fileName == "PT1.txt") {
								wordList.elementAt(indexOfWord).setCountPT(wordList.elementAt(indexOfWord).getCountPT() + 1);
							}else if (fileName == "YT1.txt") {
								wordList.elementAt(indexOfWord).setCountYT(wordList.elementAt(indexOfWord).getCountYT() + 1);
							}
							
						}
					}
				}
			}
		}
		//close the scanner and returns Vector containing all Word objects scanned
		scan.close();
		return wordList;
	}
	
	//parameters - none
	//outputs all words in Vector to txt file for debugging
	public void writeData() {
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter("debug1.txt");
			for (int i = 0; i < wordList.size(); i++) {
				outfile.write(wordList.elementAt(i).getValue() + "\n");
			}				
			
			outfile.close();
		}catch (Exception e) {
			System.out.println("File was not created");
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}

}
