import java.util.Iterator;

/**
 * The <tt>Dictionary</tt> ADT stores unique keys.
 * 
 * Operations are provided to insert, lookup, and delete information as well 
 * as to go through the keys in order from smallest to largest (based on the 
 * ordering given by <tt>compareTo</tt>).  Additionally, the Dictionary ADT 
 * provides some methods that give insight about the relative efficiency of a 
 * particular map: the size (# of keys) and the total path length.  The total 
 * path length is the sum of the lengths of the paths to each key.  Thus, one 
 * measure of the average lookup time is (total path length)/size.
 * 
 * The Dictionary does not allow null keys to be added.
 * 
 * DO NOT CHANGE THIS FILE
 *  
 * @author CS 367
 * @param <K> class representing the key, should implement the Comparable<K> 
 *            interface 
 */
public interface DictionaryADT<K extends Comparable<K>> extends Iterable<K> {
    
    /**
     * Inserts the given key into the Dictionary if the key is not already in 
     * the Dictionary.  If the key is already in the Dictionary, a
     * DuplicatException is thrown.
     * @param key the key to insert into the Dictionary
     * @throws DuplicateException if the key is already in the Dictionary
     * @throws IllegalArgumentException if the key is null
     */    
    void insert(K key) throws DuplicateException;

    
    /**
     * Deletes the given key from the Dictionary.  If the key is in the 
     * Dictionary, the key is deleted and true is returned.  If the key is not 
     * in the Dictionary, the Dictionary is unchanged and false is returned. 
     * @param key the key  to delete from the Dictionary
     * @return true if the deletion is successful (i.e., the key was in the 
     * Dictionary and has been removed), false otherwise (i.e., the key was 
     * not in the Dictionary)
     */
    boolean delete(K key);
    
    /**
     * Searches for the given key in the Dictionary and returns the key stored
     * in the Dictionary. If the key is not in the Dictionary, null is 
     * returned.
     * @param key the key to search for
     * @return the key from the Dictionary, if the key is in the Dictionary; 
     *         null if the key is not in the Dictionary
     */
    K lookup(K key);
    
    /**
     * Returns true if and only if the Dictionary is empty.
     * @return true if the Dictionary is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the number of keys in the Dictionary.
     * @return the number of keys in the Dictionary
     */
    int size();    
    
    /**
     * Returns the total path length.  The total path length is the sum of  
     * the lengths of the paths to each (key, value) pair.
     * @return the total path length
     */
    int totalPathLength();

    /**
     * Returns an iterator over the Dictionary that iterates over the keys 
     * in the Dictionary in order from smallest to largest.
     * @return an iterator over the keys in the Dictionary in order
     */
    Iterator<K> iterator();
}
