///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             ArrayHeap.java
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

import java.util.NoSuchElementException;

/**
 * This class implements the PriorityQueueADT using an ArrayHeap. Items are 
 * stored in an array beginning at index one. They are ordered such that 
 * children will be in index n*2 and n*2 + 1 where n is the parent. Children 
 * have a priority <= their parent.
 *
 * <p>Bugs: None known
 *
 * @author Jinsoo Chung
 */
public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // default number of items the heap can hold before expanding
    private static final int INIT_SIZE = 100;
    private E[] items;          // array of items
    private int numItems;       // number of items in array

    /**
     * Calls the overloaded constructor with parameter INIT_SIZE to initialize 
     * the array with a size large enough to hold INIT_SIZE items.
     */
    public ArrayHeap() {
        this(INIT_SIZE);
    }
    
    /**
     * Constructs an ArrayHeap with an array large enough to hold size items. 
     * Sets numItems to 0.
     * 
     * @param size The number of items that can be stored in the array before it
     * needs to expand.
     */
    public ArrayHeap(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        // array is of size n+1 because arrayheap starts at index 1
        items = (E[])(new Prioritizable[size + 1]);
        numItems = 0;
    }

    /**
     * Returns whether there are no items in the ArrayHeap.
     * 
     * @return Whether the ArrayHeap is empty.
     */
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * Inserts an item into the ArrayHeap by adding it at the end, then swapping
     * with its parent until it is ordered.
     * 
     * @param E The item to insert.
     */
    public void insert(E item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if (items.length == numItems + 1) {
   		 expandArray();
        }
        
        int newIndex = numItems + 1;        // set index for new item
        items[newIndex] = item;             // add item to the end of array
        int parentIndex = newIndex / 2;     // set index of item's parent
   	 
        // keep swapping until ArrayHeap is ordered
        while (parentIndex > 0 &&
            items[parentIndex].getPriority() < items[newIndex].getPriority()) {
            // swap item and parent
            items[newIndex] = items[parentIndex];
            items[parentIndex] = item;
            // reset indices
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        
        numItems++;
    }

    /**
     * Removes and returns the item with the highest priority. It is replaced 
     * with the last value in the array which then swaps down the heap until it 
     * is ordered.
     * 
     * @return The item with the highest priority.
     * @throws NoSuchElementException if the priority queue is empty
     */
    public E removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
   	 
        E max = items[1];             // return first element
        int currentIndex = 1;         // index of the item being swapped with
        E item = items[numItems];     // the item being swapped
        items[currentIndex] = item;   // make the last item the first
        numItems--;
   	 
        boolean ordered = false;	  // whether the heap is ordered
        
        while (!ordered) {
            // case where there are no children
            if (currentIndex * 2 > numItems) {
                ordered = true;
            }
            // case where there is one child
            else if ((currentIndex * 2) + 1 > numItems) {
                // if the child is larger
                if (items[currentIndex].getPriority() <
   					 items[currentIndex * 2].getPriority()) {
                    items[currentIndex] = items[currentIndex * 2];
                    items[currentIndex * 2] = item;
                    currentIndex = currentIndex * 2;
                }
                // if the parent is larger
                else {
                    ordered = true;
                }
            }
            // case where there are two children
            // and at least one of them are larger than the parent
            else if ( (items[currentIndex].getPriority() <
                       items[currentIndex * 2].getPriority() ) ||
                     ( items[currentIndex].getPriority() <
                      items[(currentIndex * 2) + 1].getPriority() ) ) {
                    // case where left child is larger or equal to right
                    if (items[(currentIndex * 2) + 1].getPriority() <=
                        items[currentIndex * 2].getPriority()) {
                        items[currentIndex] = items[currentIndex * 2];
                        items[currentIndex * 2] = item;
                        currentIndex = currentIndex * 2;
                    }
                    // case where right child is larger than left
                    else {
                        items[currentIndex] = items[(currentIndex * 2) + 1];
                        items[(currentIndex * 2) + 1] = item;
                        currentIndex = (currentIndex * 2) + 1;
                    }
            }
            // case where there are two children
            // and neither is larger than the parent
            else {
                ordered = true;
            }
        }
        
   	 return max;
    }

    /**
     * Returns the item with the highest priority by getting the item in index 
     * one.
     * 
     * @return The item with the highest priority.
     * @throws NoSuchElementException if the priority queue is empty
     */
    public E getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[1];
    }

    /**
     * Returns the number of items in the ArrayHeap.
     * 
     * @return The size of the ArrayHeap.
     */
    public int size() {
        return numItems;
    }
    
    /**
     * Doubles the size of the array items, and copies the existing items.
     */
    private void expandArray() {
        E[] newArr = (E[]) new Prioritizable[(numItems + 1) * 2];
        System.arraycopy(items, 0, newArr, 0, numItems + 1);
        items = newArr;
    }

}
