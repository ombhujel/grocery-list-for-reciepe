package edu;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest_Demo {

	public DBTest_Demo (){
		
	}

	
	public static void main(String[] args)
	{
			DBTest_Demo DBConnect_instance = new DBTest_Demo();
	}

	public int testconnection_mysql(String fileName, InputStream iS) {
		// TODO Auto-generated method stub
		String connection_host = "localhost:3306";
		Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        int flag = 0;
    	
		try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
	    		          //.getConnection("jdbc:mysql://149.4.223.xxx/studentdb?"+ "user=whatever&password=whatever");
        				    .getConnection("jdbc:mysql://" + connection_host + "/filerepo?"+ "user=okb&password=itshol");
		      
	    	  String qry1a = "INSERT INTO contentrepo(filename, content) values (?, ?)";

	    	  //comment out test message //System.out.println(qry1a);
	    	  preparedStatement = connect.prepareStatement(qry1a);
	    	  // "id, uid, create_time, token for id_management.id_logtime";
	    	  // Parameters start with 1
	    	  preparedStatement.setString(1, fileName);
	    	  //preparedStatement.setString(2, file);
	           if (iS != null) {
	                // fetches input stream of the upload file for the blob column
	                preparedStatement.setBlob(2, iS);
	            }

	            // sends the statement to the database server
	    	  preparedStatement.executeUpdate();
	           
	           
	    	} catch (Exception e) {
	    		try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  	} finally {
			      if (preparedStatement != null) {
				        try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }	      

			      if (connect != null) {
			        try {
						connect.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      }
		    }
		return flag;
		
	}

	
}
