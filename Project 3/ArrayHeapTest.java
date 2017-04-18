import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ArrayHeapTest {

	@Rule
   public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testArrayHeap() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		ah.insert(new KeyWord("a"));
	}
	
	@Test
	public void testArrayHeapException() {
		thrown.expect(IllegalArgumentException.class);
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>(-1);
	}

	@Test
	public void testArrayHeapInt() {
		ArrayHeap<KeyWord> ah1 = new ArrayHeap<KeyWord>(0);
		ArrayHeap<KeyWord> ah2 = new ArrayHeap<KeyWord>(1);
		ArrayHeap<KeyWord> ah3 = new ArrayHeap<KeyWord>(2);
		ArrayHeap<KeyWord> ah4 = new ArrayHeap<KeyWord>(1000);
		ah1.insert(new KeyWord("a"));
		ah2.insert(new KeyWord("a"));
		ah3.insert(new KeyWord("a"));
		ah4.insert(new KeyWord("a"));
	}

	@Test
	public void testIsEmpty() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		assertTrue(ah.isEmpty());
		ah.insert(new KeyWord("a"));
		assertFalse(ah.isEmpty());
		ah.removeMax();
		ah.insert(new KeyWord("a"));
		assertFalse(ah.isEmpty());
		ah.insert(new KeyWord("b"));
		assertFalse(ah.isEmpty());
		ah.removeMax();
		assertFalse(ah.isEmpty());
		ah.insert(new KeyWord("c"));
		assertFalse(ah.isEmpty());
		ah.removeMax();
		assertFalse(ah.isEmpty());
		ah.removeMax();
		assertTrue(ah.isEmpty());
	}

	@Test
	public void testInsert() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>(0);
		KeyWord a = new KeyWord("a");//1
		KeyWord b = new KeyWord("b");//2
		KeyWord c = new KeyWord("c");//3
		KeyWord d = new KeyWord("d");//4
		KeyWord e = new KeyWord("e");//4
		KeyWord f = new KeyWord("f");//6
		KeyWord g = new KeyWord("g");//21
		KeyWord h = new KeyWord("h");//21
		for (int i = 0; i < 1; i++) {
			b.increment();
		}
		for (int i = 0; i < 2; i++) {
			c.increment();
		}
		for (int i = 0; i < 3; i++) {
			d.increment();
			e.increment();
		}
		for (int i = 0; i < 5; i++) {
			f.increment();
		}
		for (int i = 0; i < 20; i++) {
			g.increment();
			h.increment();
		}
		ah.insert(a);
		assertEquals(ah.getMax(), a);
		ah.insert(b);
		assertEquals(ah.getMax(), b);
		ah.insert(c);
		assertEquals(ah.getMax(), c);
		ah.insert(d);
		assertEquals(ah.getMax(), d);
		ah.insert(e);
		assertTrue(ah.getMax().equals(d) || ah.getMax().equals(d));
		ah.insert(f);
		assertEquals(ah.getMax(), f);
		ah.insert(g);
		assertEquals(ah.getMax(), g);
		ah.insert(h);
		assertTrue(ah.getMax().equals(g) || ah.getMax().equals(h));
	}
	
	@Test
	public void testInsertException() {
		thrown.expect(IllegalArgumentException.class);
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		ah.insert(null);
	}

	@Test
	public void testRemoveMax() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>(0);
		KeyWord a = new KeyWord("a");//1
		KeyWord b = new KeyWord("b");//2
		KeyWord c = new KeyWord("c");//3
		KeyWord d = new KeyWord("d");//4
		KeyWord e = new KeyWord("e");//4
		KeyWord f = new KeyWord("f");//6
		KeyWord g = new KeyWord("g");//21
		KeyWord h = new KeyWord("h");//21
		for (int i = 0; i < 1; i++) {
			b.increment();
		}
		for (int i = 0; i < 2; i++) {
			c.increment();
		}
		for (int i = 0; i < 3; i++) {
			d.increment();
			e.increment();
		}
		for (int i = 0; i < 5; i++) {
			f.increment();
		}
		for (int i = 0; i < 20; i++) {
			g.increment();
			h.increment();
		}
		
		ah.insert(a);
		assertEquals(ah.removeMax(), a);
		assertTrue(ah.isEmpty());
		
		ah.insert(a);
		ah.insert(b);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		ah.insert(b);
		ah.insert(c);
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		ah.insert(b);
		ah.insert(c);
		ah.insert(d);
		assertEquals(ah.removeMax(), d);
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		ah.insert(b);
		ah.insert(c);
		ah.insert(d);
		ah.insert(e);
		KeyWord z = ah.removeMax();
		assertTrue(z.equals(d) || z.equals(e));
		KeyWord zz = ah.removeMax();
		assertTrue(zz.equals(d) || zz.equals(e));
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		//System.out.println(ah.getMax().getWord());
		ah.insert(b);
		//System.out.println(ah.getMax().getWord());
		ah.insert(c);
		//System.out.println(ah.getMax().getWord());
		ah.insert(d);
		//System.out.println(ah.getMax().getWord());
		ah.insert(e);
		//System.out.println(ah.getMax().getWord());
		ah.insert(f);
