package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/employeelist")
public class VIewEmployeesServlet extends HttpServlet{
	
	private static final String query = "select * from employees";

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		String url = "jdbc:mysql:///employeedb";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		
		try { 
			
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			pw.println("<table border = '2'>");
				pw.println("<tr>");
			
					pw.println("<th>Employee Id</th>");
					pw.println("<th>Employee's Name</th>");
					pw.println("<th>Employee's Designation</th>");
					pw.println("<th>Employee's Salary</th>");
			
					pw.println("<th>Edit</th>");
					pw.println("<th>Delete</th>");

				pw.println("</tr>");
			
			while(rs.next()) {
				pw.println("<tr>");
				
					pw.println("<td>" + rs.getInt(1) + "</td>");
					pw.println("<td>" + rs.getString(2) + "</td>");
					pw.println("<td>" + rs.getString(3) + "</td>");
					pw.println("<td>" + rs.getFloat(4) + "</td>");
					
					pw.println("<td><a href ='editScreen?id=" + rs.getInt(1) + "'>edit</a></td>");
					pw.println("<td><a href ='delete?id=" + rs.getInt(1) + "'>delete</a></td>");
				
				pw.println("</tr>");
			}
			
			pw.println("</table>");
			
			
		} catch (SQLException e){
			e.printStackTrace();
			PrintWriter pw2 = response.getWriter();
			pw2.println("<h1> SQL Error: " + e.getMessage() + "</h1>");
		}
		
		pw.println("<a href='index.html'>Home</a>");


		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
