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
<caption><b>Customer Details</b></caption>
<tr><th>Customer Id</th><th>Name</th><th>Address</th></tr>
<%
List<CustomerSub> customer = (List<CustomerSub>) request.getAttribute("customer");
for(CustomerSub c: customer){
%>
<tr>
<td><%= c.getId()%></td>
<td><%= c.getCustomer() %></td>
<td><%= c.getAddress() %>
</tr>
<% } %>
</table>

<table border="1">
<caption><b>Publications</b></caption>
<tr><th>Publication Name</th><th>Frequency</th><th>Publication Type</th></tr>
<%
List<CustomerSub> publication = (List<CustomerSub>) request.getAttribute("publication");
for(CustomerSub p: publication){
%>
<tr>
<td><%= p.getPublication()%></td>
<td><%= p.getFrequency()%></td>
<td><%= p.getType()%></td>
</tr>
<% } %>
</table>

<table border="1">
<caption><b>Magazine Publications</b></caption>
<tr><th>Magazine Name</th><th>Number of Issues</th><th>Subscription Cost</th></tr>
<%
List<CustomerSub> magazine = (List<CustomerSub>) request.getAttribute("magazine");
for(CustomerSub m: magazine){
%>
<tr>
<td><%= m.getPublication()%></td>
<td><%= m.getIssue()%></td>
<td><%= m.getCost() %></td>
</tr>
<% } %>
</table>

<table border="1">
<caption><b>Newspaper Publications</b></caption>
<tr><th>Newspaper</th><th>Number of Days</th><th>Cost</th></tr>
<%
List<CustomerSub> news = (List<CustomerSub>) request.getAttribute("news");
for(CustomerSub n: news){
%>
<tr>
<td><%= n.getPublication()%></td>
<td><%= n.getIssue()%></td>
<td><%= n.getCost() %></td>
</tr>
<% } %>
</table>

<table border="1">
<caption><b>Newspaper Subscription</b></caption>
<tr><th>Customer Id</th><th>Newspaper</th><th>Number of Days</th></tr>
<%
List<CustomerSub> news_subs = (List<CustomerSub>) request.getAttribute("news_subs");
for(CustomerSub ns: news_subs){
%>
<tr>
<td><%= ns.getId()%></td>
<td><%= ns.getPublication() %></td>
<td><%= ns.getIssue() %></td>
</tr>
<% } %>
</table>

<table border="1">
<caption><b>Magazine Subscription</b></caption>
<tr><th>Customer Id</th><th>Magazine</th><th>Number of Issue</th><th>Start Date</th><th>End Date</th></tr>
<%
List<CustomerSub> mag_subs = (List<CustomerSub>) request.getAttribute("mag_subs");
for(CustomerSub ms: mag_subs){
%>
<tr>
<td><%= ms.getId()%></td>
<td><%= ms.getPublication() %></td>
<td><%= ms.getIssue() %></td>
<td><%= ms.getStart() %></td>
<td><%= ms.getEnd() %></td>
</tr>
<% } %>
</table>
</body>
</html>