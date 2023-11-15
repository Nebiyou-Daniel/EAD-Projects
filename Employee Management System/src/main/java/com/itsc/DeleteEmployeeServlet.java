package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class DeleteEmployeeServlet extends HttpServlet{
	
	private static final String query = "Delete from employees where id = ?";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
	
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:mysql:///employeedb", "root", "the1trueND!");
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("<h2>Record of this employee is deleted successfully.</h2>");
			}else {
			pw.println("<h2>Record of this employee is not deleted.</h2>");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		
		pw.println("<a href='index.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='employeelist'>Employee List</a>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
