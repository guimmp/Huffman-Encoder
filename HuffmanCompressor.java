/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/21/2015
 * */

public class HuffmanCompressor 
{
	public static void main(String[] args)//main method that runs the program as a whole
	{
		//method huffmanCoder(String inputFileName, String outputFileName) is the method that runs the main part
		System.out.println(huffmanCoder(args[0],args[1])+"\n");
	}
	public static String huffmanCoder(String inputFileName, String outputFileName)
	{
		//get a char[] of the text file input
		FileReader fr = new FileReader(inputFileName);//initialize custom FileReader class
		HuffmanList hl = new HuffmanList();//initialize custom HuffmanList (extends ArrayList) to hold nodes
		char[] read = fr.readFile();//use coded method to return char[] of input file
		
		if(read == null){return "Input File Error - Encoding Not Done!";}//if char[] is null, there was an error in input process
		if(read.length == 0){return "Blank Input File - Encoding Not Done!";}//if char[] is blank, encoding can't proceed
		for(int i = 0; i < read.length; i++)//use a for loop to traverse through all characters read...
		{
			hl.insert(new HuffmanNode(read[i]));//and insert in HuffmanList (extends ArrayList)
		}
		
		HuffmanTree ht = new HuffmanTree(hl);//initialize the HuffmanTree with ascending ordered HuffmanList created above
		//the above call also called the HuffmanTree's encode() method, which will generate the codes in a String[] array
		
		//code to print out table of character, frequency of character in input file, and the corresponding character's huffman code
		System.out.println("\n\nCharacter : Frequency : Huffman Code");
		char[] c = ht.getChars();//access the chars array
		int[] f = ht.getFreqs();//access the frequency array
		String[] co = ht.getCodes();//access the latest generated huffman codes that were stored in String[] array
		for(int i = 0; i < c.length; i++)//use a for loop to traverse through all 3 arrays
			System.out.print(c[i] + "\t:\t " + f[i] + " \t:\t " + co[i] + "\n");//print out the data from each array in table form
		
		//run method to output the calculated savings resulted from possible huffman encoding
		computeSavings(read,co,f);
		
		//CODE TO PRINT OUT RESULTS TO OUTPUT FILES USING FILEWRITER CLASS
		FileWriter fw = new FileWriter(outputFileName);//initialize coded FileWriter class and point to destination
		if(fw.writeFile(co,read,c) == false){return "Error in output process!";}
		if(fw.writeHuffmanTable(co, c, f) == false){return "Error in output process of Huffman Code Coversion Table!";}
		if(fw.writeSpaceSavings(read,co,f) == false){return "Error in output process of Space Savings to text file!";}
		System.out.println("\n\n");
		return "Huffman Encoding OK - Done Successfully";//return a success string
	}
	
	//this is a void method to print out to console the savings. FileWriter's writeSpaceSavings method prints this same date to text file
	public static void computeSavings(char[] read, String[] co, int[] f)
	{
		int origSpace = (read.length * 8);//assume each previous char had 8
		int newSpace = 0;//variable to store the amount of bits taken from huffman encoding
		for(int i = 0; i < co.length; i++)//use a for loop to traverse through 3 arrays
			newSpace += (co[i].length() * f[i]);//add the length of the huffman code to total num of bits taken from huffman encoding
		System.out.println("\nThe original space taken: "+origSpace+" bits\nThe new space taken: "+newSpace+" bits\nSpace Savings: "+(origSpace-newSpace)+" bits");
	}
}
