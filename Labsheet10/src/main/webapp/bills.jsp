<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.Bill"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Components/bills.js"></script>


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Billing Management </h1>
				<form id="formBill_Details" name="formBill_Details">
					BILL ID: <input id="id" name="id" type="text"
			class="form-control form-control-sm"> <br> Account Number: <input
			id="acc_no" name="acc_no" type="text"
			class="form-control form-control-sm"> <br> Customer Name: <input
			id="customer_name" name="customer_name" type="text"
			class="form-control form-control-sm"> <br> 
			Address: <input id="address" name="address" type="text"
			class="form-control form-control-sm"> <br> 
			qty: <input id="qty" name="qty" type="text"
			class="form-control form-control-sm"> <br> 
			Total Price: <input id="total_price" name="total_price" type="text"
			class="form-control form-control-sm"> <br> 
			Date: <input id="date_time" name="date_time" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hididSave" name="hididSave" value=""> <input
			id="btnreset" name="reset" type="reset" value="Reset"
			class="btn btn-danger">		
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divbillsGrid">
					<%
					Bill billObj = new Bill();
								out.print(billObj.readBills());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>