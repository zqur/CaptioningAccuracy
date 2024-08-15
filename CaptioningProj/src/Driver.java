//Zain Qureshi
//CMSC 331 - SP22
//Driver class for Captioning project
import java.util.*;

public class Driver {
	
	private Vector<Word> wordList;
	private FileIO workingFile;
	Hashing hashTable;
	
	//parameters - none
	//uses FileIO to read data from PT and YT files
	void readFiles() {
		workingFile = new FileIO();
		wordList = workingFile.readTextFile("PT1.txt");
		wordList = workingFile.readTextFile("YT1.txt");
		hashTable = new Hashing(wordList);
	}
	//parameters - none
	//uses writeData function of FileIO class to create debug txt file
	void debug() {
		workingFile.writeData();
	}
	//parameters - none
	//uses writeListEqual function of Hashing class to create debug txt file
	void createListEqual() {
		hashTable.writeListEqual();
	}
	//parameters - none
	//uses writeListDifference function of Hashing class to create debug txt file
	void createListDiff() {
		hashTable.writeListDifference();
	}
	//parameters - none
	//uses writeEqual function of Hashing class to create debug txt file
	void createEqual() {
		hashTable.writeEqual();
	}
	//parameters - none
	//uses writeDifference function of Hashing class to create debug txt file
	void createDiff() {
		hashTable.writeDifference();
	}
}
