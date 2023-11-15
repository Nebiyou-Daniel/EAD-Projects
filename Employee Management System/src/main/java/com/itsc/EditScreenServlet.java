package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet{
	
	private static final String query = "select name, designation, salary from employees where id = ?";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:mysql:///employeedb";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			pw.println("<form action = 'editEmployee?id="+ id +"' method = 'post'>");
				pw.println("<table>");
					pw.println("<tr>");
						pw.println("<td>Employee's Name</td>");
						pw.println("<td><input type = 'text', name = 'name', value = '" + rs.getString(1)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td>Employee's Designation</td>");
						pw.println("<td><input type = 'text', name = 'designation', value = '" + rs.getString(2)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td>Employee's Salary</td>");
						pw.println("<td><input type = 'text', name = 'salary', value = '" + rs.getFloat(3)+"'</td>");
					pw.println("</tr>");
					pw.println("<tr>");
						pw.println("<td><input type = 'submit' value = 'Edit'></td>");
						pw.println("<td><input type = 'reset' value = 'Cancel'></td>");
					pw.println("</tr>");
				pw.println("</table>");
			pw.println("</form>");
			
		} catch (SQLException e) {
			e.printStackTrace();
			pw.println("<h1> SQL Error: " + e.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		
		pw.println("<a href='index.html'>Home</a>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
