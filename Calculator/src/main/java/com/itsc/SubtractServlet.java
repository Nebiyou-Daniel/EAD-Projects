package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/subtract")
public class SubtractServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		float number1 = Float.parseFloat(request.getParameter("num1"));
		float number2 = Float.parseFloat(request.getParameter("num2"));
		
		float finalNumber = number1 - number2;
		
		pw.println("<h1> The result of the subtraction of " + Float.toString(number2) + " from " + 
				Float.toString(number1) + " is: " + Float.toString(finalNumber));
		
		System.out.println(finalNumber);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
