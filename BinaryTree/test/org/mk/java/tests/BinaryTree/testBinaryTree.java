package org.mk.java.tests.BinaryTree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testBinaryTree {
	
	private BinaryTree bt;

	@Before
	public void setup(){
		bt = new BinaryTree();
		bt.add("1");
		bt.add("2");
		bt.add("3");
		bt.add("4");
	}

	@Test
	public void testAdd() {
		assertEquals(4,bt.count);
	}
	
	@Test
	public void testFind() {
		assertEquals("2", bt.find("2"));
		assertEquals(null, bt.find("7"));
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
}
