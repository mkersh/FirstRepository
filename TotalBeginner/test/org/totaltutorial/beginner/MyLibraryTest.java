package org.totaltutorial.beginner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MyLibraryTest {

	@Test
	public void testMyLibrary() {
		MyLibrary ml = new MyLibrary("Test");
		assertEquals("Test", ml.name);
		
		assertTrue(ml.books instanceof ArrayList<?>);
		assertTrue(ml.people instanceof ArrayList<?>);
		
	}

}
