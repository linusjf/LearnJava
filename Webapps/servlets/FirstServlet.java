package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/HTML");
    PrintWriter out = response.getWriter();
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Simple Servlet</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<BR><BR><BR>");
    out.println("<CENTER><H1>A Simple Servlet</H1></CENTER>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }
}
