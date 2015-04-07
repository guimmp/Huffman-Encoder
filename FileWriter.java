import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//import java.sql.Timestamp;
//import java.util.Calendar;

/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/17/2015
 * */

/* FILEWRITER EXPLANATION:
 * This java file is responsible for not only outputting the generated Huffman code into the output file, but also to
 * create two auto-generated files that contain space-saving information and a text file with a Huffman conversion table,
 * complete with characters, frequencies of characters, and the corresponding huffman code.
 * */
public class FileWriter 
{
	String output;//the filename to output to, assuming it's in the same directory
	public FileWriter(String o)
	{
		output = o;//set output to given filename of output text file
	}
	//this method writes the outputted huffman encoded version of input file
	//it uses the array of chars read by FileReader.java, and then two char arrays
	//that correspond to each other (like a Map) that have the index char, and its
	//corresponding huffman code. The method outputs the code for each read char.
	public boolean writeFile(String[] codes, char[] read, char[] index)
	{
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter(output, "UTF-8");//set Java's PrintWriter pointed to the output filename
		} 
		catch (FileNotFoundException e)//not really going to be caught, since file is generated
		{
			//System.out.println("File not Found!");
			//e.printStackTrace();
			return false;//returning false signifies failure in output
		} 
		catch (UnsupportedEncodingException e)//if encoding has an error
		{
			//System.out.println("Unsupported Encoding!");
			//e.printStackTrace();
			return false;//returning false signifies failure in output
		}
		int copy = 0;//an int to use in for loop for code copying
		for(int i = 0; i < read.length; i++)//go through each of the read chars
		{
			for(int x = 0; x < index.length; x++)//search through each of the index chars
			{
				if(index[x] == read[i])//if there is a match, then set placeholder to index
				{
					copy = x;
				}
				//the above if statement will always run once, as read char HAS to be one of index
			}
			writer.print(codes[copy]);//as long as index was found in char, copy corresponding code
		}
		writer.close();//close the PrintWriter to end process
		return true;//return true to signify success
	}
	//a method to generate a text file containing the huffman coding conversion table
	//simply takes input arrays and prints them in table format to "GeneratedHuffmanTable.txt"
	public boolean writeHuffmanTable(String[] codes, char[] index, int[] freq)
	{
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter("GeneratedHuffmanTable.txt", "UTF-8");
		} 
		catch (FileNotFoundException e) 
		{
			//System.out.println("File not Found!");
			//e.printStackTrace();
			return false;//returning false signifies failure in output
		} 
		catch (UnsupportedEncodingException e) 
		{
			//System.out.println("Unsupported Encoding!");
			//e.printStackTrace();
			return false;//returning false signifies failure in output
		}
		//Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		writer.println("Generated Huffman Table Of Latest Input");
		writer.println("Character : Frequency : Huffman Code");
		writer.println();
		for(int i = 0; i < codes.length; i++)//use a for loop to traverse through one array (which has identical dimensions to other arrays)
		{
			writer.print(index[i] + "\t:\t " + freq[i] + " \t:\t " + codes[i] + "\n");//print out table
		}
		writer.close();
		return true;//return true to signify success
	}
	//a method to generate a text file containing the possible space savings resulting from huffman encoding
	//simply calculates savings and prints them to "GeneratedSpaceSavings.txt"
	public boolean writeSpaceSavings(char[] read, String[] co, int[] f)
	{
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter("GeneratedSpaceSavings.txt", "UTF-8");
		} 
		catch (FileNotFoundException e) 
		{
			//System.out.println("File not Found!");
			//e.printStackTrace();
			return false;//return false to signify failure
		} 
		catch (UnsupportedEncodingException e) 
		{
			//System.out.println("Unsupported Encoding!");
			//e.printStackTrace();
			return false;//return false to signify failure
		}
		//Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		writer.println("Generated Space Savings of Latest Input");
		writer.println();
		int origSpace = (read.length * 8);//assume each previous char had 8 bits
		int newSpace = 0;
		for(int i = 0; i < co.length; i++)//for each code
			newSpace += (co[i].length() * f[i]);//add the length of the code for bits taken
		writer.println("\nThe original space taken: "+origSpace+" bits\nThe new space taken: "+newSpace+" bits\nSpace Savings: "+(origSpace-newSpace)+" bits\n");
		writer.close();
		return true;//return true for success
	}
}
