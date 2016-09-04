<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>AccessDenied page</title>
	<style>
		body {
			background-image: url(http://www.mediacurrent.com/sites/default/files/styles/article_full_width_600_/public/better-accessed-denied-01.png?itok=P-2XRGmU); /* ???? ? ???????? ??????????? */
			background-color: #c7b39b; /* ???? ???? */
		}
	</style>
</head>
<body>
	Dear <strong>${user}</strong>, You are not authorized to access this page.
	<br/>
	<a href="<c:url value="/" />">Go to home</a> OR <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>