/**
 * <tt>BSTnode</tt> implements a node for a binary search tree (BST).  Each 
 * <tt>BSTnode</tt> keeps track of a key as well as its children.
 * 
 * DO NOT CHANGE THIS FILE
 * 
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 */
class BSTnode<K> {
    // Data members
    private K key;                   // the key for this node
    private BSTnode<K> left, right;  // the left and right children
 
    /**
     * Constructs a BST node with the given key and whose left and right 
     * children are null.
     * @param key the key to store in this node
     */
    public BSTnode(K key) {
        this(key, null, null);
    }
    
    /**
     * Constructs a BST node with the given key, left child, and right child.
     * @param key the key value
     * @param leftChild the left child
     * @param rightChild the right child
     */
    public BSTnode(K key, BSTnode<K> leftChild, BSTnode<K> rightChild) {
        this.key = key;
        left = leftChild;
        right = rightChild;
    }
 
    /**
     * Returns the key for this BST node.
     * @return the key
     */
    public K getKey() { 
        return key; 
    }
    
    /**
     * Returns the left child for this BST node.
     * @return the left child
     */
    public BSTnode<K> getLeft() { 
        return left; 
    }
    
    /**
     * Returns the right child for this BST node.
     * @return the right child
     */
    public BSTnode<K> getRight() { 
        return right; 
    }
 
    /**
     * Changes the key for this node to the one given.
     * @param newK the new key  
     */
    public void setKey(K newKey) { 
        key = newKey; 
    }
    
    /**
     * Changes the left child for this node to the one given.
     * @param newL the new left child
     */
    public void setLeft(BSTnode<K> newLeft) { 
        left = newLeft; 
    }
    
    /**
     * Changes the right child for this node to the one given.
     * @param newR the new right child
     */
    public void setRight(BSTnode<K> newRight) { 
        right = newRight; 
    }
}
