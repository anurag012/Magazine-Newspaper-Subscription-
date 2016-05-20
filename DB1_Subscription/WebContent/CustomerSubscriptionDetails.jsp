<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<%@ page import="subscribe.CustomerSub" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr><th>Customer</th><th>Address</th><th>Publication</th><th>Type</th><th>Frequency</th><th>Issues/Days</th><th>Cost</th><th>Start Date</th><th>End Date</th></tr>
<%
List <CustomerSub> results = new ArrayList<CustomerSub>();
  results = (List<CustomerSub>) request.getAttribute("subsInfo");
  
  for(CustomerSub obj : results){ %>
  <tr>
  <td><%=obj.getCustomer() %></td>
  <td><%= obj.getAddress()%></td>
  <td><%= obj.getPublication()%></td>
  <td><%= obj.getType()%></td>
  <td><%= obj.getFrequency()%></td>
  <td><%= obj.getIssue()%></td>
  <td><%= obj.getCost()%></td>
  <td><%= obj.getStart()%></td>
  <td><%= obj.getEnd()%></td>
  </tr>
  
  <%  
  }
%>
</table>
</body>
</html>