<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<HTML>
  <HEAD>
    <TITLE><%= currentProduct %></TITLE>
    <STYLE>body{text-align=center; color=blue}
    </STYLE>
  </HEAD>
  <BODY>
    <%
    String currentProduct;
    currentProduct = request.getParameter("Product");
    if (currentProduct.equals("Checkout"))
      response.sendRedirect("Checkout.jsp");
    else
      session.setAttribute("currentProd",currentProduct);
    %>
    <H1>
      <P><%= currentProduct %></P>
    </H1>
    <BR/><BR/><BR/>
    <FORM METHOD="POST" ACTION="WeightX">
      <TABLE>
        <TR>
          <TD>Quantity required (kg)
            <INPUT TYPE='Text' NAME='Qty' VALUE='' SIZE='5'>
          </TD>
        </TR>
      </TABLE>
      <BR><BR><BR>
      <TABLE>
        <TR>
          <TD>
            <INPUT TYPE='Radio' NAME='Option' VALUE='Add' CHECKED>Add to cart.
          </TD>
        </TR>
        <TR>
          <TD>
            <INPUT TYPE='Radio' NAME='Option'
                   VALUE='Remove'>Remove item from cart.
          </TD>
        </TR>
        <TR>
          <TD>
            <INPUT TYPE='Radio' NAME='Option' VALUE='Next'>Choose next item.
          </TD>
        </TR>
        <TR>
          <TD>
            <INPUT TYPE='Radio' NAME='Option' VALUE='Checkout'>Go to checkout.
          </TD>
        </TR>
      </TABLE>
      <BR/><BR/><BR/>
      <INPUT TYPE='Submit' VALUE='Submit'>
    </FORM>
  </BODY>
</HTML>
