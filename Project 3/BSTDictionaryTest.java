import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class BSTDictionaryTest {

	@Test
	public void testBSTDictionary() {
		BSTDictionary<KeyWord> a = new BSTDictionary<KeyWord>();
		assertTrue(a.isEmpty());
	}

	@Test
	public void testInsert() {
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		
		//first
		try {
			dic.insert(a);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 1);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		
		boolean reachCatch = false;
		try {
			dic.insert(a);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 1);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertTrue(reachCatch);
		
		//second
		try {
			dic.insert(b);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 2);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		
		reachCatch = false;
		try {
			dic.insert(b);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 2);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertTrue(reachCatch);
		
		//third
		try {
			dic.insert(f);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 3);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		
		reachCatch = false;
		try {
			dic.insert(f);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 3);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertTrue(reachCatch);
		
		//fourth
		try {
			dic.insert(d);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 4);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		
		reachCatch = false;
		try {
			dic.insert(d);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 4);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		assertTrue(reachCatch);
		
		//fifth
		try {
			dic.insert(e);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 5);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		assertEquals(dic.lookup(e), new KeyWord("e"));
		
		reachCatch = false;
		try {
			dic.insert(e);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 5);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		assertEquals(dic.lookup(e), new KeyWord("e"));
		assertTrue(reachCatch);
		
		//sixth
		try {
			dic.insert(c);
		} catch (DuplicateException ex) {
			fail();
		}
		assertTrue(dic.size() == 6);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		assertEquals(dic.lookup(e), new KeyWord("e"));
		assertEquals(dic.lookup(c), new KeyWord("c"));
		
		reachCatch = false;
		try {
			dic.insert(c);
		} catch (DuplicateException ex) {
			reachCatch = true;
		}
		assertTrue(dic.size() == 6);
		assertEquals(dic.lookup(a), new KeyWord("a"));
		assertEquals(dic.lookup(b), new KeyWord("b"));
		assertEquals(dic.lookup(f), new KeyWord("f"));
		assertEquals(dic.lookup(d), new KeyWord("d"));
		assertEquals(dic.lookup(e), new KeyWord("e"));
		assertEquals(dic.lookup(c), new KeyWord("c"));
		assertTrue(reachCatch);
	}

	@Test
	public void testDelete() {
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		assertFalse(dic.delete(null));
		assertFalse(dic.delete(a));
		try {
			dic.insert(a);
			dic.insert(b);
			dic.insert(f);
			dic.insert(d);
			dic.insert(e);
			dic.insert(c);
		} catch (DuplicateException ex) {
			fail();
		}
		assertFalse(dic.delete(new KeyWord("sadfas")));
		assertFalse(dic.delete(new KeyWord("abc")));
		assertTrue(dic.size() == 6);
		assertTrue(dic.delete(new KeyWord("d")));
		assertTrue(dic.size() == 5);
		assertTrue(dic.delete(new KeyWord("e")));
		assertTrue(dic.size() == 4);
		assertTrue(dic.delete(new KeyWord("c")));
		assertTrue(dic.size() == 3);
		assertTrue(dic.delete(new KeyWord("b")));
		assertTrue(dic.size() == 2);
		assertEquals(dic.lookup(f), f);
		assertEquals(dic.lookup(a), a);
	}

	@Test
	public void testLookup() {
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		try {
			dic.insert(a);
			dic.insert(b);
			dic.insert(f);
			dic.insert(d);
			dic.insert(e);
			dic.insert(c);
		} catch (DuplicateException ex) {
			fail();
		}
		assertEquals(dic.lookup(new KeyWord("a")), new KeyWord("a"));
		assertEquals(dic.lookup(new KeyWord("b")), new KeyWord("b"));
		assertEquals(dic.lookup(new KeyWord("c")), new KeyWord("c"));
		assertEquals(dic.lookup(new KeyWord("d")), new KeyWord("d"));
		assertEquals(dic.lookup(new KeyWord("e")), new KeyWord("e"));
		assertEquals(dic.lookup(new KeyWord("f")), new KeyWord("f"));
		assertNull(dic.lookup(new KeyWord("ab")));
		assertNull(dic.lookup(new KeyWord("bc")));
		assertNull(dic.lookup(new KeyWord("cd")));
		assertNull(dic.lookup(new KeyWord("de")));
	}

	@Test
	public void testIsEmpty() throws DuplicateException {
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		assertTrue(dic.isEmpty());
		dic.insert(new KeyWord("a"));
		assertFalse(dic.isEmpty());
		dic.delete(new KeyWord("a"));
		assertTrue(dic.isEmpty());
		dic.insert(new KeyWord("a"));
		assertFalse(dic.isEmpty());
		dic.insert(new KeyWord("b"));
		assertFalse(dic.isEmpty());
		dic.delete(new KeyWord("a"));
		assertFalse(dic.isEmpty());
		dic.delete(new KeyWord("b"));
		assertTrue(dic.isEmpty());
	}

	@Test
	public void testSize() throws DuplicateException{
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		assertTrue(dic.size() == 0);
		dic.insert(new KeyWord("a"));
		assertTrue(dic.size() == 1);
		dic.delete(new KeyWord("a"));
		assertTrue(dic.size() == 0);
		dic.insert(new KeyWord("a"));
		assertTrue(dic.size() == 1);
		dic.insert(new KeyWord("b"));
		assertTrue(dic.size() == 2);
		dic.delete(new KeyWord("a"));
		assertTrue(dic.size() == 1);
		dic.delete(new KeyWord("b"));
		assertTrue(dic.size() == 0);
		
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		dic.insert(a);
		assertTrue(dic.size() == 1);
		dic.insert(b);
		assertTrue(dic.size() == 2);
		dic.insert(f);
		assertTrue(dic.size() == 3);
		dic.insert(d);
		assertTrue(dic.size() == 4);
		dic.insert(e);
		assertTrue(dic.size() == 5);
		dic.insert(c);
		assertTrue(dic.size() == 6);
		assertTrue(dic.delete(new KeyWord("d")));
		assertTrue(dic.size() == 5);
		assertTrue(dic.delete(new KeyWord("e")));
		assertTrue(dic.size() == 4);
		assertTrue(dic.delete(new KeyWord("c")));
		assertTrue(dic.size() == 3);
		assertTrue(dic.delete(new KeyWord("b")));
		assertTrue(dic.size() == 2);
	}

	@Test
	public void testTotalPathLength() throws DuplicateException{
		BSTDictionary<KeyWord> dic = new BSTDictionary<KeyWord>();
		KeyWord a = new KeyWord("a");
		KeyWord b = new KeyWord("b");
		KeyWord c = new KeyWord("c");
		KeyWord d = new KeyWord("d");
		KeyWord e = new KeyWord("e");
		KeyWord f = new KeyWord("f");
		dic.insert(a);
		assertTrue(dic.totalPathLength() == 1);
		dic.insert(b);
		assertTrue(dic.totalPathLength() == 3);
		dic.insert(f);
		assertTrue(dic.totalPathLength() == 6);
		dic.insert(d);
		assertTrue(dic.totalPathLength() == 10);
		dic.insert(e);
		assertTrue(dic.totalPathLength() == 15);
		dic.insert(c);
		assertTrue(dic.totalPathLength() == 20);
		
		BSTDictionary<KeyWord> dic2 = new BSTDictionary<KeyWord>();
		dic2.insert(d);
		assertTrue(dic2.totalPathLength() == 1);
		dic2.insert(b);
		assertTrue(dic2.totalPathLength() == 3);
		dic2.insert(f);
		assertTrue(dic2.totalPathLength() == 5);
		dic2.insert(a);
		assertTrue(dic2.totalPathLength() == 8);
		dic2.insert(c);
		assertTrue(dic2.totalPathLength() == 11);
		dic2.insert(e);
		assertTrue(dic2.totalPathLength() == 14);
		
		
		BSTDictionary<KeyWord> dic3 = new BSTDictionary<KeyWord>();
		dic3.insert(a);
		assertTrue(dic3.totalPathLength() == 1);
		dic3.insert(b);
		assertTrue(dic3.totalPathLength() == 3);
		dic3.insert(c);
		assertTrue(dic3.totalPathLength() == 6);
		dic3.insert(d);
		assertTrue(dic3.totalPathLength() == 10);
		dic3.insert(e);
		assertTrue(dic3.totalPathLength() == 15);
		dic3.insert(f);
		assertTrue(dic3.totalPathLength() == 21);
	}

	@Test
	public void testIterator() {
		assertTrue(new BSTDictionary<KeyWord>().iterator() instanceof Iterator);
	}

}
