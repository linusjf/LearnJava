package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GetPreferences")
public class GetPreferences extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/HTML");
    HttpSession adderSession = request.getSession();
    adderSession.setAttribute("firstVisit", "Yes");
    PrintWriter out = response.getWriter();
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Preferences</TITLE>");
    out.println("</STYLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<BR/><BR/><BR/>");
    out.println("<FORM METHOD=POST ACTION='ShowSum'>");
    out.println("User Preferences");
    out.println("<BR/>");
    out.println("<TABLE>");
    out.println("<TR>");
    out.println("<TD>First name</TD>");
    out.println("<TD><INPUT TYPE='Text' "
                + "NAME='Name' VALUE='' SIZE=15></TD>");
    out.println("</TR>");
    out.println("<TR>");
    out.println("<TD>Foreground colour</TD>");
    out.println("<TD><INPUT TYPE='Text' "
                + "NAME='ForeColour' VALUE=''"
                + "SIZE=10></TD>");
    out.println("</TR>");
    out.println("<TR>");
    out.println("<TD>Background colour</TD>");
    out.println("<TD><INPUT TYPE='Text' "
                + "NAME='BackColour' VALUE=''"
                + "SIZE=10></TD>");
    out.println("</TR>");
    out.println("</TABLE>");
    out.println("<BR/><BR/>");
    out.println("<INPUT TYPE='Submit' "
                + "VALUE = 'Submit'>");
    out.println("<INPUT TYPE='Reset' "
                + "VALUE='Clear'>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }
}
