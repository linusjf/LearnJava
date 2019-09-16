<!DOCTYPE html>
<HTML> 
 <%@ page language="java" contentType="text/html" 
 import="java.util.*" errorPage="JDBCError.jsp" %> 
 <jsp:useBean id="data" class="jdbc.JdbcBean" /> 
 <HEAD> 
 <TITLE>JDBC Bean Test</TITLE> 
 <STYLE>body{text-align:center;}</STYLE>
</HEAD> 
 <BODY> 
 <H1>Results</H1> 
 <BR/><BR/><BR/> 
 <TABLE STYLE="background-color:aqua" BORDER=1> 
 <TR> 
 <TH STYLE="background-color:orange"> 
 Acct.No.</TH> 
 <TH STYLE="background-color:orange"> 
 Acct.Name</TH> 
 <TH STYLE="background-color:orange"> 
 Balance</TH> 
 </TR> 
 <% 
 Vector<Object> nums=data.getAcctDetails(); 
 int acctNum; 
 String acctName; 
 float balance; 
 final int NUM_FIELDS = 3; 
 for (int i=0;i<nums.size()/NUM_FIELDS;i++) 
 { 
 //Auto-unboxing doesn't work here! 
 acctNum = (Integer)nums.elementAt( 
 i*NUM_FIELDS); 
 acctName = (String)nums.elementAt( 
 i*NUM_FIELDS + 1); 
 balance = (Float)nums.elementAt( 
 i*NUM_FIELDS + 2); 
 %> 
 <TR> 
 <TD><%= acctNum %></TD> 
 <TD><%= acctName %></TD> 
 <TD> 
 <%= String.format("%.2f",balance %></TD> 
 </TR> 
 <% 
 } 
 %> 
 </TABLE> 
 </BODY> 
</HTML>
