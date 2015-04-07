/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/17/2015
 * */

import java.util.*;
@SuppressWarnings("serial")//just to make compiler stop complaining about some missing serial long
public class HuffmanList extends ArrayList<HuffmanNode>
{	
	/*
	 * ArrayList vs. LinkedList - Reasons for Choosing ArrayList
	 * 	The main reason I chose the ArrayList implementation was due to two main reasons.
	 * 	First, I wanted to be able to use the built-in Java's Collections.sort(List) method,
	 * and this method works flawlessly with my overriden compareTo method in my HuffmanNode
	 * class, allowing for the HuffmanList to always be sorted in ascending order.
	 * 	
	 * 	Second, my approach to the problem of this assignment was not to use a normal ArrayList 
	 * or LinkedList to store the HuffmanNodes from the input file, but rather to create this 
	 * specialized class than extended an ArrayList and created a special insert method to 
	 * work as I see fit (add no duplicate characters, add one to frequency, etc.). Then,
	 * I used the HuffmanTree class to empty this sorted list into a PriorityQueue, and assembled
	 * the tree from there. An ArrayList simply seemed like an easier way to input and sort the initial
	 * HuffmanNodes, and also to empty into a queue to assemble the tree.
	 */
	
	
	//edited insert method to ensure no duplicate entries into arraylist/huffmanlist
	public void insert(HuffmanNode in)
	{
		//a for loop to go through all HuffmanNodes already added
		for(int i = 0; i < this.size(); i++)
		{
			if(in.getChar() == this.get(i).getChar())//if there is already the same character
			{
				this.get(i).addFreq();//add one to frequency
				return;//end method so no duplicate char is added
			}
		}
		this.add(in);//add the char if it is not already found in arraylist/huffmanlist
		Collections.sort(this);//use Java's sort to sort frequencies in ascending order
	}
}