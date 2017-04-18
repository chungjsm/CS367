import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ComparisonSortTest {
	
	public static SortObject[] baseArrayNull;
	public static SortObject[] baseArrayEmpty;
	public static SortObject[] baseArrayOne;
	public static SortObject[] baseArrayTwo;
	public static SortObject[] baseArrayOrdered;
	public static SortObject[] baseArrayReversed;
	public static SortObject[] baseArrayJumbled1;
	public static SortObject[] baseArrayJumbled2;
	public static SortObject[] copyArrayNull;
	public static SortObject[] copyArrayEmpty;
	public static SortObject[] copyArrayOne;
	public static SortObject[] copyArrayTwo;
	public static SortObject[] copyArrayOrdered;
	public static SortObject[] copyArrayReversed;
	public static SortObject[] copyArrayJumbled1;
	public static SortObject[] copyArrayJumbled2;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		SortObject[] objectsToSort = new SortObject[20];
		for (int i = 0; i < 10; i++) {
			objectsToSort[i] = new SortObject(i);
		}
		objectsToSort[10] = new SortObject(9);
		for (int i = 11; i < 19; i++) {
			objectsToSort[i] = new SortObject(i - 1);
		}
		objectsToSort[19] = new SortObject(17);
		
		baseArrayNull = null;
		baseArrayEmpty = new SortObject[0];
		baseArrayOne = new SortObject[1];
		baseArrayOne[0] = new SortObject(1);
		baseArrayTwo = new SortObject[2];
		baseArrayTwo[0] = new SortObject(2);
		baseArrayTwo[1] = new SortObject(1);
		
		baseArrayOrdered = new SortObject[20];
		for (int i = 0; i < 20; i++) {
			baseArrayOrdered[i] = objectsToSort[i];
		}
		
		baseArrayReversed = new SortObject[20];
		for (int i = 0; i < 20; i++) {
			baseArrayReversed[19 - i] = objectsToSort[i];
		}
		
		baseArrayJumbled1 = new SortObject[20];
		baseArrayJumbled1[0] = objectsToSort[17];
		baseArrayJumbled1[1] = objectsToSort[1];
		baseArrayJumbled1[2] = objectsToSort[4];
		baseArrayJumbled1[3] = objectsToSort[5];
		baseArrayJumbled1[4] = objectsToSort[13];
		baseArrayJumbled1[5] = objectsToSort[0];
		baseArrayJumbled1[6] = objectsToSort[15];
		baseArrayJumbled1[7] = objectsToSort[3];
		baseArrayJumbled1[8] = objectsToSort[12];
		baseArrayJumbled1[9] = objectsToSort[7];
		baseArrayJumbled1[10] = objectsToSort[2];
		baseArrayJumbled1[11] = objectsToSort[10];
		baseArrayJumbled1[12] = objectsToSort[16];
		baseArrayJumbled1[13] = objectsToSort[14];
		baseArrayJumbled1[14] = objectsToSort[18];
		baseArrayJumbled1[15] = objectsToSort[9];
		baseArrayJumbled1[16] = objectsToSort[8];
		baseArrayJumbled1[17] = objectsToSort[11];
		baseArrayJumbled1[18] = objectsToSort[19];
		baseArrayJumbled1[19] = objectsToSort[6];
		
		baseArrayJumbled2 = new SortObject[20];
		baseArrayJumbled2[0] = objectsToSort[9];
		baseArrayJumbled2[1] = objectsToSort[6];
		baseArrayJumbled2[2] = objectsToSort[14];
		baseArrayJumbled2[3] = objectsToSort[7];
		baseArrayJumbled2[4] = objectsToSort[1];
		baseArrayJumbled2[5] = objectsToSort[18];
		baseArrayJumbled2[6] = objectsToSort[4];
		baseArrayJumbled2[7] = objectsToSort[19];
		baseArrayJumbled2[8] = objectsToSort[12];
		baseArrayJumbled2[9] = objectsToSort[8];
		baseArrayJumbled2[10] = objectsToSort[17];
		baseArrayJumbled2[11] = objectsToSort[13];
		baseArrayJumbled2[12] = objectsToSort[3];
		baseArrayJumbled2[13] = objectsToSort[0];
		baseArrayJumbled2[14] = objectsToSort[11];
		baseArrayJumbled2[15] = objectsToSort[2];
		baseArrayJumbled2[16] = objectsToSort[15];
		baseArrayJumbled2[17] = objectsToSort[10];
		baseArrayJumbled2[18] = objectsToSort[5];
		baseArrayJumbled2[19] = objectsToSort[16];
	}

	@Before
	public void setUp() throws Exception {
		
		copyArrayNull = null;
		copyArrayEmpty = new SortObject[0];
		copyArrayOne = new SortObject[1];
		copyArrayOne[0] = new SortObject(1);
		copyArrayTwo = new SortObject[2];
		copyArrayTwo[0] = new SortObject(2);
		copyArrayTwo[1] = new SortObject(1);
		
		copyArrayOrdered = new SortObject[baseArrayOrdered.length];
		System.arraycopy(baseArrayOrdered, 0, copyArrayOrdered, 0, baseArrayOrdered.length);;
		
		copyArrayReversed = new SortObject[baseArrayReversed.length];
		System.arraycopy(baseArrayReversed, 0, copyArrayReversed, 0, baseArrayReversed.length);
		
		copyArrayJumbled1 = new SortObject[baseArrayJumbled1.length];
		System.arraycopy(baseArrayJumbled1, 0, copyArrayJumbled1, 0, baseArrayJumbled1.length);;
		
		copyArrayJumbled2 = new SortObject[baseArrayJumbled2.length];
		System.arraycopy(baseArrayJumbled2, 0, copyArrayJumbled2, 0, baseArrayJumbled2.length);;
	}

	@Test
	public void testSelectionSort() {
//		ComparisonSort.selectionSort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.selectionSort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.selectionSort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.selectionSort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.selectionSort(copyArrayOrdered);
		ComparisonSort.selectionSort(copyArrayReversed);
		ComparisonSort.selectionSort(copyArrayJumbled1);
		ComparisonSort.selectionSort(copyArrayJumbled2);
		
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testSelectionSortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.selectionSort(copyArrayNull);
	}

	@Test
	public void testInsertionSort() {

//		ComparisonSort.insertionSort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.insertionSort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.insertionSort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.insertionSort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.insertionSort(copyArrayOrdered);
		ComparisonSort.insertionSort(copyArrayReversed);
		ComparisonSort.insertionSort(copyArrayJumbled1);
		ComparisonSort.insertionSort(copyArrayJumbled2);
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testInsertionSortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.insertionSort(copyArrayNull);
	}

	@Test
	public void testMergeSort() {
//		ComparisonSort.mergeSort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.mergeSort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.mergeSort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.mergeSort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.mergeSort(copyArrayOrdered);
		ComparisonSort.mergeSort(copyArrayReversed);
		ComparisonSort.mergeSort(copyArrayJumbled1);
		ComparisonSort.mergeSort(copyArrayJumbled2);
		
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testMergeSortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.mergeSort(copyArrayNull);
	}

	@Test
	public void testQuickSort() {
		
//		ComparisonSort.quickSort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.quickSort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.quickSort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.quickSort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.quickSort(copyArrayOrdered);
		ComparisonSort.quickSort(copyArrayReversed);
		ComparisonSort.quickSort(copyArrayJumbled1);
		ComparisonSort.quickSort(copyArrayJumbled2);
		
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testQuickSortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.quickSort(copyArrayNull);
	}

	@Test
	public void testHeapSort() {
		
//		ComparisonSort.heapSort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.heapSort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.heapSort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.heapSort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.heapSort(copyArrayOrdered);
		ComparisonSort.heapSort(copyArrayReversed);
		ComparisonSort.heapSort(copyArrayJumbled1);
		ComparisonSort.heapSort(copyArrayJumbled2);
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testHeapSortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.heapSort(copyArrayNull);
	}

	@Test
	public void testSelection2Sort() {
		
//		ComparisonSort.selection2Sort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.selection2Sort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.selection2Sort(copyArrayOne);
		assertEquals(copyArrayOne[0], new SortObject(1));
		ComparisonSort.selection2Sort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.selection2Sort(copyArrayOrdered);
		ComparisonSort.selection2Sort(copyArrayReversed);
		ComparisonSort.selection2Sort(copyArrayJumbled1);
		ComparisonSort.selection2Sort(copyArrayJumbled2);
		
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testSelection2SortException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.selection2Sort(copyArrayNull);
	}

	@Test
	public void testInsertion2Sort() {
//		ComparisonSort.insertion2Sort(copyArrayNull);
//		assertTrue(copyArrayNull == null);
		ComparisonSort.insertion2Sort(copyArrayEmpty);
		assertTrue(copyArrayEmpty.length == 0);
		ComparisonSort.insertion2Sort(copyArrayTwo);
		assertEquals(copyArrayTwo[0], new SortObject(1));
		assertEquals(copyArrayTwo[1], new SortObject(2));
		
		ComparisonSort.insertion2Sort(copyArrayOrdered);
		ComparisonSort.insertion2Sort(copyArrayReversed);
		ComparisonSort.insertion2Sort(copyArrayJumbled1);
		ComparisonSort.insertion2Sort(copyArrayJumbled2);
		for (int i = 0; i < 20; i++) {
			assertEquals(copyArrayOrdered[i], baseArrayOrdered[i]);
			assertEquals(copyArrayReversed[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled1[i], baseArrayOrdered[i]);
			assertEquals(copyArrayJumbled2[i], baseArrayOrdered[i]);
		}
	}
	
	@Test
	public void testInsertion2SortNPException() {
		thrown.expect(NullPointerException.class);
		ComparisonSort.insertion2Sort(copyArrayNull);
	}
	
	@Test
	public void testInsertion2SortIAException() {
		thrown.expect(IllegalArgumentException.class);
		ComparisonSort.insertion2Sort(copyArrayOne);
	}

//	@Test
//	public void testRunAllSorts() {
//		fail("Not yet implemented");
//	}

}
