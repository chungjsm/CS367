///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 2
// Files:            LinkedLoop.java, EmptyLoopException.java, 
//							LinkedLoopIterator.java, MessageLoopEditor.java
// Semester:         CS 367 - Summer 2016
//
// Author:           Jinsoo Chung
// Email:            jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      
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
// Pair Partner:     Young Lee
// Email:            ylee546@wisc.edu
// CS Login:         younghoon
// Lecturer's Name:  Amanda Strominger
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
 * This class allows for user manipulation of a loop of messages.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class MessageLoopEditor {
    
    private static Loop<String> messages = new LinkedLoop<String>();
    
    // Creates an empty loop using the implemented LinkedLoop class
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean useFile = false;
        
        /**
         * If there exists a command line argument, set the argument
         * read as filename to read input from
         */
        if (args.length == 1) {
            File file = new File(args[0]);
            if (!file.exists() || !file.canRead()) {
                System.err.println("problem with input file!");
                System.exit(1);
            }
            
            in = new Scanner(file);
            useFile = true;
        }
        
        // give error if there is more than 1 command line argument
        if (args.length > 1) {
            System.out.println("invalid command-line arguments");
            System.exit(0);
        }
        
        boolean again = true;

        while (again) {
            System.out.print("enter command (? for help): ");
            String input = in.nextLine();
            if (useFile) {
                System.out.println(input);
            }

            if (input.length() > 0) {
                char choice = input.charAt(0);
                String remainder = "";
                if (input.length() > 1) {
                    // trim any leading/trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                switch (choice) {
                
                // display help menu
                case '?':
                    System.out.println("a (add after)  b (add before)  d (display)");
                    System.out.println("f (find)       r (remove)      u (update)"); 
                    System.out.println("> (next)       < (previous)    j (jump)");
                    System.out.println("s (save)       l (load)        q (quit)");   
                    break;

                // add new message after the current message
                case 'a':
                    if (remainder.length() > 0) {
                        
                  	  if (!messages.isEmpty()) {
                  		  messages.forward();
                  	  }
                  	  messages.insert(remainder);
                  	  displayContext();
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                
                // add new message before the current message
                case 'b':
                    if (remainder.length() > 0) {
                        messages.insert(remainder);
                        displayContext();
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                
                // display all messages in loop
                case 'd': 
                    display();
                    break;

                // find message (search forward in loop, starting with message afte current)
                case 'f':
                    if (remainder.length() > 0) {
                  	  if (messages.isEmpty()) {
                   		 System.out.println("no messages");
                   	 } else {
                   		 messages.forward();
                   		 
                   		 Iterator<String> itr = messages.iterator();
                   		 boolean found = false;
                   		 int count = 0;
                   		 while (itr.hasNext() && !found) {
                   			 if (itr.next().contains(remainder)) {
                   				 found = true;
                   				 
                   				 for (int i = 0; i < count; i++) {
                   					 messages.forward();
                   				 }
                   				 displayContext();
                   				 
                   			 }
                   			 count++;
                   		 }
                   		 if (!found) {
                   			messages.backward();
                   			System.out.println("not found");
                   		 }
                   		 
                   	 }
                    } else {
                        System.out.println("invalid command");
                    }
                    break;

                // remove current message from loop
                case 'r':
               	 if (messages.isEmpty()) {
               		 System.out.println("no messages");
               	 } else {
               		 messages.removeCurrent();
               		 if (messages.isEmpty()) {
               			 System.out.println("no messages");
               		 } else {
               			 displayContext();
               		 }
               	 }
                    break;
                
                // update current message
                case 'u':
                    if (remainder.length() > 0) {
                  	  if (messages.isEmpty()) {
                   		 System.out.println("no messages");
                   	 } else {
                   		 messages.removeCurrent();
                   		 messages.insert(remainder);
                   		 displayContext();
                   	 }
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                
                // go forward to next message
                case '>':
               	 if (messages.isEmpty()) {
               		 System.out.println("no messages");
               	 } else {
               		 messages.forward();
               		 displayContext();
               	 }
                   break;
                
                // go backwards to previous message
                case '<':
               	 if (messages.isEmpty()) {
               		 System.out.println("no messages");
               	 } else {
               		 messages.backward();
               		 displayContext();
               	 }
                   break;
                
                // jump N messages in loop
                case 'j':
                    try {
                        int value = Integer.parseInt(remainder);
                        if (messages.isEmpty()) {
                        	System.out.println("no messages");
                        } else {
                        	if (value < 0) {
                        		for (int i = 0; i > value; i--) {
                        			messages.backward();
                        		}
                        	} else if (value > 0) {
                        		for (int i = 0; i < value; i++) {
                        			messages.forward();
                        		}
                        	}
                        	displayContext();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("invalid command");
                    }
                    break;        

                // save all messages to a file
                case 's':
                    if (remainder.length() > 0) {
                        save(remainder);
                    } else {
                        System.out.println("invalid command");
                    }
                    break;

                // load messages from filename in order given
                case 'l':
                    if (remainder.length() > 0) {
                        load(remainder);
                    } else {
                        System.out.println("invalid command");
                    }
                    break;
                
                // display "quit" and quit the program
                case 'q': 
                    System.out.println("quit");
                    again = false;
                    break;

                default:
                    System.out.println("invalid command");
                }
            }
        }
    }

    
    /**
     * If the message loop is empty, display "no messages to save".  
     * Otherwise, save all the messages to a file named filename, one message
     * per line starting with the current message (and proceeding forward 
     * through the loop).  If filename already exists, display "warning: 
     * file already exists, will be overwritten" before saving the messages.  
     * If filename cannot be written to, display "unable to save".
     *   
     * @param filename the name of the file to which to save the messages
     */
    private static void save(String filename) {
        if (messages.size() == 0) {
            System.out.println("no messages to save");
            return;
        }

        File file = new File(filename);
        
        // give warning message if overwriting existing file
        if (file.exists()) {
            System.out.print("warning: file already exists, ");
            System.out.println("will be overwritten");
        }
        
        // return "unable to save" if not able to write to file
        if (file.exists() && !file.canWrite()) {
            System.out.println("unable to save");
            return;
        }
        
        // print each message to the file
        try {
            PrintStream outFile = new PrintStream(file);
            for (String msg : messages)
                outFile.println(msg);
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to save");
        }
    }

    
    /**
     * If a file named filename does not exists or cannot be read from, print
     * "unable to load". Otherwise, load the messages from filename in the
     * order they are given before the current message.  The current message is
     * not changed.
     * 
     * It is assumed that the input file contains one message per line, that
     * there are no blank lines, and that the file is not empty (i.e., it has
     * at least one line).
     * 
     * @param filename the name of the file from which to load the messages
     */
    private static void load(String filename) {
        File file = new File(filename);
        
        // check for existence and readability
        if (!file.exists() || !file.canRead()) {
            System.out.println("unable to load");
            return;
        }
        
        try {
            Scanner inFile = new Scanner(file);
            while (inFile.hasNext()) {
                // trim any leading/trailing spaces before adding
                messages.insert(inFile.nextLine().trim());
                messages.forward();
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to load");
        }
    }

    
    /**
     * If message loop is empty, print "no message". Otherwise,
     * print all messages in loop, one message per line, starting
     * with the current message.
     */
    private static void display() {
        if (messages.size() == 0)
            System.out.println("no messages");
        
        else {
            Iterator<String> iter = messages.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
    
    /**
     * If message loop is empty, print "no message". Otherwise,
     * print current message, indicated by "===[ ]===",
     * along with message before/after current, if available.
     */
    private static void displayContext() {
   	 if (messages.isEmpty()) {
   		 System.out.println("no messages");
   	 } else if (messages.size() == 1) {
   		 System.out.printf("===[ %s ]===\n", messages.getCurrent());
   	 } else if (messages.size() == 2) {
   		 System.out.printf("===[ %s ]===\n", messages.getCurrent());
   		 messages.forward();
   		 System.out.printf("     %s\n", messages.getCurrent());
   		 messages.backward();
   	 } else {
   		 messages.backward();
   		 System.out.printf("     %s\n", messages.getCurrent());
   		 messages.forward();
   		 System.out.printf("===[ %s ]===\n", messages.getCurrent());
   		 messages.forward();
   		 System.out.printf("     %s\n", messages.getCurrent());
   		 messages.backward();
   	 }
    }
}
