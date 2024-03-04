package edu;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class add
 */
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String ing1 = request.getParameter("ing1");
		String ing2 = request.getParameter("ing2");
		String ing3 = request.getParameter("ing3");
		String ing4 = request.getParameter("ing4");
		String ing5 = request.getParameter("ing5");
		Connection con = null;
		//RequestDispatcher disp = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filerepo","filerepo","filerepo");
			PreparedStatement p = con.prepareStatement("insert into recipes(name, ing1, ing2, ing3, ing4, ing5) values (?, ?, ?, ?, ?, ?)");
			p.setString(1,  name);
			p.setString(2,  ing1);
			p.setString(3,  ing2);
			p.setString(4,  ing3);
			p.setString(5,  ing4);
			p.setString(6,  ing5);
			
			int row = p.executeUpdate();
			//disp = request.getRequestDispatcher("login.html");
			//if (row > 0) {
			//	request.setAttribute("status", "success");
			//}
			//else {
			//	request.setAttribute("status", "failed");
			//}
			//disp.forward(request, response);
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
