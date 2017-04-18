import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class KeyWordTest {

	@Rule
   public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testKeyWord() {
		KeyWord kw = new KeyWord("a");
		assertTrue("Occurrences is not 0", kw.getOccurrences() == 0);
		assertEquals("KeyWord is not 'a'", "a", kw.getWord());
		
		KeyWord kw2 = new KeyWord("ABCD1eFg");
		assertTrue("Occurrences is not 0", kw.getOccurrences() == 0);
		assertEquals("KeyWord is not 'abcd1efg'", "abcd1efg", kw2.getWord());
		
		KeyWord kw3 = new KeyWord("aB");
		assertTrue("Occurrences is not 0", kw.getOccurrences() == 0);
		assertEquals("KeyWord is not 'ab'", "ab", kw3.getWord());
		
		KeyWord kw4 = new KeyWord("143");
		assertTrue("Occurrences is not 0", kw.getOccurrences() == 0);
		assertEquals("KeyWord is not '143'", "143", kw4.getWord());
	}
	
	@Test
	public void testKeyWordExceptionNull() {
		thrown.expect(IllegalArgumentException.class);
		KeyWord kw = new KeyWord(null);
	}
	
	@Test
	public void testKeyWordExceptionEmpty() {
		thrown.expect(IllegalArgumentException.class);
		KeyWord kw = new KeyWord("");
	}

	@Test
	public void testGetPriority() {
		KeyWord kw = new KeyWord("a");
		assertTrue("", kw.getPriority() == 0);
		kw.increment();
		assertTrue("", kw.getPriority() == 1);
		kw.increment();
		assertTrue("", kw.getPriority() == 2);
		kw.increment();
		assertTrue("", kw.getPriority() == 3);
		kw.increment();
		assertTrue("", kw.getPriority() == 4);
		kw.increment();
		assertTrue("", kw.getPriority() == 5);
	}

	@Test
	public void testGetOccurrences() {
		KeyWord kw = new KeyWord("a");
		assertTrue("", kw.getOccurrences() == 0);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 1);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 2);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 3);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 4);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 5);
	}

	@Test
	public void testGetWord() {
		KeyWord kw1 = new KeyWord("a");
		assertEquals("", "a", kw1.getWord());
		KeyWord kw2 = new KeyWord("a");
		assertEquals("", "a", kw2.getWord());
		KeyWord kw3 = new KeyWord("a");
		assertEquals("", "a", kw3.getWord());
		KeyWord kw4 = new KeyWord("a");
		assertEquals("", "a", kw4.getWord());
	}

	@Test
	public void testIncrement() {
		KeyWord kw = new KeyWord("a");
		assertTrue("", kw.getOccurrences() == 0);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 1);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 2);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 3);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 4);
		kw.increment();
		assertTrue("", kw.getOccurrences() == 5);
	}

	@Test
	public void testCompareTo() {
		KeyWord kw1 = new KeyWord("ABC");
		KeyWord kw2 = new KeyWord("def");
		KeyWord kw3 = new KeyWord("abc");
		assertTrue("", kw1.compareTo(kw2) < 0);
		assertTrue("", kw2.compareTo(kw1) > 0);
		assertTrue("", kw1.compareTo(kw3) == 0);
		assertTrue("", kw3.compareTo(kw1) == 0);
	}

	@Test
	public void testEqualsObject() {
		KeyWord kw1 = new KeyWord("ABC");
		KeyWord kw2 = new KeyWord("def");
		KeyWord kw3 = new KeyWord("abc");
		assertFalse("", kw1.equals(new Integer(1)));
		assertTrue("", kw1.equals(kw3));
		assertFalse("", kw1.equals(kw2));
		assertTrue("", kw3.equals(kw1));
		assertFalse("", kw2.equals(kw1));
	}

}
