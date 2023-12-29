package com.itsc;

import java.io.IOException;
import java.sql.Connection;
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


@WebServlet("/editTaskSetup")
public class EditTaskSetupServlet extends HttpServlet{
	private static final String DB_URL = "jdbc:mysql://localhost:3306/taskmaster";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "the1trueND!";
	private static final String query = "select * from tasks where taskId = ?";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		// Retrive taskId data
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		try {			
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, taskId);
			
			try (ResultSet rs = statement.executeQuery()) {
				rs.next();
				
				request.setAttribute("taskName", rs.getString("taskName"));
				request.setAttribute("taskDesc", rs.getString("taskDesc"));
				request.setAttribute("taskDate", rs.getDate("taskDate"));
				request.setAttribute("taskPriority", rs.getString("taskPriority"));
				
				RequestDispatcher rd = request.getRequestDispatcher("editTask.jsp?id=" + taskId);
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
		
	}
}
