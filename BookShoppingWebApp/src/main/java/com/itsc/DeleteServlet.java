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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "Delete from book where id = ?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
	
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql:///books", "root", "the1trueND!");
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("<h2>Record is deleted successfully.</h2>");
			}else {
			pw.println("<h2>Record not deleted.</h2>");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		pw.println("<a href='Home.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='booklist'>Book List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
