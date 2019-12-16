package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/DbServlet")
public class DbServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String URL = "jdbc:derby:HomeDB";
  private transient Connection link;

  public void init() throws ServletException {
    super.init();
    try {
      link = DriverManager.getConnection(URL, "", "");
    } catch (SQLException ex) {
      System.err.println(ex);
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/HTML");
    PrintWriter out = response.getWriter();
    printHtmlHeader(out);
    String forenames = request.getParameter("Forenames");
    String surname = request.getParameter("Surname");
    String telNum = request.getParameter("PhoneNum");
    try (PreparedStatement statement =
             link.prepareStatement("INSERT INTO PhoneNums VALUES(?,?,?)")) {
      statement.setString(1, forenames);
      statement.setString(2, surname);
      statement.setString(3, telNum);
      int rowsInserted = statement.executeUpdate();
      System.out.printf("%d rows inserted.%n", rowsInserted);
    } catch (SQLException sqlEx) {
      printHtmlInsertError(out);
      return;
    }
    try (Statement statement = link.createStatement();
         ResultSet results = statement.executeQuery("SELECT * FROM PhoneNums");) {
      printHtmlTableHeader(out);
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
      printHtmlSelectError(out);
      return;
    }
    out.println("<BODY>");
    out.println("</HTML>");
    out.flush();
  }

  private void printHtmlHeader(PrintWriter out) {
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Servlet + JDBC</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
  }

  private void printHtmlTableHeader(PrintWriter out) {
    out.println("Updated table:");
    out.println("<BR/><BR/>");
    out.println("<TABLE BORDER>");
    out.println("<TR><TH>Surname</TH>");
    out.println("<TH>Forename(s)</TH>");
    out.println("<TH>Phone No.</TH></TR>");
  }

  private void printHtmlInsertError(PrintWriter out) {
    out.println("<BR/><H2>Unable to execute"
        + " insertion!</H2>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }

  private void printHtmlSelectError(PrintWriter out) {
    out.println("<BR/><H2>Unable to retrieve data!</H2>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }

  public void destroy() {
    try {
      link.close();
    } catch (SQLException ex) {
      System.err.println("Error on closing database!");
      System.err.println(ex);
    }
  }
}
