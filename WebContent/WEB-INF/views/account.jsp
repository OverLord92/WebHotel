<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			<input type="submit" id="submitRequest" value="Send request" />
		</div>
	</form>
	</div>
	
	<div class="container">
		<!-- print register requests -->
		<c:if test="${request.type == 'roomChange'}">
			<c:url value="/approveRoomChange/${request.id}"
				var="changeRoomUrl" />
			<a href="${changeRoomUrl}">odobri premjestanje korisnika u drugu sobu
				${request.username}</a>
		</c:if>
	</div>
</body>
</html>