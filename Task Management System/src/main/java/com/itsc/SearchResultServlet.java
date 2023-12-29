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

@WebServlet("/searchResult")
public class SearchResultServlet extends HttpServlet{
	private static final String DB_URL = "jdbc:mysql://localhost:3306/taskmaster";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "the1trueND!";
	
//	private static final String query = "SELECT * FROM task WHERE userId = ? AND taskName LIKE '%?%';";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		// Retrive userId data
		Object userId = request.getSession().getAttribute("userId");
		String searchQuery = request.getParameter("query");
		
		try {	
			String query = "SELECT * FROM tasks WHERE userId = ? AND taskName LIKE '%" + searchQuery + "%';";
			
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setObject(1,(int) userId);
			
			try (ResultSet rs = statement.executeQuery()) {
				
				request.setAttribute("resultSet", rs);
				
				RequestDispatcher rd = request.getRequestDispatcher("mainPage.jsp");
				rd.forward(request, response);
				
				connection.close();				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			
		}
		
	}
}
