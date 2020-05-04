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
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "HospitalsAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});	
});

function onHospitalSaveComplete(response, status) {
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
	$("#hidHopsitalIDSave").val("");
	$("#formHospital")[0].reset();
}

//DELETE=============================================================================
	$(document).on("click", ".btnRemove", function(event) {
		$.ajax({
			url : "HospitalsAPI",
			type : "DELETE",
			data : "Hospital_ID=" + $(this).data("HospitalID"),
			dataType : "text",
			complete : function(response, status) {
				onHospitalDeleteComplete(response.responseText, status);
			}
		});
	});
	
	
	function onHospitalDeleteComplete(response, status) {
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
			$("#alertError").text("Unknown error while deleting hospital..");
			$("#alertError").show();
		}
	}


// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidHospitalIDSave").val(
					$(this).closest("tr").find('#hidHospitalIDUpdate').val());
			$("#H_Name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#H_Address").val($(this).closest("tr").find('td:eq(1)').text());
			$("#H_City").val($(this).closest("tr").find('td:eq(2)').text());
			$("#H_phonenumber").val($(this).closest("tr").find('td:eq(3)').text());
			$("#H_Desc").val($(this).closest("tr").find('td:eq(4)').text());
		});
// CLIENTMODEL=========================================================================
function validateItemForm() {
	// HOSPITALNAME
	if ($("#H_Name").val().trim() == "") {
		return "Insert Hospital name.";
	}
	// ADDRESS
	if ($("#H_Address").val().trim() == "") {
		return "Insert Address .";
	}
	// CITY-------------------------------
	if ($("#H_City").val().trim() == "") {
		return "Insert City.";
	}
	// PHONE NUMBER------------------------
	if ($("#H_phonenumber").val().trim() == "") {
		return "Insert PhoneNumber.";
	}
	// is numerical value
	var tmpPhone = $("#H_phonenumber").val().trim();
	if (!$.isNumeric(tmpPhone)) {
		return "Insert Only Numbers for Phone No.";
	}
	// DESC------------------------
	if ($("#H_Desc").val().trim() == "") {
		return "Insert Dscription.";
	}
	return true;
}
