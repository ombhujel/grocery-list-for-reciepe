package edu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Connection con = null;
		RequestDispatcher disp = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filerepo","filerepo","filerepo");
			PreparedStatement p = con.prepareStatement("select * from user where email = ? and password = ?");
			p.setString(1, email);
			p.setString(2, password);
			
			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				disp = request.getRequestDispatcher("main.html");
			}
			else {
				disp= request.getRequestDispatcher("main.html");
			}
			disp.forward(request, response);
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
