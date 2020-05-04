$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
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
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UsersAPI",
		type : type,
		data : $("#formUser").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onUserSaveComplete(response.responseText, status);
		}
	});	
});

function onUserSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
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
	$("#hidUserIDSave").val("");
	$("#formUser")[0].reset();
}

//DELETE=============================================================================
	$(document).on("click", ".btnRemove", function(event) {
		$.ajax({
			url : "UsersAPI",
			type : "DELETE",
			data : "user_id=" + $(this).data("userid"),
			dataType : "text",
			complete : function(response, status) {
				onUserDeleteComplete(response.responseText, status);
			}
		});
	});
	
	
	function onUserDeleteComplete(response, status) {
		if (status == "success") {
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success") {
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divUsersGrid").html(resultSet.data);
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


// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidUserIDSave").val(
					$(this).closest("tr").find('#hidUserIDUpdate').val());
			$("#username").val($(this).closest("tr").find('td:eq(0)').text());
			$("#phoneNo").val($(this).closest("tr").find('td:eq(1)').text());
			$("#age").val($(this).closest("tr").find('td:eq(2)').text());
			$("#address").val($(this).closest("tr").find('td:eq(3)').text());
			$("#gender").val($(this).closest("tr").find('td:eq(4)').text());
			$("#email").val($(this).closest("tr").find('td:eq(5)').text());
		});
// CLIENTMODEL=========================================================================
function validateItemForm() {
	// USERNAME
	if ($("#username").val().trim() == "") {
		return "Insert Username.";
	}
	// PHONENO
	if ($("#phoneNo").val().trim() == "") {
		return "Insert Phone No.";
	}
	// is numerical value
	var tmpPhone = $("#phoneNo").val().trim();
	if (!$.isNumeric(tmpPhone)) {
		return "Insert Only Numbers for Phone No.";
	}
	// AGE-------------------------------
	if ($("#age").val().trim() == "") {
		return "Insert Age.";
	}
	// is numerical value
	var tmpAge = $("#age").val().trim();
	if (!$.isNumeric(tmpAge)) {
		return "Insert a numerical value for Age.";
	}
	// ADDRESS------------------------
	if ($("#address").val().trim() == "") {
		return "Insert Address.";
	}
	// GENDER------------------------
	if ($("#gender").val().trim() == "") {
		return "Insert Gender.";
	}
	// EMAIL------------------------
	if ($("#email").val().trim() == "") {
		return "Insert Email.";
	}
	return true;
}
