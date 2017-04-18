////////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             BSTDictionary.java
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

import java.util.Iterator;

/**
 * The BSTDictionary is a binary search tree implementation of the DictionaryADT
 * interface. The structure is composed of BSTnodes, with the root node being
 * pointed to by the BSTDictionary. It also keeps track of the number of items 
 * in the tree.
 *
 * <p>Bugs: none known
 *
 * @author Jinsoo Chung
 */
public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
	
	private BSTnode<K> root;        // root node
	private int numItems;           // number of items in dictionary
    private boolean deletion;       // was the deletion completed?

	/**
     * The constructor for the BSTDictionary creates an empty tree.
     * Sets the root to null and numItems to 0.
     */
	public BSTDictionary() {
		root = null;
		numItems = 0;
	}

	/**
	 * The insert method calls a recursive auxiliary method and increments 
	 * numItems if successful.
	 * 
	 * @param key the key to be inserted in the tree
	 * @throws DuplicateException if the key is already in the Dictionary
	 * @throws IllegalArgumentException if the key is null
	 */
	public void insert(K key) throws DuplicateException {
        if (key == null) {
			throw new IllegalArgumentException();
		}
        
		root = insert(root, key);
        numItems++;
	}

	/**
	 * The auxiliary method traverses subtrees until it reaches the place where 
	 * the key belongs (or it finds it already present). When the key is 
	 * inserted, the new node is returned, being set as the left or right subtree
	 * of its parent - onward and onward up the chain. Assumes that the public 
	 * method checked if the key is null.
	 * 
	 * @param n The root node of the tree/subtree.
	 * @param key The key to be inserted in the tree.
	 * @return The node as modified
	 * @throws DuplicateException if the key is already in the Dictionary
	 */
	private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
		if (n == null) {
			return new BSTnode<K>(key, null, null);
		}

		if (n.getKey().equals(key)) {
			// key is already present
			throw new DuplicateException();
		}

		if (key.compareTo(n.getKey()) < 0) {
			// add key to left subtree
			n.setLeft(insert(n.getLeft(), key));
			return n;
		}

		else {
			// add key to right subtree
			n.setRight(insert(n.getRight(), key));
			return n;
		}
	}

	/**
	 * The delete method calls a recursive auxiliary method to delete the key. If
	 * the key is found and deleted, numItems is decremented and the method 
	 * returns True.
	 * 
	 * @param key The key to delete.
	 * @return Whether the key was found (and deleted).
	 */
	public boolean delete(K key) {
		deletion = false;
		if (key != null) {
			root = delete(root, key);
		}
		if (deletion) {
			numItems--;
		}
		return deletion;
	}

	/**
	 * The auxiliary method traverses subtrees until it finds the key or finds 
	 * where the key would be if present. When the node is deleted, a new subtree 
	 * must be returned. If the node had no children, it returns null, if it had 
	 * one, then it returns that subtree, if it had two, then it returns the 
	 * smallest node in the right subtree. Precondition: the key is not null.
	 * 
	 * @param n The root node of the tree/subtree.
	 * @param key The key to be deleted.
	 * @return The node as modified.
	 */
	private BSTnode<K> delete(BSTnode<K> n, K key) {
		
		if (n == null) {
			return null;
		}

		if (key.equals(n.getKey())) {
			deletion = true;
			
			if (n.getLeft() == null && n.getRight() == null) {
				//no children
				return null;
			}
			if (n.getLeft() == null) {
				//only right child
				return n.getRight();
			}
			if (n.getRight() == null) {
				//only left child
				return n.getLeft();
			}

			// n has 2 children
			K smallVal = smallest(n.getRight());
			n.setKey(smallVal);
			n.setRight(delete(n.getRight(), smallVal));
			return n;
		}

		else if (key.compareTo(n.getKey()) < 0) {
			n.setLeft(delete(n.getLeft(), key));
			return n;
		}

		else {
			n.setRight(delete(n.getRight(), key));
			return n;
		}
	}

	/**
	 * Returns the smallest key in a subtree with root n. This method recursively 
	 * goes through the tree following left children. Precondition: n is not 
	 * null.
	 * 
	 * @param n The root of the subtree to search for the smallest value.
	 * @return The smallest key in the subtree.
	 */
	private K smallest(BSTnode<K> n) {
		if (n.getLeft() == null) {
			// no left child, this value is the smallest
			return n.getKey();
		} else {
			// if left child exists, go to left subtree
			return smallest(n.getLeft());
		}
	}

	/**
	 * This calls a recursive auxiliary method to find a given key. It returns 
	 * the key if found and otherwise returns null.
	 * 
	 * @param key The key to find.
	 * @return The key if found, null if not found.
	 */
	public K lookup(K key) {
		if (key == null) {
			return null;
		}
		return lookup(root, key);
	}
	
	/**
	 * This method traverses the tree recursively to the point where the key 
	 * would be. If found, it returns the key. If it reaches a null node, it 
	 * returns null.
	 * 
	 * @param n The root of the subtree to search.
	 * @param key The key to find.
	 * @return The key if found, null if not found.
	 */
	private K lookup(BSTnode<K> n, K key) {
		if (n == null) {
			return null;
		}

		if (n.getKey().equals(key)) {
			return n.getKey();
		}

		if (key.compareTo(n.getKey()) < 0) {
			// key is less than node's key, go to left subtree
			return lookup(n.getLeft(), key);
		}

		else {
			// key is greater than node's key, go to right subtree
			return lookup(n.getRight(), key);
		}
	}

	/**
	 * Returns whether the tree has zero items.
	 * 
	 * @return Whether the BSTDictionary is empty.
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * Returns the number of items in the tree.
	 * 
	 * @return The number of items in the tree.
	 */
	public int size() {
		return numItems;
	}

	/**
	 * This calls a recursive auxiliary method and returns the sum of all the 
	 * path lengths for this tree.
	 * 
	 * @return The total length of all paths.
	 */
	public int totalPathLength() {
		return totalPathLength(root, 1);
	}
	
	/**
	 * This calculates the total path length by adding the current node's path 
	 * length (it's depth) to the total path length of it's right and left 
	 * children. Returns zero when n is null.
	 * 
	 * @param n The root of the subtree for which the total path length is being
	 * calculated.
	 * @param depth The depth of node n.
	 * @return The total path length for n and all of n's children.
	 */
	private int totalPathLength(BSTnode<K> n, int depth) {
		if (n == null) {
			return 0;
		}
        
		if (n.getLeft() == null && n.getRight() == null) {
			return depth;
		}
        
		return depth + totalPathLength(n.getLeft(), depth + 1) + 
				totalPathLength(n.getRight(), depth + 1);
	}

	/**
	 * Returns an iterator for this tree.
	 * 
	 * @return An iterator for this tree.
	 */
	public Iterator<K> iterator() {
		return new BSTDictionaryIterator<K>(root);
	}
}
