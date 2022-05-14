<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/customers.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Customers Management</h1>
<form id="formCustomer" name="formCustomer">
 Account No:
 <input id="accountNo" name="accountNo" type="text"
 class="form-control form-control-sm">
 <br> Customer name:
 <input id="customerName" name="customerName" type="text"
 class="form-control form-control-sm">
 <br> Customer Address:
 <input id="customerAddress" name="customerAddress" type="text"
 class="form-control form-control-sm">
 <br> Customer Phone number:
 <input id="customerPhone" name="customerPhone" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidCustomerIDSave"
 name="hidCustomerIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Customer customerObj = new Customer();
 out.print(customerObj.readCustomers());
 %>
</div>
</div> </div> </div>
</body>
</html>