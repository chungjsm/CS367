import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class LinkedLoopTest {

	@Rule
   public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testLinkedLoop() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		assertTrue("New loop is not empty", ll.isEmpty());
		assertNull("Current node is not null", ll.getCurrentNode());
	}

	@Test
	public void testGetCurrent() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		assertEquals("Current is not \"a\"", ll.getCurrent(), "a");
		ll.insert("b");
		assertEquals("Current is not \"b\"", ll.getCurrent(), "b");
		ll.forward();
		assertEquals("Current is not \"a\"", ll.getCurrent(), "a");
		ll.forward();
		assertEquals("Current is not \"b\"", ll.getCurrent(), "b");
		ll.backward();
		assertEquals("Current is not \"a\"", ll.getCurrent(), "a");
		ll.backward();
		assertEquals("Current is not \"b\"", ll.getCurrent(), "b");
		ll.removeCurrent();
		assertEquals("Current is not \"a\"", ll.getCurrent(), "a");
		ll.insert("b");
		ll.insert("c");
		assertEquals("Current is not \"c\"", ll.getCurrent(), "c");
		ll.insert("d");
		assertEquals("Current is not \"d\"", ll.getCurrent(), "d");
		ll.forward();
		assertEquals("Current is not \"c\"", ll.getCurrent(), "c");
		ll.removeCurrent();
		assertEquals("Current is not \"b\"", ll.getCurrent(), "b");
		ll.backward();
		assertEquals("Current is not \"d\"", ll.getCurrent(), "d");
		ll.removeCurrent();
		assertEquals("Current is not \"b\"", ll.getCurrent(), "b");
	}
	
	@Test
	public void testGetCurrentException() {
		thrown.expect(EmptyLoopException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.getCurrent();
	}

	@Test
	public void testInsert() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		
		//inserting the first node
		ll.insert("a");
		
		assertTrue("Size is not one", ll.size() == 1);
		assertEquals("Current node isn't \"a\"", ll.getCurrent(), "a");
		assertEquals("Previous is not set to current node for one node loop",
				ll.getCurrentNode().getPrev().getData(), ll.getCurrent());
		assertEquals("Next is not set to current node for one node loop",
				ll.getCurrentNode().getNext().getData(), ll.getCurrent());
		
		
		//inserting a second node
		ll.insert("b");
		
		assertTrue("Size is not two", ll.size() == 2);
		assertEquals("Current node isn't \"b\"", ll.getCurrent(), "b");
		assertEquals("Prev is not \"a\"", 
				ll.getCurrentNode().getPrev().getData(), "a");
		assertEquals("Next is not \"a\"", 
				ll.getCurrentNode().getNext().getData(), "a");
		
		//inserting a third node
		ll.insert("c");
		
		assertTrue("Size is not three", ll.size() == 3);
		assertEquals("Current node isn't \"c\"", ll.getCurrent(), "c");
		assertEquals("Prev is not \"a\"", 
				ll.getCurrentNode().getPrev().getData(), "a");
		assertEquals("Next is not \"b\"", 
				ll.getCurrentNode().getNext().getData(), "b");
		
		//inserting a fourth node
		ll.insert("d");
		
		assertTrue("Size is not four", ll.size() == 4);
		assertEquals("Current node isn't \"d\"", ll.getCurrent(), "d");
		assertEquals("Prev is not \"a\"", 
				ll.getCurrentNode().getPrev().getData(), "a");
		assertEquals("Next is not \"c\"", 
				ll.getCurrentNode().getNext().getData(), "c");
		assertEquals("Two nodes ahead is not \"b\"", 
				ll.getCurrentNode().getNext().getNext().getData(), "b");
	}

	@Test
	public void testRemoveCurrent() {
		//remove from size one loop
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		assertEquals("Value is not \"a\"", ll.removeCurrent(), "a");
		assertTrue("Size is not 0", ll.size() == 0);
		
		//remove from size two loop
		LinkedLoop<String> ll2 = new LinkedLoop<String>();
		ll2.insert("a");
		ll2.insert("b");
		assertEquals("Value is not \"b\"", ll2.removeCurrent(), "b");
		assertTrue("Size is not 1", ll2.size() == 1);
		assertEquals("Value is not \"a\"", ll2.getCurrent(), "a");
		
		//remove from size three loop
		LinkedLoop<String> ll3 = new LinkedLoop<String>();
		ll3.insert("a");
		ll3.insert("b");
		ll3.insert("c");
		assertEquals("Value is not \"c\"", ll3.removeCurrent(), "c");
		assertTrue("Size is not 2", ll3.size() == 2);
		assertEquals("Value is not \"b\"", ll3.getCurrent(), "b");
		
		//remove from size four loop
		LinkedLoop<String> ll4 = new LinkedLoop<String>();
		ll4.insert("a");
		ll4.insert("b");
		ll4.insert("c");
		ll4.insert("d");
		assertEquals("Value is not \"d\"", ll4.removeCurrent(), "d");
		assertTrue("Size is not 3", ll4.size() == 3);
		assertEquals("Value is not \"c\"", ll4.getCurrent(), "c");
		
		//shifts plus remove
		LinkedLoop<String> ll5 = new LinkedLoop<String>();
		ll5.insert("a");
		ll5.insert("b");
		ll5.insert("c");
		ll5.insert("d");
		ll5.forward();
		assertEquals("Value is not \"c\"", ll5.removeCurrent(), "c");
		assertTrue("Size is not 3", ll5.size() == 3);
		assertEquals("Value is not \"b\"", ll5.getCurrent(), "b");
		ll5.backward();
		assertEquals("Value is not \"d\"", ll5.removeCurrent(), "d");
		assertTrue("Size is not 2", ll5.size() == 2);
		assertEquals("Value is not \"b\"", ll5.getCurrent(), "b");
		
		//remove insert remove
		LinkedLoop<String> ll6 = new LinkedLoop<String>();
		ll6.insert("a");
		ll6.insert("b");
		ll6.insert("c");
		ll6.removeCurrent();
		ll6.insert("d");
		assertEquals("Value is not \"d\"", ll6.removeCurrent(), "d");
		assertTrue("Size is not 2", ll6.size() == 2);
		assertEquals("Value is not \"b\"", ll6.getCurrent(), "b");
	}

	@Test
	public void testRemoveCurrentException() {
		thrown.expect(EmptyLoopException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.removeCurrent();
	}
	
	@Test
	public void testForward() {
		LinkedLoop<String> ll1 = new LinkedLoop<String>();
		ll1.insert("a");
		ll1.forward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.forward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.forward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.forward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		
		LinkedLoop<String> ll2 = new LinkedLoop<String>();
		ll2.insert("a");
		ll2.insert("b");
		ll2.forward();
		assertEquals("Value is not \"a\"", ll2.getCurrent(), "a");
		ll2.forward();
		assertEquals("Value is not \"b\"", ll2.getCurrent(), "b");
		ll2.forward();
		assertEquals("Value is not \"a\"", ll2.getCurrent(), "a");
		ll2.forward();
		assertEquals("Value is not \"b\"", ll2.getCurrent(), "b");
		
		LinkedLoop<String> ll3 = new LinkedLoop<String>();
		ll3.insert("a");
		ll3.insert("b");
		ll3.insert("c");
		ll3.forward();
		assertEquals("Value is not \"b\"", ll3.getCurrent(), "b");
		ll3.forward();
		assertEquals("Value is not \"a\"", ll3.getCurrent(), "a");
		ll3.forward();
		assertEquals("Value is not \"c\"", ll3.getCurrent(), "c");
		ll3.forward();
		assertEquals("Value is not \"b\"", ll3.getCurrent(), "b");
		ll3.forward();
		assertEquals("Value is not \"a\"", ll3.getCurrent(), "a");
		
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		ll.insert("b");
		ll.insert("c");
		ll.insert("d");
		ll.insert("e");
		
		ll.forward();
		assertEquals("Value is not \"d\"", ll.getCurrent(), "d");
		ll.forward();
		assertEquals("Value is not \"c\"", ll.getCurrent(), "c");
		ll.forward();
		assertEquals("Value is not \"b\"", ll.getCurrent(), "b");
		ll.forward();
		assertEquals("Value is not \"a\"", ll.getCurrent(), "a");
		ll.forward();
		assertEquals("Value is not \"e\"", ll.getCurrent(), "e");
		ll.forward();
		assertEquals("Value is not \"d\"", ll.getCurrent(), "d");
		ll.forward();
		assertEquals("Value is not \"c\"", ll.getCurrent(), "c");
		ll.forward();
		assertEquals("Value is not \"b\"", ll.getCurrent(), "b");
		ll.forward();
		assertEquals("Value is not \"a\"", ll.getCurrent(), "a");
		ll.forward();
		assertEquals("Value is not \"e\"", ll.getCurrent(), "e");
	}
	
	@Test
	public void testForwardException() {
		thrown.expect(EmptyLoopException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.forward();
	}

	@Test
	public void testBackward() {
		LinkedLoop<String> ll1 = new LinkedLoop<String>();
		ll1.insert("a");
		ll1.backward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.backward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.backward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		ll1.backward();
		assertEquals("Value is not \"a\"", ll1.getCurrent(), "a");
		
		LinkedLoop<String> ll2 = new LinkedLoop<String>();
		ll2.insert("a");
		ll2.insert("b");
		ll2.backward();
		assertEquals("Value is not \"a\"", ll2.getCurrent(), "a");
		ll2.backward();
		assertEquals("Value is not \"b\"", ll2.getCurrent(), "b");
		ll2.backward();
		assertEquals("Value is not \"a\"", ll2.getCurrent(), "a");
		ll2.backward();
		assertEquals("Value is not \"b\"", ll2.getCurrent(), "b");
		
		LinkedLoop<String> ll3 = new LinkedLoop<String>();
		ll3.insert("a");
		ll3.insert("b");
		ll3.insert("c");
		ll3.backward();
		assertEquals("Value is not \"a\"", ll3.getCurrent(), "a");
		ll3.backward();
		assertEquals("Value is not \"b\"", ll3.getCurrent(), "b");
		ll3.backward();
		assertEquals("Value is not \"c\"", ll3.getCurrent(), "c");
		ll3.backward();
		assertEquals("Value is not \"a\"", ll3.getCurrent(), "a");
		ll3.backward();
		assertEquals("Value is not \"b\"", ll3.getCurrent(), "b");
		
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		ll.insert("b");
		ll.insert("c");
		ll.insert("d");
		ll.insert("e");
		
		ll.backward();
		assertEquals("Value is not \"a\"", ll.getCurrent(), "a");
		ll.backward();
		assertEquals("Value is not \"b\"", ll.getCurrent(), "b");
		ll.backward();
		assertEquals("Value is not \"c\"", ll.getCurrent(), "c");
		ll.backward();
		assertEquals("Value is not \"d\"", ll.getCurrent(), "d");
		ll.backward();
		assertEquals("Value is not \"e\"", ll.getCurrent(), "e");
		ll.backward();
		assertEquals("Value is not \"a\"", ll.getCurrent(), "a");
		ll.backward();
		assertEquals("Value is not \"b\"", ll.getCurrent(), "b");
		ll.backward();
		assertEquals("Value is not \"c\"", ll.getCurrent(), "c");
		ll.backward();
		assertEquals("Value is not \"d\"", ll.getCurrent(), "d");
		ll.backward();
		assertEquals("Value is not \"e\"", ll.getCurrent(), "e");
	}
	
	@Test
	public void testBackwardException() {
		thrown.expect(EmptyLoopException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.backward();
	}

	@Test
	public void testSize() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		assertTrue("Size is not 0", ll.size() == 0);
		ll.insert("a");
		assertTrue("Size is not 1", ll.size() == 1);
		ll.removeCurrent();
		assertTrue("Size is not 0", ll.size() == 0);
		ll.insert("a");
		assertTrue("Size is not 1", ll.size() == 1);
		ll.insert("b");
		assertTrue("Size is not 2", ll.size() == 2);
		ll.insert("c");
		assertTrue("Size is not 3", ll.size() == 3);
		ll.insert("d");
		assertTrue("Size is not 4", ll.size() == 4);
		ll.removeCurrent();
		assertTrue("Size is not 3", ll.size() == 3);
		ll.removeCurrent();
		assertTrue("Size is not 2", ll.size() == 2);
		ll.removeCurrent();
		assertTrue("Size is not 1", ll.size() == 1);
	}

	@Test
	public void testIsEmpty() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		assertTrue("Not returning true", ll.isEmpty());
		ll.insert("a");
		assertFalse("Not returning false", ll.isEmpty());
		ll.removeCurrent();
		assertTrue("Not returning true", ll.isEmpty());
		ll.insert("a");
		assertFalse("Not returning false", ll.isEmpty());
		ll.forward();
		assertFalse("Not returning false", ll.isEmpty());
		ll.backward();
		assertFalse("Not returning false", ll.isEmpty());
		ll.insert("b");
		assertFalse("Not returning false", ll.isEmpty());
		ll.removeCurrent();
		ll.removeCurrent();
		assertTrue("Not returning true", ll.isEmpty());
	}

	@Test
	public void testIterator() {
		LinkedLoop<String> ll = new LinkedLoop<String>();
		Iterator<String> li = ll.iterator();
		
		assertFalse("Empy loop returns true for hasNext()", li.hasNext());
		
		ll.insert("a");
		Iterator<String> li2 = ll.iterator();
		assertTrue("Single element loop returns false for hasNext()",li2.hasNext());
		
		ll.insert("b");
		Iterator<String> li3 = ll.iterator();
		assertTrue("Returns false for hasNext()",li3.hasNext());
		assertEquals("", li3.next(), "b");
		assertTrue("Returns false for hasNext()",li3.hasNext());
		assertEquals("", li3.next(), "a");
		assertFalse("Completed iteration returns true for hasNext()", li3.hasNext());
		
		ll.insert("c");
		ll.insert("d");
		Iterator<String> li4 = ll.iterator();
		assertTrue("Returns false for hasNext()",li4.hasNext());
		assertEquals("", li4.next(), "d");
		assertTrue("Returns false for hasNext()",li4.hasNext());
		assertEquals("", li4.next(), "c");
		assertTrue("Returns false for hasNext()",li4.hasNext());
		assertEquals("", li4.next(), "b");
		assertTrue("Returns false for hasNext()",li4.hasNext());
		assertEquals("", li4.next(), "a");
		assertFalse("Completed iteration returns true for hasNext()", li4.hasNext());
		
		ll.removeCurrent();
		Iterator<String> li5 = ll.iterator();
		assertTrue("Returns false for hasNext()",li5.hasNext());
		assertEquals("", li5.next(), "c");
		assertTrue("Returns false for hasNext()",li5.hasNext());
		assertEquals("", li5.next(), "b");
		assertTrue("Returns false for hasNext()",li5.hasNext());
		assertEquals("", li5.next(), "a");
		assertFalse("Completed iteration returns true for hasNext()", li5.hasNext());
	}
	
	@Test
	public void testIteratorException() {
		thrown.expect(NoSuchElementException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		Iterator<String> itr = ll.iterator();
		itr.next();
	}
	
	@Test
	public void testIteratorException2() {
		thrown.expect(NoSuchElementException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		Iterator<String> itr = ll.iterator();
		itr.next();
		itr.next();
	}
	
	@Test
	public void testIteratorException3() {
		thrown.expect(NoSuchElementException.class);
		LinkedLoop<String> ll = new LinkedLoop<String>();
		ll.insert("a");
		ll.insert("b");
		Iterator<String> itr = ll.iterator();
		itr.next();
		itr.next();
		itr.next();
	}

}
