

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getDay
 */
@WebServlet("/day")
public class getDay extends HttpServlet {
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
    public getDay() {
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
		String news = request.getParameter("news_issue");
		if(request.getParameter("upnewCost")!= null){
			int count = Integer.parseInt(request.getParameter("count_day"));
			int newCost = Integer.parseInt(request.getParameter("newsCost"));
			String query1 = "update news_subs_rate set Cost="+newCost+" where NName='"+news+"' and NoOfDays="+count+";";
			try{
				Statement st = connection.createStatement();
				connection.setAutoCommit(false);
				st.executeUpdate(query1);
				connection.commit();
				System.out.println("Updated and rolled back");
			} catch(SQLException e){
				e.printStackTrace();
			}
		} else{
		
		String query = "select * from news_subs_rate where NName='"+news+"'";
		ArrayList<Integer> arr = new ArrayList<Integer>();
		try {
			pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			while(rs.next()){
				arr.add(rs.getInt("NoOfDays"));
				
			}
			request.setAttribute("news_name", news);
			request.setAttribute("days", arr);
			request.getRequestDispatcher("userInterface.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
