package com;
import java.sql.*;
public class Customer
{
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
	 }
	 catch (Exception e)
	 {
	 e.printStackTrace();
	 }
	 return con;
	 }
	
	//read
	public String readCustomers()
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
	 output = "<table border='1'><tr><th>Account No</th> <th>Customer Name</th><th>Customer Address</th>" + "<th>Customer Phone</th> <th>Update</th><th>Remove</th></tr>";
	 String query = "select * from customers";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String customerID = Integer.toString(rs.getInt("customerID"));
		 String accountNo = rs.getString("accountNo");
		 String customerName = rs.getString("customerName");
		 String customerAddress = rs.getString("customerAddress");
		 String customerPhone = rs.getString("customerPhone");
	 // Add into the html table
	output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='" + customerID
	 + "'>" + accountNo + "</td>";
	 output += "<td>" + customerName + "</td>";
	 output += "<td>" + customerAddress + "</td>";
	 output += "<td>" + customerPhone + "</td>";
	 // buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='"
	 + customerID + "'>" + "</td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the customers.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//insert
		public String insertCustomer(String no, String name, String address, String phone)
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
		 String query = " insert into customers (`customerID`,`accountNo`,`customerName`,`customerAddress`,`customerPhone`)"
		 + " values (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, no);
				 preparedStmt.setString(3, name);
				 preparedStmt.setString(4, address);
				 preparedStmt.setString(5, phone);
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newCustomers = readCustomers();
				 output = "{\"status\":\"success\", \"data\": \"" +
						 newCustomers + "\"}";
				 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":	 \"Error while inserting the customer.\"}";
				 System.err.println(e.getMessage());
				 }
				 return output;
				 }
		
		//update
		public String updateCustomer(String ID, String no, String name,
		 String address, String phone)
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
		 String query = "UPDATE customers SET accountNo=?,customerName=?,customerAddress=?,customerPhone=? WHERE customerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, no);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, phone);
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newCustomers = readCustomers();
		 output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//delete
		public String deleteCustomer(String customerID)
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
		 String query = "delete from customers where customerID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(customerID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newCustomers = readCustomers();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newCustomers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the Customer.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
}