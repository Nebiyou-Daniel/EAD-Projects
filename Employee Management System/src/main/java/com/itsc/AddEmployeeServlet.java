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


@WebServlet("/add")
public class AddEmployeeServlet extends HttpServlet {
	
	private static final String query = "insert into employees(name, designation, salary) values(?, ?, ?)";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		String designation = request.getParameter("designation");
		float salary = Float.parseFloat(request.getParameter("salary"));
		
		String url = "jdbc:mysql:///employeedb";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
							
			ps.setString(1, name);
			ps.setString(2, designation);
			ps.setFloat(3, salary);
			
			int count = ps.executeUpdate();
							
			if(count == 1) {
				pw.println("<h2> Employee registered successfully.</h2");
			}
			else {
				pw.println("<h2> Employee Not registered.</h2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
