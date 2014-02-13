package org.totaltutorial.beginner;

public class Person {
	//fields
	private String name; // name of the person
	private int maximumBooks; // most books person can check out
	
	//constructors
	public Person() {
		name = "unknown name";
		maximumBooks = 81900; //yes or no or maybe
		// This line
		// and this
	}
	
	// methods 
	public String getName() {
		// yyy
		return name;
	}
	
	public void setName(String anyName) {
		name = anyName;
	}

	public int getMaximumBooks() {
		// and this
		return maximumBooks;
	}

	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}
	
	public String toString(){
		String str;
		str = getName() + " (" + getMaximumBooks() + " books)";
		return str;
	}
	}
