<!DOCtype html>
<%-- Checkout.jsp --%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Enumeration" %>
<html> 
 <head> 
    <meta name="viewport" content="initial-scale=1.0"/>
 <title>Checkout</title> 
 <style> 
 body{text-align=center; } 
 table,th,td{border:2px solid black;} 
 table{background-color:aqua;} 
 </style> 
 </head> 
 <body> 
 <%
 final float APPLES_PRICE = 1.45F;
 final float PEARS_PRICE = 1.75F;
 %>
 <br/><br/><br/> 
 <h1><p>Order List</p></h1> 
 <br><br><br> 
 <table> 
 <tr> 
 <th>Item</th> 
 <th>Weight(kg)</th> 
 <th>Cost(£)</th> 
 </tr> 
<%-- Now make use of the implicit object session --%> 
<%-- to retrieve the contents of the shopping cart… --%> 
<%
 session.removeAttribute("currentProd");
 Enumeration prodNames = session.getAttributeNames();
 float totalCost = 0;
 int numProducts = 0;
 while (prodNames.hasMoreElements())
 {
 float wt = 0,cost = 0;
 String product = (String)prodNames.nextElement();
 String stringWt =
 (String)session.getAttribute(product);
 wt = Float.parseFloat(stringWt);
 if (product.equals("Apples"))
 cost = APPLES_PRICE * wt;
 else if (product.equals("Pears"))
 cost = PEARS_PRICE * wt;
%>
 <tr> 
 <td> <%= product %></td> 
 <td> <%= wt %></td> 
 <td> <%= String.format("%.2f",cost) %></td> 
 </tr> 
<%
 totalCost += cost;
 numProducts++;
 }
%>
 <tr> 
<%
 if (numProducts == 0)
 {
 %>
 <td>*** No orders placed! ***</td> 
 </tr> 
<%
 }
 else
 {
%>
 <tr> 
 <td></td> 
 <td>Total cost:</td> 
 <td> 
 <%= String.format("%.2f",totalCost) %></td> 
 </tr> 
<%
 }
%>
 </table> 
 </body> 
</html>
