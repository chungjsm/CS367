///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Project 4
// Files:            ComparisonSort.java, Questions.txt
// Semester:         CS 367 - Summer 2016
//
// Author:           Jinsoo Chung
// Email:            jmchung2@wisc.edu
// CS Login:         jinsoo
// Lecturer's Name:  Amanda Strominger
// Lab Section:      NA
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     NA
// Email:            NA
// CS Login:         NA
// Lecturer's Name:  NA
// Lab Section:      NA
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          NA
//
// Online sources:   NA
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class implements six different comparison sorts (and an optional
 * seventh sort for extra credit):
 * 1. selection sort
 * 2. insertion sort
 * 3. merge sort
 * 4. quick sort
 * 5. heap sort
 * 6. selection2 sort
 * 7. insertion2 sort (EXTRA CREDIT)
 * It also has a method that runs all the sorts on the same input array and
 * prints out statistics.
 */

public class ComparisonSort {
	// data move counter
    private static int dataMoves = 0;
	
   /**
    * Resets the data move counter to 0.
    */
   public static void resetDataMoves() {
       dataMoves = 0;
   }

    /**
     * Sorts the given array using the selection sort algorithm. You may use
     * either the algorithm discussed in the on-line reading or the algorithm
     * discussed in lecture (which does fewer data moves than the one from the
     * on-line reading). Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void selectionSort(E[] A) {
        if (A == null) {
            throw new NullPointerException();
        }
   	 
        int j;					// current element's position
        int k;					// position in the array to find element for
        int minIndex;			// index of lowest element
        int N = A.length;       // length of the array
       
        for (k = 0; k < N; k++) {
            minIndex = k;
            // find smallest value in k+1 to end of array
            for (j = k+1; j < N; j++) {
                if (A[j].compareTo(A[minIndex]) < 0) {
                minIndex = j;
         	 }
          }
          // swap the min with value at k
          E min = A[minIndex];
          dataMoves++;
          A[minIndex] = A[k];
          dataMoves++;
          A[k] = min;
          dataMoves++;
       }
    }

    /**
     * Sorts the given array using the insertion sort algorithm. Note: after
     * this method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void insertionSort(E[] A) {
        if (A == null) {
            throw new NullPointerException();
        }
   	 
        int k;              // position of element being inserted
        int j;              // position of current element to compare to
        E tmp;              // holder for the element being inserted
        int N = A.length;	// length of the array
         
        for (k = 1; k < N; k++) {
            tmp = A[k];
            dataMoves++;
            j = k - 1;
            while ((j >= 0) && (A[j].compareTo(tmp) > 0)) {
                A[j + 1] = A[j]; // move one value over one place to the right
                dataMoves++;
                j--;
            }
            A[j + 1] = tmp;    // insert kth value in correct place relative
                               // to previous values
            dataMoves++;
        }
    }

    /**
     * Sorts the given array using the merge sort algorithm. Note: after this
     * method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void mergeSort(E[] A) {
        if (A == null) {
            throw new NullPointerException();
        }
   	 
        if (A.length == 0) {
            return;         // nothing to sort
        }
   	 
        mergeAux(A, 0, A.length - 1); // call the aux. function to do all the work
    }
    
    /**
     * The recursive auxiliary method for doing a merge sort. It divides the 
     * array in half, doing a merge sort on both halves. It then places items in 
     * a temporary array one by one (by comparing the lowest item in each half) 
     * and finally copies them into the original array.
     * 
     * @param A The array to sort
     * @param low Lower bound of portion to be sorted
     * @param high Upper bound of portion to be sorted
     */
    private static <E extends Comparable<E>> void mergeAux(E[] A, int low, int high) {
        // base case
        if (low == high) {
            return;
        }
    
        // recursive case
       
        // Step 1: Find the middle of the array (conceptually, divide it in half)
        int mid = (low + high) / 2;
        
        // Steps 2 and 3: Sort the 2 halves of A
        mergeAux(A, low, mid);
        mergeAux(A, mid + 1, high);
    
        // Step 4: Merge sorted halves into an auxiliary array
        E[] tmp = (E[])(new Comparable[high - low + 1]);
        int left = low;          // index into left half
        int right = mid + 1;     // index into right half
        int pos = 0;         // index into tmp
        
        while ((left <= mid) && (right <= high)) {
            // choose the smaller of the two values "pointed to" by left, right
            // copy that value into tmp[pos]
            // increment either left or right as appropriate
            // increment pos
            if (A[left].compareTo(A[right]) <= 0) {
      	        tmp[pos] = A[left];
      	        dataMoves++;
      	        left++;
      	    }
      	    else {
      	        tmp[pos] = A[right];
      	        dataMoves++;
      	        right++;
      	    }
      	    pos++;
        }
       
        // when one of the two sorted halves has "run out" of values, but
        // there are still some in the other half, copy all the remaining
        // values to tmp
        // Note: only 1 of the next 2 loops will actually execute
        while (left <= mid) {
            tmp[pos] = A[left];
            dataMoves++;
            left++;
            pos++;
        }
        while (right <= high) {
            tmp[pos] = A[right];
            dataMoves++;
            right++;
            pos++;
        }
    
        // all values are in tmp; copy them back into A
        System.arraycopy(tmp, 0, A, low, tmp.length);
        dataMoves += tmp.length;
    }

