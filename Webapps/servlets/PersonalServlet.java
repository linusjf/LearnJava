package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String UTF_8 = 
    StandardCharsets.UTF_8.displayName();

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/HTML");
    PrintWriter out = response.getWriter();
    printResponse(out,request);
  }

  private void printResponse(PrintWriter out,
      HttpServletRequest request) {
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Simple Servlet</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<BR/><BR/><BR/>");
    String name = request.getParameter("FirstName");
    out.println("<H1>A Simple Servlet for ");
    if (name == null)
      out.println("'No name provided'");
    else
      out.println(URLEncoder.encode(name, UTF_8));
    out.println("</H1>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }
}
