package servlets;

import static servlets.Prices.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebServlet("/Checkout")
public class Checkout extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @SuppressWarnings({"PMD.AvoidDuplicateLiterals", "checkstyle:javancss"})
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/HTML");
    // print page and table header
    PrintWriter out = response.getWriter();
    printHtmlHeader(out);
    HttpSession cart = request.getSession();
    cart.removeAttribute("currentProd");
    Enumeration<String> prodNames = cart.getAttributeNames();
    float totalCost = 0;
    int numProducts = 0;
    // print product lines
    while (prodNames.hasMoreElements()) {
      float wt = 0;
      float cost = 0;
      String product = prodNames.nextElement();
      String stringWt = (String)cart.getAttribute(product);
      wt = Float.parseFloat(stringWt);
      if ("Apples".equals(product))
        cost = APPLES_PRICE * wt;
      else if ("Pears".equals(product))
        cost = PEARS_PRICE * wt;
      out.println("<TR>");
      out.println("<TD>" + product + "</TD>");
      out.format("<TD> %4.2f </TD>%n", wt);
      out.format("<TD> %5.2f </TD>%n", cost);
      out.println("</TR>");
      totalCost += cost;
      numProducts++;
    }
    if (numProducts == 0) {
      out.println("<TR>");
      out.println("<TD>*** No orders placed! ***</TD></TR>");
    } else {
      out.println("<TR>");
      out.println("<TD></TD>");  // Blank cell.
      out.println("<TD>Total cost:</TD>");
      out.format("<TD> %5.2f </TD>%n", totalCost);
      out.println("</TR>");
    }
    out.println("</TABLE>");
    out.println("</BODY>");
    out.println("</HTML>");
    out.flush();
  }

  private void printHtmlHeader(PrintWriter out) {
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Checkout</TITLE>");
    out.println("</STYLE>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<BR/><BR/><BR/>");
    out.println("<H1>Order List</H1>");
    out.println("<BR/><BR/><BR/>");
    out.println("<TABLE>");
    out.println("<TR>");
    out.println("<TH>Item</TH>");
    out.println("<TH>Weight(kg)</TH>");
    out.println("<TH>Cost(£)</TH>");
    out.println("</TR>");
  }
}
