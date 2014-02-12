package org.totaltutorial.beginner;

public class Person {
	//fields
	private String name; // name of the person
	private int maximumBooks; // most books person can check out
	
	//constructors
	public Person() {
		name = "unknown name";
		maximumBooks = 8900; //yes and no xxxy
		//Another line here
	}
	
	// methods 
	public String getName() {
		return name;
	}
	
	public void setName(String anyName) {
		name = anyName;
	}

	public int getMaximumBooks() {
		return maximumBooks;
	}

	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}
}
