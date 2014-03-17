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
		Delicious del = new Delicious();
		del.parseXML(DELICIOUSFILE);
	}
	
	protected void parseXML(String xmlFile) throws ParserConfigurationException, IOException, SAXException{
		File fXmlFile = new File(DELICIOUSFILE);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();

		Node book;
		try {
			NodeList bookList = (NodeList) xpath.evaluate("//book", doc, XPathConstants.NODESET);
			for (int temp = 0; temp < bookList.getLength(); temp++) { 
				book = bookList.item(temp);
				Node title = (Node) xpath.evaluate("./title", book, XPathConstants.NODE); // I get null here.
				System.out.println("\nCurrent Element :" + title.getTextContent());
			}
			
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
	
		
	protected void emailItem(String itemXML){
		System.out.printf("email %s\n", EMAILADDRESS);
	}

}
