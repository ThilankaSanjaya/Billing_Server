$(document).ready(function(){
	$("#alertSuccess").hide();
	$("#alertError").hide();
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateBill_DetailsForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "BillsAPI",
			type: type,
			data: $("#formBill_Details").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onBillSaveComplete(response.responseText, status);
			}
		});
});

function onBillSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
			
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
	$("#hididSave").val("");
	$("#formBill_Details")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hididSave").val($(this).data("id")); 
 $("#acc_no").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#customer_name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#qty").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#total_price").val($(this).closest("tr").find('td:eq(3)').text());
 $("#date_time").val($(this).closest("tr").find('td:eq(3)').text());
});



$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "BillsAPI",
			type: "DELETE",
			data: "id=" + $(this).data("id"),
			dataType: "text",
			complete: function(response, status) {
				onBillDeleteComplete(response.responseText, status);
			}
		});
});

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
// CLIENT-MODEL================================================================
function validateBill_DetailsForm() {
	// CODE
	if ($("#id").val().trim() == "") {
		return "Insert id.";
	}
	// NAME
	if ($("#acc_no").val().trim() == "") {
		return "Insert acc_no.";
	}
	// NAME
	if ($("#customer_name").val().trim() == "") {
		return "Insert customer_name.";
	}
	// NAME
	if ($("#address").val().trim() == "") {
		return "Insert address.";
	}
	// NAME
	if ($("#qty").val().trim() == "") {
		return "Insert qty.";
	}
	// PRICE-------------------------------
	if ($("#total_price").val().trim() == "") {
		return "Insert total_price.";
	}
	// is numerical value
	var tmpPrice = $("#total_price").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for total_price.";
	}
	// convert to decimal price
	$("#total_price").val(parseFloat(tmpPrice).toFixed(2));
	// DESCRIPTION------------------------
	if ($("#date_time").val().trim() == "") {
		return "Insert date_time.";
	}
	return true;
}
