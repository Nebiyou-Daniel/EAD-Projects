package tomcat;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class HelloWorld extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		PrintWriter pw = res.getWriter();
		
//		pw.println("Hello Wolrd!");
		
		String userName = req.getParameter("username");
//		pw.println("Hello, " + userName);
		
		int age = Integer.parseInt(req.getParameter("age"));
//		pw.printf("%s is %d years old", userName, age);
		pw.println(userName + " is " + Integer.toString(age) + " years old.");
	}
}

