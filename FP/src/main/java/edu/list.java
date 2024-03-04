package edu;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
        try 
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filerepo","filerepo","filerepo"); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from recipes");  
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>LIST: </th><tr>");  
            while (rs.next()) 
            {  
                String n1 = rs.getString("ing1");
                String n2 = rs.getString("ing2");
                String n3 = rs.getString("ing3");
                String n4 = rs.getString("ing4");
                String n5 = rs.getString("ing5");
                   
                out.println("<tr><td>" + n1 + "</td></tr>");
                out.println("<tr><td>" + n2 + "</td></tr>");
                out.println("<tr><td>" + n3 + "</td></tr>");
                out.println("<tr><td>" + n4 + "</td></tr>");
                out.println("<tr><td>" + n5 + "</td></tr>");
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            con.close();  
           }  
            catch (Exception e) 
           {  
            out.println("error");  
        }  
	         
	}

}
