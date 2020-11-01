package pobj.tme6.test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import pobj.tme6.BoundContext;
import pobj.tme6.ContextTurtle;
import pobj.tme6.IColorTurtle;

public class Question4Test {
	
	@Test public void test() {
		BoundContext c = new BoundContext();        
		IColorTurtle t = new ContextTurtle(c);
		t.move(100);
		t.turn(90);
		t.move(100);
		assertEquals(0, c.getMinX());
		System.out.println(c.getMinY());
		assertEquals(0, c.getMinY());
		assertEquals(100, c.getMaxX());
		assertEquals(100, c.getMaxY());
	}
}
