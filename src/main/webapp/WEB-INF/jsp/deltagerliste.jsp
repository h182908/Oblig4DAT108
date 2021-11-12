<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<c:forEach var="item" items="${userlist}">
			<tr bgcolor="${(item.phone_number == user.phone_number) ? '#aaffaa' : '#ffffff'}">
				<td align="center">${item.gender == "kvinne" ? '&#9792;' : '&#9794;'}</td>
				<td>${item.first_name} ${item.last_name}</td>
				<td>${item.phone_number}</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="${logoutUrl}">Ferdig</a>
	</p>
</body>
</html>