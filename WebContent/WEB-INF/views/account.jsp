<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="com.hotel.beans.Bill" %>
<%@ page import="com.hotel.beans.Room" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/changeRoom.js"/>"></script>
</head>
<body>
	<div class="container">
	<h1>User Account:</h1>
	
	<spring:url value="/requestRoomChange" var="changeRoomUrl" />
	<form action="${changeRoomUrl}" method="POST">
		<h3>Request a change of room type</h3>
		<div class="fomr-group">
			<label for="roomType">New Room Type:</label>
			<input type="text" id="roomType" name="roomType" />
			<span id="roomStatus"></span>
			<input type="submit" id="submitRoomRequest" value="Send request" />
		</div>
	</form>
	</div>
	
	<div class="container">
		<spring:url value="/requestServiceChange" var="changeServiceUrl" />
		<form action="${changeServiceUrl}" method="POST">
		<h3>Request a change of services</h3>
		<div class="checkbox">
  					<label>
    					<input type="checkbox" value="gym" name="gym">
    					Teretana
  						</label>
					</div>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="cinema" name="cinema">
    					Kino
  						</label>
					</div>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="restaurant" name="restaurant">
    					Restoran
  						</label>
					</div>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="pool" name="pool">
    					Bazen
  						</label>
					</div>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="sauna" name="sauna">
    					Sauna
  						</label>
					</div>
					<input type="submit" id="submitServiceRequest" value="Send request" />
			</form>
	</div>
	<br><br>
	
	<div class="container">
	<h3>User Bills</h3>
		<c:forEach items="${user.bills}" var="bill">
			<table class="table table-hover">
			<tr>
			<th>Bill number</th>
			<th>Start date</th>
			<th>End date</th>
			<th>Room price</th>
			<th>Quantity</th>
			<th>Gym</th>
			<th>Cinema</th>
			<th>Restaurant</th>
			<th>Pool</th>
			<th>Sauna</th>
			<th>Total</th>
			</tr>
			<tr>
			<td>${bill.id}</td>
			<td>${bill.startDate}</td>
			<td>${bill.endDate}</td>
			<td>
				<c:choose>
					<c:when test="${bill.roomType==Room.ONE_BED}">
						${Room.ONE_BED_PRICE}
					</c:when>
					<c:when test="${bill.roomType==Room.TWO_BED}">
						${Room.TWO_BED_PRICE}
					</c:when>
					<c:when test="${bill.roomType==Room.APARTMENT}">
						${Room.APARTMENT_PRICE}
					</c:when>
					
				</c:choose>
			</td>
			<td>${bill.numberOfDays}</td>
			
			<td>
				<c:choose>
					<c:when test="${bill.gym}">
						${Bill.GYM_PRICE}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>	
				</c:choose>
			</td>
			
			
			<td>	
				<c:choose>
					<c:when test="${bill.cinema}">
						${Bill.CINEMA_PRICE}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>	
				</c:choose>
			</td>
			<td><c:choose>
					<c:when test="${bill.restaurant}">
						${Bill.RESTAURANT_PRICE}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>	
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${bill.pool}">
						${Bill.POOL_PRICE}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>	
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${bill.sauna}">
						${Bill.SAUNA_PRICE}
					</c:when>
					<c:otherwise>
						0
					</c:otherwise>	
				</c:choose>
			</td>
			<td>
				${bill.total}
			</td>
			</tr>
			</table><br><br>
		</c:forEach>
	</div>
	
	<div class="container">
		<h4>Excluding the last bill you need to pay: ${user.totalAmountToPay}</h4>
	<br><br>
	<spring:url value="/requestLogOut" var="requestLogOut" />
	<button onclick="requestLogOut">Request LogOut</button>
	<br><br>
	</div>
</body>
</html>