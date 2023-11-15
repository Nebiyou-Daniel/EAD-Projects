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


@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	private static final String query = "insert into book(book_name, book_edition, book_price) values(?, ?, ?)";

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {	
			
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
						
			//get the book info
			String bookName = request.getParameter("bookName");
			String bookEdition = request.getParameter("bookEdition");
			float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));

					
			String url = "jdbc:mysql://localhost:3306/books";
			String username = "root";
			String password = "the1trueND!";
		
			Connection connection;
			try {
				connection = DriverManager.getConnection(url, username, password);
				PreparedStatement ps = connection.prepareStatement(query);
								
				ps.setString(1, bookName);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookPrice);
				
				int count = ps.executeUpdate();
								
				if(count == 1) {
					pw.println("<h2> Book registered successfully.</h2");
				}
				else {
					pw.println("<h2> Book Not registered.</h2");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.println("<a href='Home.html'>Home</a>");
			pw.print("<br>");
			pw.println("<a href='booklist'>Book List</a>");
			
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
