/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/17/2015
 * */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * HuffmanTree - Explanation:
 * This class will not only assemble the tree, but will also generate the Huffman encoding.
 * In addition, this class will also:
 * -create 3 arrays with codes, characters, and frequencies for access in program
 * -use a recursive preorder traversal of the HuffmanTree to create codes
 */
public class HuffmanTree 
{
	//the list that contains the HuffmanNodes in ascending order
	private HuffmanList hl;
	//a root placeholder that will be assigned once the tree is constructed
	private HuffmanNode root;
	//three arrays to hold the characters and their respective codes and frequencies
	private String[] codes;
	private char[] chars;
	private int[] freqs;
	//a basic integer that keeps track of where the recursive method should add the generated code to
	int iterator = 0;
	//I used a queue's FIFO element to generate the HuffmanTree
	Queue<HuffmanNode> q = new PriorityQueue<HuffmanNode>();
	
	//a basic constructor takes in the list of ascending nodes, sorts them again (to make sure), and creates arrays of correct size
	//then, it runs the initialize method, which constructs the HuffmanTree
	public HuffmanTree(HuffmanList hlIn)
	{
		hl = hlIn;
		Collections.sort(hl);//sort the given list in ascending order just in case
		codes = new String[hl.size()];
		chars = new char[hl.size()];
		freqs = new int[hl.size()];
		initialize();//construct tree
	}
	//The actual method that constructs the HuffmanTree
	public void initialize()
	{
		//store the initial size of the list given in a placeholder variable
		int sizeOfList = hl.size();
		//using a for loop, empty out the root of the given list and add to the priority queue
		for(int i = 0; i < sizeOfList; i++)
		{
			q.add(hl.remove(0));
		}
		//EXPLANATION OF HOW TREE IS BUILT:
		//now, use a while loop until the only element in queue is the root node itself
		while(q.size() > 1)
		{
			//...and here's how we do it. we assign the left and right nodes of the future parent as the top two
			//elements in the queue, which are the smallest 2 elements in the list, as per the queue's FIFO characteristic
			//thus, we know what the two children of the future parent will be.
			HuffmanNode left = q.poll();
            HuffmanNode right = q.poll();
            //now, we make a parent node that has the two previously made children as its own, but has a frequency
            //that is equal to the sum of the children's. Since it is an interior node, the inChar variable is set to null.
            HuffmanNode parent = new HuffmanNode(null, left.getFreq()+right.getFreq(), left, right);
            //each time this loop runs, children are taken out and one parent is put back in. regardless of the structure of 
            //the tree, the last remaining element in the priority queue will be the root of the new HuffmanTree
            q.add(parent);
		}
		//set root to the only remaining element in the queue, which is the root of the overall tree
		root = q.peek();
		//run the method to generate the codes based on the most recently generated HuffmanTree
		encode();
	}
	
	//this method simply resets the iterator to 0, ensuring that each of the 3 arrays above is rewritten based on the new Tree
	//also, it runs the recursive method generateCode(HuffmanNode,String) to generate the codes and match it to the characters
	public void encode()
	{
		//IF THERE IS ONLY ONE CHARACTER INPUTTED!!!
		if((root.left == null) && (root.right == null))
		{
			codes[0] = "0";//just put 0 automatically for a root node's huffman code
			chars[0] = root.getChar();
			freqs[0] = root.getFreq();
			return;
		}
		iterator = 0;
		generateCode(root,"");
	}
	
	//accessor methods
	public int[] getFreqs(){return freqs;}//return the frequency array, stored in specific order
	public char[] getChars(){return chars;}//return corresponding characters
	public String[] getCodes(){return codes;}//return corresponding codes for the same order as the above two arrays
	
	//THIS IS THE METHOD THAT GENERATES HUFFMAN CODING
	//it does this by traversing through the tree in pre-order.
	//then, it tests whether the node checked is a leaf. If it is, then it adds code, character, and frequency in the appropriate position as given by iterator value
	//the code is generated through a string that is built on itself recursively. The method first calls the same method generateCode, but changes 
	//HuffmanNode curr to curr.left. Doing this, it appends a "0" to the string. It does the same respective action for right child, but adds a "1" instead.
	public void generateCode(HuffmanNode curr, String str) 
	{
        // if iterator is null, return
        if (curr == null) return;
        
        // else if leaf, display path and value
        if (curr.getChar() != null) 
        {
            codes[iterator] = str;
            chars[iterator] = curr.getChar();
            freqs[iterator] = curr.getFreq();
            iterator++;
        }

        // add 0 if before moving to left child
        str += "0";
        // recursively call in pre-order from left child
        generateCode(curr.left, str);
        
        // adjust path to the right child, so delete last number in code string
        str = str.substring(0, str.length()-1);
        //add "1" if before moving to right child
        str += "1";
        //recursively call in pre-order from right child
        generateCode(curr.right, str);
    }
}
