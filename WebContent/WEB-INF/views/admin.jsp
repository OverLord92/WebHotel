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
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/jquery-ui.min.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.ui.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/checkIfUserExists.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/checkIfFreeRoomExists.js"/>"></script>
<script>
	$(document).ready(function() {
		$('#tabs').tabs();
	});
</script>
</head>
<body>

	<div class="container">
		<h1>Admin</h1>
		<div id="tabs">
			<ul>
				<li><a href="#addNewUser">Add new User</a></li>
				<li><a href="#userRequests">User requests</a></li>
				<li><a href="#users">Users</a></li>
				<li><a href="#searchUsers">Search Users</a></li>
			</ul>

			<div id="addNewUser">
				<spring:url value="/registerUser" var="registerUrl" />
				<form:form action="${registerUrl}" method="POST"
					modelAttribute="user" class="form-inline" id="registerForm">
					<div class="form-group">
						<label for="idNumber">ID Number:</label>
						<form:input path="idNumber" class="form-control"
							id="idNumber" name="idNumber" />
						<form:errors class="help-block" path="password" />
					</div><br>
					<div class="form-group">
						<label for="username">Username:</label>
						<form:input path="username" class="form-control" id="username"
							name="username" />
						<form:errors class="help-block" path="username" />
					</div><br>
					<div class="form-group">
						<label for="password">Password:</label>
						<form:input path="password" class="form-control"
							id="password" name="password" />
						<form:errors class="help-block" path="password" />
					</div><br>
					<div class="form-group">
						<label for="roomType">Room Type:</label>
						<input class="form-control"
							id="roomType" name="roomType" />
					</div><br>
					<div class="form-group">
						<label for="roomNumber">Room Number:</label>
						<input class="form-control" type="text"
							id="roomNumber" name="roomNumber" readOnly />
					</div><br>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="gym" name="gym">
    					Teretana
  						</label>
					</div><br>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="cinema" name="cinema">
    					Kino
  						</label>
					</div><br>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="restaurant" name="restaurant">
    					Restoran
  						</label>
					</div><br>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="pool" name="pool">
    					Bazen
  						</label>
					</div><br>
					<div class="checkbox">
  					<label>
    					<input type="checkbox" value="sauna" name="sauna">
    					Sauna
  						</label>
					</div><br>
					<input id="sumbtiRegistration" type="submit" />
				</form:form>
			</div>
			
			<div id="userRequests">

				<c:forEach items="${requests}" var="request">
					<!-- print room change requests -->
					<c:if test="${request.type == 'roomChange'}">
						<c:url value="/approveRoomChange/${request.id}"
							var="changeRoomUrl" />
						<a href="${changeRoomUrl}">odobri premjestanje korisnika u drugu sobu
							${request.username}</a><br>
					</c:if>
					
					<!-- print service change requests -->
					<c:if test="${request.type == 'serviceChange'}">
						<c:url value="/approveServiceChange/${request.id}"
							var="changeServiceUrl" />
						<a href="${changeServiceUrl}">odobri promjene servisa korsnika
							${request.username}</a><br>
					</c:if>
				</c:forEach>

			</div>
			
			<div id="users">
				<c:forEach items="${users}" var="user">
					<!-- print enabled users -->
					<c:if test="${user.enabled == 'true'}">
						<c:url value="/disableUser/${user.username}" var="disableUserUrl" />
						<a href="${disableUserUrl}">disejblaj korisnika
							${user.username}</a>
					</c:if>
					
					<!-- print disabled users -->
					<c:if test="${user.enabled == 'false'}">
						<c:url value="/enableUser/${user.username}" var="enableUserUrl" />
						<a href="${enableUserUrl}">enejblah korisnika ${user.username}</a>
					</c:if>
					<br>
				</c:forEach>
			</div>
			
			<div id="searchUsers">
			njesto
			</div>

		</div>
	</div>
</body>
</html>