<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ref" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<style type="text/css">
	 .foot{
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	
	background-color: black;
	color: white;
	text-align: center;
}
	</style>
</head>
<body>
	<nav class="navbar navbar-light" style="background-color:black;">
  		<div>
  			<img height="100px" 
  				src="https://media.istockphoto.com/id/1342748790/vector/car-parking-icon-parking-space-parking-lot-car-park.jpg?s=612x612&w=0&k=20&c=hNw7RyLko256Z_yvk1IeRnxttUsgtECmCK5zIQvOlQg=">
  			<a href="Info.jsp"><button>Home</button></a>
  		</div>
	</nav>
<form action="informationView" method="get">

Location :<select name = "location" placeholder="select Location " >
<option></option>
<option>RajajiNagar</option>
<option>JayaNagar</option>
<option>VijayNagar</option>
<option>KamalaNagar</option>
<option>IndraNagar</option>


</select>
<button>Search</button>
</form>
<table class="table" border="5">
<tr>

<th>Location</th>
<th>VehicleType</th>
<th>VehicleClassification</th>
<th>Terms</th>
<th>Price</th>
<th>Discount</th>
</tr>


<ref:forEach items = "${ list}" var= "list">
<tr>
<td>${list.getLocation()}</td>
<td>${list.getVehicleType()}</td>
<td>${list.getVehicleClassification()}</td>
<td>${list.getTerms()}</td>
<td>${list.getPrice()}</td>
<td>${list.getDiscount()}</td>
</tr>


</ref:forEach>
</tr>

</table>
<footer>
	<div class="foot">
		<small>@ 2023 Copyright &copy; xworkz.com</small>
	</div>
</footer>
</body>
</html>