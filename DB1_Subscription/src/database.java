

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subscribe.CustomerSub;

/**
 * Servlet implementation class database
 */
@WebServlet("/database")
public class database extends HttpServlet {
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
    public database() {
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
		String customer = "select * from customer";
		String publication = "select * from publication";
		String magazine = "select * from mag_subs_rate";
		String news = "select * from news_subs_rate";
		String mag_subs = "select * from mag_subscription";
		String news_subs = "select * from news_subscription";
		try {
			pstmt = connection.createStatement();
			Statement st2 = connection.createStatement();
			Statement st3 = connection.createStatement();
			Statement st4 = connection.createStatement();
			Statement st5 = connection.createStatement();
			Statement st6 = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(customer);
			ResultSet rs2 = st2.executeQuery(publication);
			ResultSet rs3 = st3.executeQuery(magazine);
			ResultSet rs4 = st4.executeQuery(news);
			ResultSet rs5 = st5.executeQuery(mag_subs);
			ResultSet rs6 = st6.executeQuery(news_subs);
			
			List<CustomerSub> list = new ArrayList<CustomerSub>();
			List<CustomerSub> list2 = new ArrayList<CustomerSub>();
			List<CustomerSub> list3 = new ArrayList<CustomerSub>();
			List<CustomerSub> list4 = new ArrayList<CustomerSub>();
			List<CustomerSub> list5 = new ArrayList<CustomerSub>();
			List<CustomerSub> list6 = new ArrayList<CustomerSub>();
			while(rs.next()){
				CustomerSub cs = new CustomerSub();
				cs.setId(rs.getInt(1));
				cs.setCustomer(rs.getString(2));
				cs.setAddress(rs.getString(3));
				list.add(cs);
			}
			request.setAttribute("customer", list);
			
			while(rs2.next()){
				CustomerSub cs2 = new CustomerSub();
				cs2.setPublication(rs2.getString(1));
				cs2.setFrequency(rs2.getString(2));
				cs2.setType(rs2.getString(3));
				list2.add(cs2);
			}
			request.setAttribute("publication", list2);
			
			while(rs3.next()){
				CustomerSub cs3 = new CustomerSub();
				cs3.setPublication(rs3.getString(1));
				cs3.setIssue(rs3.getInt(2));
				cs3.setCost(rs3.getInt(3));
				list3.add(cs3);				
			}
			request.setAttribute("magazine", list3);
			
			while(rs4.next()){
				CustomerSub cs4 = new CustomerSub();
				cs4.setPublication(rs4.getString(1));
				cs4.setIssue(rs4.getInt(2));
				cs4.setCost(rs4.getInt(3));
				list4.add(cs4);
			}
			request.setAttribute("news", list4);
			
			while(rs5.next()){
				CustomerSub cs5 = new CustomerSub();
				cs5.setId(rs5.getInt(1));
				cs5.setPublication(rs5.getString(2));
				cs5.setIssue(rs5.getInt(3));
				cs5.setStart(rs5.getString(4));
				cs5.setEnd(rs5.getString(5));
				list5.add(cs5);
			}
			request.setAttribute("mag_subs", list5);
			
			while(rs6.next()){
				CustomerSub cs6 = new CustomerSub();
				cs6.setId(rs6.getInt(1));
				cs6.setPublication(rs6.getString(2));
				cs6.setIssue(rs6.getInt(3));
				list6.add(cs6);
			}
			request.setAttribute("news_subs", list6);
			
			request.getRequestDispatcher("completeData.jsp").forward(request, response);
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
