// Hide alerts on page load =============================
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


// Save =================================================
$(document).on("click", "#btnSave", function(event) {
	
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	
	// Form validation-------------------
	var status = validateBillForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}	
	
	//var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	var type;
	if ($("#hidBillIDSave").val() == ""){
		type = "POST";
	}
	else{
		type = "PUT";
	}
	
	$.ajax(
		{
			url: "BillsAPI",
			type: type,
			data: $("#formBill").serialize(),
			dataType: "text",
			error: function(response, status, error) {
  				console.log(response);
			},
			complete: function(response, status) 
			{
				onBillSaveComplete(response.responseText, status);
			}
			
		});
	
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidBillIDSave").val($(this).data("billID"));
	$("#UserID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#BillingDate").val($(this).closest("tr").find('td:eq(1)').text());
	$("#UnitCost").val($(this).closest("tr").find('td:eq(2)').text());
	$("#UnitsUsed").val($(this).closest("tr").find('td:eq(3)').text());
	$("#ServiceCharge").val($(this).closest("tr").find('td:eq(4)').text());
	$("#BillSettled").val($(this).closest("tr").find('td:eq(5)').text());
});


// DELETE==============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "BillsAPI",
			type: "DELETE",
			data: "billID=" + $(this).data("billID"),
			dataType: "text",
			complete: function(response, status) {
				onBillDeleteComplete(response.responseText, status);
			}
		});
});



//Client-Model

function validateBillForm(){
	// UserID
	if ($("#UserID").val().trim() == "") {
		return "Insert User ID.";
	}
		
	// is numerical value
	var tmpUserID = $("#UserID").val().trim();
	if (!$.isNumeric(tmpUserID)) {
		return "Insert a numerical value for User ID.";
	}
	
	// Billing Date
	if ($("#BillingDate").val().trim() == "") {
		return "Insert Billing Date.";
	}
		
		
	// Unit Cost
	if ($("#UnitCost").val().trim() == "") {
		return "Insert Unit Cost.";
	}
		
	// is numerical value
	var tmpUnitCost = $("#UnitCost").val().trim();
	if (!$.isNumeric(tmpUnitCost)) {
		return "Insert a numerical value for Unit Cost.";
	}
		
	// Units Used
	if ($("#UnitsUsed").val().trim() == "") {
		return "Insert Units Used.";
	}
		
	// is numerical value
	var tmpUnitsUsed = $("#UnitsUsed").val().trim();
	if (!$.isNumeric(tmpUnitsUsed)) {
		return "Insert a numerical value for Units Used.";
	}
	
	// Service Charge
	if ($("#ServiceCharge").val().trim() == "") {
		return "Insert Service Charge.";
	}
		
	// is numerical value
	var tmpServiceCharge = $("#ServiceCharge").val().trim();
	if (!$.isNumeric(tmpServiceCharge)) {
		return "Insert a numerical value for Service Charge.";
	}
	
	// Bill Settled
	if ($("#BillSettled").val().trim() == "") {
		return "Insert Bill Settled Option.";
	}
	
	return true;
}



function onBillSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidBillIDSave").val("");
	$("#formBill")[0].reset();
}



function onBillDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

