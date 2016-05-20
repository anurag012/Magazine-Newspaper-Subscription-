

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
 * Servlet implementation class subscriptionDetails
 */
@WebServlet("/details")
public class subscriptionDetails extends HttpServlet {
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
    public subscriptionDetails() {
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
		String customer = request.getParameter("custInfo");
		String query = "SELECT c.CName as customer_Name, c.Address as Address, SMName as Publication_name,p.Pub_type as Publication_Type,"+
 "p.Frequency as Frequency, SMNoOfIssues as Number_of_Issues_or_Days, m.SubsCost as Subscription_Cost, StartDate, EndDate "+
"from mag_subscription , mag_subs_rate as m, publication as p, customer as c "+
"where SMIdNo=c.IdNo and SMName = p.Name and SMNoOfIssues=m.NoOfIssues and SMName=m.MName and c.CName='"+customer+"'"+
" union "+
"SELECT c.CName, c.Address, SNName,p.Pub_type, p.Frequency, SNNoOfDays, n.Cost, null, null "+
"from news_subscription , news_subs_rate as n, publication as p, customer as c "+
"where SNIdNo=c.IdNo and SNName = p.Name and SNNoOfDays=n.NoOfDays and SNName=n.NName and c.CName='"+customer+"';";
		System.out.println(query);
		try{
			pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);
			List<CustomerSub> list = new ArrayList<CustomerSub>();
			while(rs.next()){
				CustomerSub cs = new CustomerSub();
				cs.setCustomer(rs.getString(1));
				cs.setAddress(rs.getString(2));
				cs.setPublication(rs.getString(3));
				cs.setType(rs.getString(4));
				cs.setFrequency(rs.getString(5));
				cs.setIssue(rs.getInt(6));
				cs.setCost(rs.getInt(7));
				cs.setStart(rs.getString(8));
				cs.setEnd(rs.getString(9));
				list.add(cs);
				//System.out.println(rs.getString(1)+""+rs.getString(2)+""+rs.getString(3)+""+rs.getString(4)+""+rs.getString(5));
			}
			request.setAttribute("subsInfo", list);
			request.getRequestDispatcher("CustomerSubscriptionDetails.jsp").forward(request, response);
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

}
