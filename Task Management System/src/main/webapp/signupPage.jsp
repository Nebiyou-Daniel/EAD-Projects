<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login-signup.css">
<title>Sign Up</title>
</head>
<body>
<%
    // Check if an error message exists in the request
    String errorMessage = (String) request.getAttribute("errorMessage");

	// To output what the user has already inserted uon loading
	String insertedEmail = (String) request.getAttribute("insertedEmail");
	String insertedPassword = (String) request.getAttribute("insertedPassword");
	String insertedUserName = (String) request.getAttribute("insertedUserName");

	String userName = (insertedUserName == null) ? "" : insertedUserName;
	String email = (insertedEmail == null) ? "" : insertedEmail;
	String password = (insertedPassword == null) ? "" : insertedPassword;

%>
	<div class="main">
		<h1>SIGN UP</h1>
		
		<form action="signup" method="post">
		
		    <label for="name">Name:</label>
		    <input type="text" id="name" name="userName" size="25" value="<%= userName %>" required>
		    
		    <div style="margin: 16px;"></div>
		    
		    <label for="email">Email:</label>
		    <input type="text" id="email" name="email" size="25" value="<%= email %>" required>
<%
// Display error message if it exists

 if (errorMessage != null) { %>
   <p style="color: red;"><%= errorMessage %></p>
<% } %> 
		    <div style="margin: 16px;"></div>
		    
		    <label for=password>Password:</label>
		    <input type="password" id="password" name="password" size="22" value="<%= password %>" required> <br>
		    
		    <div style="margin: 8px;"></div>
		    
		    <input type="checkbox" onclick="showPassword()">
		    <span style="font-size:18px; font-style:italic;">Show Password</span><br>
		    
		    
		    <div style="margin: 8px;"></div>
			
			<input type="submit" value="Submit" class="submitButton">
			<input type="reset" value="Reset" class="submitButton">
			
			<div style="margin-top: 16px;"><a href="loginPage.jsp">Already have an account? Login</a></div>
			
		</form>
	</div>
	
	<script>
		function showPassword() {
			  var x = document.getElementById("password");
			  if (x.type === "password") {
			    x.type = "text";
			  } else {
			    x.type = "password";
			  }
			}
	</script>
	
</body>
</html>