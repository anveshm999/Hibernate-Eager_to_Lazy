package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pwd = "hbstudent";
		
		try{
			
			System.out.println("Connecting to database: " +jdbcURL);
			Connection myConnection = DriverManager.getConnection(jdbcURL, user, pwd);
			System.out.println("Connection successfull !! ");
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
