package pobj.tme4.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.tme4.HashMultiSet;
import pobj.tme4.MultiSet;
import pobj.tme4.NaiveMultiSet;

public class HashMultiSetTest {

	@Test
	public void testAdd() {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
		m.remove("a",3);
		m.remove("a");
		assertEquals(2,m.count("a"));
		
		m.add("b",5);
		m.clear();
		assertEquals(0,m.count("a"));
		assertEquals(0,m.count("b"));
		assertEquals(0,m.size());
		
		
		MultiSet<String> m2 = new NaiveMultiSet<>();
		
		m2.add("a");
		m2.add("a",5);
		assertEquals(6, m2.count("a"));
		m2.remove("a",3);
		m2.remove("a");
		assertEquals(2,m2.count("a"));
		
		m2.add("b",5);
		m2.clear();
		assertEquals(0,m2.count("a"));
		assertEquals(0,m2.count("b"));
		assertEquals(0,m2.size());
		
		
	}

}
