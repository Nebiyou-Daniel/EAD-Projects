package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeTask")
public class RemoveTaskServlet extends HttpServlet{
	public static final String query = "DELETE FROM tasks WHERE taskId = ?";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrive taskId data
		int taskId = Integer.parseInt(request.getParameter("taskId"));
				
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, taskId);
							
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

