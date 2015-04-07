/*
 * Name: Shreyas Tallamraju
 * EECS 233 - Case Western Reserve University
 * Prof. Rabinovich
 * Version 1.3
 * Latest Date: 03/17/2015
 * */

/*
 * This class, FileReader, is a class that simply uses the built-in Java File library and the java URL library to 
 * create a method that simply reads a text file and returns a char[] array of ALL of the characters in the input file.
 * The actual frequencies are calculated in the HuffmanList and HuffmanTree classes.
 * */

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class FileReader 
{
	//the name of file, presumably in the same directory as .class files of this project
	private String filename;
	//constructor to set the filename.
	public FileReader(String in)
	{
		filename = in;
	}
	//THIS SCANS THE INPUT FILE:
	//This method uses a FileInputStream and a URL to use the FIS.read(filename) method to return a byte array,
	//then converts this byte array to a char array, and returns this resulting char array.
	
	//FOR ERROR PURPOSES: The method return nulls to signify an error with input file, the main method will test if
	//the return from this method is null to raise an error message to the main program.
	public char[] readFile()
	{
		FileInputStream fileInputStream = null;
		URL path = ClassLoader.getSystemResource(filename);
		if(path == null) //if the URL failed to load, return null to signify failure
		{
			//System.out.println("Error: cannot find file!");
			return null;
		}
		
		File file = null;//use a Java file object to read the input file
		try 
		{
			file = new File(path.toURI());//import the File in question
		} 
		catch (URISyntaxException e1) //if fail, return null to signify failure
		{
			//System.out.println("Error in importing file!");
			return null;
		}
		//create the byte array that will hold the data from input file
        byte[] bFile = new byte[(int) file.length()];
        //create a char array that is the same length as byte array to return
        char[] ret = new char[bFile.length];
        try //try to use built-in read method to file byte array
        {
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
		    //use for loop to convert each byte to a char
		    for (int i = 0; i < bFile.length; i++) 
		    {
		       	ret[i] = (char)bFile[i];
	        }
        }
        catch(Exception e)
        {
        	//e.printStackTrace();
        	return null;//return null to signify failure with reading of input file
        }
        return ret;//return char array of read characters
	}
}