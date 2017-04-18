// Copyright 2014 Beck Hasti. All rights reserved.

/**
 * A SortObject is essentially an integer wrapper class that keeps track of
 * the number of comparisons that are done on SortObjects.
 * 
 * @author Beck Hasti <hasti@cs.wisc.edu>
 * 
 */
public class SortObject implements Comparable<SortObject> {
    private static int compares = 0; // the comparison counter
    private int data;                // data for each SortObject object

    /**
     * Resets the comparison counter to 0.
     */
    public static void resetCompares() {
        compares = 0;
    }

    /**
     * Returns the value of the comparison counter.
     * 
     * @return the value of the comparison counter
     */
    public static int getCompares() {
        return compares;
    }

    /**
     * Creates a new SortObject with the given value.
     * 
     * @param data  the integer value for this object
     */
    public SortObject(int data) {
        this.data = data;
    }

    /**
     * Returns the integer data value for this SortObject.
     * 
     * @return the integer data value for this SortObject
     */
    public int getData() {
        return data;
    }

    /**
     * Compares this SortObject to the one given and increments the 
     * comparison counter.
     * 
     * @param other  the item to compare to
     * @return < 0, 0, > 0 depending on whether this SortObject is less than,
     *         equal to, or greater than the one given
     */
    public int compareTo(SortObject other) {
        compares++;
        if (data == other.data)
            return 0;
        else if (data < other.data)
            return -1;
        else
            return 1;
    }

    /**
     * Returns true if the given SortObject has the same integer data value as
     * this one.
     * 
     * @param obj  other object to compare with this one
     * @return true if the integer data values are the same
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SortObject))
            return false;
        return ((SortObject) obj).data == this.data;
    }

    /**
     * Returns a String representation, in this case a String containing the
     * integer data value.
     * 
     * @return a String representation suitable for printing
     */
    public String toString() {
        return Integer.toString(data);
    }
}