    /**
     * Sorts the given array using the quick sort algorithm, using the median of
     * the first, last, and middle values in each segment of the array as the
     * pivot value. Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A   the array to sort
     */
    public static <E extends Comparable<E>> void quickSort(E[] A) {
        if (A == null) {
            throw new NullPointerException();
        }
        if (A.length == 0) {
            return;     // nothing to sort
        }
   	 
        quickAux(A, 0, A.length - 1);	// call the aux. function to do the sort
    }
    
    /**
     * The auxiliary method for doing a quick sort. It partitions the 
     * array and calls itself recursively on each portion.
     * 
     * @param A The array to sort
     * @param low Lower bound of portion to be sorted
     * @param high Upper bound of portion to be sorted
     */
    private static <E extends Comparable<E>> void quickAux(E[] A, int low, int high) {
   	 // if length is two, compare and swap if necessary
   	 // if length is one, nothing happens
       if (high - low < 2) {
      	 if (A[low].compareTo(A[high]) > 0) {
      		 E tmp = A[low];
      		 dataMoves++;
      		 A[low] = A[high];
      		 dataMoves++;
      		 A[high] = tmp;
      		 dataMoves++;
      	 }
       }
        
       else {
           int right = partition(A, low, high);
           quickAux(A, low, right);
           quickAux(A, right + 2, high);
       }
    }
    
    /**
     * Partition calls medianOfThree, then sorts values to the left or right of 
     * the pivot. It does this by using right and left indexes that move toward 
     * each other, swapping when they have values in the "wrong" side of the 
     * array.
     * 
     * @param A The array to sort
     * @param low The lower bound of the portion to be sorted
     * @param high The upper bound of the portion to be sorted
     * @return The index to the left of the pivot
     */
    private static <E extends Comparable<E>> int partition(E[] A, int low, int high) {
   	// precondition: A.length > 2
		E pivot = medianOfThree(A, low, high);// store the pivot from medianOfThree
		dataMoves++;
		
		int left = low + 1;		// item just after the low item
		int right = high - 2;	// item before the pivot
		while (left <= right) {
			// increment left until an item not less than pivot
			while (A[left].compareTo(pivot) < 0) {
				left++;
			}
            
			// decrement right until an item not greater than pivot
			while (A[right].compareTo(pivot) > 0) {
				right--;
			}
            
			// as long as left and right index still haven't crossed, swap
			if (left <= right) {
				E tmp = A[left];
				dataMoves++;
				A[left] = A[right];
				dataMoves++;
				A[right] = tmp;
				dataMoves++;
				left++;
				right--;
			}
		}

		// swap the lowest item in the right portion with the pivot
		E tmp = A[right + 1];
		dataMoves++;
		A[right + 1] = A[high - 1];
		dataMoves++;
		A[high - 1] = tmp;
		dataMoves++;
		
		return right;
    }
    
