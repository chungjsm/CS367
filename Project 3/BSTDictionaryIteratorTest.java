import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class BSTDictionaryIteratorTest {

	@Rule
   public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testBSTDictionaryIterator() {
		Iterator<KeyWord> itr = 
				new BSTDictionaryIterator<KeyWord>(new BSTnode<KeyWord>(new KeyWord("a")));
		assertTrue(itr.hasNext());
		assertEquals(itr.next().getWord(), "a");
		assertFalse(itr.hasNext());
	}

	@Test
	public void testHasNext() {
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		BSTDictionary<KeyWord> dic2 = new BSTDictionary<KeyWord>();
		BSTDictionary<KeyWord> dic3 = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		try {
			dic.insert(a);
			dic.insert(b);
			dic.insert(c);
			dic.insert(d);
			dic.insert(e);
			dic.insert(f);
		} catch (DuplicateException ex) {
			
		}
		try {
			dic2.insert(a);
			dic2.insert(b);
			dic2.insert(f);
			dic2.insert(d);
			dic2.insert(e);
			dic2.insert(c);
		} catch (DuplicateException ex) {
			
		}
		try {
			dic3.insert(d);
			dic3.insert(b);
			dic3.insert(f);
			dic3.insert(a);
			dic3.insert(c);
			dic3.insert(e);
		} catch (DuplicateException ex) {
			
		}
		Iterator<KeyWord> itr1 = dic.iterator();
		Iterator<KeyWord> itr2 = dic2.iterator();
		Iterator<KeyWord> itr3 = dic3.iterator();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertTrue(itr1.hasNext());
		assertTrue(itr2.hasNext());
		assertTrue(itr3.hasNext());
		itr1.next();
		itr2.next();
		itr3.next();
		assertFalse(itr1.hasNext());
		assertFalse(itr2.hasNext());
		assertFalse(itr3.hasNext());
	}

	@Test
	public void testNext() throws DuplicateException{
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		BSTDictionary<KeyWord> dic2 = new BSTDictionary<KeyWord>();
		BSTDictionary<KeyWord> dic3 = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		try {
			dic.insert(a);
			dic.insert(b);
			dic.insert(c);
			dic.insert(d);
			dic.insert(e);
			dic.insert(f);
		} catch (DuplicateException ex) {
			
		}
		try {
			dic2.insert(a);
			dic2.insert(b);
			dic2.insert(f);
			dic2.insert(d);
			dic2.insert(e);
			dic2.insert(c);
		} catch (DuplicateException ex) {
			
		}
		try {
			dic3.insert(d);
			dic3.insert(b);
			dic3.insert(f);
			dic3.insert(a);
			dic3.insert(c);
			dic3.insert(e);
		} catch (DuplicateException ex) {
			
		}
		Iterator<KeyWord> itr1 = dic.iterator();
		Iterator<KeyWord> itr2 = dic2.iterator();
		Iterator<KeyWord> itr3 = dic3.iterator();
		assertEquals(itr1.next().getWord(), "a");
		assertEquals(itr2.next().getWord(), "a");
		assertEquals(itr3.next().getWord(), "a");
		assertEquals(itr1.next().getWord(), "b");
		assertEquals(itr2.next().getWord(), "b");
		assertEquals(itr3.next().getWord(), "b");
		assertEquals(itr1.next().getWord(), "c");
		assertEquals(itr2.next().getWord(), "c");
		assertEquals(itr3.next().getWord(), "c");
		assertEquals(itr1.next().getWord(), "d");
		assertEquals(itr2.next().getWord(), "d");
		assertEquals(itr3.next().getWord(), "d");
		assertEquals(itr1.next().getWord(), "e");
		assertEquals(itr2.next().getWord(), "e");
		assertEquals(itr3.next().getWord(), "e");
		assertEquals(itr1.next().getWord(), "f");
		assertEquals(itr2.next().getWord(), "f");
		assertEquals(itr3.next().getWord(), "f");
	}
	
	@Test
	public void testNextException() {
		thrown.expect(NoSuchElementException.class);
		Iterator<KeyWord> itr = 
				new BSTDictionaryIterator<KeyWord>(new BSTnode<KeyWord>(new KeyWord("a")));
		itr.next();
		itr.next();
	}

}
