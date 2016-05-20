

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newSubscription
 */
@WebServlet("/addsubscription")
public class newSubscription extends HttpServlet {
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
    public newSubscription() {
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
		String publication = request.getParameter("publication");
		if(request.getParameter("newSubs")!= null){
			
			String cName = request.getParameter("cName");
			int count_pub = Integer.parseInt(request.getParameter("count_publication"));
			int subsCost = Integer.parseInt(request.getParameter("value"));
			String q1 = "select * from publication where Name='"+publication+"'";
			String cus = "select IdNo from customer where CName='"+cName+"'";
			try{
				Statement st4 = connection.createStatement();
				Statement st5 = connection.createStatement();
				ResultSet rs4 = st4.executeQuery(cus);
				int Cid = 0;
				while(rs4.next()){
					Cid=rs4.getInt(1);
				}
				ResultSet rs5 = st5.executeQuery(q1);
				String freq = "";
				String pType = "";
				while(rs5.next()){
					freq = rs5.getString("Frequency");
					pType = rs5.getString("Pub_type");
				}
				if(pType.equalsIgnoreCase("Magazine")){
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String startDate = format.format(cal.getTime());
					cal.setTime(new Date());
					String endDate = "";
					if(freq.equalsIgnoreCase("Weekly")){
						cal.add(Calendar.DATE, 7);
						endDate = format.format(cal.getTime());
					} else if(freq.equalsIgnoreCase("Monthly")){
						cal.add(Calendar.DATE, 30);
						endDate = format.format(cal.getTime());
					} else {
						cal.add(Calendar.DATE, 120);
						endDate = format.format(cal.getTime());
					}
				String query2 = "insert into mag_Subscription values ("+Cid+",'"+publication+"',"+count_pub+",'"+startDate+"','"+endDate+"')";
					Statement st6 = connection.createStatement();
					st6.executeUpdate(query2);					
				} else {
					String query3 = "insert into news_subscription values ("+Cid+",'"+publication+"',"+count_pub+")";
					Statement st7 = connection.createStatement();
					st7.executeUpdate(query3);
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else {
			
		String build = "select Pub_type from publication where Name='"+publication+"'";
		try{
			pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(build);
			String type = "";
			while(rs.next()){
			 type =  rs.getString(1);
			}
			String query;
			ArrayList<Integer> arr = new ArrayList<Integer>();
			/*******************CHECK WHETHER MAGAZINE OR NEWSPAPER****************************************/
			if(type.equalsIgnoreCase("Magazine")){
				query="select * from mag_subs_rate where MName='"+publication+"'";
			} else {
				query="select * from news_subs_rate where NName='"+publication+"'";
			}
			Statement st2 = connection.createStatement();
			ResultSet rs2 = st2.executeQuery(query);
			while(rs2.next()){
				arr.add(rs2.getInt(2));
			}
			String query2="";
			int price=0;
			//String qty="";
			int count=0;
			try{
			if(Integer.parseInt(request.getParameter("count_publication"))==0){
			
			}else {
				count = Integer.parseInt(request.getParameter("count_publication"));
				if(type.equalsIgnoreCase("Magazine")){
				query2="select SubsCost from mag_subs_rate where MName='"+publication+"' and NoOfIssues="+count+";";	
				}else {
					query2="select Cost from news_subs_rate where NName='"+publication+"' and NoOfDays="+count+";";	
				}
				Statement st3 = connection.createStatement();
				ResultSet rs3 = st3.executeQuery(query2);
				while(rs3.next()){
					price = rs3.getInt(1);
					//qty = rs3.getString(2);
				}
				
				
			}
			} catch(NumberFormatException e){
				
			}
			
			request.setAttribute("pubName", publication);
			request.setAttribute("pubCount", arr);
			request.setAttribute("price", Integer.toString(price));
			request.setAttribute("qty", Integer.toString(count));
			request.getRequestDispatcher("userInterface.jsp").forward(request, response);
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	}

}
