package it.formarete;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class HelloWorldServlet
 */
//@WebServlet(name = "hello-world", description = "Servlet that responds with 'Hello world!'", urlPatterns = {
//"/hello-world" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello World";
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		String name = request.getParameter("name");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>");
		out.append("<html>");
		out.append("  <head>");
		out.append("    <title>" + message + "</title>");
		out.append("  </head>");
		out.append("  <body>");
		out.append("    <h1>Hello " + name + "!</h1>");
		out.append("  </body>");
		out.append("</html>");
	}

	public void destroy() {
		// do nothing.
	}

}
