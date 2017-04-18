import java.util.*;

/**
 * The ArrayListLoop class implements the Loop ADT (interface) using an 
 * ArrayList internally to hold the items in the loop.
 * 
 * You can use this implementation of a Loop to develop and test your 
 * MessageLoopEditor independently of your LinkedLoop class. 
 * 
 * Note: this class does NOT meet all the specifications of the Loop interface.
 * In particular, it does not throw EmptyLoopExceptions.
 * 
 * @author Beck Hasti (hasti@cs.wisc.edu) for CS 367, Summer 2014
 */
public class ArrayListLoop<E> implements Loop<E> {
    private ArrayList<E> loop;
    private int curr;  // index of current item
    
    /**
     * Constructs a new empty loop.
     */
    public ArrayListLoop() {
        loop = new ArrayList<E>();
        curr = -1;
    }

    public void backward() {
        curr = (curr == 0) ? loop.size() - 1 : curr - 1;
    }

    public void forward() {
        curr = (curr == loop.size()-1) ? 0 : curr + 1;
    }
    
    public E getCurrent() {
        return loop.get(curr);
    }

    public void insert(E item) {
        if (loop.isEmpty()) {
            loop.add(0, item);
            curr = 0;
        } else {
            loop.add(curr, item);
        }    
    }

    public boolean isEmpty() {
        return loop.isEmpty();
    }

    public Iterator<E> iterator() {
        int size = loop.size();
        ArrayList<E> copy = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            copy.add(loop.get((curr + i)%size));
        }
        return copy.iterator();
    }

    public E removeCurrent() {
        E item = loop.remove(curr);
        if (curr == loop.size()) { // removed at last position in the list
            curr = 0;              // position 0 is next
        }
        if (loop.isEmpty()) {
            curr = -1;
        }
        return item;
    }

    public int size() {
        return loop.size();
    }
}
