package tomcat;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//Retrieve values of num1 and num2 from the re	quest
		int n1 = Integer.parseInt(req.getParameter("num1"));
		int n2 = Integer.parseInt(req.getParameter("num2"));
 
		System.out.println(n1 + n2);
		
		PrintWriter out = resp.getWriter();
		out.println("Result: " + (n1 + n2));
	}
}