
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Info</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
  			<a href="UserSignIn.jsp"><button type="button" class="btn btn-light">Home</button></a>
  		</div>
  		<div class="nav navbar-nav navbar-right">
  			<a href="UserLogin.jsp"><button type="button" class="btn btn-light">LogOut</button></a>
  			<p class="navbar-text" style="color: white; font-size: 20px; font-family:serif;">User , ${userDto.userName}</p>
  		</div>
	</nav>
	
	<div class="container">
  <div class="center">
	<a href="Parking.jsp"><button>ParkingInfo</button></a>
	<a href="fetchAndUpdate"><button>View</button></a>
	</div>
	</div>
	
	
	<div class="footer">
		<small>@ 2023 Copyright &copy; xworkz.com</small>
		<p>last_login_time: ${userDto.loginTime}</p>
	</div>
	
</body>
</html>