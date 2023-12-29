package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet{
	public static final String query = "UPDATE tasks SET taskName = ?, taskDesc = ?, taskDate = ?, taskPriority = ?, userId = ? WHERE taskId = ?";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrive taskId data
		int taskId = Integer.parseInt(request.getParameter("taskId"));
				
		String taskName = request.getParameter("taskName");
		String taskDesc = request.getParameter("taskDesc");
		Date taskDate = Date.valueOf(request.getParameter("taskDate"));		
		String taskPriority = request.getParameter("taskPriority");		
		Object userId = request.getSession().getAttribute("userId");
		
		
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
		
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, taskName);
			ps.setString(2, taskDesc);
			ps.setDate(3, taskDate);
			ps.setString(4, taskPriority);
			ps.setInt(5, (int) userId);
			ps.setInt(6, taskId);
							
			int count = ps.executeUpdate();
			
			if(count == 1) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/main");
				rd.forward(request, response);
				
				connection.close();
			}
			else {
				connection.close();
				response.sendRedirect("error.html");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
