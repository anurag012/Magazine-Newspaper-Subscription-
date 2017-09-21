# SubscriptionDatabase

The main goal of this project is to create a redundant normalized database. Implemented key, entity & referential integrity constraints to achieve robustness. Given the requirements, created EER diagram, mapped EER diagram to relational schema and created tables. Created database was redundant, with minimum null values and in 3NF for faster data retrieval
Implemented a web app to allow user to maintain subscription of magazines and newspapers. 
Project is done in JAVA using JSP, Servlet and Tomcat and using MySQL as database. Database tables are in normalized form.
There are 3 jsp files, 1 java class and 10 java servlets.

## JSP Files

### userInterface.jsp
This is the user interface where user will input and output all the transactions.
Used JavaScript to dynamically change content when user changes the input.

    <select name="mag_issue" onchange="javascript:document.form1.submit();"> 

### completeData.jsp
This page displays the data of whole database and all the tables.

### CustomerSubscriptionDetails.jsp
This page displays the subscription details of a customer.

## Servlets

### addCustomer.java:
This servlets adds the new customer details to the database.

    static java.sql.Connection connection;
	 static Statement pstmt;
	 try{
			pstmt = connection.createStatement();
			String query = "insert into customer(CName, Address) values('"+name+"','"+address+"');";
			pstmt.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}

### addMagazine.java:
This servlet adds information about a new magazine.



### addNewspaper.java:
This servlet adds information about a new newspaper. 

### addIssues.java:
This servlet adds a new number of issues and rate for an existing magazine. 

### getIssue.java:
This servlet changes the rate of an existing magazine with exiting number of issues. 

### getDay.java:
This servlet changes the subscription cost of an existing newspaper with existing number of days. 

### newSubscription.java:
This servlets adds a new subscription between an existing customer and existing publications. 

### subscriptionDetails.java:
This servlet takes customer name as input and passes the result to CustomerSubscriptionDetails.jsp where the subscription details of a customer are displayed. 

### Database.java:
This servlet passes the complete database table details to completeData.jsp where the data of all tables is displayed. 

### loadData.java:
This class loads the initial data to database. 
