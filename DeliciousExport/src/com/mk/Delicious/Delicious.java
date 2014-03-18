package com.mk.Delicious;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Delicious {
	
	static final String EMAILADDRESS = "kershaw.mark@gmail.com";
	static final String DELICIOUSFILE = "/home/mark/delicious.xml";
	private Hashtable tags;

	/***
	 * The aim of this program is to parse the delicious.xml file and send
	 * all the items to an email address
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {	
		ParseDelicious parser = new DeliciousParser1();
		DeliciousVisitor visitor = new DeliciousVisitor1();
		DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance();
		
		parser.parse(prop.getProperty("DeliciousFile"),visitor);
	}
	
}
