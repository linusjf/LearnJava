<!DOCTYPE html>
<%-- Adder.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="NumError.jsp" %>
<%
 String value1 = request.getParameter("Num1");
 String value2 = request.getParameter("Num2");
 int num1 = Integer.parseInt(value1);
 int num2 = Integer.parseInt(value2);
 int sum = num1 + num2;
%>
<HTML> 
 <HEAD> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
 <TITLE>Result</TITLE> 
 </HEAD> 
 <BODY> 
 <BR/><BR/><BR/> 
 <H1> 
 <%= "Result = " + sum %>
 </H1> 
 </BODY> 
</HTML>
