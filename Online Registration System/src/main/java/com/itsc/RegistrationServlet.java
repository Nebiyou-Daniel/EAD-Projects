package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "the1trueND!";
	private static final String query = "insert into users(name, email, password) values(?, ?, ?)";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		// Retrive form data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

//		// Create new User Object
//		User user = new User();
//		user.setName(name);
//		user.setEmail(email);
//		user.setPassword(password);
//
//		 // Store the user (you can use a simple ArrayList here or a database)
//		 // For demonstration purposes, let's assume adding to a list
//		 List<User> userList = new ArrayList<>();
//		 userList.add(user);
		 
		 
		 // Redirect to confirmation page
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
			
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("password", password);

			 
			response.sendRedirect("confirmation.jsp");
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
	}
}
