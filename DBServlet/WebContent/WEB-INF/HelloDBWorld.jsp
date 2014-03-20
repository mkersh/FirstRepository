<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="com.mk.Tests.XMLHelper" import="org.w3c.dom.*"%>
    
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap 101 Template</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/bootstrap.js"></script>
</head>
<body> 
<h2>${message}</h2>
<table class="table table-striped table-bordered table-hover table-condensed">
<thead>
<tr>
<th>ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Position</th>
</tr>
</thead>
<tbody>
<%
    String xmlRS = (String)request.getAttribute("CustRS");
    //String str = XMLHelper.prettyPrintXML(xmlRS);
    //out.println("XML: " + str);
    XMLHelper xmlObj = new XMLHelper(); 
	xmlObj.loadXML(xmlRS);
	NodeList recList = xmlObj.getNodeList("//r");
	for (int temp = 0; temp < recList.getLength(); temp++) { 
		out.println("<tr>");
		Node rec = recList.item(temp);
		String id = xmlObj.getTextContent("ID",rec);
		String first = xmlObj.getTextContent("First_Name",rec);
		String last = xmlObj.getTextContent("Last_Name",rec);
		String position = xmlObj.getTextContent("Position",rec);
		out.println("<td>" + id + "</td>");
		out.println("<td>" + first + "</td>");
		out.println("<td>" + last + "</td>");
		out.println("<td>" + position + "</td>");
		out.println("</tr>");
	}
%>
</tbody>
</table>
</body>
</html>