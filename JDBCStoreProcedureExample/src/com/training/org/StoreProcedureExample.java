package com.training.org;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class StoreProcedureExample {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
//			SQL Connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newcodecreateddatabase", "root", "1234");
//			 to get the Input from the jdbc we need to give the Define parameter. 
			CallableStatement cs = con.prepareCall("{call updateEmpSalary(?,?)}");
			cs.setInt(1,101);
			cs.setInt(2,1);
			cs.executeUpdate();
			System.out.println("Salary Updated Successfully.....");	
						
			// to get the output from the jdbc we need to give the TYPE as an parameter. 
			CallableStatement cs1 = con.prepareCall("{call countEmp(?)}");
			cs1.registerOutParameter(1, Types.INTEGER);
			cs1.execute();
			System.out.println("Number of Employees are: " + cs1.getInt(1));
			
			// CallableStatement to increment the counter.
			CallableStatement cs2 = con.prepareCall("{call incCounter(?)}");
			cs2.registerOutParameter(1, Types.INTEGER);
			cs2.setInt(1,3);
			cs2.execute();
			System.out.println("Counter: " + cs2.getInt(1));
			
//			// new salary update 
			CallableStatement cs4 = con.prepareCall("{call salaryMap(?,?,?,?)}");
			cs4.setInt(1,104);
			cs4.setInt(2,5);
			cs4.registerOutParameter(3, Types.INTEGER);
			cs4.registerOutParameter(4, Types.INTEGER);
			cs4.execute();
			System.out.println("Increamneted Salary is: " + cs4.getInt(3));
			System.out.println("Old Salary is: " + cs4.getInt(4));
//			
			// is prime method the is prime function is called in the workbench
			CallableStatement cs5=con.prepareCall("{?=call isPrime(?)}");
            cs5.registerOutParameter(1, Types.CHAR);
            cs5.setInt(2,6);
            cs5.execute();
            System.out.println("Result : "+cs5.getString(1));
			
		}
		catch(Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
		// connection close
		finally {
			con.close();
			System.out.println("Connection close successfully....");
		}
	}
}
