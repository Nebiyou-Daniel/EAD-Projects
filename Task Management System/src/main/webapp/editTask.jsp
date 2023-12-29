<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit your Task</title>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%
	// Retrive userId data
	int taskId = Integer.parseInt(request.getParameter("taskId"));
	request.setAttribute("taskId", taskId);
	
	Object userId = request.getSession().getAttribute("userId");
	request.setAttribute("userId", userId);

%>
<body>
	<div class="main">
		<h1>Edit Task</h1>

		<form action="editTask?taskId=<%=Integer.parseInt(request.getParameter("taskId"))%>" method="post">
		
		    <label for="taskname">Task Name:</label>
		    <input type="text" id="taskname" name="taskName" size="20" value="<%= request.getAttribute("taskName") %>"> <br><br>
		    
		    <label for="desc">Description:</label>
		    <textarea id="desc" name="taskDesc" rows="10" cols="30"><%= request.getAttribute("taskDesc") %></textarea> <br><br>
		    
			<label for="priority">Priority:</label>
			<select id="priority" name="taskPriority">
			  <option value="high" <%= "high".equals(request.getAttribute("taskPriority").toString()) ? "selected" : "" %>>High</option>
			  <option value="medium" <%= "medium".equals(request.getAttribute("taskPriority").toString()) ? "selected" : "" %>>Medium</option>
			  <option value="low" <%= "low".equals(request.getAttribute("taskPriority").toString()) ? "selected" : "" %>>Low</option>
			</select>
			<br><br>
					    
		    <label for="currentDate">Due Date:</label>
		    <input type="date" id="currentDate" name="taskDate" value="<%= request.getAttribute("taskDate") %>"> <br><br>	
			
			<input type="submit" value="Apply Changes" class="submit">
			
		</form>
	</div>
</body>
</html>