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

@WebServlet("/profilePageSetup")
public class ProfilePageSetupServlet extends HttpServlet{
	private static final String DB_URL = "jdbc:mysql://localhost:3306/taskmaster";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "the1trueND!";
	private static final String query = "select * from users where userId = ?";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		// Retrive taskId data
//		int userId = Integer.parseInt(request.getParameter("userId"));
		Object userId = request.getSession().getAttribute("userId");
		
		try {			
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, (int) userId);
			
			try (ResultSet rs = statement.executeQuery()) {
								
				request.setAttribute("profileInfo", rs);
//				rs.next();
//				
//				request.setAttribute("userName", rs.getString("userName"));
//				request.setAttribute("email", rs.getString("email"));
//				request.setAttribute("password", rs.getString("password"));
//				request.setAttribute("userId", userId);
				
				RequestDispatcher rd = request.getRequestDispatcher("profilePage.jsp?id=" + userId.toString());
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
	}
}