//		
//		for(int i = 1; i <= ah.size(); i++) {
//			System.out.print(ah.get(i).getWord() + " ");
//		}
//		System.out.println();
//		
		assertEquals(ah.removeMax(), f);
		
//		for(int i = 1; i <= ah.size(); i++) {
//			System.out.print(ah.get(i).getWord() + " ");
//		}
//		System.out.println();
//		
		KeyWord zzz = ah.removeMax();

//		for(int i = 1; i <= ah.size(); i++) {
//			System.out.print(ah.get(i).getWord() + " ");
//		}
//		System.out.println();
//		
		assertTrue(zzz.equals(d) || zzz.equals(e));
		KeyWord zzzz = ah.removeMax();
		assertTrue(zzzz.equals(d) || zzzz.equals(e));
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		ah.insert(b);
		ah.insert(c);
		ah.insert(d);
		ah.insert(e);
		ah.insert(f);
		ah.insert(g);
		assertEquals(ah.removeMax(), g);
		assertEquals(ah.removeMax(), f);
		KeyWord zzzzz = ah.removeMax();
		assertTrue(zzzzz.equals(d) || zzzzz.equals(e));
		KeyWord zzzzzz = ah.removeMax();
		assertTrue(zzzzzz.equals(d) || zzzzzz.equals(e));
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
		
		ah.insert(a);
		ah.insert(b);
		ah.insert(c);
		ah.insert(d);
		ah.insert(e);
		ah.insert(f);
		ah.insert(g);
		ah.insert(h);
		KeyWord x = ah.removeMax();
		assertTrue(x.equals(g) || x.equals(h));
		KeyWord xx = ah.removeMax();
		assertTrue(xx.equals(g) || xx.equals(h));
		assertEquals(ah.removeMax(), f);
		KeyWord yy = ah.removeMax();
		assertTrue(yy.equals(d) || yy.equals(e));
		KeyWord yyy = ah.removeMax();
		assertTrue(yyy.equals(d) || yyy.equals(e));
		assertEquals(ah.removeMax(), c);
		assertEquals(ah.removeMax(), b);
		assertEquals(ah.removeMax(), a);
	}
	
	@Test
	public void testRemoveMaxException() {
		thrown.expect(NoSuchElementException.class);
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		ah.removeMax();
	}

	@Test
	public void testGetMax() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>(0);
		KeyWord a = new KeyWord("a");//1
		KeyWord b = new KeyWord("b");//2
		KeyWord c = new KeyWord("c");//3
		KeyWord d = new KeyWord("d");//4
		KeyWord e = new KeyWord("e");//4
		KeyWord f = new KeyWord("f");//6
		KeyWord g = new KeyWord("g");//21
		KeyWord h = new KeyWord("h");//21
		for (int i = 0; i < 1; i++) {
			b.increment();
		}
		for (int i = 0; i < 2; i++) {
			c.increment();
		}
		for (int i = 0; i < 3; i++) {
			d.increment();
			e.increment();
		}
		for (int i = 0; i < 5; i++) {
			f.increment();
		}
		for (int i = 0; i < 20; i++) {
			g.increment();
			h.increment();
		}
		ah.insert(a);
		assertEquals(ah.getMax(), a);
		ah.insert(b);
		assertEquals(ah.getMax(), b);
		ah.insert(c);
		assertEquals(ah.getMax(), c);
		ah.insert(d);
		assertEquals(ah.getMax(), d);
		ah.insert(e);
		assertTrue(ah.getMax().equals(d) || ah.getMax().equals(d));
		ah.insert(f);
		assertEquals(ah.getMax(), f);
		ah.insert(g);
		assertEquals(ah.getMax(), g);
		ah.insert(h);
		assertTrue(ah.getMax().equals(g) || ah.getMax().equals(h));
	}
	
	@Test
	public void testGetMaxException() {
		thrown.expect(NoSuchElementException.class);
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		ah.getMax();
	}

	@Test
	public void testSize() {
		ArrayHeap<KeyWord> ah = new ArrayHeap<KeyWord>();
		assertTrue(ah.size() == 0);
		ah.insert(new KeyWord("a"));
		assertTrue(ah.size() == 1);
		ah.removeMax();
		assertTrue(ah.size() == 0);
		ah.insert(new KeyWord("a"));
		assertTrue(ah.size() == 1);
		ah.insert(new KeyWord("b"));
		assertTrue(ah.size() == 2);
		ah.removeMax();
		assertTrue(ah.size() == 1);
		ah.insert(new KeyWord("c"));
		assertTrue(ah.size() == 2);
		ah.removeMax();
		assertTrue(ah.size() == 1);
		ah.removeMax();
		assertTrue(ah.size() == 0);
	}

//	private void printArray(ArrayHeap<KeyWord> ah) {
//		ArrayList<KeyWord> kwArr = new ArrayList<KeyWord>();
//		for (int i = 0; i < ah.size(); i++) {
//			kwArr.add(ah.removeMax());
//		}
//		for (int j = kwArr.size(); j > 0; j--) {
//			System.out.print(kwArr.get(j) + " ");
//		}
//		
//	}
}
