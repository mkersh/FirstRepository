package com.mk.Tests;
import java.io.File;
import java.io.StringReader;
import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLHelper {
	
	private Document xmlDoc = null;
	private XPath xpath = null;
	
	static public String RSToXML(ResultSet rs){
		StringBuilder strBuilder = new StringBuilder();
		try {
			strBuilder.append("<rs>");
			while(rs.next()){
				ResultSetMetaData metaData = rs.getMetaData();
				strBuilder.append("<r>");
				int count;
				count = metaData.getColumnCount();
				for (int i = 1; i <= count; i++)
				{
				   String colName = metaData.getColumnName(i);
				   String colValue = rs.getString(i);
				   String element = String.format("<%s>%s</%s>", colName, colValue, colName);
				   strBuilder.append(element);
				}
				strBuilder.append("</r>");
		      }
			strBuilder.append("</rs>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return strBuilder.toString();
	}
	
	static public String prettyPrintXML(String str){
		str = str.replace("<rs>", "<rs>\n");
		str = str.replace("</r>", "</r>\n");
		
		return escapeXML(str);
	}
	
	static public String escapeXML(String str){
		str = str.replace("&", "&amp;");
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("\n", "<br/>");
		return str;
	}
	
	public void loadXML(String xmlStr){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			InputSource src = new InputSource(new StringReader(xmlStr));
			xmlDoc = dBuilder.parse(src);
			XPathFactory factory = XPathFactory.newInstance();
			xpath = factory.newXPath();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Node getNode(String xpathStr){
		return getNode(xpathStr,xmlDoc);
	}
	
	public Node getNode(String xpathStr, Node startNode){
		try {
			return (Node)xpath.evaluate(xpathStr, startNode, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public NodeList getNodeList(String xpathStr){
		return getNodeList(xpathStr,xmlDoc);
	}
	
	public NodeList getNodeList(String xpathStr, Node startNode){
		try {
			return (NodeList) xpath.evaluate(xpathStr, xmlDoc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getTextContent(String xpathStr, Node startNode){
		Node n;
		try {
			n = (Node)xpath.evaluate(xpathStr, startNode, XPathConstants.NODE);
			return n.getTextContent();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} 
		return null;
	}


}
