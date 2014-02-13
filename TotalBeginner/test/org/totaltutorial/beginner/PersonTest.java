package org.totaltutorial.beginner;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testPerson() {
		Person p1 = new Person();
		assertEquals("unknown name", p1.getName());
		assertEquals(81900, p1.getMaximumBooks());
	}

	@Test
	public void testSetName() {
		Person p1 = new Person();
		p1.setName("Mark");
		assertEquals("Mark", p1.getName());
	}

	@Test
	public void testSetMaximumBooks() {
		Person p1 = new Person();
		p1.setMaximumBooks(3000);
		assertEquals(3000, p1.getMaximumBooks());
	}
	
	@Test
	public void testToString() {
		Person p1 = new Person();
		p1.setName("Mark");
		p1.setMaximumBooks(3);
		String testString = "Mark (3 books)";
		assertEquals(testString, p1.toString());
	}

}
