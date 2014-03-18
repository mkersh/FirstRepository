package com.mk.Delicious;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DeliciousParser1 implements ParseDelicious {

	@Override
	public void parse(String filePath, DeliciousVisitor visitor) {
		
		
		try {
			filePath = URLHelper.expandHomeSymbol(filePath);
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			Node post;
			DeliciousBean bean;
			NodeList postList = (NodeList) xpath.evaluate("//post", doc, XPathConstants.NODESET);
			for (int temp = 0; temp < postList.getLength(); temp++) { 
				post = postList.item(temp);
				Node description = (Node) xpath.evaluate("./@description", post, XPathConstants.NODE); 
				Node extended = (Node) xpath.evaluate("./@extended", post, XPathConstants.NODE); 
				Node href = (Node) xpath.evaluate("./@href", post, XPathConstants.NODE); 
				Node tags = (Node) xpath.evaluate("./@tag", post, XPathConstants.NODE); 
				Node time = (Node) xpath.evaluate("./@time", post, XPathConstants.NODE); 
				
				bean = new DeliciousBean();
				bean.setDescription(description.getNodeValue());
				bean.setExtended(extended.getNodeValue());
				bean.setHref(href.getNodeValue());
				bean.setTags(tags.getNodeValue());
				bean.setTime(time.getNodeValue());
				
				if (visitor.visitNode(bean) == false){
					break; // Terminate the parsing
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
