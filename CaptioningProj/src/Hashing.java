//Zain Qureshi
//CMSC 331 - SP22
//Hashing class for Captioning project
import java.util.HashMap;
import java.util.*;
import java.io.*;
public class Hashing{
	//hashmap will map int key values according to hashCode and BST values 
	private HashMap<Integer, BST> wordMapBST;
	private HashMap<Integer, Vector<Word>> wordMapVec;
	private Vector<Word> wordList;
	
	//we know that we want exactly 26 buckets, one for each letter of alphabet
	private int MAX_CAPACITY = 26;
	//by using load factor of 1 we can prevent rehashing since we know our size beforehand
	private int LOAD_FACTOR = 1;
	
	//default contructor for Hashing
	public Hashing() {
		wordMapBST = new HashMap<>(MAX_CAPACITY +1, LOAD_FACTOR);
		wordMapVec = new HashMap<>(MAX_CAPACITY +1, LOAD_FACTOR);
	}
	//parameters - Vector<Word> inWords
	//overloaded constructor
	public Hashing(Vector<Word> inWords) {
		//create hashmap and set private members
		wordMapBST = new HashMap<>(MAX_CAPACITY +1, LOAD_FACTOR);
		wordMapVec = new HashMap<>(MAX_CAPACITY +1, LOAD_FACTOR);
		wordList = inWords;
		//calls helper function to populate the hashmap
		addToBSTMap(wordList, wordMapBST);
		addToHashMap(wordList, wordMapVec);
	}
	//parameters - Vector<Word> inWords, HashMap<Integer, BST> map
	//uses previously populated Vector of Word objs to create and populate BSTs and map those in hashmap
	private void addToBSTMap(Vector<Word> inWords, HashMap<Integer, BST> map) {
		for (int i = 0; i < inWords.size(); i++) {
			//gets hashcode for first character in the current word
			int currHashCode = Character.hashCode(inWords.elementAt(i).getValue().charAt(0));
			//we use get() to check the HashMap index of the hashCode and see if tree exists already
			if (map.get(currHashCode) == null) { //tree doesn't exist
				//creates new tree and inserts Word obj
				BST newTree = new BST();
				newTree.insert(inWords.elementAt(i));
				//maps the newTree to the index of the hashCode
				map.put(currHashCode, newTree);
			}else { //tree exists
				//fetch current tree in bucket and inserts Word obj
				BST newTree = map.get(currHashCode);
				newTree.insert(inWords.elementAt(i));
				//maps the updated tree to index of hashCode
				map.put(currHashCode, newTree);
			}
		}
	}
	//parameters - Vector<Word> inwords, HashMap<Integer, Vector<Word>> map
	//uses previously populated Vector of Word objs and creates vectors to populate hash table
	private void addToHashMap(Vector<Word> inWords, HashMap<Integer, Vector<Word>> map) {
		for (int i = 0; i < inWords.size(); i++) {
			int currHashCode = Character.hashCode(inWords.elementAt(i).getValue().charAt(0));
			//if returns null then there is no existing vector in bucket
			if (map.get(currHashCode) == null) {
				//create new vector and add word
				Vector<Word> newHashVector = new Vector<Word>();
				newHashVector.add(inWords.elementAt(i));
				//maps new vector to index of hashcode
				map.put(currHashCode, newHashVector);
			}else { //vector exists
				Vector<Word> currHashVector = map.get(currHashCode);
				currHashVector.add(inWords.elementAt(i));
				//map the updated vector to index of hashcode
				map.put(currHashCode, currHashVector);
			}
		}
	}
	
