package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebServlet("/Weight")
public class Weight extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    HttpSession cart = request.getSession();
    String currentProduct = (String)cart.getAttribute("currentProd");
    // Current product ('Apples' or 'Pears') retrieved.
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
    if (choice.equals("Next"))
      response.sendRedirect("ShoppingCart.html");
    if (choice.equals("Checkout"))
      response.sendRedirect("Checkout");
    if (choice.equals("Add")) {
      doAdd(cart, request);
      response.sendRedirect("ShoppingCart.html");
    }
    if (choice.equals("Remove"))
    // Not really possible for it to be
    // anything else, but play safe!
    {
      doRemove(cart);
      response.sendRedirect("ShoppingCart.html");
    }
  }

  private void doAdd(HttpSession cart, HttpServletRequest request) {
    String currentProduct = (String)cart.getAttribute("currentProd");
    String qty = request.getParameter("Qty");
    // Value of weight entered by user retrieved here.
    if (qty != null)
    // Check that user actually entered a value!
    {
      if (currentProduct.equals("Apples"))
        cart.setAttribute("Apples", qty);
      else
        cart.setAttribute("Pears", qty);
    }
  }

  private void doRemove(HttpSession cart) {
    String currentProduct = (String)cart.getAttribute("currentProd");
    Object product = cart.getAttribute(currentProduct);
    // Note that there is no need for a typecast into
    // String , since we only need to know that there
    // is an order for the current product in the cart.
    if (product != null)
      // Product found in cart.
      cart.removeAttribute(currentProduct);
  }
}
