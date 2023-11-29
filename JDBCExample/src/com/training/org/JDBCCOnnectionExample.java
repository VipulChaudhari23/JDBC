package com.training.org;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCCOnnectionExample {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "1234";
		// Connection is the inbuilt function.
		Connection con = null;
		String query = "SELECT version()";
		Statement statement = null;
        ResultSet resultSet = null;
        
		try {
			con = DriverManager.getConnection(url, username, password);
			statement = con.createStatement();
            resultSet = statement.executeQuery(query);
			
			// This is the Connection Object
			System.out.println("This is the Connection Object: "+con);
			if (con != null) {
				System.out.println("Connection is successfull...");
			} 
			// COndition to Print the Version
			if (resultSet.next()) {
				// Assuming the query returns a single value.
                String version = resultSet.getString(1); 
                System.out.println("MySQL Version is: " + version);
            } else {
                System.out.println("Query didn't return any result.");
            } 
		} 
		
		catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		} 
		
		finally {
			// Closing Conditions.
			if (con != null) {
				con.close();
				System.out.println("Clossing Conniction...");
			} 
			else {
				System.out.println("Cant able to close the connection.....");
			}
		}

	}

}
