
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/user.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="formItem" name="formItem" method="post" action="user.jsp">
<div class="form-group">
<br>

	 ID:
	<input id="id" name="id" type="text" class="form-control">
	
	<br>  Name
	<input id="name" name="name" type="text" class="form-control">
	
	<br>  Address:
	<input id="address" name="address" type="text" class="form-control">
	
	<br> Phone:
	<input id="phone" name="phone" type="text" class="form-control">
	<br>
	
	<br> NIC:
	<input id="nic" name="nic" type="text" class="form-control">
	<br>
	
	<br> Mail:
	<input id="mail" name="mail" type="text" class="form-control">
	<br>
	
	<br> power area:
	<input id="powerarea" name="powerarea" type="text" class="form-control">
	<br>
	
	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	
	<input type="hidden" id="hidItemIDSave"name="hidItemIDSave" value="">
	</div>
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<div id="divItemsGrid"></div>

	<%
	User itemObj = new User();
	out.print(itemObj.readUser());
	%>
</body>
</html>