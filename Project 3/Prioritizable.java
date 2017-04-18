/**
 * The <tt>Prioritizable</tt> interface is implemented by classes whose 
 * instances are going to be inserted into a <tt>PriorityQueueADT</tt>. 
 * 
 * The <tt>getPriority</tt> method is used by the priority queue to order 
 * the values returned by <tt>removeMax</tt> (where larger integer values 
 * represent higher priorities).
 * 
 * DO NOT CHANGE THIS FILE
 *  
 * @author CS 367
 */
public interface Prioritizable {
    
    /**
     * Return the priority for this item.
     * @return the priority for this item.
     */
    int getPriority();
}
