package org.totaltutorial.beginner;

import java.util.ArrayList;

public class MyLibrary {

	ArrayList<Book> books;
	ArrayList<Person> people;
	String name;

	public MyLibrary(String string) {
		name = string;
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
	}

}
