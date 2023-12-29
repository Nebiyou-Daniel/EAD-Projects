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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	public static final String query = "select * from users where email = ? AND password = ?";
	public static final String emailQuery = "select * from users where email = ?";
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String userPassword = request.getParameter("password");
		
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(emailQuery);
							
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				// There is an account with this email and password			

				String storedPassword = rs.getString("password");

				if (userPassword.equals(storedPassword)) {
					//authentication successful
					
					HttpSession session = request.getSession();
					session.setAttribute("userName", rs.getString(2));
					session.setAttribute("userId", rs.getInt(1));
					connection.close();
					
					RequestDispatcher rd = request.getRequestDispatcher("/main");
					rd.forward(request, response);
					
				}else {
					// Password is not correct 
					
					connection.close();
					String errorMessage = "Your password is INCORRECT, please try again.";
					request.setAttribute("errorMessage", errorMessage);
					
					request.setAttribute("insertedEmail", email);
					request.setAttribute("insertedPassword", userPassword);
					
		            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
				}
					
			} else {
				// no account found with the given email
					
				connection.close();
	            String errorMessage = "There is NO such email, please try again.";
	            request.setAttribute("errorMessage", errorMessage);	
				
				request.setAttribute("insertedEmail", email);
				request.setAttribute("insertedPassword", userPassword);
	                        
	            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			System.out.println("There was an SQL error when trying to authneticate");
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println("There was a Servlet error when trying to authneticate");
			e.printStackTrace();			
		}
	}

}
