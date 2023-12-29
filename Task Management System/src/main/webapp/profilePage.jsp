<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<style>
a:hover {
	background-color: orange;
}
a:active {
	background-color: yellow;
}
</style>
</head>
<%
ResultSet rs = (ResultSet) request.getAttribute("profileInfo");
rs.next();
%>
<body>

	<div class="header">TASK MASTER</div>
	<div class="main">
		<h1>My Profile</h1>

		<form action="editProfile?userId=<%=rs.getInt("userId")%>" method="post">
		
		    <label for="taskname">User Name:</label>
		    <input type="text" id="taskname" name="taskName" size="20" value="<%= rs.getString("userName") %>"> <br><br>
		    
		    <label for="desc">Email:</label>
		    <input type="email" id="taskname" name="taskName" size="25" value="<%= rs.getString("email") %>"> <br><br>
		     
		    <label for="password">Password:</label>
		    <input type="password" id="password" name="password" size="21" value="<%= rs.getString("password") %>"> <br><br>	
		    
		    <input type="checkbox" onclick="showPassword()" ><span style="font-size:16px; font-style:italic;">Show Password</span> <br><br>	
			
			<input type="submit" value="EDIT">
			
		</form>
		<br><br><br><br><br>
		<div class="buttons" style="display: flex; gap:8px;">
			<button onclick="confirmLogOut()">Log Out</button>
			<button onclick="confirmDeleteAccount('<%=rs.getInt("userId")%>')">Delete Account</button>
		</div>
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
	
	function confirmDeleteAccount(userId) {
	    var confirmation = confirm("Are you sure you want to delete your account?");
	
	    if (confirmation) {
	        window.location.href = 'deleteAccount?userId=' + userId;
	    }
	}
	
	function confirmLogOut() {
	    var confirmation = confirm("Are you sure you want to logout?");
	
	    if (confirmation) {
	        window.location.href = 'logout';
	    }
	}
</script>
	
</body>
</html>