    /**
     * Determines the min, max, and median from three values, then puts them in 
     * the appropriate position of the array for a quick sort. Min goes to the 
     * left, max to the right, and median one left of the max.
     * 
     * @param A The array containing the items
     * @param low Index of the left-most item
     * @param high Index of the right-most item
     * @return The median item
     */
    private static <E extends Comparable<E>> E medianOfThree(E[] A, int low, int high) {
        int minIndex = low;							// index of smallest item
        int medianIndex = (low + high) / 2;         // index of median item
        int maxIndex = high;						// index of largest item
   	 
        // swap if current min > current median
        if (A[minIndex].compareTo(A[medianIndex]) > 0) {
            int tmp = medianIndex;
            medianIndex = minIndex;
            minIndex = temp;
        }
        
        // swap if current median greater > current max
        if (A[medianIndex].compareTo(A[maxIndex]) > 0) {
            int tmp = medianIndex;
            medianIndex = maxIndex;
            maxIndex = temp;
        }
        
        // swap if current min > current median
        if (A[minIndex].compareTo(A[medianIndex]) > 0) {
            int tmp = medianIndex;
            medianIndex = minIndex;
            minIndex = temp;
        }
   	 
        // put everything in the right place
        E min = A[minIndex];
        dataMoves++;
        E median = A[medianIndex];
        dataMoves++;
        E max = A[maxIndex];
        dataMoves++;
        A[low] = min;
        dataMoves++;
        A[high] = max;
        dataMoves++;
        A[(low + high) / 2] = A[high - 1];
        dataMoves++;
        A[high - 1] = median;
        dataMoves++;
   	 
        return A[high - 1];
    }


