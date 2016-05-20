

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addNewspaper
 */
@WebServlet("/newspaper")
public class addNewspaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewspaper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DatabaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String name = request.getParameter("nName");
		String freq = request.getParameter("nFreq");
		int day= Integer.parseInt(request.getParameter("nDays"));
		int cost = Integer.parseInt(request.getParameter("nCost"));
		
		try{
			pstmt = connection.createStatement();
			connection.setAutoCommit(false);	
			String query="insert into publication values ('"+name+"', '"+freq+"', 'Newspaper');";
			pstmt.executeUpdate(query);
			String query2= "insert into news_subs_rate values('"+name+"', "+day+", "+cost+");";
			pstmt.executeUpdate(query2);
			connection.commit();
			System.out.println("Update happened and rolled back");
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
