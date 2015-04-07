Shreyas Tallamraju - EECS 233 - Prof. Rabinovich - CWRU 2015
+———————————————————————————————————————————————————————————+
Table of Contents
—————————————————
1. How to Run Huffman Encoder
2. Guide to Files Generated
2. Notes to TA
3. Source of Input File
+———————————————————————————————————————————————————————————+
1. How to Run Huffman Encoder
—————————————————————————————
1) Move terminal to same directory as all of the class files.

2) Type in this command to compile/build:
	javac HuffmanCompressor.java

3) Type in this command to run initial program:
	java HuffmanCompressor in.txt out.txt

4) You can change in.txt and out.txt to whatever you see fit
to test different inputs to the program.
+———————————————————————————————————————————————————————————+
2. Guide to Files Generated
———————————————————————————
1) output file (outputFile.txt)
	-Converted original input file to encoded file

2) GeneratedHuffmanTable.txt
	-Produces a Huffman Code Conversion Table automatically

3) GeneratedSpaceSavings.txt
	-Calculates and produces a report on the space saved
+———————————————————————————————————————————————————————————+
3. Notes to TA
———————————————
-I added a timestamp feature to the FileWriter.java method, but
due to possible compatibility issues I removed the feature.

-Please read NOTES TO TA/GRADER.txt for a reference of where everything is in my code, matched up to the rubric given.

-Please contact me at shreyastallamraju@gmail.com if any issues
arise.
+———————————————————————————————————————————————————————————+
4. Source of Input File
————————————————————————
Sample Input Text From Pride and Prejudice by Jane Austen
URL: http://www.gutenberg.org/cache/epub/1342/pg1342.txt
+———————————————————————————————————————————————————————————+
Made by Shreyas Tallamraju - 3/17/15 thru 3/21/15