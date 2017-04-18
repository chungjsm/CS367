///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MessageLoopEditor.java
// File:             LinkedLoopIterator.java
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator for LinkedLoops. Note that it will not iterate through a
 * loop more than once.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class LinkedLoopIterator<E> implements Iterator<E> {

	private boolean pastStart; // boolean of whether or not the iterator has passed the start node
	private DblListnode<E> startNode; // starting node
	private DblListnode<E> currentNode; // current node of iterator

	/**
	 * Constructs an iterator from the node that should be considered start.
	 * 
	 * @param startNode The node to be treated as start by the iterator.
	 */
	LinkedLoopIterator(DblListnode<E> startNode) {
		this.startNode = startNode;
		currentNode = startNode;
		pastStart = false;
	}

	/**
	 * Returns false if there is no next node or the start node has been reached
	 * again. Otherwise returns true.
	 * 
	 * @return True if there is another element to iterate through.
	 */
	public boolean hasNext() {
		if (currentNode == null) {
			return false;
		} else if (currentNode == startNode && pastStart){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * If there is not a next node, throws a NoSuchElementException. Otherwise,
	 * returns the data in the next node in the loop.
	 * 
	 * @return The data in the next node.
	 */
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		if (!pastStart) {
			pastStart = true;
		}
		
		E data = currentNode.getData();
		currentNode = currentNode.getNext();
		return data;
	}

	/**
	 * Throws UnsupportedOperationException.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
