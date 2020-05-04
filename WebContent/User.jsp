<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.User"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
	<h1>User Management</h1>



	<form id="formUser" name="formUser" method="post" action="User.jsp">
		Username : <input id="username" name="username" type="text"
			class="form-control form-control-sm"> <br> Phone No : <input
			id="phoneNo" name="phoneNo" type="text"
			class="form-control form-control-sm"> <br> Age : <input
			id="age" name="age" type="text" class="form-control form-control-sm">
		<br> Address : <input id="address" name="address" type="text"
			class="form-control form-control-sm"> <br> Gender : <input
			id="gender" name="gender" type="text"
			class="form-control form-control-sm"> <br> Email : <input
			id="email" name="email" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidUserIDSave" name="hidUserIDSave" value="">
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divUsersGrid">
		<%
			User UserObj = new User();
		out.print(UserObj.readUsers());
		%>
	</div>

</body>
</html>