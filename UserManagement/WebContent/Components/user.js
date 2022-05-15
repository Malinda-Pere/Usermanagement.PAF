$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// ID
if ($("#id").val().trim() == "")
{
return "Insert Id.";
}
// NAME
if ($("#name").val().trim() == "")
{
return "Insert  Name.";
}
// Address
if ($("#address").val().trim() == "")
{
return "Insert  Address.";
}
//Phone
if ($("#phone").val().trim() == "")
{
return "Insert Phone";
}
//NIC
if ($("#nic").val().trim() == "")
{
return "Insert nic";
}
//MAil
if ($("#mail").val().trim() == "")
{
return "Insert mail";
}

// power Area
if ($("#powerarea").val().trim() == "")
{
return "Insert power area.";
}
return true;
}

//Save Func
function onUserSaveComplete(response, status) {
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
	$("#hidCustomerIDSave").val("");
	$("#formAccount")[0].reset();
}


// Save Btn
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------  
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------  
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "userAPI",
			type: type,
			data: $("#formItem").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onAccountSaveComplete(response.responseText, status);
			}
		});
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("code"));
$("#id").val($(this).closest("tr").find('td:eq(0)').text());
$("#name").val($(this).closest("tr").find('td:eq(1)').text());
$("#address").val($(this).closest("tr").find('td:eq(2)').text());
$("#phone").val($(this).closest("tr").find('td:eq(3)').text());
$("#nic").val($(this).closest("tr").find('td:eq(4)').text());
$("#mail").val($(this).closest("tr").find('td:eq(5)').text());
$("#powerarea").val($(this).closest("tr").find('td:eq(6)').text());
});

//Delete Func
function onItemDeleteComplete(response, status) {
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


// DELETE Click
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "UserAPI",
			type: "DELETE",
			data: "code=" + $(this).data("code"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});


