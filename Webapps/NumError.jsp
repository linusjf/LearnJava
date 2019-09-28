<!DOCtype html>
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%-- NumError.jsp --%>
<html> 
 <head> 
    <meta name="viewport" content="initial-scale=1.0"/>
 <title>Error Page</title> 
 </head> 
 <body> 
 <br/><br/><br/> 
 <h3>Data Entry Error<br/><br/> 
 <%= exception.toString() %>
 </h3> 
 <br/><br/><br/> 
 <form method="GET" action="SimpleAdderX.html"> 
 <input type="Submit" value="Try again"> 
 </form> 
 </body> 
</html>
