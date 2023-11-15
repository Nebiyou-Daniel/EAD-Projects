package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet{
	private static final String query = "update book set book_name=?, book_edition=?, book_price=? where id = ?";


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String bookName = request.getParameter("bookName");
		String bookEdition = request.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));

		
		String url = "jdbc:mysql:///books";
		String username = "root";
		String password = "the1trueND!";
	
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			ps.setInt(4, id);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("<h2>Record is edited successfully.</h2>");
			}else {
				pw.println("<h2>Record not edited.</h2>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		} 
		pw.println("<a href='Home.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='booklist'>Book List</a>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
