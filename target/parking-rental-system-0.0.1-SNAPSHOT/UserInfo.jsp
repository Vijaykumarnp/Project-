<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ref" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</style>
<script type="text/javascript">



function findPrice() {
	
	var location = document.getElementById("location").value;
	console.log(location);
	var vType = document.getElementById("vehicleType").value;
	console.log(vType);
	var vClassification  = document.getElementById("vehicleClassification").value;
	console.log(vClassification);
	var term = document.getElementById("term").value;
	console.log(term);
	const httpRequest = new XMLHttpRequest();
	
	httpRequest.open("GET" , "http://localhost:8080/parking-rental-system/userAjax/" + location+ "/"+
			vType + "/" + vClassification + "/"+ term);
	
	httpRequest.send();
	
	httpRequest.onload = function(){
		console.log(this.responseText);
		document.getElementById("error").innerHTML = this.responseText;
		con
	}
	
}






</script>




</head>

<body>

	<nav class="navbar navbar-light" style="background-color:black;">
  		<div>
  			<img height="100px" 
  				src="https://media.istockphoto.com/id/1342748790/vector/car-parking-icon-parking-space-parking-lot-car-park.jpg?s=612x612&w=0&k=20&c=hNw7RyLko256Z_yvk1IeRnxttUsgtECmCK5zIQvOlQg=">
  			<a href="MainPage.jsp"><button type="button" class="btn btn-light">Home</button></a>
  		</div>
	</nav>

<form action="user" method="post">


ParkingId : <input type="number" name="parkingId">

UserId : <input type="number" name="userId">

UserName : <input type="text" name="userName">
EmailId : <input type="text" name="emailId">
ContactNo : <input type="number" name="contactNo">

Location :<select name = "location" placeholder="select Location " required="required" id="location" >
<option></option>
<option>RajajiNagar</option>
<option>JayaNagar</option>
<option>VijayNagar</option>
<option>KamalaNagar</option>
<option>IndraNagar</option>


</select><br>



VehicleType :<select name="vehicleType" required="required"  id="vehicleType">
<option></option>
<option>Two Wheeler</option>
<option>Four Wheeler </option>
<option>Electric</option>
</select><br>


Vehicle Classification :<select name = "vehicleClassification" required="required" id="vehicleClassification" >
<option></option>
<option>Bike</option>
<option>KIA </option>
<option>Innova Crysta</option>
<option>Swift</option>
<option>BMW</option>


</select><br>


Terms :<select name = "terms" required="required" id="term" onchange="findPrice()" >
<option></option>
<option>1 day</option>
<option>7 Day </option>
<option>15 Days</option>
<option>30 Days</option>
<option>60 Days</option>
</select><br>

Price : ${list.price }

Discount : ${list.discount}

TotalAmount : <input ty>

Agreement <input type="radio" required="required">

<input type="submit" name="Submit">
</form>
</body>
</html>