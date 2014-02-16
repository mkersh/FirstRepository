package org.mk.java.tests.BinaryTree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testBinaryTree {
	
	private BinaryTree bt;

	@Before
	public void setup() throws Exception{
		bt = new BinaryTree();
		bt.add("10");
		bt.add("20");
		bt.add("30");
		bt.add("40");
		bt.add("1");
		bt.add("2");
		bt.add("3");
		bt.add("4");
	}

	@Test
	public void testAdd() {
		assertEquals(8,bt.count);
	}
	
	@Test
	public void testFind() {
		assertEquals("20", bt.find("20"));
		assertEquals(null, bt.find("70"));
	}

	@Test
	public void testToString() {
		String btStr = "";
		btStr += "<1\n";
		btStr += "<>2\n";
		btStr += "<>>3\n";
		btStr += "<>>>4\n";
		btStr += "10\n";
		btStr += ">20\n";
		btStr += ">>30\n";
		btStr += ">>>40\n";
		
		assertEquals(btStr , bt.toString());
	}
}
