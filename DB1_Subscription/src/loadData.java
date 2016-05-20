import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class loadData {

	
	static java.sql.Connection connection;
	static Statement pstmt;
	
	public static void DatabaseConn() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			String url="jdbc:mysql://localhost:3306/subscription";
			String username="root";
			String password="1234";
			//Database connection is established
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console. ");
			e.printStackTrace();
		}
		if (connection == null) {
			System.out.println("Failed to make connection!.. ");
		}
		else
			System.out.println("connection established");		
	}     
	
	public static void main(String[] args) throws InterruptedException {
		try {
			DatabaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "load data local infile 'c:/users/anurag/desktop/Database1/Customers.csv' into table customer fields terminated by ','"+
		" lines terminated by '\r\n' (CName, Address)";
		String query2 = "load data local infile 'c:/users/anurag/desktop/Database1/Publications.csv' into table publication fields terminated by ','"+
				" lines terminated by '\r\n' ";
		String query3 = "load data local infile 'c:/users/anurag/desktop/Database1/Mag_Subs_Rate.csv' into table mag_subs_rate fields terminated by ','"+
				" lines terminated by '\r\n' ";
		String query4 = "load data local infile 'c:/users/anurag/desktop/Database1/News_Subs_Rate.csv' into table news_subs_rate fields terminated by ','"+
				" lines terminated by '\r\n' ";
		String query5 = "load data local infile 'c:/users/anurag/desktop/Database1/MagazineSubscription.csv' into table mag_subscription fields terminated by ','"+
				" lines terminated by '\r\n' ";
		String query6 = "load data local infile 'c:/users/anurag/desktop/Database1/NewspaperSubscription.csv' into table news_subscription fields terminated by ','"+
				" lines terminated by '\r\n' ";
		try {
			pstmt = connection.createStatement();
			Statement st2 = connection.createStatement();
			Statement st3 = connection.createStatement();
			Statement st4 = connection.createStatement();
			Statement st5 = connection.createStatement();
			Statement st6 = connection.createStatement();
			connection.setAutoCommit(false);
			pstmt.executeUpdate(query);
			st2.executeUpdate(query2);
			Thread.sleep(1000);
			st3.executeUpdate(query3);
			st4.executeUpdate(query4);
			Thread.sleep(1000);
			st5.executeUpdate(query5);
			st6.executeUpdate(query6);
			connection.commit();
			
			System.out.println("Data Inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
