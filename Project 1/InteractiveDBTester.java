///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Programming Project 1
// Files:            InteractiveDBTester.java
//					 CustomerDatabase.java
// Semester:         CS367 Summer 2016
//
// Author:           Jinsoo Michael Chung
// Email:            jmchung2@wisc.com
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
// Lab Section:      
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Constructs a simple database of customers and wish lists
 * containing product identification codes.
 * 
 * @author Jinsoo Chung and Young Lee
 */
public class InteractiveDBTester {
    public static void main(String[] args) {
	    /** Check whether exactly one command-line argument is given; 
		* if not, display "Please provide input file as command-line argument" 
		* and quit. */
    	if(args.length != 1) {
    		System.out.println("Please provide input file as command-line argument");
    		System.exit(1);
    	}

    	/** Check whether the input file exists and is readable; 
    	 * if not, display "Error: Cannot access input file" and quit.
    	 * */
    	File srcFile = new File(args[0]);
		try 
		{
			Scanner fileIn = new Scanner(srcFile);	// possible FileNotFoundException		    	
	    	CustomerDatabase database = new CustomerDatabase();
	    	String username = null; 
	    	while(fileIn.hasNext())
	    	{
	    		String line = fileIn.nextLine();
	    		line = line.toLowerCase();	// handle uppercase input
	    		String[] list = line.split(",");
	    		for(int i = 0; i < list.length; i++)
	    		{
	    			if(i == 0)	// first item in each line is the username
	    			{
	    				username = list[i];
	    				database.addCustomer(username);
	    			}
	    			else	// other items are products in the wishlist
	    			{
	    				database.addProduct(username,list[i]);
	    			}
	    		}
	    	}

	    	Scanner stdin = new Scanner(System.in);  // for reading console input
	        printOptions();
	        boolean done = false;
	        while (!done) 
	        {
	            System.out.print("Enter option ( dfhisqr ): ");
	            String input = stdin.nextLine();
	            input = input.toLowerCase();  // convert input to lower case
	
	            // only do something if the user enters at least one character
	            if (input.length() > 0) {
	                char choice = input.charAt(0);  // strip off option character
	                String remainder = "";  // used to hold the remainder of input
	                if (input.length() > 1) 
	                {
	                    // trim off any leading or trailing spaces
	                    remainder = input.substring(1).trim(); 
	                }
	
	                switch (choice) {
	                
	                case 'd':{
	                    /** Discontinue product from database
	                     * if product not in database display "product not found" */
                		if(database.removeProduct(remainder)) {	// if removal is successful
                			System.out.println("product discontinued");
                		}
                		else{	
                			System.out.println("product not found");
                		}	
	                    break;
	                }	
	
	                case 'f': {
	                    /** Find customer and display wishlist.
	                     * if customer not in database display "customer not found" */
	                	
	                	List<String> wishlist = database.getProducts(remainder); 	
	                	// getProducts() returns null if customer is not in the database
	                	if(wishlist == null) { 
                			System.out.println("customer not found");
                		}
                		else {	
                			Iterator<String> itrP= wishlist.iterator();	
                			System.out.print(remainder + ":");
                			while(itrP.hasNext()) {
                				String product = itrP.next();
                				System.out.print(product);
                				if(itrP.hasNext()) {	// if next product exists
                					System.out.print(",");
                				}
                			}
            				System.out.print("\n");
                		}
                		break;
	                }
	
	                case 'h': {
	                    printOptions();
	                    break;
	                }
	                
	                case 'i': {	                	
	                	//List to store unique products
	                	List<String> uniqueP = new ArrayList<String>();

	                	{/** Displays Customers: integer, Products: integer" */ 
		                	Iterator<Customer> itrCustomers = database.iterator();
		                	Customer customer = null;
		                	List<String> wishlist;
		                	String product;
		                	while(itrCustomers.hasNext())
		                	{
		                		customer = itrCustomers.next();
		                		wishlist = customer.getWishlist();
		                		Iterator<String> itrProducts = wishlist.iterator();
	                			while (itrProducts.hasNext()) {
		                			product = itrProducts.next();
		                			if(!uniqueP.contains(product))
		                			{
		                				uniqueP.add(product);
		                			}
	                			}
	 	                	}
		                	System.out.print("Customers: " + database.size());
		                	System.out.println(", Products: " + uniqueP.size());
	                	}	                	
	                	{ /** displays max/min/average for products per customer) */
		                	int maxP = 0;
		            		int minP = -1;
		            		int aveP;
		            		int wlSize;	// var to hold wishlist size
		            		double totalP = 0;
		            		Customer customer;
		            		List<String> wishlist = new ArrayList<String>();
		            		Iterator<Customer> itrCustomer = database.iterator();
		            		while(itrCustomer.hasNext()) {
		                		customer = itrCustomer.next();
		                		wishlist = customer.getWishlist();
		                		wlSize = wishlist.size();
		                		if(wlSize > maxP) {
		                			maxP = wlSize;
		                		}
		                		if(minP == -1) {
		                			minP = wlSize;
		                		}
		                		else if(wlSize < minP) {
		                			minP = wlSize;
		                		}
		                		totalP += wlSize;
		                	}
		            		if(database.size()!=0) { // avoids division by 0
		            			aveP = (int) Math.round(totalP / database.size());
			            		System.out.print("# of products/customer: ");
			            		System.out.print("most " + maxP);
			            		System.out.print(", least " + minP);
			            		System.out.println(", average " + aveP);
		            		} 
		            		else{
		            			System.out.println("database is empty");
		            		}
	                	}

	                	{ /** displays max/min/average customers per product */
		            		List<String> wishlist = new ArrayList<String>();
		            		Iterator<String> itrUniqueP = uniqueP.iterator();
		            		String product;
		            		int maxC = 0;
		            		int minC = -1;
		            		int aveC;
		            		int wlSize;
		            		double totalC = 0;
		            		while(itrUniqueP.hasNext()) {
		            			product = itrUniqueP.next();
		            			wishlist = database.getCustomers(product);
		                		// getCustomers returns null if p not in database
		            			if(wishlist != null) { 
			            			wlSize = wishlist.size();
			                		if(wlSize > maxC) {
			                			maxC = wlSize;
			                		}
			                		if(minC == -1) {
			                			minC = wlSize;
			                		}
			                		else if(wlSize < minC) {
			                			minC = wlSize;
			                		}
			                		totalC += wlSize;
		            			}
		            		}
		            		if(uniqueP.size()!=0) {	// avoids division by 0
		            			aveC = (int) Math.round(totalC/uniqueP.size());
			            		System.out.print("# of customers/product: ");
			            		System.out.print("most " + maxC);
			            		System.out.print(", least " + minC);
			            		System.out.println(", average " + aveC);
		            		} else {
		            			System.out.println("No products in database");
		            		}
	                	}

	                	{ /** Displays most popular product(s) */
		            		String product;
		            		int numCustomers; // counts custsomers with the product
		            		int numPopular = 0;	// counts number of ties
		            		String popularItems = null;	// holds popular Item(s)
		                	Iterator<String> itrUniqueP = uniqueP.iterator();
		                	List<String> customers = new ArrayList<String>();

		                	while(itrUniqueP.hasNext()) {
		                		product = itrUniqueP.next();
		                		customers = database.getCustomers(product);
		                		// getCustomers returns null if p not in database
		                		if(customers != null) {
			                		numCustomers = customers.size();
			                		if(numCustomers>numPopular) {
			                			popularItems = product;
			                			numPopular = numCustomers;
			                		}
			                		else if(numCustomers == numPopular) {
			                			popularItems = popularItems + "," + product;    
			                		}
		            			}
		                	}
		                	System.out.print("Most popular product: ");
		                	System.out.println(popularItems + " [" + numPopular + "]");
	                	}
	                    break;
	                }
	                
	                case 's': {
	                	/** finds product and display customers with it
	                		if product not found displays "product not found" */

	                	List<String> customers = database.getCustomers(remainder);

	                	// getCustomers() returns null if product is not in the database
	                	if(customers == null) {	
                			System.out.println("product not found");
                		}
                		else{
                			Iterator<String> itrCustomers = customers.iterator();
                			String customer;
                			System.out.print(remainder + ":");
                			while(itrCustomers.hasNext()) {
                				customer = itrCustomers.next();
                				System.out.print(customer);
                				if(itrCustomers.hasNext()) {	// if next customer exists
                					System.out.print(",");
                				}
                			}
                			System.out.print("\n");
                		}
            			break;
	                }
                				                
	                case 'q': {
	                    done = true;
	                    System.out.println("quit");
	                    break;
	                }
	                
	                case 'r': {
	                    /** removes customer
	                		if customer not in database display "customer not found" */

	                	// removeCustomer() returns true iff customer is removed
                		if(database.removeCustomer(remainder)) { 
                			System.out.println("customer removed");
                		}
                		else{
                			System.out.println("customer not found");
                		}
	                    break;
	                }
	
	                default: { // ignore any unknown commands
	                    break;
	                }
	                }
	            }
	        }
	        stdin.close();
	    	fileIn.close();
	    }
		catch (FileNotFoundException e) {
			System.out.println("Error: Cannot access input file");
		}
	}	
    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() 
    {
        System.out.println("d <product> - discontinue the given <product>");
        System.out.println("f <customer> - find the given <customer>");
        System.out.println("h - display this help menu");
        System.out.println("i - display information about this customer database");
        System.out.println("s <product> - search for the given <product>");
        System.out.println("q - quit");
        System.out.println("r <customer> - remove the given <customer>");
    }
}
