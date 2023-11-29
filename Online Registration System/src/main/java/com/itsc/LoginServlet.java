package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "the1trueND!";
	private static final String query = "select * from users where email = ? AND password = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		// Retrive form data
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {			
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				//authentication successful
				HttpSession session = request.getSession();
				session.setAttribute("name", rs.getString(2));
				session.setAttribute("email", rs.getString(3));
				connection.close();

				response.sendRedirect("welcome.jsp");
			} else {
				//authentication failed
				connection.close();
				response.sendRedirect("login.jsp");
			}
			
			statement.executeUpdate();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
		
	}
}
