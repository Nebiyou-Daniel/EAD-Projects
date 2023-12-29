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

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet{
	public static final String query = "DELETE FROM users WHERE userId = ?";
	public static final String taskQuery = "DELETE FROM tasks WHERE userId = ?";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrive taskId data
		int userId = Integer.parseInt(request.getParameter("userId"));
				
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			PreparedStatement ps2 = connection.prepareStatement(taskQuery);

			ps.setInt(1, userId);
			ps2.setInt(1, userId);
							
			ps2.executeUpdate();
			int count = ps.executeUpdate();
			
			if(count == 1) {
				
				connection.close();
				response.sendRedirect("index.html");				
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
