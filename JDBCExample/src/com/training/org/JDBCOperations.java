package com.training.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBCOperations {
	private Connection conn;
	private String username;
	private String password;
	private String query;

	public JDBCOperations() {
		System.out.println("Default constructor of JDBCOperations is called");
		conn = null;
		username = "";
		password = "";
		query = "";
	}

	public static Connection mysqlConnection(String username, String password) throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NewCodeCreatedDataBase", username, password);
		return conn;

	}

	public JDBCOperations(String username, String password) throws SQLException {
		System.out.println("Parameterised constructor of JDBCOperations is called");
		this.username = username;
		this.password = password;
		this.conn = JDBCOperations.mysqlConnection(username, password);
		System.out.println("This is the Connection Object: " + this.conn);
		query = "";
	}

	// Method to show Database
	public void ShowDatabase() throws SQLException {
		ResultSet resultSet = null;
		Statement stmt = conn.createStatement();
		String sql = "SHOW DATABASEs";
		resultSet = stmt.executeQuery(sql);

		while (resultSet.next()) {
			String databaseName = resultSet.getString(1);
			System.out.println("Database Names: " + databaseName);
		}
	}

	// Method to create new Database.
	public void CreateDataBase(String name) throws SQLException {
		Statement stmt = conn.createStatement();
		// send SQL Command to create database
		String sql = "CREATE DATABASE " + name;
		stmt.executeUpdate(sql);
		System.out.println("Database " + name + " created successfully.");
	}

	// Method to Delete Database.
	public void DeleteDataBase(String name) throws SQLException {
		Statement stmt = conn.createStatement();
		// send SQL Command to delete database
		String sql = "DROP DATABASE " + name;
		stmt.executeUpdate(sql);
		System.out.println("Database " + name + " deleted successfully.");
	}

	// Method to CreateTable in the database
	public void CreateTable(String query, String name) throws SQLException {
		Statement stmt = conn.createStatement();
		// Send Sql Command to create the table
		String sql = "USE DATABASE " + name;
		System.out.println("DataBase use" + name + "Successfully");
		System.out.println(stmt.executeUpdate(query));
		System.out.println("Table is created...");

	}

	// Method to show Tables in the database
		public void ShowTablesInDatabase() throws SQLException {
			ResultSet resultSet = null;
			Statement stmt = conn.createStatement();
			String sql = "SHOW TABLES";
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				String tableName = resultSet.getString(1);
				System.out.println("Table Name: " + tableName);
			}
		}

	// Method to insert data in the table
	public void insertData() throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("insert into Employee values(101, 'Vipul', 10000)");
		stmt.execute("insert into Employee values(102, 'Saiem', 20000)");
		stmt.execute("insert into Employee values(103, 'Yash', 30000)");
		stmt.execute("insert into Employee values(104, 'Vinay', 40000)");
		System.out.println("Records inserted");
	}

	// Method to Insert Data in the Department table using PreparedStatement.
	public void PreparedStatementTOinsertinDepartment() throws SQLException {
		PreparedStatement pst = conn.prepareStatement("insert into Department values(?,?)");

		Scanner input = new Scanner(System.in);
		// taking values from keyboard
		System.out.println("Enter Department ID:");
		int id = input.nextInt();

		System.out.println("Enter Department Name:");
		String name = input.next();

		// The values are stored in local variables, id, name and salary
		pst.setInt(1, id);
		pst.setString(2, name);

		pst.executeUpdate();

		System.out.println(name + " Record is inserted");
	}

	public void showRecords(String name) throws SQLException {
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from "+name);
//		while(rs.next()) {
//			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
//		}

		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		
		// Get column names
		for (int i = 1; i <= columns; i++) {
			columnNames.add(md.getColumnName(i));
		}
		System.out.println(columnNames);
		// Get row data
		while (rs.next()) {
			ArrayList row = new ArrayList(columns);
			for (int i = 1; i <= columns; i++) {
				row.add(rs.getObject(i));
			}
			System.out.println(row);
//           data.add(row);
		}
		System.out.println("---------------------------------");
	}

	public void closeMySqlConnection() throws SQLException {
		if (this.conn != null) {
			this.conn.close();
			System.out.println("Connection is Closed");
		} else {
			System.out.println("Con't able to close the Connection");
		}
	}

}
