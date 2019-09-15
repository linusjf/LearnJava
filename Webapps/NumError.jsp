<!DOCTYPE html>
<!-- NumError.jsp -->
<%@ page isErrorPage="true" %>
<HTML> 
 <HEAD> 
 <TITLE>Error Page</TITLE> 
 </HEAD> 
 <BODY> 
 <BR/><BR/><BR/> 
 <H3>Data Entry Error<BR/><BR/> 
 <%= exception.toString() %>
 </H3> 
 <BR/><BR/><BR/> 
 <FORM METHOD=GET ACTION="SimpleAdderX.html"> 
 <INPUT TYPE="Submit" VALUE="Try again"> 
 </FORM> 
 </BODY> 
</HTML>

