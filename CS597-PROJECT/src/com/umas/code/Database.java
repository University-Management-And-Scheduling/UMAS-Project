package com.umas.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Connection conn;
		
	private static String url;
	private static String dbName;
	private static String driver;
	private static String userName;
	private static String password;
	
	static{
		conn = null;
		url = "jdbc:mysql://localhost:3306/";
		dbName = "university";
		driver = "com.mysql.jdbc.Driver";
		userName = "root";
		password = "engineering";
	}
	
//Constructor added	
//	public Database(){
//		conn = null;
//		url = "jdbc:mysql://localhost:3306/";
//		dbName = "university";
//		driver = "com.mysql.jdbc.Driver";
//		userName = "root";
//		password = "engineering";
//	}
	
	// Call this function as " Connection conn = Database.getConnection(); " 
	// to connect to the database
//	public synchronized Connection getConnection(){
	public static Connection getConnection(){
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		
		try{
			if(conn == null){
				conn = DriverManager.getConnection(url + dbName, userName, password);
				conn.setAutoCommit(false);
	            System.out.println("Connected to the database");
			}
		} catch (SQLException e){
			System.out.println(e);
		}
			
		return conn;
	}
	
	// Call this function to commit when transaction is successful
	public static void commitTransaction(Connection conn){
		try {
			if (conn != null) {
		    	conn.commit();
		        System.out.println("Transaction Committed!");
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	// Call this function to rollback when transaction is unsuccessful
	public static void rollBackTransaction(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
	            System.out.println("Transaction Rolledback!");
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
		
	
	//Call this function to close the Database Connection
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Closed the connection to database!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }	
	
}
