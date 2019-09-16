<!DOCTYPE html>
<!-- JdbcXError.jsp --> 
<%@ page isErrorPage="true" %> 
<HTML> 
 <HEAD> 
 <TITLE>Error Page</TITLE> 
 <STYLE>body{text-align:center;}</STYLE> 
 </HEAD> 
 <BODY> 
 <BR/><BR/><BR/> 
 <H3>Data Retrieval Error<BR/><BR/> 
 <P STYLE="color:red"> 
 <%= exception.toString() %></P></H3> 
 <BR/><BR/><BR/>
 <FORM METHOD=GET ACTION="JdbcBeanX.jsp"> 
 <INPUT TYPE="Submit" VALUE="Try again"> 
 </FORM> 
 </BODY> 
</HTML>
