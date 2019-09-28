<!DOCtype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<html>
<jsp:useBean id="purchase" class="shopping.OrderBean"> 
 <jsp:setProperty name="purchase" property="*" /> 
</jsp:useBean> 
 <head> 
    <meta name="viewport" content="initial-scale=1.0"/>
 <title>Order Bean Test</title> 
 <style> 
 body {text-align:center;} 
 </style> 
 </head> 
 <body> 
 <h1>Results</h1> 
 <br/> 
 <table> 
 <tr> 
 <th> 
 Field Name</th> 
 <th> 
 Value</th> 
 </tr> 
 <tr> 
 <td>name</td> 
 <td><jsp:getProperty name="purchase" 
 property="name" /></td> 
 </tr> 
 <tr> 
 <td>addressLine1</td> 
 <td><jsp:getProperty name="purchase" property="addressLine1" /></td> 
 </tr> 
 <tr> 
 <td>addressLine2</td> 
 <td><jsp:getProperty name="purchase" 
 property="addressLine2" /></td> 
 </tr> 
 <tr> 
 <td>addressLine3</td> 
 <td><jsp:getProperty name="purchase" 
 property="addressLine3" /></td> 
 </tr> 
 <tr> 
 <td>postCode</td> 
 <td><jsp:getProperty name="purchase" 
 property="postCode" /></td> 
 </tr> 
 <tr> 
 <td>orderItem</td> 
 <td><jsp:getProperty name="purchase" 
 property="orderItem" /></td> 
 </tr> 
 <tr> 
 <td>quantity</td> 
 <td><jsp:getProperty name="purchase" 
 property="quantity" /></td> 
 </tr> 
 </table> 
 <br/><br/> 
 <form method="GET" action="Acceptance.html"> 
 <%-- 
 When confirm button pressed, 
 display Acceptance.html . 
 --%> 
 <input type="submit" value="Confirm"> 
 </form> 
 </body> 
</html> 
