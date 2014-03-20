package com.mk.Tests;

import java.sql.*;


/***
 * 
 * 20th  March 2014: Had a major issue getting Transactions to work today with the MariaDB.
 * 
 * It turned out that the problem was because of the storage engine that I used for the Tables.
 * The Tables where set to ENGINE=MyISAM and this is Non-Transactional. It needed to be ENGINE=InnoDB.
 * 
 * Do "SHOW ENGINES" to see the names of the engines
 * 
 *
 */
public class Main {
	
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost/test";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";

	public static void main(String[] args) {
		try{
			Class.forName(JDBC_DRIVER);
			
			ResultSet rs = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			Statement stmt2 = null;
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt2 = conn.createStatement();
			
			String sql;
			
	        conn.setAutoCommit(false);
	        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	        Savepoint save1 = conn.setSavepoint();
	        sql = "Update Employees set First_Name=? where ID=?"; //Original name Mustapha
		    stmt = conn.prepareStatement(sql);
		    
		    //stmt.setString(1, "Mark Kershaw");
		    stmt.setString(1, "AAAAAAAAMustapha");
		    stmt.setInt(2, 1);
		    stmt.executeUpdate();
	        //sql = "Update Employees set First_Name='YYYY' where ID=1";
	        //sql = "INSERT INTO `Employees` (`First_Name`, `Last_Name`, `Position`, `Home_Address`, `Home_Phone`) VALUES ('DDDDDDDDDDDDDDDDDDDD', 'Mond', 'Chief Executive Officer', '692 Promiscuous Plaza', '326-555-3492')";
	        //stmt2.executeUpdate(sql);
// This next line was not rolling back and had my stumped for a long time. It was because the database table did not support transactions!!
// Had to change the storage engine to get it working		    
		    conn.rollback(save1);
		    //conn.commit();
		    
		    sql = "SELECT * FROM Employees";
		    stmt = conn.prepareStatement(sql);
		    rs = stmt.executeQuery();
		    printResultSet(rs);
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	static private void printResultSet(ResultSet rs){
		try {
			StringBuilder strBuilder = new StringBuilder();
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
			
			System.out.println(strBuilder.toString());
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
