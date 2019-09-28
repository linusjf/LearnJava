<!DOCtype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><%= currentProduct %></title>
    <style>body{text-align=center; color=blue}
    </style>
  </head>
  <body>
    <%
    String currentProduct = request.getParameter("Product");
    if (currentProduct.equals("Checkout"))
      response.sendRedirect("Checkout.jsp");
    else
      session.setAttribute("currentProd",currentProduct);
    %>
    <h1>
      <P><%= currentProduct %></P>
    </h1>
    <br/><br/><br/>
    <form method="POST" action="WeightX">
      <table>
        <tr>
          <td>Quantity required (kg)
            <input type='Text' name='Qty' value='' size='5'>
          </td>
        </tr>
      </table>
      <br><br><br>
      <table>
        <tr>
          <td>
            <input type='Radio' name='Option' value='Add' checked>Add to cart.
          </td>
        </tr>
        <tr>
          <td>
            <input type='Radio' name='Option'
                   value='Remove'>Remove item from cart.
          </td>
        </tr>
        <tr>
          <td>
            <input type='Radio' name='Option' value='Next'>Choose next item.
          </td>
        </tr>
        <tr>
          <td>
            <input type='Radio' name='Option' value='Checkout'>Go to checkout.
          </td>
        </tr>
      </table>
      <br/><br/><br/>
      <input type='Submit' value='Submit'>
    </form>
  </body>
</html>
