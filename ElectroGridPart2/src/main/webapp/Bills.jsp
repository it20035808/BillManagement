<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Bill Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/Bills.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
				<h1>Bill Management</h1>
				
				<form id="formBill" name="formBill">
					
					UserID : <input id="UserID" name="UserID" type="text" class= "form-control form-control-sm" required> <br>
					
					Billing Date (yyyy-mm-dd): <input id="BillingDate" name="BillingDate" type="text" class= "form-control form-control-sm" required> <br>
					
					Unit Cost : <input id="UnitCost" name="UnitCost" type="text" class="form-control form-control-sm" required> <br>
					
					Units Used : <input id="UnitsUsed" name="UnitsUsed" type="text" class="form-control form-control-sm" required> <br>
					
					Service Charge : <input id="ServiceCharge" name="ServiceCharge" type="text" class="form-control form-control-sm" required> <br>
					
					Bill Settled :  <select id="BillSettled" name="BillSettled" required>
										<option value="false">False</option>
										<option value="true">True</option>
									</select>
									
					<br><br><br>
					
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					 
					<input type="hidden" id="hidBillIDSave" name="hidBillIDSave" value="">
					
				</form>
				
				<br>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				
				<div id="divItemsGrid">
				</div>
			
			</div>
		</div>
	</div>
</body>
</html>