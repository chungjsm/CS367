///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  InteractiveDBTester
// File:             CustomerDatabase
// Semester:         CS 367 Summer 2016
//
// Author:           Jinsoo Chung jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Young Lee
// Email:            ylee546@wisc.edu
// CS Login:         younghoon
// Lecturer's Name:  Amanda Strominger
// Lab Section:      
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CustomerDatabase class is used represent a database of customers.
 *
 * <p>Bugs: none known
 *
 * @author Young Lee and Jinsoo Chung
 */

public class CustomerDatabase {
	/** counts customers in database */
	private int numCustomers;	
	
	/** list of customers */
	private List<Customer> customers;
	
	/**
	 * Constructs an empty customer database.
	 * @param args UNUSED
	 */
	public CustomerDatabase(){
		numCustomers = 0;
		customers = new ArrayList<Customer>();
	}
	
	/**
	 * Add a customer with the given username c to the end of the database. 
	 * If a customer with username c is already in the database, just return.
	 *
	 * @param c Customer username
	 * @return void
	 */
	public void addCustomer(String c){
		if(c == null) {	
			throw new IllegalArgumentException();
		}		
		Iterator<Customer> itr = customers.iterator(); 
		Customer customer; 
		while(itr.hasNext()){
			customer = itr.next();
			if(customer.getUsername().equals(c)){	// if customer username matches c
				return;
			}
		}
		customers.add(new Customer(c));	
		numCustomers++;
	}
	
	/**
	 * Add the given product p to the wish list for customer c in the database. 
	 * If customer c is not in the database throw a java.lang.IllegalArgumentException. 
	 * If p is already in the wish list for customer c, just return.
	 * 
	 * @param c Customer username
	 * @param p Product
	 * @return void
	 */
	public void addProduct(String c, String p){
		if(c == null || p == null){
			throw new IllegalArgumentException();
		}

		Iterator<Customer> itrCustomer = customers.iterator();
		Customer customer;
		while(itrCustomer.hasNext()){
			customer = itrCustomer.next();
			if(customer.getUsername().equals(c)){
				if(hasProduct(customer.getUsername(), p)){
					return;
				}
				customer.getWishlist().add(p);
				return;
			}
		}
		throw new IllegalArgumentException(); // c not in database
	}
	
	/**
	 * Return true iff customer c is in the database. 
	 * 
	 * @param c Customer username
	 * 
	 * */
	
	/**
	 * Return true iff customer c is in the database. 
	 * 
	 * @param c Customer username
	 * @return true iff customer c is in the database.
	 */
	public boolean containsCustomer(String c){
		if(c == null){
			throw new IllegalArgumentException();
		}
		
		Iterator<Customer> itr = customers.iterator();
		Customer customer;
		while(itr.hasNext()){
			customer = itr.next();
			if(customer.getUsername().equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return true iff product p appears in at least one customer's wish list in the database.
	 * 
	 * @param p
	 * @return true iff product p appears in at least one customer's wish list in the database.
	 */
	public boolean containsProduct(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}

		if(getCustomers(p) == null){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Returns true iff product p is in the wish list for customer c. 
	 * If customer c is not in the database, return false.
	 * @param c
	 * @param p
	 * @return true iff product p is in the wish list for customer c.
	 */
	public boolean hasProduct(String c, String p){
		if(c == null || p == null){
			throw new IllegalArgumentException();
		}
		
		Iterator<Customer> itrCustomer = customers.iterator();
		Customer customer;
		while(itrCustomer.hasNext()){
			customer = itrCustomer.next();
			if(customer.getUsername().equals(c)){
				List<String> wishlist = customer.getWishlist();
				Iterator<String> itrProduct = wishlist.iterator();
				while(itrProduct.hasNext()){
					if(itrProduct.next().equals(p)){
						return true;
					}
				}
				return false; // c does not have p
			}
		}
		return false; // c not in database
	}
	
	/**
	 * Return the list of customers who have product p in their wish list. 
	 * If product p is not in the database, return null. 
	 * 
	 * @param p
	 * @return List<String> of usernames of customers containing p; null if p is not in the database.
	 */
	public List<String> getCustomers(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}
		List<String> hasP = new ArrayList<String>(); 
		Iterator<Customer> itrCustomer = customers.iterator();
		Customer customer;
		while(itrCustomer.hasNext()){
			customer = itrCustomer.next();
			if(hasProduct(customer.getUsername(),p)){
				hasP.add(customer.getUsername());
			}
		}
		if(hasP.size() == 0){
			hasP = null;
		}
		return hasP;		
	}
	
	/**
	 * Return the wish list for the customer c. 
	 * If a customer c is not in the database, return null. 
	 * @param c
	 * @return List<String> of customer c's wishlist.
	 */
	public List<String> getProducts(String c){
		if(c == null){
			throw new IllegalArgumentException();
		}
		Iterator<Customer> itr = customers.iterator();
		Customer customer;
		while(itr.hasNext()){
			customer = itr.next();
			if(customer.getUsername().equals(c)){
				return customer.getWishlist();
			}
		}
		return null;		
	}
	
	/**
	 * Return an Iterator over the Customer objects in the database. 
	 * The customers should be returned in the order they were added to the database (resulting from the order in which they are in the text file). 
	 * 
	 * @return iterator<Customer> over CustomerDatabase.
	 */
	public Iterator<Customer> iterator(){
		return customers.iterator();
	}
	
	/**
	 * Remove customer c from the database. 
	 * If customer c is not in the database, return false; 
	 * otherwise (i.e., the removal is successful) return true. 
	 * 
	 * @param c
	 * @return 
	 */
	public boolean removeCustomer(String c){
		if(c == null){
			throw new IllegalArgumentException();
		}
		
		Iterator<Customer> itrCustomers = customers.iterator();
		Customer customer;
		while(itrCustomers.hasNext()){
			customer = itrCustomers.next();
			if(customer.getUsername().equals(c)){
				itrCustomers.remove(); 
				numCustomers--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove product p from the database, i.e., remove product p from every wish list in which it appears. 
	 * If product p is not in the database, return false; 
	 * otherwise (i.e., the removal is successful) return true. 
	 * 
	 * @param p
	 * @return
	 */
	public boolean removeProduct(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}

		boolean removed = false;
		Iterator<Customer> itrCustomers = customers.iterator();
		Customer customer;
		List<String> wishlist; 
		while(itrCustomers.hasNext()){
			customer = itrCustomers.next();
			wishlist = customer.getWishlist();
			Iterator<String> itrProduct = wishlist.iterator();
			while(itrProduct.hasNext()){
				if(itrProduct.next().equals(p)){
					itrProduct.remove();
					removed = true;
				}
			}
		}
		return removed;		
	}
	
	/**
	 * Return the number of customers in this database.
	 * 
	 * @return
	 */
	public int size(){
		return numCustomers;
	}
}


