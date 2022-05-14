<%@page import="com.bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bills Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/bills.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Bills</h1>

<form id="formItem" name="formItem">
 Customer Name:
 <input id="cusName" name="cusName" type="text"
 class="form-control form-control-sm">
 <br> 
 Customer Email:
 <input id="cusEmail" name="cusEmail" type="text"
 class="form-control form-control-sm">
 <br>
 Account Number:
 <input id="accNo" name="accNo" type="text"
 class="form-control form-control-sm">
 <br> 
 Customer Contact Number:
 <input id="cusCNo" name="cusCNo" type="text"
 class="form-control form-control-sm">
 <br>
 Bill Value:
 <input id="billval" name="billval" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 bill itemObj = new bill();
 out.print(itemObj.readItems());
 %>
</div>
</div> </div> </div>
</body>
</html>