    /**
     * Sorts the given array using the heap sort algorithm outlined below. Note:
     * after this method finishes the array is in sorted order.
     *
     * The heap sort algorithm is:
     *
     * for each i from 1 to the end of the array
     *     insert A[i] into the heap (contained in A[0]...A[i-1])
     *     
     * for each i from the end of the array up to 1
     *     remove the max element from the heap and put it in A[i]
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void heapSort(E[] A) {
		if (A == null) {
			throw new NullPointerException();
		}

		// Create the heap:
		// For 1 to end of array: compare child with their parent. If child is
		// greater than parent, swap, and continue upwards comparing to next parent
		for (int i = 1; i < A.length; i++) {
			int parentIndex = (i - 1) / 2;	//index of child's parent
			int childIndex = i;					//index of child
			while (childIndex > 0 && A[childIndex].compareTo(A[parentIndex]) > 0) {
				E tmp = A[childIndex];
				dataMoves++;
				A[childIndex] = A[parentIndex];
				dataMoves++;
				A[parentIndex] = tmp;
				dataMoves++;
				childIndex = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			}
		}
		
		// Sort from the heap:
		// Start removing the max, swapping it with the last unsorted item
		// (index i). Reorder the heap as necessary.
		for (int i = A.length - 1; i > 0; i--) {
			E tmp = A[i];
			dataMoves++;
			A[i] = A[0];
			dataMoves++;
			A[0] = tmp;
			dataMoves++;

			int currentIndex = 0;		// index of the item to reorder
			boolean ordered = false;	// whether the heap is ordered
			while (!ordered) {
				// no children
				if ((currentIndex * 2) + 1 > i - 1) {
					ordered = true;
				}
                
				// one child (left)
				else if ((currentIndex * 2) + 2 > i - 1) {
					// child is larger
					if (A[currentIndex].compareTo(A[(currentIndex * 2) + 1]) < 0) {
						E parent = A[currentIndex];
						dataMoves++;
						A[currentIndex] = A[(currentIndex * 2) + 1];
						dataMoves++;
						A[(currentIndex * 2) + 1] = parent;
						dataMoves++;
						currentIndex = (currentIndex * 2) + 1;
					}
					// child is not larger
					else {
						ordered = true;
					}
				}
                
				// two children
				else {
					// find larger child
					int lgChildIndex;	// index of the larger of the two children
					if (A[(currentIndex * 2) + 1]
							.compareTo(A[(currentIndex * 2) + 2]) > 0) {
						lgChildIndex = (currentIndex * 2) + 1;
					}
                    else {
						lgChildIndex = (currentIndex * 2) + 2;
					}
					// swap if less than larger child
					if (A[currentIndex].compareTo(A[lgChildIndex]) < 0) {
						E parent = A[currentIndex];
						dataMoves++;
						A[currentIndex] = A[lgChildIndex];
						dataMoves++;
						A[lgChildIndex] = parent;
						dataMoves++;
						currentIndex = lgChildIndex;
					}
                    else {
						ordered = true;
					}
				}
			}
		}
    }

    /**
     * Sorts the given array using the selection2 sort algorithm outlined
     * below. Note: after this method finishes the array is in sorted order.
     *
     * The selection2 sort is a bi-directional selection sort that sorts
     * the array from the two ends towards the center. The selection2 sort
     * algorithm is:
     *
     * begin = 0, end = A.length-1
     * 
     * // At the beginning of every iteration of this loop, we know that the 
     * // elements in A are in their final sorted positions from A[0] to A[begin-1]
     * // and from A[end+1] to the end of A.  That means that A[begin] to A[end] are
     * // still to be sorted.
     * do
     *     use the MinMax algorithm (described below) to find the minimum and maximum 
     *     values between A[begin] and A[end]
     *     
     *     swap the maximum value and A[end]
     *     swap the minimum value and A[begin]
     *     
     *     ++begin, --end
     * until the middle of the array is reached
     *
     * The MinMax algorithm allows you to find the minimum and maximum of N
     * elements in 3N/2 comparisons (instead of 2N comparisons). The way to do
     * this is to keep the current min and max; then
     *
     * 1. take two more elements and compare them against each other
     * 2. compare the current max and the larger of the two elements
     * 3. compare the current min and the smaller of the two elements
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void selection2Sort(E[] A) {
        if (A == null) {
            throw new NullPointerException();
        }
   	 
        int begin = 0;			// beginning of unsorted portion of array
        int end = A.length - 1; // end of unsorted portion of array
        int minIndex            // index of min value in unsorted portion
        int maxIndex;           // index of max value in unsorted portion
		 
        while (begin < end) {
            // index of the two items to compare
            int i = begin;
            int j = i + 1;
   		 
            // set min to smaller of i and j, max to larger
            if (A[i].compareTo(A[j]) < 0) {
                minIndex = i;
                maxIndex = j;
            }
            else {
                minIndex = j;
                maxIndex = i;
            }
   		 
            // keep comparing pairs until end
            while (i < end) {
                // if i is smaller, compare it to min and j to max
                if (A[i].compareTo(A[j]) < 0) {
                    if (A[i].compareTo(A[minIndex]) < 0) {
                        minIndex = i;
                    }
                    if (A[j].compareTo(A[maxIndex]) > 0) {
                        maxIndex = j;
                    }
                }
                // compare j to min and i to max
                else {
                    if (A[j].compareTo(A[minIndex]) < 0) {
                        minIndex = j;
   				 }
   				 if (A[i].compareTo(A[maxIndex]) > 0) {
   					 maxIndex = i;
   				 }
   			 }
   			 i += 2;
   			 j += 2;
   		 }
   		 
   		 // swap min with begin
   		 E min = A[minIndex];
   		 dataMoves++;
   		 A[minIndex] = A[begin];
   		 dataMoves++;
   		 A[begin] = min;
   		 dataMoves++;
   		 
   		 // if max item was previously in begin, it is now in what was minIndex
   		 // (because of the above swap)
   		 if (maxIndex == begin) {
   			 maxIndex = minIndex;
   		 }
   		 
   		 // swap max with end
   		 E max = A[maxIndex];
   		 dataMoves++;
   		 A[maxIndex] = A[end];
   		 dataMoves++;
   		 A[end] = max;
   		 dataMoves++;
   		 
   		 begin++;
   		 end--;
        }
    }

    
    /**
     * Extra Credit: Sorts the given array using the insertion2 sort
     * algorithm outlined below.  Note: after this method finishes the array 
     * is in sorted order.
     *
     * The insertion2 sort is a bi-directional insertion sort that sorts the 
     * array from the center out towards the ends.  The insertion2 sort 
     * algorithm is:
     *
     * precondition: A has an even length
     * left = element immediately to the left of the center of A
     * right = element immediately to the right of the center of A
     * if A[left] > A[right]
     *     swap A[left] and A[right]
     * left--, right++ 
     *  
     * // At the beginning of every iteration of this loop, we know that the elements
     * // in A from A[left+1] to A[right-1] are in relative sorted order.
     * do
     *     if (A[left] > A[right])
     *         swap A[left] and A[right]
     *  
     *     starting with with A[right] and moving to the left, use insertion sort 
     *     algorithm to insert the element at A[right] into the correct location 
     *     between A[left+1] and A[right-1]
     *     
     *     starting with A[left] and moving to the right, use the insertion sort 
     *     algorithm to insert the element at A[left] into the correct location 
     *     between A[left+1] and A[right-1]
     *  
     *     left--, right++
     * until left has gone off the left edge of A and right has gone off the right 
     *       edge of A
     *
     * This sorting algorithm described above only works on arrays of even 
     * length.  If the array passed in as a parameter is not even, the method 
     * throws an IllegalArgumentException
     *
     * @param  A the array to sort
     * @throws IllegalArgumentException if the length or A is not even
     */    
    public static <E extends Comparable<E>> void insertion2Sort(E[] A) { 
        if (A == null) {
            throw new NullPointerException();
        }
   	 
        if (A.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        
        // nothing to sort
        if (A.length == 0) {
            return;
        }
   	 
        int right = A.length / 2;		// item on the right side to be inserted
        int left = (A.length / 2) - 1;	// item on the left side to be inserted
   	 
        // if left is bigger, swap
        if (A[left].compareTo(A[right]) > 0) {
            E tmp = A[right];
            dataMoves++;
            A[right] = A[left];
            dataMoves++;
            A[left] = tmp;
            dataMoves++;
        }
        
        left--;
        right++;
   	 
        while (left >= 0 && right < A.length) {
            // swap if left is bigger
            if (A[left].compareTo(A[right]) > 0) {
                E tmp = A[right];
                dataMoves++;
                A[right] = A[left];
                dataMoves++;
                A[left] = tmp;
                dataMoves++;
            }

            // do an insertion sort for right item
            E tmpRt = A[right];
            dataMoves++;
            int i = right - 1;
            while ((i >= left + 1) && (A[i].compareTo(tmpRt) > 0)) {
                A[i + 1] = A[i]; //move one value over one place to the right
                dataMoves++;
                i--;
            }
          
            A[i + 1] = tmpRt;	//insert right value in correct place relative
                                //to previous values
            dataMoves++;

            // do an insertion sort for left item
            E tmpLt = A[left];
            dataMoves++;
            int j = left + 1;
            while ((j <= right - 1) && (A[j].compareTo(tmpLt) < 0)) {
                A[j - 1] = A[j]; //move one value over one place to the left
                dataMoves++;
                j++;
            }
            
            // insert left value in correct place relative to previous values
            A[j - 1] = tmpLt;
            dataMoves++;
          
            left--;
            right++;
        }
    }

    /**
     * Internal helper for printing rows of the output table.
     * 
     * @param sort          name of the sorting algorithm
     * @param compares      number of comparisons performed during sort
     * @param moves         number of data moves performed during sort
     * @param milliseconds  time taken to sort, in milliseconds
     */
    private static void printStatistics(String sort, int compares, int moves,
                                        long milliseconds) {
        System.out.format("%-23s%,15d%,15d%,15d\n", sort, compares, moves, 
                          milliseconds);
    }

    /**
     * Sorts the given array using the six (seven with the extra credit)
     * different sorting algorithms and prints out statistics. The sorts 
     * performed are:
     * 1. selection sort
     * 2. insertion sort
     * 3. merge sort
     * 4. quick sort
     * 5. heap sort
     * 6. selection2 sort
     * 7. insertion2 sort (EXTRA CREDIT)
     *
     * The statistics displayed for each sort are: number of comparisons, 
     * number of data moves, and time (in milliseconds).
     *
     * Note: each sort is given the same array (i.e., in the original order) 
     * and the input array A is not changed by this method.
     * 
     * @param A  the array to sort
     */
    static public void runAllSorts(SortObject[] A) {
        System.out.format("%-23s%15s%15s%15s\n", "algorithm", "data compares",
                          "data moves", "milliseconds");
        System.out.format("%-23s%15s%15s%15s\n", "---------", "-------------", 
                          "----------", "------------");

        int totalDataCompares;      // number of data compares for sort
        int totalDataMoves;         // number of data moves for sort
        long startTime;				// time at start of sort
        long endTime;				// time at end of sort
        long totalTime;				// total time taken for sort
        
        // selection sort
        SortObject[] ssArray = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        selectionSort(ssArray);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("selection", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // insertion sort
        SortObject[]  isArray = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        insertionSort(isArray);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("insertion", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // merge sort
        SortObject[]  msArray = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        mergeSort(msArray);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("merge", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // quick sort
        SortObject[]  qsArray = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        quickSort(qsArray);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("quick", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // heap sort
        SortObject[]  hsArray = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        heapSort(hsArray);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("heap",
totalDataCompares, totalDataMoves, totalTime);
        
        // selection2 sort
        SortObject[]  ss2Array = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        selection2Sort(ss2Array);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("selection2", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // (extra credit) insertion2 sort
        SortObject[] is2Array = A.clone();	// clone to be sorted
        SortObject.resetCompares();
        dataMoves = 0;
        
        startTime = System.currentTimeMillis();
        insertion2Sort(is2Array);
        endTime = System.currentTimeMillis();
        
        totalTime = endTime - startTime;
        totalDataCompares = SortObject.getCompares();
        totalDataMoves = dataMoves;
        
        printStatistics("insertion2", 
      		  totalDataCompares, totalDataMoves, totalTime);
        
        // reset at end of method
        SortObject.resetCompares();
        dataMoves = 0;
    }
}
