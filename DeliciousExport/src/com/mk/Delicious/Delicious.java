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

import org.springframework.context.*;
import org.springframework.context.support.*;

public class Delicious {
	
	private ApplicationContext ctx = null;
	private ParseDelicious parser;
	private DeliciousVisitor visitor;
	
	public Delicious(){
		ctx = new FileSystemXmlApplicationContext("file:/home/mark/git/FirstRepository/DeliciousExport/delicious-beans.xml");
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
		Delicious del = new Delicious();
		del.parseDeliciousXML();
	}
	
	/***
	 * Going introduce Spring to DI implementation instances of ParseDelicious and DeliciousVisitor to use
	 */
	public void parseDeliciousXML(){
		parser = (ParseDelicious) ctx.getBean("ParseDelicious");
		visitor = (DeliciousVisitor) ctx.getBean("DeliciousVisitor");
		DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance();
		
		parser.parse(prop.getProperty("DeliciousFile"),visitor);
	}
	
}
