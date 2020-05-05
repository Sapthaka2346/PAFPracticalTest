package model;

import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hospital {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem( String H_Name, String H_Address, String H_City,String H_phonenumber,String H_Desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into reg_Hospital(`Hospital_ID`,`H_Name`,`H_Address`,`H_City`,`H_phonenumber`,`H_Desc`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, H_Name);
			preparedStmt.setString(3, H_Address);
			preparedStmt.setString(4, H_City);
			preparedStmt.setString(5, H_phonenumber);
			preparedStmt.setString(6, H_Desc);
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			String newHospitals = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +newHospitals + "\"}";
			
		} catch (Exception e) {
			//output = "Error while inserting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Hospital.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>H_Name</th><th>H_Address</th><th>H_City<th>H_phonenumber</th><th>H_Desc</th></th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from reg_hospital";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
				String H_Name = rs.getString("H_Name");
				String H_Address = rs.getString("H_Address");
				String H_City = rs.getString("H_City");
				String H_phonenumber = Integer.toString(rs.getInt("H_phonenumber"));
				String H_Desc = rs.getString("H_Desc");
				// Add into the html table
				output += "<tr><td><input id='hidHopsitalIDUpdate' name='hidHopsitalIDUpdate' type='hidden' value='" + Hospital_ID + "'>" + H_Name + "</td>";
				
				/*output += "<tr><td><input id=\"hidUserIDUpdate\"name=\"hidUserIDUpdate\"type=\"hidden\" value=\""
						+ userID + "\">" + userName + "</td>";*/
				
//				output += "<tr><td>" + Hospital_ID + "</td>";
//				output += "<td>" + H_Name + "</td>";
				output += "<td>" + H_Address + "</td>";
				output += "<td>" + H_City + "</td>";
				output += "<td>" + H_phonenumber + "</td>";
				output += "<td>" + H_Desc + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='"
						 + Hospital_ID + "'>" + "</td></tr>";
				
				/*output += "<td><input name=\"btnUpdate\"type=\"button\" value=\"Update\"class=\" btnUpdate btn btn-secondary\"></td><td><form method=\"post\" action=\"User.jsp\"><input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\"><input name=\"hidUserIDDelete\" type=\"hidden\"value=\""
						+ userID + "\">" + "</form></td></tr>";*/
				
				/*
				 * output +=
				 * "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
				 * + "<td><form method=\"post\" action=\"items.jsp\">" +
				 * "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
				 * + "<input name=\"userID\" type=\"hidden\" value=\"" + userID + "\">" +
				 * "</form></td></tr>";
				 */
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String Hospital_ID ,String H_Name, String H_Address, String H_City,String H_phonenumber,String H_Desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE reg_hospital SET H_Name=?,H_Address=?,H_City=?,H_phonenumber=?,H_Desc=? WHERE Hospital_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, H_Name);
			preparedStmt.setString(2, H_Address);
			preparedStmt.setString(3, H_City);
			preparedStmt.setString(4, H_phonenumber);
			preparedStmt.setString(5, H_Desc);
			preparedStmt.setInt(6, Integer.parseInt(Hospital_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			
			String newUsers = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while updating the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String Hospital_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from reg_hospital where Hospital_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Hospital_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newUsers = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while deleting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Hospital.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}



	public String readHospitalAppointmentsHistory(String Hospital_ID1) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			 output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Appointment type</th><th>Appointment no</th><th>Appointment Date</th><th><Appointment Description</th><th>User ID</th><th>Doctor ID</th><th>Hospital ID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payment where user_id="+ Hospital_ID1;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Appointment_ID = Integer.toString(rs.getInt("Appointment_ID"));  
			       String Appointment_type = rs.getString("Appointment_type");   
			       String Appointment_no = rs.getString("Appointment_no");      
			       String Appointment_date = rs.getString("Appointment_date");
			       String Appointment_description = rs.getString(("Appointment_description")); 
			       String Ruser_ID = rs.getString("Ruser_ID");
			       String Rdoctor_ID = rs.getString("Rdoctor_ID");
			       String Hospital_ID = rs.getString("Hospital_ID");
		   

				// Add into the html table
			       output += "<tr><td>" + Appointment_ID + "</td>";     
			       output += "<td>" + Appointment_type + "</td>";  
			       output += "<td>" + Appointment_no + "</td>";        
			       output += "<td>" + Appointment_date + "</td>"; 
			       output += "<td>" + Appointment_description + "</td>";
			       output += "<td>" + Ruser_ID + "</td>";     
			       output += "<td>" + Rdoctor_ID + "</td>";    
			       output += "<td>" + Hospital_ID + "</td>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	


	public String userLogin(String username, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			String query ="select username,phoneNo from users where username="+username+" AND phoneNo="+password;
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = ((java.sql.Statement) preparedStmt).executeQuery(query);
			
			
			  while (rs.next()) {
			        String UserName = rs.getString("username");
			        String Password = rs.getString("phoneNo");
			        
			  
			        if((username.equalsIgnoreCase(UserName)) && (password.equalsIgnoreCase(Password))) {
			        	//output ="     Login Failed  !!";
			        	output ="     Login Successful  !!           You're logged as"   +username;
			        	}
		              else {
		                output ="      Login Failed...!!";
		            	 //output ="     Login Successful  !!           You're logged as"   +username;
		              	} 
			  	}
			
			con.close();
			
		}catch (Exception e) {
			output = "Error while Logging.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
}
