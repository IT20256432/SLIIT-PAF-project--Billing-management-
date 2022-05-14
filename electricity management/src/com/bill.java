package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bill 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:4306/electricitymanagmentsystem", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 



public String readItems() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr> <th>Customer Name</th><th>Customer Email</th>"+ "<th>Account Number</th> <th>Customer Contact Number</th> <th>Bill Value</th> <th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from bill"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String billID = Integer.toString(rs.getInt("billID")); 
 String cusName = rs.getString("cusName");
 String cusEmail = rs.getString("cusEmail"); 
 String accNo = rs.getString("accNo"); 
 String cusCNo = rs.getString("cusCNo"); 
 String billval = rs.getString("billval");
 // Add into the html table
 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + billID+ "'>" + cusName + "</td>"; 
 output += "<td>" + cusEmail + "</td>"; 
 output += "<td>" + accNo + "</td>"; 
 output += "<td>" + cusCNo + "</td>"; 
 output += "<td>" + billval + "</td>";
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-billid='"+ billID+"'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='" + billID + "'>" + "</td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 



public String insertItem(String cusName, String cusEmail, String accNo, String cusCNo, String billval) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for inserting."; 
 } 
 // create a prepared statement
 String query = " insert into bill (billID,cusName,cusEmail,accNo,cusCNo,billval)" + " values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, cusName); 
		 preparedStmt.setString(3, cusEmail); 
		 preparedStmt.setString(4, accNo); 
		 preparedStmt.setString(5, cusCNo);
		 preparedStmt.setString(6, billval);
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\":  \"Error while inserting the item.\"}"; System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 



		public String updateItem(String billID, String cusName, String cusEmail, String accNo, String cusCNo, String billval) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE bill SET cusName=?,cusEmail=?,accNo=?,cusCNo=?,billval=? WHERE billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, cusName);
		 preparedStmt.setString(2, cusEmail);
		 preparedStmt.setString(3, accNo);
		 preparedStmt.setString(4, cusCNo);
		 preparedStmt.setString(5, billval);
		 preparedStmt.setInt(6, Integer.parseInt(billID)); 
		// execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		public String deleteItem(String billID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from bill where billID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(billID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


