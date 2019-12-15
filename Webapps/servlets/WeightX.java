package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebServlet("/WeightX")
public class WeightX extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    // Note the necessity for a typecast from Object
    // into String .
    String choice = request.getParameter("Option");

    /*
           Above parameter determines whether user wishes
           to select another product, add the current order
           to the cart, remove an existing order from
           the cart or proceed to the checkout.
           User is redirected to the appropriate page
           (after any required updating of the shopping
           cart session variable has been carried out).
     */
    if ("Next".equals(choice)) response.sendRedirect("ShoppingCartX.html");
    if ("Checkout".equals(choice)) response.sendRedirect("Checkout.jsp");
    if ("Add".equals(choice)) {
      doAdd(request.getSession(), request);
      response.sendRedirect("ShoppingCartX.html");
    }
    if ("Remove".equals(choice)) {
      // Not really possible for it to be
      // anything else, but play safe!
      doRemove(request.getSession());
      response.sendRedirect("ShoppingCartX.html");
    }
  }

  private void doAdd(HttpSession cart, HttpServletRequest request) {
    String qty = request.getParameter("Qty");

    // Value of weight entered by user retrieved here.
    if (qty != null) {
      String currentProduct = (String) cart.getAttribute("currentProd");

      // Check that user actually entered a value!
      if ("Apples".equals(currentProduct)) cart.setAttribute(
        "Apples",
        qty
      ); else cart.setAttribute("Pears", qty);
    }
  }

  private void doRemove(HttpSession cart) {
    String currentProduct = (String) cart.getAttribute("currentProd");
    Object product = cart.getAttribute(currentProduct);

    // Note that there is no need for a typecast into
    // String , since we only need to know that there
    // is an order for the current product in the cart.
    // Product found in cart.
    if (product != null) cart.removeAttribute(currentProduct);
  }
}
