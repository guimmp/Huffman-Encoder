/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/17/2015
 * */

/*
 * HuffmanNode - Explanation:
 * This is the main node class that holds the character (or null for interior nodes), frequency, and children 
 * of the HuffmanTree. I implemented Comparable to overwrite the compareTo method, so that the built-in Java's
 * Collections.sort(List) method could be used effectively to sort the HuffmanList in ascending order easily.
 */
public class HuffmanNode implements Comparable<HuffmanNode>
{
	Character inChar;//the character of the node (null if interior)
	private int frequency = 1;//frequency of character (starts at 1)
	HuffmanNode left;//left child of HuffmanNode, comes into play in HuffmanTree's assembly of tree
	HuffmanNode right;//see above
	
	//constructor with character, frequency, left, and right when assembling HuffmanTree
	public HuffmanNode(Character in, int f, HuffmanNode l, HuffmanNode r)
	{
		inChar = in;
		frequency = f;
		left = l;
		right = r;
	}
	
	//constructor with only character, for first addition to HuffmanList
	//when first adding HuffmanNodes, everything but Character in is taken care of in HuffmanList/Tree,
	//so only Character in needs to be given.
	public HuffmanNode(Character in)
	{
		inChar = in;
		frequency = 1;
		left = null;
		right = null;
	}
	
	//accessor methods
	public int getFreq(){return frequency;}
	public Character getChar(){return inChar;}
	
	//adds one to frequency, for insertion purposes in HuffmanList to keep count of frequencies
	public void addFreq(){frequency++;}
	
	//override default compareTo so that Collections.sort works properly on HuffmanList
	//this will make HuffmanNodes comparable between frequencies, so it is sorted from least frequent to most
	@Override
	public int compareTo(HuffmanNode o)//compare this HuffmanNode to another HuffmanNode o
	{
		//this compareTo method will compare frequencies of each node, to ensure a
		//HuffmanList that is sorted in ascending frequency order
		int compared = o.getFreq();//get other HuffmanNode's frequency
		if (this.getFreq() > compared)//if this node's freq. is greater
		{
			return 1;//return 1, as per default compareTo standard
		} 
		else if (this.getFreq() == compared)//if equal frequencies between both
		{
			return 0;//return 0, as per default compareTo standard
		} 
		else//else if this node's freq. is smaller than the other's
		{
			return -1;//return -1, as per default compareTo standard
		}
	}
}