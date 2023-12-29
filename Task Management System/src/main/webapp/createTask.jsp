<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a Task</title>
</head>
<body>

	<div class="main">
		<h1>New Task</h1>
		
		<form action="create" method="post">
		
		    <label for="taskname">Task Name:</label>
		    <input type="text" id="taskname" name="taskName" size="20" autofocus> <br><br>
		    
		    <label for="desc">Description:</label>
		    <textarea id="desc" name="taskDesc" rows="10" cols="30"></textarea> <br><br>
		    
			<label for="priority">Priority:</label>
			<select id="priority" name="taskPriority">
			  <option value="high">High</option>
			  <option value="medium">Medium</option>
			  <option value="low" selected>Low</option>
			</select>
			
			<br><br>
					    
		    <label for="currentDate">Due Date:</label>
		    <input type="date" id="currentDate" name="taskDate"> <br><br> 	
			
			<input type="submit" value="Submit" class="submit">
			
		</form>
	</div>

</body>
</html>