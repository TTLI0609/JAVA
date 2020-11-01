package pobj.tme5.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import pobj.tme5.*;



public class HashMultiSetTest2 {

	private MultiSet<String> m;

	@Before
	public void before() {
		m = new HashMultiSet<>();
	}
	
	@Test 
	public void testRemove1() {
		m.add("a");
		m.add("a",5);
		m.remove("a");
		assertEquals(5, m.count("a"));
		m.remove("a",2);
		assertEquals(3, m.count("a"));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.remove("je");
		m.remove("b",-4);
		m.remove("b",0);
	}

	@Test 
	public void testSize() {
		assertEquals(0, m.size());
		m.add("a",5);
		assertEquals(5, m.size());
	}
	
	@Test 
	public void testToString() {
		m.add("a",5);
		m.add("b");
		m.add("c",2);
		assertEquals("[ a: 5; b: 1; c: 2 ]", m.toString());
	}
	
	@Test 
	public void testClear() {
		m.add("a",5);
		m.add("b");
		m.add("c",2);
		m.clear();
		assertEquals(0, m.size());
	}
	
	
	@Test 
	public void testCount() {
		assertEquals(0, m.count("aaa"));
		m.add("a",5);
		m.add("b");
		m.add("b");
		assertEquals(5, m.count("a"));
		assertEquals(2, m.count("b"));
	}
	
	
	
	
	
}
