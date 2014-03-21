package com.mk.Delicious;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.io.IOException;

import org.springframework.context.*;
import org.springframework.context.support.*;

public class Delicious {
	
	// Using Spring to inject into the following properties
	private ParseDelicious parser;
	private DeliciousVisitor visitor;
	
	public Delicious(){
	}

	/***
	 * The aim of this program is to parse the delicious.xml file and send
	 * all the items to an email address
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {	
		ApplicationContext ctx = new FileSystemXmlApplicationContext("file:/home/mark/git/FirstRepository/DeliciousExport/delicious-beans.xml");
		
		// Next line creates an instance of this class and injects objects into parser and visitor properties
		Delicious del = (Delicious)ctx.getBean("Delicious");
		del.parseDeliciousXML();
		// Next line is a bit messy. If not included eclipse complains that you haven't closed a resource
		// Probably best typing ctx as FileSystemXmlApplicationContext to avoid the need to cast
		((FileSystemXmlApplicationContext) ctx).close();
	}
	
	public ParseDelicious getParser() {
		return parser;
	}

	public void setParser(ParseDelicious parser) {
		this.parser = parser;
	}

	public DeliciousVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(DeliciousVisitor visitor) {
		this.visitor = visitor;
	}

	/***
	 * Going introduce Spring to DI implementation instances of ParseDelicious and DeliciousVisitor to use
	 */
	public void parseDeliciousXML(){
		DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance();
		
		parser.parse(prop.getProperty("DeliciousFile"),visitor);
	}
	
}
