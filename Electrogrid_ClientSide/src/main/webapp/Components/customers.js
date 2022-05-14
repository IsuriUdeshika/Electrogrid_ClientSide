$(document).ready(function()
{ 
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateCustomerForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
 url : "CustomersAPI",
 type : type,
 data : $("#formCustomer").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerSaveComplete(response.responseText, status);
 }
});
});
function onCustomerSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidCustomerIDSave").val("");
 $("#formCustomer")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
 $("#accountNo").val($(this).closest("tr").find('td:eq(0)').text());
 $("#customerName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#customerAddress").val($(this).closest("tr").find('td:eq(2)').text());
 $("#customerPhone").val($(this).closest("tr").find('td:eq(3)').text());
});

//delete
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "CustomersAPI",
 type : "DELETE",
 data : "customerID=" + $(this).data("customerid"),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerDeleteComplete(response.responseText, status);
 }
 });
});
function onCustomerDeleteComplete(response, status)
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
// CLIENT-MODEL================================================================
function validateCustomerForm()
{
// CODE
if ($("#accountNo").val().trim() == "")
 {
 return "Insert Account Number.";
 }
// NAME
if ($("#customerName").val().trim() == "")
 {
 return "Insert Customer Name.";
 } 
 // Address-------------------------------
if ($("#customerAddress").val().trim() == "")
 {
 return "Insert Customer Address.";
 }
// Phone------------------------
if ($("#customerPhone").val().trim() == "")
 {
 return "Insert Customer Phone number.";
 }
return true;
}
