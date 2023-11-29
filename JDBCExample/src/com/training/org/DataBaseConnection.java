package com.training.org;

import java.sql.SQLException;

public class DataBaseConnection {
	public static void main(String[] args) throws SQLException {
		try {
			JDBCOperations jb = new JDBCOperations("root", "1234");
			jb.ShowDatabase();
//			jb.CreateDataBase("NewCodeCreatedDataBase");
//			jb.DeleteDataBase("NewJavaDataBase");
			
//			jb.CreateTable("Create Table Department(DeptId int, Deptname varchar(30))", "newcodecreateddatabase");
//			jb.ShowTablesInDatabase();
//			jb.insertData();
//			jb.PreparedStatementTOinsertinDepartment();
			jb.showRecords("Employee");
			jb.showRecords("Department");
			jb.closeMySqlConnection();
		}
		catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
}
