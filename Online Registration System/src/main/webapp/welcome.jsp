<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<body>

<%
	Object name = session.getAttribute("name");
%>

	<h1>Welcome! <%= name %></h1>

</body>
</html>