<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <h1>DB1 Project 2 Part 3</h1>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB1 Project 2</title>
</head>
<body>
===================================================================================================================================================
<h2>2</h2>
<form action="database" method="post">
<input type="submit" name="getData" value="Get All Data">
</form>
----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.1</h2>
<form action="customer" method="post">
Name: <input type="text" name="cName" value=""> &nbsp;&nbsp;
Address: <input type="text" name="cAddress" value=""> <br><br>
<input type="submit" name="submit" value="submit">
</form>

----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.2</h2>
<form action="magazine" method="post">
Name: <input type="text" name="mName" value="">&nbsp;&nbsp;
Frequency: <input type="text" name="mFreq" value="">&nbsp;&nbsp;
No. of Issues: <input type="text" name="mIssues" value="">&nbsp;&nbsp;
Subscription Cost: <input type="text" name="mCost" value=""> <br><br>
<input type="submit" name="submit" value="submit">
</form>
-----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.3</h2>
<form action="newspaper" method="post">
Name: <input type="text" name="nName" value="">&nbsp;&nbsp;
Frequency: <input type="text" name="nFreq" value="">&nbsp;&nbsp;
No. of Days: <input type="text" name="nDays" value="">&nbsp;&nbsp;
Subscription Cost: <input type="text" name="nCost" value=""> <br><br>
<input type="submit" name="submit" value="submit">
</form>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/subscription",
        "root", "1234");

Statement st2 = con.createStatement();
Statement st1 = con.createStatement();
Statement st3 = con.createStatement();
Statement st4 = con.createStatement();
Statement st5 = con.createStatement();
Statement st6 = con.createStatement();
ResultSet rs2;
ResultSet rs1;
ResultSet rs3;
ResultSet rs4;
ResultSet rs5;
ResultSet rs6;
rs2 = st2.executeQuery("select * from publication where Pub_type='Magazine'");
rs1 = st1.executeQuery("select * from publication where Pub_type='Magazine'");
rs3 = st3.executeQuery("select * from publication where Pub_type='Newspaper'");
rs4 = st4.executeQuery("select * from publication");
rs5 = st5.executeQuery("select * from customer");
rs6 = st6.executeQuery("select * from customer");
%>

----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.4</h2>
<form action="addissue" method="post">
Magazine:
<select name="mags"> 
	<option selected="" value=""> </option>
<%
while(rs2.next()){
%>
	<option value="<%= rs2.getString("Name")%>"><%= rs2.getString("Name")%></option>
<% } %>
</select> &nbsp;&nbsp;
No. of Issues: <input type="text" name="mIssues" value=""> &nbsp;&nbsp;
Subscription Cost: <input type="text" name="mCost" value=""> <br><br>
<input type="submit" name="submit" value="submit">
</form>
----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.5</h2>
<%
String pubName = (String)request.getAttribute("pub_name");
ArrayList<Integer> list = (ArrayList<Integer>) request.getAttribute("issues");

%>

<form name="form1" action="issue" method="post">
Magazine:
<select name="mag_issue" onchange="javascript:document.form1.submit();"> 
	<option selected="" value="<%= pubName%>"><%= pubName %> </option>
<%
while(rs1.next()){
%>
	<option value="<%= rs1.getString("Name")%>"><%= rs1.getString("Name")%></option>
<% } %>
</select> &nbsp;&nbsp;
No. of Issues:
<select name="count_issue"> 
<%
try{
if(!list.isEmpty()){
	for(int a: list){	%>
	<option value="<%= a%>"><%= a %></option>		
<% 	}
}
} catch(Exception e){
	e.printStackTrace();
}
%>
</select> &nbsp;&nbsp;
 New Cost: <input type="text" name="newCost" value=""> <br><br>
<input type="submit" name="upCost" value="submit">
</form>
----------------------------------------------------------------------------------------------------------------------------------------------------
<h2> 3.6</h2>
<%
String pubName1 = (String)request.getAttribute("news_name");
ArrayList<Integer> list1 = (ArrayList<Integer>) request.getAttribute("days");

%>

<form name="form2" action="day" method="post">
Newspaper:
<select name="news_issue" onchange="javascript:document.form2.submit();"> 
	<option selected="" value="<%= pubName1%>"><%= pubName1 %> </option>
<%
while(rs3.next()){
%>
	<option value="<%= rs3.getString("Name")%>"><%= rs3.getString("Name")%></option>
<% } %>
</select> &nbsp;&nbsp;
No. of days:
<select name="count_day"> 
<%
try{
if(!list1.isEmpty()){
	for(int a1: list1){	%>
	<option value="<%= a1%>"><%= a1 %></option>		
<% 	}
}
} catch(Exception e){
	e.printStackTrace();
}
%>
</select> &nbsp;&nbsp;
New Cost: <input type="text" name="newsCost" value=""> <br><br>
<input type="submit" name="upnewCost" value="submit">
</form>
----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.7</h2>

<%
String publication = (String) request.getAttribute("pubName");
ArrayList<Integer> count = (ArrayList<Integer>) request.getAttribute("pubCount");
int qty = 0;

int rate=0;
try{
rate = Integer.parseInt((String)request.getAttribute("price"));
qty = Integer.parseInt((String)request.getAttribute("qty"));
}catch(Exception e){
	e.printStackTrace();
}
%>

<form name = "form3" action="addsubscription" method = "post">
Customer:
<select name="cName"> 
<%
while(rs6.next()){
%>
	<option value="<%= rs6.getString("CName")%>"><%= rs6.getString("CName")%></option>
<% } %>
</select> &nbsp;&nbsp;

Publication:
<select name = "publication" onchange="javascript: document.form3.submit();"> 
<option selected="" value="<%= publication%>"><%= publication%></option>
<%
while(rs4.next()){ %>
<option value="<%= rs4.getString("Name")%>"><%= rs4.getString("Name")%></option>
<% 	} %>
</select> &nbsp;&nbsp;
Issues/Days:
<select name="count_publication" onchange="javascript: document.form3.submit();"> 
<option selected="" value="<%= qty%>"><%= qty%></option>
<% try{
if(!count.isEmpty()){
	for(int a2: count){	%>
	<option value="<%= a2%>"><%= a2 %></option>		
<% 	}
}
} catch(Exception e){
	e.printStackTrace();
}
%>
</select> &nbsp;&nbsp;
Price: <label for="<%= rate%>" id="value"><%= rate%></label> <br><br>
<input type="hidden" value="<%= rate%>" name="value">
<input type="submit" name="newSubs" value="submit">
</form>


-----------------------------------------------------------------------------------------------------------------------------------------------------
<h2>3.8</h2>
<form action="details" method="post">
Customer Subscription Info:
<select name="custInfo"> 
<%
while(rs5.next()){
%>
	<option value="<%= rs5.getString("CName")%>"><%= rs5.getString("CName")%></option>
<% } %>
</select> <br><br>
<input type="submit" value="submit" name="custDetails">
</form>
============================================================================================================================================
</body>
</html>