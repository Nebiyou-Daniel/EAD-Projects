package tomcat;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/circle")
public class CircleServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, 
		HttpServletResponse res) throws IOException{
		
		PrintWriter pw = res.getWriter();
		int radius = Integer.parseInt(req.getParameter("radius"));
		String unit = req.getParameter("unit");
		
		//calculate Area
		int area = (int) (Math.PI * Math.pow(radius, 2));
		res.setContentType("text/html");
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<h1>Circle Area Calculator</h1>");
		pw.printf("<p>Radius: %d %s</p>\n", radius, unit);
		pw.printf("<p>Area: %d squared %s</p>\n", area, unit);
		pw.println("</body>");
		pw.println("</html>");
	}
	
	protected void goPost(HttpServletRequest req, 
		HttpServletResponse res) throws IOException{
		
//		doGet(req, res);
		PrintWriter pw = res.getWriter();
		int radius = Integer.parseInt(req.getParameter("radius"));
		String unit = req.getParameter("unit");
		
		//calculate Area
		int area = (int) (Math.PI * Math.pow(radius, 2));
		res.setContentType("text/html");
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<h1>Circle Area Calculator</h1>");
		pw.printf("<p>Radius: %d %s</p>\n", radius, unit);
		pw.printf("<p>Area: %d squared %s</p>\n", area, unit);
		pw.println("</body>");
		pw.println("</html>");
	}
}
