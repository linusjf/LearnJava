<!DOCtype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.Vector" errorPage="JDBCError.jsp" %>
<html>
<jsp:useBean id="data" class="jdbc.JdbcBean" />
 <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
   <title>JDBC Bean Test</title>
   <style>body{text-align:center;}</style>
 </head>
<body>
   <h1>Results</h1>
 <br/><br/><br/>
 <table>
   <tr>
     <th>Acct.No.</th>
     <th>Acct.Name</th>
     <th>Balance</th>
 </tr>
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
 %<tr>
   <td><%= acctNum %></td>
   <td><%= acctName %></td>
 <td>
   <%= String.format("%.2f",balance %></td>
 </tr>
 <%
 }
 %>
 %</table>
 </body>
</html>
