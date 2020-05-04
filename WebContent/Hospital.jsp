<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Hospital"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/Hospitals.js"></script>
</head>
<body>
	<h1>Hospital Management</h1>



	<form id="formHospital" name="formHospital" method="post" action="Hospital.jsp">
		Hospital Name : <input id="H_Name" name="H_Name" type="text"
			class="form-control form-control-sm"> <br> Hospital Address : <input
			id="H_Address" name="H_Address" type="text"
			class="form-control form-control-sm"> <br> Hospital City : <input
			id="H_City" name="H_City" type="text" class="form-control form-control-sm">
		<br> Hospital Phone Number : <input id="H_phonenumber" name="H_phonenumber" type="text"
			class="form-control form-control-sm"> <br> Hospital Description : <input
			id="H_Desc" name="H_Desc" type="text"
			class="form-control form-control-sm"> 
			 <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divUsersGrid">
		<%
			Hospital UserObj = new Hospital();
		out.print(UserObj.readItems());
		%>
	</div>

</body>
</html>