package org.totaltutorial.beginner;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	@Test
	public void testBook() {
		Book b1 = new Book("Great Expectations");
		b1.setAuthor("Dickens");
		assertEquals("Great Expectations", b1.name); 
		assertEquals("Dickens", b1.author);
	}
	
	@Test
	public void testGetPerson(){
		Book b1 = new Book("Great Expectations");
		Person p1 = new Person();
		p1.setName("Elvis");
		
		b1.setPerson(p1);
		
		Person testPerson = b1.getPerson();
		String testName = testPerson.getName();
		
		assertEquals("Elvis", testName);
	}

}
