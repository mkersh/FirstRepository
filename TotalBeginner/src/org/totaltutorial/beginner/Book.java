package org.totaltutorial.beginner;

public class Book {

	public String name;
	public String author;
	private Person person;
	
	//Constructors
	public Book(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPerson(Person p1) {
		this.person = p1;
		
	}

	public Person getPerson() {
		return this.person;
	}
	

}
