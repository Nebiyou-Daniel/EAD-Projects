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

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet{
	private static final String query =  "select id, book_name, book_edition, book_price from book";

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {	
			
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			
			String url = "jdbc:mysql:///books";
			String username = "root";
			String password = "the1trueND!";
		
			Connection connection;
			try {
				connection = DriverManager.getConnection(url, username, password);
				PreparedStatement ps = connection.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				 
				pw.println("<table border = '1'>");
				pw.println("<tr>");
				
				pw.println("<th>Book Id</th>");
				pw.println("<th>Book Name</th>");
				pw.println("<th>Book Edition</th>");
				pw.println("<th>Book Price</th>");
				
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
					pw.println("<td><a href ='deleteurl?id=" + rs.getInt(1) + "'>delete</a></td>");
					
					pw.println("</tr>");
				}
				
				pw.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SQL Error: " + e.getMessage());
			}
			pw.println("<a href='Home.html'>Home</a>");
			
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
