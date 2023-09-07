<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Information</title>
<link rel="stylesheet" 
href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
 integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
 crossorigin="anonymous">
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
 
 <script type="text/javascript">
 function priceValidation(){
	 var price = document.getElementById("price");
	 var priceValue =  price.value;
	 var priceSpan = document.getElementById("prices");
	 
	 if(priceValue >20 && priceValue != null && priceValue != ""){
		 priceSpan.innerHTML = "";
		 
	 }else{
		 priceSpan.innerHTML = "price should be greater than 20";
		 
		 
	 }
	 
	 
 }
 
 function discountValidation(){
	 var discount = document.getElementById("discount");
	 var discountValue =  discount.value;
	 var discountSpan = document.getElementById("discounts");
	 
	 if( discountValue != null && discountValue != ""){
		 discountSpan.innerHTML = "";
		 
	 }else{
		 discountSpan.innerHTML = "discount should be given";
		 
		 
	 }
	 
	 
 }
 
 </script>
</head>
<body style="text-align: center;">

	<nav class="navbar navbar-light" style="background-color:black;">
  		<div>
  			<img height="100px" 
  				src="https://media.istockphoto.com/id/1342748790/vector/car-parking-icon-parking-space-parking-lot-car-park.jpg?s=612x612&w=0&k=20&c=hNw7RyLko256Z_yvk1IeRnxttUsgtECmCK5zIQvOlQg=">
  			<a href="Info.jsp"><button>Home</button></a>
  		</div>
	</nav>
<c:forEach items="${errors}"  var = "error">
<span style="color: red;">${error.defaultMessage}</span></br>
</c:forEach>
<span style="color: green;">${sucessmsg}</span>
<div class="container">
<div class="card border-0 shadow">
<div class="card card-body"> 
	

<form action="saveInformation" method="post">

<div class="form-group">
Location :<select name = "location" placeholder="select Location " required="required" class="form-control" >

<option></option>
<option>RajajiNagar</option>
<option>JayaNagar</option>
<option>VijayNagar</option>
<option>KamalaNagar</option>
<option>IndraNagar</option>


</select><br>
</div>

<div class="form-group">
VehicleType :<select name="vehicleType" required="required" class="form-control">
<option></option>
<option>Two Wheeler</option>
<option>Four Wheeler </option>
<option>Electric</option>
</select><br>
</div>
<div class="form-group">
Vehicle Classification :<select name = "vehicleClassification" required="required" class="form-control" >
<option></option>
<option>Bike</option>
<option>KIA </option>
<option>Innova Crysta</option>
<option>Swift</option>
<option>BMW</option>


</select><br>
</div>
<div class="form-group">
Terms :<select name = "terms" required="required" class="form-control" >
<option></option>
<option>1 day</option>
<option>7 Day </option>
<option>15 Days</option>
<option>30 Days</option>
<option>60 Days</option>
</select><br>
</div>


<div class="form-group">
Price :<input name="price" type="number" id="price" onblur="priceValidation()" class="form-control" ><br>
<span id="prices" style="color: red;"></span>
</div>
<div class="form-group">
Discount :<input name="discount" type="text" id="discount" onblur="discountValidation()" class="form-control"><br>
<span id="discounts" style="color: red;"></span>
</div>
<input type="submit" name="submit"  class="btn btn-primary">

</form>
</div>
</div>
</div>

<footer>
	<div class="foot">
		<small>@ 2023 Copyright &copy; xworkz.com</small>
	</div>
</footer>

</body>
</html>