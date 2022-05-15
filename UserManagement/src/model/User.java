package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement", "root", "Malinda11Max@");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	return con;
	}
	
	
	//insert data
	
	public String insertUser( String id, String name , String address, String phone, String nic,String mail,String powerarea)
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
		String query = " insert into user (`code`,`id`,`name`,`address`,`phone`,`nic`,`mail`,`powerarea`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, id);
		preparedStmt.setString(3, name);
		preparedStmt.setString(4, address);
		preparedStmt.setString(5, phone);
		preparedStmt.setString(6, nic);
		preparedStmt.setString(7, mail);
		preparedStmt.setString(8, powerarea);
		
		
		// execute the statement
		
		preparedStmt.execute();
		con.close();
		String newUser = readUser();
		   output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
	return output;
	}
	
	
	
	
	//read data
	public String readUser()
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
		output = "<table border='1'><tr><th>User ID</th><th>User Name</th>" +
		"<th>Address</th>" +
		"<th>Phone</th>" +
		"<th>NIC Number</th>" +
		"<th>Mail Address</th>" +
		"<th>Power Area</th>" +
		"<th>Update</th><th>Remove</th></tr>";
		String query = "select * from user";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
			String code = Integer.toString(rs.getInt("code"));
			String id = rs.getString("id");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String phone = rs.getString("phone");
			String nic = rs.getString("nic");
			String mail = rs.getString("mail");
			String powerarea = rs.getString("powerarea");
			
			// Add into the html table
			output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + code + "'>" + id + "</td>"; 
			output += "<td>" + name + "</td>";
			output += "<td>" + address + "</td>";
			output += "<td>" + phone + "</td>";
			output += "<td>" + nic + "</td>";
			output += "<td>" + mail + "</td>";
			output += "<td>" + powerarea + "</td>";
			
			// buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' "
					+ "class='btnUpdate btn btn-secondary' data-code='" + code + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' "
					+ "class='btnRemove btn btn-danger' data-code='" + code + "'></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the user.";
			System.err.println(e.getMessage());
		}
	return output;
	}
	
	//update user
	public String updateUser(String code, String id, String name , String address, String phone, String nic,String mail,String powerarea)
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
		String query = "UPDATE user SET id=?, name=?, address=?,phone=? ,nic=?, mail=?, powerarea=? WHERE code=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, address);
		preparedStmt.setString(4, phone);
		preparedStmt.setString(5, nic);
		preparedStmt.setString(6, mail);
		preparedStmt.setString(7, powerarea);
		preparedStmt.setInt(8, Integer.parseInt(code));
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newUser = readUser();
		output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while Updataing the user.\"}";
			System.err.println(e.getMessage());
		}
	return output;
	}
	
	
	//delete user
	public String deleteUser(String code)
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
	String query = "delete from user where code=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(code));
	// execute the statement
	preparedStmt.execute();
	con.close();
	String newUser = readUser();
	output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
		}
		catch (Exception e)
			{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
				System.err.println(e.getMessage());
			}
	return output;
	}
	
	
	

}