	//parameters - none
	//finds words with equal counts for PT and YT file in Vector implementation of Hash and writes them to debug file
	public void writeListEqual() {
		PrintWriter outfile;
		
		try {
			outfile = new PrintWriter("resultsListEqual.txt");
			Vector<Word> currVector;
			//found this gnarly way of indexing a hashmap online
			//site: https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
			//for each Vector in the hashmap
			for (Map.Entry<Integer, Vector<Word>> mapElement : wordMapVec.entrySet()) {
				currVector = mapElement.getValue();
				for (int i = 0; i < currVector.size(); i++) {
					if (currVector.elementAt(i).getCountPT() == currVector.elementAt(i).getCountYT()) {
						outfile.write(currVector.elementAt(i).getValue() + "\t\t" + currVector.elementAt(i).getCountPT() + "\n");
					}
				}
			}
			//close file to save
			outfile.close();
		}catch (Exception e) {
			System.out.println("File was not found");
			e.printStackTrace();
			System.exit(0);
		}
	}
	//parameters - none
	//finds words with difference in count for PT and YT file in Vector implementation of Hash and writes them to debug file
	public void writeListDifference() {
		PrintWriter outfile;
		
		try {
			outfile = new PrintWriter("resultsListDiff.txt");
			Vector<Word> currVector;
			//for each vector in the hashmap
			for (Map.Entry<Integer, Vector<Word>> mapElement : wordMapVec.entrySet()) {
				currVector = mapElement.getValue();
				for (int i = 0; i < currVector.size(); i++) {
					//check if count is different
					if (currVector.elementAt(i).getCountPT() != currVector.elementAt(i).getCountYT()) {
						//if countPT is greater
						if (currVector.elementAt(i).getCountPT() > currVector.elementAt(i).getCountYT()) {
							//check if lower count is zero (for formatting)
							if (currVector.elementAt(i).getCountYT() == 0) {
								outfile.write(currVector.elementAt(i).getValue() + "\t\t" + "+" + currVector.elementAt(i).getCountPT() + " PT - ZERO" + "\n");
							//else we print the difference
							}else {
								outfile.write(currVector.elementAt(i).getValue() + "\t\t" + "+" +(currVector.elementAt(i).getCountPT() - wordList.elementAt(i).getCountYT())+ " PT" + "\n");

							}
						//countYT is greater	
						}else if(currVector.elementAt(i).getCountPT() < currVector.elementAt(i).getCountYT()) {
							if (currVector.elementAt(i).getCountPT() == 0) {
								outfile.write(currVector.elementAt(i).getValue() + "\t\t" + "+" + currVector.elementAt(i).getCountYT()+ " YT - ZERO" + "\n");
							}else {
								outfile.write(currVector.elementAt(i).getValue() + "\t\t" + "+" +(currVector.elementAt(i).getCountYT() - wordList.elementAt(i).getCountPT())+ " YT" + "\n");

							}
						}
					}
				}
			}
			outfile.close();
		}catch (Exception e) {
			System.out.println("File was not created");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//parameters - none
	//finds words with equal counts using BST implementation of hash and formats them to file
	public void writeEqual() {
		PrintWriter outfile;
		
		try {
			outfile = new PrintWriter("resultsEqual.txt");
			BST currTree;
			//for each BST in our hashmap
			for (Map.Entry<Integer, BST> mapElement : wordMapBST.entrySet()) {
				currTree = mapElement.getValue();
				//calls helper function in BST to use inOrder traversal and write to our file
				currTree.inOrderWriteEquals(outfile, currTree.root);
			}
			outfile.close();
		}catch (Exception e) {
			System.out.println("File was not created");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//parameters - none
	//finds words with difference in PT and YT counts in BSTs in hashmap and formats them to file
	public void writeDifference() {
		PrintWriter outfile;
		
		try {
			outfile = new PrintWriter("resultsDiff.txt");
			BST currTree;
			//for each BST in our hashmap
			for (Map.Entry<Integer, BST> mapElement : wordMapBST.entrySet()) {
				currTree = mapElement.getValue();
				//calls helper function in BST to use inOrder traversal and write to our file
				currTree.inOrderWriteDiff(outfile, currTree.root);
			}
			outfile.close();

		}catch (Exception e) {
			System.out.println("File was not created");
			e.printStackTrace();
			System.exit(0);
		}
	}
}


