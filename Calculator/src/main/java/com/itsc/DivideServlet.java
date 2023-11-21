package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/divide")
public class DivideServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		float number1 = Float.parseFloat(request.getParameter("num1"));
		float number2 = Float.parseFloat(request.getParameter("num2"));

		try {
			float finalNumber = number1 / number2;
			
			pw.println("<h1> The result of the division of " + Float.toString(number1) + " by " + 
					Float.toString(number2) + " is: " + Float.toString(finalNumber));
			
			System.out.println(finalNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h2>You can't divide a number by zero!</h2>");
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}

