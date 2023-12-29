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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	
	public static final String query = "insert into users(userName, email, password) values(?, ?, ?)";
	public static final String selectUserQuery = "SELECT * FROM users WHERE email = ?";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String userPassword = request.getParameter("password");
		
		String url = "jdbc:mysql://localhost:3306/taskmaster";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			PreparedStatement ps2 = connection.prepareStatement(selectUserQuery);
										
			ps2.setString(1, email);
				
			// CHECK IF EMAIL HAS BEEN TAKEN ALREADY OR NOT
			
			ResultSet resultSet = ps2.executeQuery();
			
            if (resultSet.next()) {
            	// There is an email account similar with the inserted email
           	                	
				String errorMessage = "This email is TAKEN, please use another one.";
				request.setAttribute("errorMessage", errorMessage);
				
				request.setAttribute("insertedUserName", userName);
				request.setAttribute("insertedEmail", email);
				request.setAttribute("insertedPassword", userPassword);

                // Forward the result to the JSP page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signupPage.jsp");
                dispatcher.forward(request, response);
                          	
            } else {
            	// There is no account with an email similar to the one inserted
            	
				ps.setString(1, userName);
				ps.setString(2, email);
				ps.setString(3, userPassword); 
				
	            int count = ps.executeUpdate();
	    												
				if (count == 1) {
					// The account has been created
					try{
						
						ps2.setString(1, email);
						ResultSet rs2 = ps2.executeQuery();
						
						rs2.next();
						
						HttpSession session = request.getSession();
						session.setAttribute("userName", rs2.getString("userName"));
						session.setAttribute("userId", rs2.getInt("userId"));
						
						connection.close();
						
						RequestDispatcher rd = request.getRequestDispatcher("/main");
						rd.forward(request, response);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// A new account wasn't created
 
					connection.close();
					response.sendRedirect("error.html");
				}
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
