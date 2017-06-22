<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 --%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyInfo</title>

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 -->
<c:url value='/static/w3.css' var="defaultcss" />
<link href="${defaultcss}" rel="stylesheet">

<c:url value='/static/w3-theme-teal.css' var="tealthemecss" />
<link href="${tealthemecss}" rel="stylesheet">


<script src="/webjars/jquery/3.1.1/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready (function () {
		// getting the public ip address from api and setting on text box
		// ip api : https://www.ipify.org/
		$.get( "https://api.ipify.org?format=json", function( data ) {
			  console.log(data);
			  $( "#ip" ).val(data.ip) ;
			  $( "#ipLabel" ).html(data.ip) ;
			  $( "#ipForm" ).submit();
	    });
		
		function showLocationOnMap (location) {
			  var map;
		      map = new google.maps.Map(document.getElementById('map'), {
		          center: {lat: Number(location.latitude), lng: Number(location.longitude)},
		          zoom: 15
		      });
		      var marker = new google.maps.Marker({
		          position: {lat: Number(location.latitude), lng: Number(location.longitude)},
		          map: map,
		          title: "Public IP:"+location.ipAddress+" @ "+location.city
		      });
		      $( "#myip" ).val(location.ipAddress);
			  $( "#mycity" ).val(location.city);
			  $( "#cityLabel" ).html(location.city) ;
		}
		
		$( "#ipForm" ).submit(function( event ) {
			  event.preventDefault();
			  $.ajax({
				  url: "/geoIp",
				  type: "POST",
				  contentType: "application/x-www-form-urlencoded; charset=UTF-8", // send as JSON
				  data: $.param( {ipAddress : $("#ip").val()} ),

				  complete: function(data) {
				      console.log ("Request complete");
				  },
				  success: function(data) {
					  /* $("#status").html(JSON.stringify(data)); */
					  
					  if (data.ipAddress !=null) {
						  console.log ("Success:"+data.ipAddress); 
					      showLocationOnMap(data);
					  }
				  },
				  error: function(err) {
				      console.log(err);
				      /* $("#status").html("Error:"+JSON.stringify(data)); */
				  },
			  });
		});
		
	});
</script>
</head>
<body>
	<%-- <spring:url value="/admin/new_student" var="createEntry" /> --%>

	<!-- Header -->
	<header class="w3-container w3-theme w3-padding" id="myHeader">
		<i onclick="w3_open()" class="fa fa-bars w3-xlarge w3-button w3-theme"></i>
		<div class="w3-center">
			<h4>Metode Avansate de Analiza Criptografica</h4>
			<h1 class="w3-xxxlarge w3-animate-bottom">WebStats</h1>
			<%-- <form id="sendForm" action="" method="POST">
				<input type="hidden" name="myip" id="myip" /> <input type="hidden"
					name="mycity" id="mycity" />
					<input type="hidden"
					name="mybrowser" id="mybrowser" value = "${browser}"/>
					<input type="hidden"
					name="myos" id="myos" value = "${os}"/>
					<input type="hidden"
					name="mydevicetype" id="mydevicetype" value = "${devicetype}"/>
				<button disabled class="w3-btn w3-xlarge w3-theme-dark w3-hover-teal"
					type="submit" name="send">Send data</button>
			</form> --%>
		</div>
	</header>

	<div class="w3-row-padding w3-center w3-margin-top">
		<div class="w3-half">
			<div class="w3-card-2 w3-container" style="min-height: 100px">
				<h3>Browser</h3>
				<p>${browser}</p>
			</div>
			<div class="w3-card-2 w3-container" style="min-height: 100px">
				<h3>Operation System</h3>
				<p>${os}</p>
			</div>
			<div class="w3-card-2 w3-container" style="min-height: 100px">
				<h3>Device Type</h3>
				<p>${devicetype}</p>
			</div>
			<div class="w3-card-2 w3-container " style="min-height: 100px">
				<h3>Global IP</h3>
				<p id="ipLabel"></p>
			</div>
			<div class="w3-card-2 w3-container " style="min-height: 500px">
				<h3>City</h3>
				<p id="cityLabel"></p>
				<form id="ipForm" action="GeoIPTest" method="POST">
					<input type="hidden" name="ipAddress" id="ip" /> <input
						class="w3-button w3-theme" type="submit" name="submit"
						value="Recenter" />
				</form>
				<div id="map" style="margin-top:10px; margin-bottom:10px; height: 500px; width: 100%"></div>
			</div>
		</div>

		<div class="w3-half">
			<c:forEach var="i" items="${rhlist}">
				<div class="w3-card-2 w3-container" style="min-height: 100px">
					<h3>${i.headerName}</h3>
					<p>${i.headerValue}</p>
				</div>
			</c:forEach>
		</div>

	</div>

	<!-- <div id="status"></div> -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=MAPS_DEVELOPER_API_KEY"
		async defer></script>
</body>
</html>