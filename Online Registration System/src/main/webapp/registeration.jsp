<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Registration Form</title>
	</head>
	
	<body>
		<%@ include file="header.jsp" %>
		<h2>Registration Form</h2>
		
		<form action="register" method="post">
		 Name: <input type="text" name="name" value="<%= request.getParameter("name") %>"><br><br>
		 Email: <input type="text" name="email" size="30" value="<%= request.getParameter("email") %>"><br><br>
		 Password: <input type="password" name="password"><br><br><br>
		 <input type="submit" value="Register">
		</form>
		
		 <%! // Declaration - User class definition
			 public class User {
			 private String name;
			 private String email;
			 private String password;
			 // Constructors, getters, and setters
			 }
		 %>
	</body>
</html>