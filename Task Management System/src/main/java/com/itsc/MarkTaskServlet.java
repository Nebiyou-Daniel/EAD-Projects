package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/markTask")
public class MarkTaskServlet extends HttpServlet{
	public static final String updateQuery = "UPDATE tasks SET completed = ? WHERE taskId = ?";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrive taskId data
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		boolean isCompleted = Boolean.parseBoolean(request.getParameter("completed"));
					
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
		
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
//			PreparedStatement ps = connection.prepareStatement(selectQuery);
			PreparedStatement ps = connection.prepareStatement(updateQuery);

//			ps.setInt(1, taskId);
			if (isCompleted) {
				isCompleted = false;
			} else {
				isCompleted = true;
			}
			
			ps.setBoolean(1, isCompleted);
			ps.setInt(2, taskId);
							
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
