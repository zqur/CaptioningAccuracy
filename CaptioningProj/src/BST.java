//Zain Qureshi
//CMSC 331 - SP22
//BST class for Captioning project
import java.io.PrintWriter;

public class BST {
	//structure for Node
	public class Node {
		Word key;
		Node left;
		Node right;
		//overloaded constructor
		public Node(Word data) {
			key = data;
			left = right = null;
		}
	}
	Node root;
	//default constructor
	public BST() {
		root = null;
	}
	//used code from outside source for insert and recursiveInsert function
	//site: https://www.softwaretestinghelp.com/binary-search-tree-in-java/
	//standard implementation of BST
	public void insert(Word data) {
		root = recursiveInsert(root, data);
	}
	//helper function for insert
	private Node recursiveInsert(Node root, Word key) {
		if (root == null) {
			root = new Node(key);
			return root;
		}
		if (key.compareTo(root.key) < 0) {
			root.left = recursiveInsert(root.left, key);
		}else if (key.compareTo(root.key) > 0) {
			root.right = recursiveInsert(root.right, key);
		}
		return root;
	}
	//parameters - PrintWriter outfile, Node root
	//helper function for writeEqual()
	//inOrder traverses given tree and finds words with equal PT and YT counts to write to the outfile
	public void inOrderWriteEquals(PrintWriter outfile, Node root) {
		if (root != null) {
			inOrderWriteEquals(outfile, root.left);
			if (root.key.getCountPT() == root.key.getCountYT()) {
				outfile.write(root.key.getValue() + "\t\t" + (root.key.getCountPT()) + "\n");
			}
			inOrderWriteEquals(outfile, root.right);
		}
	}
	//parameters - PrintWriter outfile, Node root
	//helper function for writeDifference()
	//inOrder traverses given tree and finds words with difference in PT and YT counts and formats them to file
	public void inOrderWriteDiff(PrintWriter outfile, Node root) {
		if (root != null) {
			//left first
			inOrderWriteDiff(outfile, root.left);
			//formatting to file
			if (root.key.getCountPT() != root.key.getCountYT()) {
				if (root.key.getCountPT() > root.key.getCountYT()) {
					if (root.key.getCountYT() == 0) {
						outfile.write(root.key.getValue() + "\t\t" + "+" + root.key.getCountPT() + " PT - ZERO" + "\n");
					}else {
						outfile.write(root.key.getValue() + "\t\t" + "+" +(root.key.getCountPT() - root.key.getCountYT())+ " PT" + "\n");
					}
				}else if (root.key.getCountPT() < root.key.getCountYT()){
					if (root.key.getCountPT() == 0) {
						outfile.write(root.key.getValue() + "\t\t" + "+" + root.key.getCountYT() + " YT - ZERO" + "\n");
					}else {
						outfile.write(root.key.getValue() + "\t\t" + "+" +(root.key.getCountYT() - root.key.getCountPT())+ " YT" + "\n");
					}
				}
			}
			//right last
			inOrderWriteDiff(outfile, root.right);
		}
	}

}
