<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Failure</title>
<link href="assets/css/bootstrap-united.css" rel="stylesheet" />
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>
	<script src="jquery-1.8.3.js">
		
	</script>

	<script src="bootstrap/js/bootstrap.js">
		
	</script>

	<div class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/Reunion">Home</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contact us</a></li>
						<li class="divider"></li>
						<li><a href="#">Further Actions</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<!-- 
	<legend>SeniorCitizen Enrollment Login Success</legend>
	 -->
	<div class="panel panel-danger">
		<div class="panel-heading">
			<h3 class="panel-title">SeniorCitizen Requests</h3>
		</div>
		<div class="panel-body">
			<table>
				<tr>
					<th>ID</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>User Name</th>
					<th>Mobile</th>
					<th>Email</th>
					<th>Address</th>
					<th>status</th>
					<th>Action</th>
				</tr>
				<c:forEach var = "enquiry" items="${AllEnquiries}">
				<tr>
					<td>${enquiry.id }</td>
					<td>${enquiry.firstName }</td>
					<td>${enquiry.lastName }</td>
					<td>${enquiry.userName }</td>
					<td>${enquiry.phoneNumber }</td>
					<td>${enquiry.emailAddress }</td>
					<td>${enquiry.address }</td>
					<td>${enquiry.status }</td>
					<td><c:if test = "${enquiry.status != 'approved'}"><a href="approve.html?id=${enquiry.id }">Approve</a></c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div></div>
	<div></div>
</body>
</html>