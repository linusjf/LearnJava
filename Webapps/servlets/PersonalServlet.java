package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
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
    String name = request.getParameter("FirstName");
    out.println("<H1> A Simple Servlet for ");
    out.println(name + "</H1></CENTER>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }
}
