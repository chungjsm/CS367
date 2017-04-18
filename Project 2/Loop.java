import java.util.*;

/**
 * A Loop ADT is a circular sequence of items.  A Loop has a current item and
 * the ability to move forward or backwards.  A Loop can be modified by 
 * removing the current item or by adding an item before the current item.
 * 
 * @author Beck Hasti (hasti@cs.wisc.edu) for CS 367, Summer 2014
 */
public interface Loop<E> extends Iterable<E> {
    
    /**
     * Returns the current item.  If the Loop is empty, an 
     * <tt>EmptyLoopException</tt> is thrown.
     * 
     * @return the current item
     * @throws EmptyLoopException if the Loop is empty
     */
    E getCurrent();
    
    /**
     * Adds the given <tt>item</tt> immediately <em>before</em> the current 
     * item.  After the new item has been added, the new item becomes the 
     * current item.
     * 
     * @param item the item to add
     */
    void insert(E item);
    
    /**
     * Removes and returns the current item.  The item immediately 
     * <em>after</em> the removed item then becomes the  current item.  
     * If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     * 
     * @return the removed item
     * @throws EmptyLoopException if the Loop is empty
     */
    E removeCurrent();
    
    /**
     * Advances the current item forward one item resulting in the item 
     * that is immediately <em>after</em> the current item becoming the  
     * current item.  If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     *
     * @throws EmptyLoopException if the Loop is empty
     */
    void forward();
    
    /**
     * Moves the current item backward one item resulting in the item 
     * that is immediately <em>before</em> the current item becoming the  
     * current item.  If the Loop is empty initially, an 
     * <tt>EmptyLoopException</tt> is thrown.
     *
     * @throws EmptyLoopException if the Loop is empty
     */
    void backward();
    
    /**
     * Returns the number of items in this Loop.
     * @return the number of items in this Loop
     */
    int size();

    /**
     * Returns true if this Loop is empty and false otherwise.
     * @return true if this Loop is empty; false otherwise.
     **/
    boolean isEmpty();
    
    /**
     * Returns an iterator for this Loop.
     * @return an iterator for this Loop
     */
    Iterator<E> iterator();
}
