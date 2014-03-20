package com.mk.Tests;

import java.io.IOException;
import java.sql.*;
import org.w3c.dom.Node;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/test";
//  Database credentials
   static final String USER = "root";
   static final String PASS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "Hello DB World";
		request.setAttribute("CustRS",getEmployeesFromDatabase());
        request.setAttribute("message", message); // This will be available as ${message}
        // By having the JSP in WEB-INF the public cannot go to it directly
        request.getRequestDispatcher("/WEB-INF/HelloDBWorld.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String getEmployeesFromDatabase(){
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		String xmlStr = null;
		try{
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM Employees";
	      rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      xmlStr = XMLHelper.RSToXML(rs);
	      
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }
		catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		}//end try
		System.out.println("Goodbye!");
		return xmlStr;
	}

}
