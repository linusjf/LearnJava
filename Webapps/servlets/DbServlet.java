package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;    // Don't forget this!
import java.sql.DriverManager; // Don't forget this!
import java.sql.ResultSet;     // Don't forget this!
import java.sql.SQLException;  // Don't forget this!
import java.sql.Statement;     // Don't forget this!
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/DbServlet")
public class DbServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private Connection link;
  private String url = "jdbc:derby:HomeDB";

  public void init() throws ServletException {
    super.init();
    try {
      link = DriverManager.getConnection(url, "", "");
    } catch (SQLException ex) {
      System.err.println(ex);
      System.exit(1);
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/HTML");
    PrintWriter out = response.getWriter();
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Servlet + JDBC</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    String forenames = request.getParameter("Forenames");
    String surname = request.getParameter("Surname");
    String telNum = request.getParameter("PhoneNum");
    String insertion = "INSERT INTO PhoneNums"
                       + " VALUES('" + surname + "','" + forenames + "','"
                       + telNum + "')";
    try (Statement statement = link.createStatement()) {
      statement.executeUpdate(insertion);
    } catch (SQLException sqlEx) {
      out.println("<BR/><H2>Unable to execute"
                  + " insertion!</H2>");
      out.println("</BODY>");
      out.println("</HTML>");
      out.flush();
      System.exit(1);
    }
    try (Statement statement = link.createStatement();
         ResultSet results =
             statement.executeQuery("SELECT * FROM PhoneNums");) {
      out.println("Updated table:");
      out.println("<BR/><BR/>");
      out.println("<TABLE BORDER>");
      out.println("<TR><TH>Surname</TH>");
      out.println("<TH>Forename(s)</TH>");
      out.println("<TH>Phone No.</TH></TR>");
      while (results.next()) {
        out.println("<TR>");
        out.println("<TD>");
        out.println(results.getString("Surname"));
        out.println("</TD>");
        out.println("<TD>");
        out.println(results.getString("Forenames"));
        out.println("</TD>");
        out.println("<TD>");
        out.println(results.getString("PhoneNum"));
        out.println("</TD>");
        out.println("</TR>");
      }
      out.println("</TABLE>");
    } catch (SQLException sqlEx) {
      out.println("<BR><H2>Unable to retrieve data!</H2>");
      out.println("</BODY>");
      out.println("</HTML>");
      out.flush();
      System.exit(1);
    }
    out.println("<BODY>");
    out.println("</HTML>");
    out.flush();
  }

  public void destroy() {
    try {
      link.close();
    } catch (SQLException ex) {
      System.err.println("Error on closing database!");
      System.err.println(ex);
      System.exit(1);
    }
  }
}
