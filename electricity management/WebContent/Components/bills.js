$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
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
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
    {
        url: "billsAPI",
        type: type,
        data: $("#formItem").serialize(),
        dataType: "text",
        complete: function (response, status) {
            onItemSaveComplete(response.responseText, status);
        }
    }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function (event) {
   $("#hidItemIDSave").val($(this).data("billid")); 
    $("#cusName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#cusEmail").val($(this).closest("tr").find('td:eq(1)').text());
    $("#accNo").val($(this).closest("tr").find('td:eq(2)').text());
    $("#cusCNo").val($(this).closest("tr").find('td:eq(3)').text());
    $("#bilval").val($(this).closest("tr").find('td:eq(4)').text());
});
// CLIENT-MODEL================================================================
function validateItemForm() {
    // CODE
    if ($("#cusName").val().trim() == "") {
        return "Insert Item Code.";
    }
    // NAME
    if ($("#cusEmail").val().trim() == "") {
        return "Insert Item Name.";
    }
    9
    // PRICE-------------------------------
    if ($("#accNo").val().trim() == "") {
        return "Insert Item Price.";
    }
   
    // DESCRIPTION------------------------
    if ($("#cusCNo").val().trim() == "") {
        return "Insert Item Description.";
    }
    
     // DESCRIPTION------------------------
    if ($("#billval").val().trim() == "") {
        return "Insert Item Description.";
    }
    return true;
}



function onItemSaveComplete(response, status) {
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
    $("#hidItemIDSave").val("");
    $("#formItem")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "billsAPI", 
 type : "DELETE", 
 data : "billID=" + $(this).data("billid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}