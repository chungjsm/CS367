///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MessageLoopEditor.java
// File:             LinkedLoop.java
// Semester:         CS 367 - Summer 2016
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
// Lab Section:      NA
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          NA
//
// Online sources:   NA
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;

/**
 * Implementation of the Loop interface using DblListnode class.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class LinkedLoop<E> implements Loop<E> {
    
	private DblListnode<E> currentNode; // the loop's current node
	private int numItems; // the number of items in the loop
	
	/**
	 * Constructs a new empty loop.
	 */
	public LinkedLoop() {
		currentNode = null;
        numItems = 0;
	}
	
	/**
    * Returns the current item.  If the Loop is empty, an 
    * <tt>EmptyLoopException</tt> is thrown.
    * 
    * @return the current item
    * @throws EmptyLoopException if the Loop is empty
    */
	public E getCurrent() {
		if (numItems == 0) {
			throw new EmptyLoopException();
		}
		return currentNode.getData();
	}
	
	/**
    * Adds the given <tt>item</tt> immediately <em>before</em> the current 
    * item.  After the new item has been added, the new item becomes the 
    * current item.
    * 
    * @param item the item to add
    */
	public void insert(E item) {
		if (numItems == 0) {
			currentNode = new DblListnode<E>(item);
			currentNode.setPrev(currentNode);
			currentNode.setNext(currentNode);
		} else {
			DblListnode<E> newNode = 
					new DblListnode<E>(currentNode.getPrev(), item, currentNode);
			currentNode.getPrev().setNext(newNode);
			currentNode.setPrev(newNode);
			currentNode = newNode;
		}
		
		numItems++;
		
	}
	
	/**
    * Removes and returns the current item.  The item immediately 
    * <em>after</em> the removed item then becomes the  current item.  
    * If the Loop is empty initially, an 
    * <tt>EmptyLoopException</tt> is thrown.
    * 
    * @return the removed item
    * @throws EmptyLoopException if the Loop is empty
    */
	public E removeCurrent() {
		if (numItems == 0) {
			throw new EmptyLoopException();
		} else {
			E itemToRemove = currentNode.getData();
			if (numItems == 1) {
				currentNode = null;
			} else {
				DblListnode<E> nextNode = currentNode.getNext();
				currentNode.getPrev().setNext(nextNode);
				nextNode.setPrev(currentNode.getPrev());
				currentNode = nextNode;
			}
			numItems--;
			return itemToRemove;
		}
	}
	
	/**
    * Advances the current item forward one item resulting in the item 
    * that is immediately <em>after</em> the current item becoming the  
    * current item.  If the Loop is empty initially, an 
    * <tt>EmptyLoopException</tt> is thrown.
    *
    * @throws EmptyLoopException if the Loop is empty
    */
	public void forward() {
		if (numItems == 0) {
			throw new EmptyLoopException();
		} else {
			currentNode = currentNode.getNext();
		}
	}
	
	/**
    * Moves the current item backward one item resulting in the item 
    * that is immediately <em>before</em> the current item becoming the  
    * current item.  If the Loop is empty initially, an 
    * <tt>EmptyLoopException</tt> is thrown.
    *
    * @throws EmptyLoopException if the Loop is empty
    */
	public void backward() {
		if (numItems == 0) {
			throw new EmptyLoopException();
		} else {
			currentNode = currentNode.getPrev();
		}
	}

	/**
    * Returns the number of items in this Loop.
    *
    * @return the number of items in this Loop
    */
	public int size() {
		return numItems;
	}
	
	/**
    * Returns true if this Loop is empty and false otherwise.
    *
    * @return true if this Loop is empty; false otherwise
    */
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	/**
    * Returns an iterator for this Loop.
    *
    * @return an iterator for this Loop
    */
	public Iterator<E> iterator() {
		return new LinkedLoopIterator<E>(currentNode);
	}
}
