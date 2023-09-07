

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ref" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


  <link href = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style type="text/css">




body{
text-align: center;

}

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
	



<script>
		function findPrice() {
			var location = document.getElementById("location").value;
			var vtype = document.getElementById("vehicleType").value;
			var classification = document.getElementById("vehicleClassification").value;
			var term = document.getElementById("term").value;
			const httpRequest = new XMLHttpRequest();
			httpRequest.open("GET",
					"http://localhost:8080/parking-rental-system/userAjax/"+location + "/" + vtype + "/" + classification + "/" + term);
			httpRequest.send();
			httpRequest.onload = function() {
				console.log(this.responseText);
				const obj=JSON.parse(this.responseText);
				console.log(obj)
				console.log(obj.price)
				console.log(obj.discount)
				document.getElementById("price").value=obj.price;
				document.getElementById("discount").value=obj.discount;
				
				
				console.log(obj.price
						- (obj.price * (obj.discount / 100 )));
				document.getElementById("totalAmount").value = obj.price
				- (obj.price * ( obj.discount / 100 ));
			}
		}
		
		function terms_checked(check){
			
			if(check.checked ){
				document.getElementById("submit").disabled = false;
				
			}else{
				document.getElementById("submit").disabled = true;
			}
			
		}
		
		
		
		
		
	</script>
</head>
<body>
<nav class="navbar navbar-light" style="background-color:black;">
  		<div>
  			<img height="100px" 
  				src="https://media.istockphoto.com/id/1342748790/vector/car-parking-icon-parking-space-parking-lot-car-park.jpg?s=612x612&w=0&k=20&c=hNw7RyLko256Z_yvk1IeRnxttUsgtECmCK5zIQvOlQg=">
  			
  		</div>
	</nav>


<form action="${pageContext.request.contextPath}/update" method="post" enctype="multipart/form-data">


<input type="text" value="${dto.getParkingId() }" name="ParkingId" readonly="readonly">
<br>

Location :<select name = "location" placeholder="select Location " value="${dto.getLocation()}"  required="required" id="location" class="form-control" >

<option>${dto.getLocation()}</option>
<option>RajajiNagar</option>
<option>JayaNagar</option>
<option>VijayNagar</option>
<option>KamalaNagar</option>
<option>IndraNagar</option>


</select><br>

</div>
<div class="form-group">
VehicleType :<select name="vehicleType" required="required" value = "${dto.getVehicleType()}" id="vehicleType" class="form-control">
<option>${dto.getVehicleType()}</option>
<option>Two Wheeler</option>
<option>Four Wheeler </option>
<option>Electric</option>
</select><br>
</div>
<div class="form-group">
Vehicle Classification :<select name = "vehicleClassification" value = "${dto.getVehicleClassification() }" required="required" id="vehicleClassification"class="form-control" >
<option>${dto.getVehicleClassification() }</option>
<option>Bike</option>
<option>KIA </option>
<option>Innova Crysta</option>
<option>Swift</option>
<option>BMW</option>


</select><br>
</div>
<div class="form-group">
Terms :<select name = "terms" required="required" id="term" value = "${dto.getTerms() }" onchange="findPrice()" class="form-control" >
<option>${dto.getTerms() }</option>
<option>1 day</option>
<option>7 Day </option>
<option>15 Days</option>
<option>30 Days</option>
<option>60 Days</option>
</select><br>
</div>
<div class="form-group">
Price:<input type="text" name="price" id="price" class="form-control" value = "${dto.getPrice() }" readonly="readonly"> 
</div>
<div class="form-group">
		Discount:<input type="text" name="discount" id="discount" class="form-control" value = "${dto.getDiscount() }" readonly="readonly">
</div>
<div class="form-group">
Total Amount:<input type="number" name="totalAmount" class="form-control" id="totalAmount" value = "${dto.getTotalAmount() }" readonly="readonly">
</div>

<div>
UpdateVehicleImage : <input type="file" name="file" value = "<a target="_blank"  href="filedownload?imagesName=${dto.getImageName()}&contentType=${dto.getContentType()}">${dto.getImageName() }</a>" >
</div>

<div>
<a href="UserView.jsp"><button type="submit" name="UPDATE"  class="btn btn-primary" >Submit</button></a>
</div>



</form>

<footer>
	<div class="foot">
		<small>@ 2023 Copyright &copy; xworkz.com</small>
	</div>
</footer>



</body>
</html>