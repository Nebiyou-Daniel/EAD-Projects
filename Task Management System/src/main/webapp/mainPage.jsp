<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<title>Home</title>
	<style>
	table, th, td {
	  border: 3px solid black;
	  border-collapse: separate;
	  text-align: center;	
	}
	
	tr {
	  border-collapse: collapse;
	}
	
	</style>
	
    <script>
        function toggleSearchField() {
            var searchField = document.getElementById("searchField");
            searchField.style.display = (searchField.style.display === "none") ? "flex" : "none";
        }
        function toggleSortField() {
            var searchField = document.getElementById("sortField");
            searchField.style.display = (searchField.style.display === "none") ? "flex" : "none";
        }
    </script>
</head>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<body>

	<div class="header">
	
		<div class="left">
			<button onclick="location.href = 'createTask.jsp'"><img src="images/plus-solid.svg" alt="+"></button>
			<button onclick="toggleSortField()"><img src="images/sort.png" alt="Search" width="17px" height="auto"></button>
			
		</div>
		<div class="middle">
			<p style="font-family: verdana;"><a href="main" style="text-decoration: none; color: black;">TASK MASTER</a></p>
		</div>
		<div class="right">	
			<button onclick="toggleSearchField()"><img src="images/magnifying-glass-solid.svg" alt="Search"></button>
			<button onclick="location.href = 'profilePageSetup?userId=' + <%= request.getSession().getAttribute("userId")%>"><img src="images/user-solid.svg" alt="P"></button>	
			
			
		</div>

	</div>
	
	<div class="main">
		
		<div id="searchField" style="display: none;justify-content: center; font-size: 18px; margin: 16px 0">
		
		    <form action="searchResult?userId=<%= request.getSession().getAttribute("userId")%>" method="get">
		        <label for="query">Enter your search query:</label>
		        <input type="text" id="query" name="query" style="border-radius: 5px;" autofocus>
		        <input type="submit" value="Search" style="font-size: 16px; border-radius: 5px;">
		    </form>
		</div>
		
		
		<div id="sortField" style="display: none;justify-content: center; font-size: 18px; margin: 16px 0">
		
		    <form action="sortTasks?userId=<%= request.getSession().getAttribute("userId")%>" method="get">
		        <label for="sort">Sort By:</label>
		        
       			<select id="sort" name="sortMethod" style="border-radius: 5px; font-size: 16px;">
				  <option value="taskName" style="font-size: 16px;" selected>Name</option>
				  <option value="taskDate" style="font-size: 16px;">Date</option>
				  <option value="taskPriority" style="font-size: 16px;">Priority</option>
				  <option value="completed" style="font-size: 16px;">Completed</option>
				</select>
				<input type="submit" value="Sort" style="font-size: 16px; border-radius: 5px;">
				
		    </form>
		</div>
		
		<div class="list">
			<table style="width:100%">
    <%
        ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
		Boolean isThereTask = false;
		String noTasks = "YOU HAVE NO TASKS YET! Try creating a new one using the '+' button on the top left.";
		
        try {
	
            while (resultSet.next()) {
            	isThereTask= true;
%>
			  <tr>
			    <td rowspan="2"> <a href="markTask?taskId=<%= resultSet.getInt("taskId")%>&completed=<%= resultSet.getBoolean("completed")%>"><img src="images/<%= resultSet.getBoolean("completed") ? "done.png" : "not done.png" %>" style="width: 40px; height: auto;"></a></td>
			    <td style="background-color: burlywood; font-weight: 900; font-family: verdana"><%= resultSet.getString("taskName").toUpperCase() %></td>
			    <td style="font-family: verdana; font-weight: 900;"><%= resultSet.getString("taskPriority").toUpperCase() %></td>
			    <td style="font-family: verdana; font-weight: 600; ">For: <%= resultSet.getDate("taskDate") %></td>
			    <td style="background-color:green;"><a href="editTaskSetup?taskId=<%= resultSet.getInt("taskId") %>" style="font-weight:900; text-decoration:none; color:white; font-family: verdana;">EDIT</a></td>
			    <td style="background-color:red;"><button onclick="confirmDelete('<%=resultSet.getString("taskName")%>', '<%= resultSet.getInt("taskId")%>')" style="font-size:16px; font-weight:900; text-decoration:none; color:white; font-family: verdana; background-color: red; border: 0;">DELETE</button></td>

			  </tr>
			  <tr>
			  	<td colspan="5" style="text-align:left; padding:8px; background-color:white; font-family: verdana;"><span style="text-decoration: underline;"><b>Description</b></span>: <%= resultSet.getString("taskDesc") %></td>
			  </tr>
<%
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>	
			</table>

		<br>
		<h1 style="color: green; font-weight: 900; font-size: 24px; text-align: center;"> <%= isThereTask ? "" : noTasks %></h1>

		</div>
				
	</div>
<script>
	function confirmDelete(taskTitle, taskId) {
	    var confirmation = confirm("Are you sure you want to delete the task: " + taskTitle + "?");
	
	    if (confirmation) {
	        window.location.href = 'removeTask?taskId=' + taskId;
	    }
	}
</script>

</body>
</html>