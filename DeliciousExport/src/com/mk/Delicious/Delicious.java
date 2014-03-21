package com.mk.Delicious;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.io.IOException;

public class Delicious {
	
	// Using Spring to inject into the following properties
	private Registry registry;
	
	private ParseDelicious parser;
	private DeliciousVisitor visitor;
	
	// Tried to pass Registry as a constructor parameter but had problems!!
	// Using an init function instead
	public Delicious(){
	}
	
	public void init(){
		// use the registry to get the parser and visitor beans
		parser = (ParseDelicious)registry.getBean("ParseDelicious");
		visitor = (DeliciousVisitor)registry.getBean("DeliciousVisitor");
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
		Registry registry = new RegistrySpring();
		registry.initFromFile("file:/home/mark/git/FirstRepository/DeliciousExport/delicious-beans.xml");
		
		// Next line creates an instance of this class and injects objects into parser and visitor properties
		Delicious del = (Delicious)registry.getBean("Delicious");
		// init method should now get called by Spring
		//del.init();
		del.parseDeliciousXML();
		
		registry.shutdown();
	}
	
	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
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
