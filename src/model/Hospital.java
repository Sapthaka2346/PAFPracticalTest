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

	public String readItems(String name, String phone, String age, String address, String gender, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into users(`user_id`,`username`,`phoneNo`,`age`,`address`,`gender`,`email`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, phone);
			preparedStmt.setInt(4, Integer.parseInt(age));
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, email);
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while inserting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User Name</th><th>Phone No</th><th>Age</th><th>Address</th><th>Gender</th><th>Email</th></tr>";
			String query = "select * from users";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("user_id"));
				String userName = rs.getString("username");
				String phoneNo = rs.getString("phoneNo");
				String age = Integer.toString(rs.getInt("age"));
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				// Add into the html table
				output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + userID + "'>" + userName + "</td>";
				
				/*output += "<tr><td><input id=\"hidUserIDUpdate\"name=\"hidUserIDUpdate\"type=\"hidden\" value=\""
						+ userID + "\">" + userName + "</td>";*/
				
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='"
						 + userID + "'>" + "</td></tr>";
				
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

	public String updateUserDetails(String ID, String name, String phone, String age, String address, String gender,
			String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE users SET username=?,phoneNo=?,age=?,address=?,gender=?,email=?WHERE user_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phone);
			preparedStmt.setInt(3, Integer.parseInt(age));
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, email);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while updating the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUsers(String user_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from users where user_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(user_id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while deleting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readSelectedUsers(String user_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User Name</th><th>Phone No</th><th>Age</th><th>Address</th><th>Gender</th><th>Email</th></tr>";
			String query = "select * from users where user_id=" + user_id;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("user_id"));
				String userName = rs.getString("username");
				String phoneNo = rs.getString("phoneNo");
				String age = Integer.toString(rs.getInt("age"));
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				// Add into the html table
				output += "<tr><td>" + userName + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
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
			output = "Error while reading the selected user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsersPaymentHistory(String user_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>User ID</th><th>Paymment Method</th><th>Payment time</th><th>Purpose</th><th>Amount</th><th>Statues</th></tr>";
			String query = "select * from payment where user_id="+ user_id;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentid = Integer.toString(rs.getInt("payment_id"));
				String userId = Integer.toString(rs.getInt("user_id"));
				String paymentMethod = rs.getString("Payment_method");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				String paymentTime = dateFormat.format(rs.getDate("paid_time"));
				String purpose = rs.getString("purpose");
				String Amount = Float.toString(rs.getFloat("amount"));
				String statues = rs.getString("status");

				// Add into the html table
				output += "<tr><td>" + paymentid + "</td>";
				output += "<td>" + userId + "</td>";
				output += "<td>" + paymentMethod + "</td>";
				output += "<td>" + paymentTime + "</td>";
				output += "<td>" + purpose + "</td>";
				output += "<td>" + Amount + "</td>";
				output += "<td>" + statues + "</td>";
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
	
	public String readUsersAppointmentsHistory(String userid) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Patient ID</th><th>Patient Name</th><th>Doctor ID</th><th>Doctor Name</th><th>Hospital ID</th><th>Hospital Name</th><th>Appointment Time</th><th>Appointment Date</th><th>Ward No</th></tr>";
			String query = "select * from appointments where user_id="+ userid;
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String appointment_id = Integer.toString(rs.getInt("appointment_id"));
				String user_id = Integer.toString(rs.getInt("user_id"));
				String username = rs.getString("username");
				String doctor_id = Integer.toString(rs.getInt("doctor_id"));
				String doctor_name = rs.getString("doctor_name");
				String hospital_id = Integer.toString(rs.getInt("hospital_id"));
				String hospital_name = rs.getString("hospital_name");
				String appointment_time = rs.getString("appointment_time");
				String appointment_date = rs.getString("appointment_date");
				String WardNo = rs.getString("WardNo");
	
				// Add into the html table
				output += "<tr><td>" + appointment_id + "</td>";
				output += "<td>" + user_id + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + doctor_id + "</td>";
				output += "<td>" + doctor_name + "</td>";
				output += "<td>" + hospital_id + "</td>";
				output += "<td>" + hospital_name + "</td>";
				output += "<td>" + appointment_time + "</td>";
				output += "<td>" + appointment_date + "</td>";
				output += "<td>" + WardNo + "</td>";
	
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users appointments.";
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
