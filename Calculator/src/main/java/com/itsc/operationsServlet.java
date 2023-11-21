package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class operationsServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		response.setContentType("text/html");
		
		// WORKS :0 BUT INEFFICIENT
//		
//		String page = request.getParameter("operator");
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/" + page);		
//		dispatcher.forward(request, response);
		
		
		
		// TRYING THREE DIFFERENT DISPATCHERS ::: FAILED
		
//		RequestDispatcher dispatcher1 = request.getRequestDispatcher("/add");		
//		dispatcher1.forward(request, response);
//		
//		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/multiply");		
//		dispatcher2.forward(request, response);
//

		
		
		// TRYING IF-ELSE FOR DISPATCHERS ::: WORKS (BUT ONLY THIS WAY)
		
		String page = request.getParameter("operator");
		
		if (page.equals("plus")) {
			RequestDispatcher rd = request.getRequestDispatcher("/add");
			rd.include(request, response);
			
		} else if (page.equals("minus")) {
				RequestDispatcher rd = request.getRequestDispatcher("/subtract");
				rd.forward(request, response);
				
		} else if (page.equals("times")) {
			RequestDispatcher rd = request.getRequestDispatcher("/multiply");
			rd.forward(request, response);
			
		} else if (page.equals("over")) {
			RequestDispatcher rd = request.getRequestDispatcher("/divide");
			rd.forward(request, response);
			
		} else if (page.equals("remainder")) {
			RequestDispatcher rd = request.getRequestDispatcher("/remainder");
			rd.forward(request, response);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}
}
