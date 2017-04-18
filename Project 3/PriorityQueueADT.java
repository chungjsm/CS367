import java.util.NoSuchElementException;

/**
 * The <tt>PriorityQueue</tt> ADT stores a collection of <tt>Prioritizable</tt>
 * items.
 * 
 * Operations are provided to insert, remove the item with the highest 
 * priority, and get (but not remove) the item with the highest priority.  
 * The priority of an item is determined by calling <tt>getPriority</tt> on 
 * that item; larger values correspond to higher priorities. 
 * 
 * DO NOT CHANGE THIS FILE
 *  
 * @author CS 367
 * @param <E> class representing the item stored in the priority queue, should
 *            implement the Prioritizeble interface
 */
public interface PriorityQueueADT<E extends Prioritizable> {
    
    /**
     * Returns true if this priority queue contains no items.
     * @return true if this priority queue contains no items, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Adds the given item to the priority queue.
     * @param item the item to insert into the priority queue
     */
    void insert(E item);
    
    /**
     * Removes and returns the item with the highest priority.
     * @return the item with the highest priority
     * @throws NoSuchElementException if the priority queue is empty
     */
    E removeMax();
    
    /**
     * Returns the item with the highest priority.
     * @return the item with the highest priority
     * @throws NoSuchElementException if the priority queue is empty
     */
    E getMax();
    
    /**
     * Returns the number of items in this priority queue.
     * @return the number of items in this priority queue
     */
    int size();
}
