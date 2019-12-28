package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebServlet("/ShowSum")
public class ShowSum extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("PMD.LawOfDemeter")
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession adderSession = request.getSession();
    String firstTime = (String)adderSession.getAttribute("firstVisit");
    if ("Yes".equals(firstTime))
      retrieveNewPreferences(request, response, adderSession);
    sendPage(response, adderSession);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void sendPage(HttpServletResponse reply, HttpSession session)
      throws IOException {
    /*
          Value of 'sum' originally saved as instance of
          class Integer (and saved as instance of class
          Object in session object), so we cannot typecast
          into class String as done for three values above.
          Instead, we use method toString of class
          Object…
    */
    reply.setContentType("text/HTML");
    PrintWriter out = reply.getWriter();
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Result</TITLE>");
    out.println("</HEAD>");
    String foreColour = (String)session.getAttribute("foreColour");
    String backColour = (String)session.getAttribute("backColour");
    out.println("<BODY TEXT=" + foreColour + " BGCOLOR=" + backColour + ">");
    String sum = session.getAttribute("sum").toString();
    String userName = (String)session.getAttribute("name");
    if (!userName.isEmpty())
      out.println("<H2>" + userName + "'s "
                  + "Result</H2>");
    out.println("<BR/><BR/><BR/><H3>" + sum + "</H3>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void retrieveNewPreferences(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HttpSession session) throws IOException {
    String forename = request.getParameter("Name");
    if (forename == null)
      return;
    final int age = 60;
    if (!forename.isEmpty()) {
      forename =
          URLEncoder.encode(forename, StandardCharsets.UTF_8.displayName());
      Cookie nameCookie = new Cookie("name", forename);
      nameCookie.setMaxAge(age);
      response.addCookie(nameCookie);
      session.setAttribute("name", forename);
    }
    String foreColour = request.getParameter("ForeColour");
    if (foreColour.isEmpty())
      foreColour = "Black";
    foreColour =
        URLEncoder.encode(foreColour, StandardCharsets.UTF_8.displayName());
    Cookie foreColourCookie = new Cookie("foreColour", foreColour);
    foreColourCookie.setMaxAge(age);
    response.addCookie(foreColourCookie);
    session.setAttribute("foreColour", foreColour);
    String backColour = request.getParameter("BackColour");
    if (backColour.isEmpty())
      backColour = "White";
    backColour =
        URLEncoder.encode(backColour, StandardCharsets.UTF_8.displayName());
    Cookie backColourCookie = new Cookie("backColour", backColour);
    backColourCookie.setMaxAge(age);
    response.addCookie(backColourCookie);
    session.setAttribute("backColour", backColour);
    Cookie visitCookie = new Cookie("firstVisit", "No");
    visitCookie.setMaxAge(age);
    response.addCookie(visitCookie);
    session.setAttribute("firstVisit", "No");
  }
}
