//Zain Qureshi
//CMSC 331 - SP22
//Word class for Captioning project
public class Word implements Comparable<Word>{
	private String value;
	private int countPT;
	private int countYT;
	
	//default constructor for Word obj
	public Word() {
		value = "";
		countPT = 0;
		countYT = 0;
	}
	
	//parameters - String word
	//Overloaded constructor with a String value being passed in
	public Word(String word) {
		value = word;
		countPT = 0;
		countYT = 0;
	}
	//parameters - String word, int countPT, int countPT
	//Overloaded constructor with String value passed in, as well as counts for that string in each respective file 
	public Word(String word, int count1, int count2) {
		value = word;
		countPT = count1;
		countYT = count2;
	}
	//parameters - String word
	//sets the String value of the Word obj
	public void setValue(String word) {
		value = word;
	}
	//parameters - none
	//returns the String value for Word obj
	public String getValue() {
		return value;
	}
	//parameters - int newCount
	//updates count of Word for Panapto caption file
	public void setCountPT(int newCount) {
		countPT = newCount;
	}
	//parameters - none
	//returns count of Word for Panapto caption file
	public int getCountPT() {
		return countPT;
	}
	//parameters - int newCount
	//updates count of Word for YouTube caption file
	public void setCountYT(int newCount) {
		countYT = newCount;
	}
	//parameters - none
	//returns count of Word for YouTube caption file
	public int getCountYT() {
		return countYT;
	}
	//parameters - Word x
	//compares current Word obj string value to another Word obj string value
	//overrides the Collections compareTo method
	public int compareTo(Word x) {
		return this.value.compareTo(x.getValue());
	}
	
	//parameters - none
	//toString for Word obj prints Word obj data members in correct format
	public String toString() {
		return "----------" + "\n" + value + "\n" + countPT + "\n" + countYT + "\n";
	}
	//parameters - String word
	//returns T/F if passed string is identical to Word obj value
	public boolean equals(String word) {
		return (value == word);
	}
	
}
