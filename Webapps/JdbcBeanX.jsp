<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.Vector" errorPage="JDBCXError.jsp" %>
<HTML>
 <jsp:useBean id="data" class="jdbc.JdbcBean" />
 <jsp:useBean id="countData" class="jdbc.JdbcBeanX" />
 <HEAD>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
   <TITLE>JDBC Bean Test</TITLE>
   <STYLE>body{text-align:center;}</STYLE>
 </HEAD>
 <BODY>
   <H1>Results</H1>
   <BR/><BR/><BR/>
   <TABLE>
     <TR>
       <TH>Acct.No.</TH>
       <TH>Acct.Name</TH>
       <TH>Balance</TH>
     </TR>
     <% 
     Vector<Object> nums=data.getAcctDetails();
       int acctNum;
       String acctName;
       float balance;
       final int NUM_FIELDS = 3;
       for (int i=0;i<nums.size()/NUM_FIELDS;i++) { 
       acctNum = (Integer)nums.elementAt(i*NUM_FIELDS);
       acctName = (String)nums.elementAt(i*NUM_FIELDS + 1);
       balance = (Float)nums.elementAt(i*NUM_FIELDS + 2);
       %>
       <TR><TD><%= acctNum %></TD>
       <TD><%= acctName %></TD>
       <TD><%= String.format("%.2f",balance %></TD></TR>
       <%
       }
       %>
       </TABLE>
       Number of accounts held:
       <P>
       <jsp:getProperty name="countData" property="numAccounts" />
       </P>
 </BODY>
</HTML>
