package com.umas.code;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainMaster {
	
	public static void main(String[] args) {
		
		System.out.println("WELCOME TO UMAS");
		System.out.println();
		
		Login user = authenticate();
		if (user!=null){
			System.out.println("Welcome " + user.getUsername());
			
			String username = user.getUsername();
			People person = new People(username);
			int positionID = person.getPositionID();
			if(positionID == 1){ //Admin
				adminFunctions();
			} else if(positionID == 2){ //Professor
				professorFunctions();
			} else if(positionID == 3){ //Student
				studentFunctions();
			} else if(positionID == 4){ //TA
				taFunctions();
			} else if(positionID == 5){ //Super Admin
				superAdminFunctions();
			} else {
				System.out.println("Data Corrupt. No such positionID");
			}
			
		}
	}

	private static Login authenticate() {
		Scanner in = new Scanner(System.in);
		boolean isValidUser = false;
		boolean continueInput = true;
		Login user = null;
		do{
			System.out.println("Please Login");
			System.out.println("Enter Username: ");
			String username = in.nextLine();
			System.out.println("Enter Password: ");
			String password = in.nextLine();
			user = new Login(username, password.toCharArray());
			isValidUser = user.authenticate();
			if(isValidUser == false ){
				System.out.println("Invalid Username or Password. Try Again ? Y/N: ");
				String tryAgain = in.next();
				if(tryAgain == "n" || tryAgain == "N"){
					continueInput = false;
					user = null;
					break;
				}
			} else {
				// Get user object
			}
		} while (isValidUser == false || continueInput == true);
		
		return user;
	}

	private static int adminFunctions(){
		int option = 0;
	
		do{
			option = 0;
			Scanner in = new Scanner(System.in);
			switch (option) {
	        case 1:  System.out.println("");
	        		 
	        		 
	                 break;
	        
	        
	        case 5: System.out.println("5. Exit");
	        default: System.out.println("Invalid input. Enter again.");
	                 break;
	    }

		} while (option != 5);
		
		return option;
	}

	private static int professorFunctions(){
		int option = 0;

		do{
			option = 0;
			Scanner in = new Scanner(System.in);
			switch (option) {
	        case 1:  System.out.println("");
	        		 
	        		 
	                 break;
	        
	        
	        case 5: System.out.println("5. Exit");
	        default: System.out.println("Invalid input. Enter again.");
	                 break;
	    }

		} while (option != 5);
		
		return option;
	}
	
	private static int studentFunctions(){
		int option = 0;
	
		do{
			option = 0;
			Scanner in = new Scanner(System.in);
			switch (option) {
	        case 1:  System.out.println("");
	        		 
	        		 
	                 break;
	        
	        
	        case 5: System.out.println("5. Exit");
	        default: System.out.println("Invalid input. Enter again.");
	                 break;
	    }

		} while (option != 5);
		
		return option;
	}
	
	private static int taFunctions(){
		int option = 0;
		
		do{
			option = 0;
			Scanner in = new Scanner(System.in);
			switch (option) {
	        case 1:  System.out.println("");
	        		 
	        		 
	                 break;
	        
	        
	        case 5: System.out.println("5. Exit");
	        default: System.out.println("Invalid input. Enter again.");
	                 break;
	    }

		} while (option != 5);
		
		return option;
	}
	
	private static int superAdminFunctions(){
		int option = 0;
		
		do{
			option = 0;
			Scanner in = new Scanner(System.in);
			switch (option) {
	        case 1:  System.out.println("");
	        		 
	        		 
	                 break;
	        
	        
	        case 5: System.out.println("5. Exit");
	        default: System.out.println("Invalid input. Enter again.");
	                 break;
	    }

		} while (option != 5);
		
		return option;
	}
	
}
