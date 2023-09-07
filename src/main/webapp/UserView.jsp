<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
	background-color: #eee;
}
.footer{
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	
	background-color: black;
	color: white;
	text-align: center;
}
.container {
  height: 200px;
  position: relative;
 
}

.vertical-center {
  margin: 0;
  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}
</style>
</head>
<body>

<nav class="navbar navbar-light" style="background-color: black;">
  		<div>
  			<img height="100px" 
  				src="https://media.istockphoto.com/id/1342748790/vector/car-parking-icon-parking-space-parking-lot-car-park.jpg?s=612x612&w=0&k=20&c=hNw7RyLko256Z_yvk1IeRnxttUsgtECmCK5zIQvOlQg=">
  			<a href="UserLoginSucess.jsp"><button type="button" class="btn btn-light">Home</button></a>
  		</div>
  		<div class="nav navbar-nav navbar-right">
  			
  			<p class="navbar-text" style="color: white; font-size: 20px; font-family:serif;">User , ${userDto.userName}</p>
  		</div>
	</nav>

<table border="4">

<tr>
<th>Name</th>
<th>Email</th>
<th>ContactNo</th>
</tr>



<tr>

<td>${userDto.userName }</td>
<td>${userDto.emailId }</td>
<td>${userDto.contactNo }</td>

</tr>
</table>
<table border="5">
<tr>
<th>Location</th>
<th>Vehicle Type</th>
<th>Vehicle Classification</th>
<th>Terms</th>
<th>Price</th>
<th>Discount</th>
<th>TotalAmount</th>
<th>Image</th>
<th>ACTION</th>
<th>ACTION-I</th>
</tr>
<c:forEach items="${userInfoDto}"  var = "dtoss">
<tr>

<td>${dtoss.location}</td>
<td>${dtoss.vehicleType }</td>
<td>${dtoss.vehicleClassification }</td>
<td>${dtoss.terms }</td>
<td>${dtoss.price }</td>
<td>${dtoss.discount }</td>
<td>${dtoss.totalAmount }</td>
<td><a target="_blank" href="filedownload?imageName=${dtoss.imageName}&contentType=${dtoss.contentType}">${dtoss.imageName }</a></td>
<td><a href="update/${dtoss.parkingId}">Update</a></td>
<td><a href="delete/${dtoss.parkingId}">Delete</a></td>



</tr>
</c:forEach>
</table>

<div class="footer">
		<small>@ 2023 Copyright &copy; xworkz.com</small>
		
	</div>
</body>
</html>