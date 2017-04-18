///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionaryIterator.java
// Semester:         CS 367 - Summer 2016
//
// Author:           Jinsoo Chung jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      NA
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     NA
// Email:            NA
// CS Login:         NA
// Lecturer's Name:  NA
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
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 * 
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class BSTDictionaryIterator<K> implements Iterator<K> {

	// A stack to hold nodes. Using this structure allows the iterator to push
	// nodes as it passes through them and pop nodes as needed.
	private Stack<BSTnode<K>> nodes;

	/**
	 * The constructor for a BSTDictionaryIterator fills a Stack with all the 
	 * nodes along the path from the root to the left-most node.
	 * 
	 * @param root The root of the tree to iterate over.
	 */
	BSTDictionaryIterator(BSTnode<K> root) {
		nodes = new Stack<BSTnode<K>>();
		if (root == null) {
			return;
		}
        
		BSTnode<K> n = root;
		nodes.push(n);
        
		// while there exist left (grand)children, push them onto the stack
		while (n.getLeft() != null) {
			nodes.push(n.getLeft());
			n = n.getLeft();
		}
	}
	
	/**
	 * Returns true if there are more values. The constructor and next() method 
	 * ensure that if there are more values, they will be in the stack. Thus this 
	 * can simply check if the stack is empty.
	 * 
	 * @return Whether there are more values to return.
	 */
	public boolean hasNext() {
		return nodes.isEmpty() == false;
	}

	/**
	 * Returns the next value in the tree (or throws an exception if there are no 
	 * more). It also pushes enough nodes onto the stack so that the next time 
	 * this method is called, the top of the stack will be the next value in 
	 * order.
	 * 
	 * @return The next value in the tree.
	 */
	public K next() {
		if (hasNext() == false) {
			throw new NoSuchElementException();
		}
		
        // save top node and pop it
        BSTnode<K> next = nodes.pop();
		// if there is a right child, push it onto the stack
		// then, push the line of all left (grand)children onto the stack
		if (next.getRight() != null) {
			BSTnode<K> n = next.getRight();
			nodes.push(n);
			while (n.getLeft() != null) {
				nodes.push(n.getLeft());
				n = n.getLeft();
			}
		}
		return next.getKey();
	}

	// NOT IMPLEMENTED
	public void remove() {
		// DO NOT CHANGE: you do not need to implement this method
		throw new UnsupportedOperationException();
	}
}
