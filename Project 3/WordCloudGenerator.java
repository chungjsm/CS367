///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 3
// Files:            WordCloudGenerator.java
//					 KeyWord.java
//					 BSTDictionaryIterator.java
//				     BSTDictionary.java
//					 ArrayHeap.java
// Semester:         CS 367 - Summer 2016
//
// Author:           Jinsoo Chung
// Email:            jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      NA
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     NA
// Email:            NA
// CS Login:         NA
// Lecturer's Name:  NA
// Lab Section:      NA
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          NA
//
// Online sources:   NA
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * The main class used for generating a word cloud given an input file, output 
 * file location, list of words to ignore, and max number of words to include in
 * the word cloud. It also prints info about the words in the dictionary.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class WordCloudGenerator {
    /**
     * The main method generates a word cloud as described in the program 
     * write-up.
     * 
     * @param args the command-line arguments that determine where input and 
     * output is done:
     *   1. args[0] is the name of the input file
     *   2. args[1] is the name of the output file
     *   3. args[2] is the name of the file containing the words to ignore
     *       when generating the word cloud
     *   4. args[3] is the maximum number of words to include in the word
     *       cloud
     */
    public static void main(String[] args) throws FileNotFoundException {
        // for input from text file
        Scanner in = null;         // for input from text file
        PrintStream out = null;    // for output to html file
        Scanner inIgnore = null;   // for input from ignore file
        DictionaryADT<KeyWord> dictionary = new BSTDictionary<KeyWord>();  

        // Check the command-line arguments and set up the input and output
        
        // check to see there are 4 command lines
        if (args.length != 4) {
      	  System.out.println("Four arguments required: inputFileName "
      	  		+ "outputFileName ignoreFileName maxWords");
      	  System.exit(0);
        }
        
        // set up input file
        // check to see input file exists and is readable
        File inputFile = new File(args[0]);
        if (!inputFile.exists() || !inputFile.canRead()) {
           System.err.println("Error: cannot access file " + args[0]);
           System.exit(1);
        }
        in = new Scanner(inputFile);
        
        // set up output file
        out = new PrintStream(new File(args[1]));
        
        // set up ignore file
        // check to see ignore file exists and is readable
        File ignoreFile = new File(args[2]);
        if (!ignoreFile.exists() || !ignoreFile.canRead()) {
           System.err.println("Error: cannot access file " + args[2]);
           System.exit(1);
        }
        inIgnore = new Scanner(ignoreFile);
        
        // checks whether max words is a positive integer or not
        int maxWords = 0;
        try {
            maxWords = Integer.parseInt(args[3]);
            if (maxWords <= 0) {
                System.out.println("Error: maxWords must be a positive integer");
                System.exit(0);
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Error: maxWords must be a positive integer");
            System.exit(0);
        }

        // Create the dictionary of words to ignore
        // You do not need to change this code.
        DictionaryADT<String> ignore = new BSTDictionary<String>();
        while (inIgnore.hasNext()) {
            try {
                ignore.insert(inIgnore.next().toLowerCase());
            } catch (DuplicateException e) {
                // if there is a duplicate, we'll just ignore it
            }
        }
        
        // Process the input file line by line
        while (in.hasNext()) {
            String line = in.nextLine();
            List<String> words = parseLine(line);

            for (String word : words) {
            	// check if it's in ignore
            	if (ignore.lookup(word.toLowerCase()) == null) {
            		KeyWord kw = new KeyWord(word);
            		kw.increment();
            		try {
            			dictionary.insert(kw);
            		} catch (DuplicateException ex) {
            			dictionary.lookup(kw).increment();
            		}
            		
            	}
            }

        } // end while
        
        // print dictionary
        printInfo(dictionary);
        
        // put dictionary into a priority queue
        PriorityQueueADT<KeyWord> pQueue = new ArrayHeap<KeyWord>();
        Iterator<KeyWord> itr = dictionary.iterator();
        while (itr.hasNext()) {
            pQueue.insert(itr.next());
        }
        
        // use priority queue to create a list of KeyWords of proper length
        DictionaryADT<KeyWord> mostFrequentWords = new BSTDictionary<KeyWord>();
        // if maxWords is greater than words in the priority queue, just add all
        if (pQueue.size() < maxWords) {
            while (!pQueue.isEmpty()) {
                try {
                    mostFrequentWords.insert(pQueue.removeMax());
                }
                catch (DuplicateException ex) {
                }
            }
        }
        
        else {
            for (int i = 0; i < maxWords; i++) {
                try {
                    mostFrequentWords.insert(pQueue.removeMax());
                }
                catch (DuplicateException ex) {
                }
            }
        }
        
        // generate the html output file
        generateHtml(mostFrequentWords, out);

        // Close everything
        if (in != null) 
            in.close();
        if (inIgnore != null) 
            inIgnore.close();
        if (out != null) 
            out.close();
    }
    
    /**
     * Parses the given line into an array of words.
     * 
     * @param line a line of input to parse
     * @return a list of words extracted from the line of input in the order
     *         they appear in the line
     *         
     * DO NOT CHANGE THIS METHOD.
     */
    private static List<String> parseLine(String line) {
        String[] tokens = line.split("[ ]+");
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {  // for each word
            
            // find index of first digit/letter
              boolean done = false; 
              int first = 0;
            String word = tokens[i];
            while (first < word.length() && !done) {
                if (Character.isDigit(word.charAt(first)) ||
                    Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;
            }
            
            // find index of last digit/letter
            int last = word.length()-1;
            done = false;
            while (last > first && !done) {
                if (Character.isDigit(word.charAt(last)) ||
                        Character.isLetter(word.charAt(last)))
                        done = true;
                    else last--;
            }
            
            // trim from beginning and end of string so that is starts and
            // ends with a letter or digit
            word = word.substring(first, last+1);
  
            // make sure there is at least one letter in the word
            done = false;
            first = 0;
            while (first < word.length() && !done)
                if (Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;           
            if (done)
                words.add(word);
        }
        
        return words;
    }
    
    /**
     * Generates the html file using the given list of words.  The html file
     * is printed to the provided PrintStream.
     * 
     * @param words a list of KeyWords
     * @param out the PrintStream to print the html file to
     * 
     * DO NOT CHANGE THIS METHOD
     */
    private static void generateHtml(DictionaryADT<KeyWord> words, 
                                     PrintStream out) {
           String[] colors = { 
                "6F", "6A", "65", "60",
                "5F", "5A", "55", "50",
                "4F", "4A", "45", "40",
                "3F", "3A", "35", "30",
                "2F", "2A", "25", "20",
                "1F", "1A", "15", "10",        
                "0F", "0A", "05", "00" 
                };
           int initFontSize = 80;
           
        // Print the header information including the styles
        out.println("<head>\n<title>Word Cloud</title>");
        out.println("<style type=\"text/css\">");
        out.println("body { font-family: Arial }");
        
        // Each style is of the form:
        // .styleN {
        //      font-size: X%;
        //      color: #YYAA;
        // }
        // where N and X are integers and Y is two hexadecimal digits
        for (int i = 0; i < colors.length; i++)
            out.println(".style" + i + 
                    " {\n    font-size: " + (initFontSize + i*20)
                    + "%;\n    color: #" + colors[i] + colors[i]+ "AA;\n}");
        
        out.println("</style>\n</head>\n<body><p>");        
        
        // Find the minimum and maximum values in the collection of words
        int min = Integer.MAX_VALUE, max = 0;
        for (KeyWord word : words) {
            int occur = word.getOccurrences();
            if (occur > max)
                max = occur;
            else if (occur < min)
                min = occur;
        }

        double slope = (colors.length - 1.0)/(max - min);
        
        for (KeyWord word : words) {
            out.print("<span class=\"style");
            
            // Determine the appropriate style for this value using
            // linear interpolation
            // y = slope *(x - min) (rounded to nearest integer)
            // where y = the style number
            // and x = number of occurrences
            int index = (int)Math.round(slope*(word.getOccurrences() - min));
            
            out.println(index + "\">" + word.getWord() + "</span>&nbsp;");
        }
        
        // Print the closing tags
        out.println("</p></body>\n</html>");
    }
    
    /**
     * Prints the number of keys, average path length, and the average path 
     * length for a linear structure with that number of keys for the given 
     * dictionary.
     * 
     * @param dictionary The dictionary to print info for.
     */
    private static void printInfo(DictionaryADT<KeyWord> dictionary) {
   	 // number of keys
   	 int numKeys = dictionary.size();
   	 // average path length
   	 float avgPathLength = (dictionary.totalPathLength()) / numKeys;
   	 // average path length for linear data structure
   	 float linAvgPath = (1 + numKeys) / 2;
   	 
   	 System.out.println("# keys: " + numKeys);
   	 System.out.println("avg path length: " + avgPathLength);
   	 System.out.println("linear avg path: " + linAvgPath);
    }
 }
