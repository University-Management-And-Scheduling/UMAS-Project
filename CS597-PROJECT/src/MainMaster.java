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
		
		System.out.println("Welcome " + user.getUsername());
		
		
//		do{
//			int option = 0;
//			Scanner in = new Scanner(System.in);
//			switch (option) {
//	        case 1:  System.out.println("");
//	        		 
//	        		 
//	                 break;
//	        
//	        
//	        default: System.out.println("Invalid input. Enter again.");
//	                 break;
//	    }
//
//		} while (user != null);
		
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
			user = new Login(username, password);
			isValidUser = user.authenticate();
			if(isValidUser == false ){
				System.out.println("Invalid Username or Password. Try Again ? Y/N: ");
				String tryAgain = in.next();
				if(tryAgain == "n" || tryAgain == "N"){
					continueInput = false;
					user = null;
					break;
				}
			}
		} while (isValidUser == false || continueInput == true);
		
		return user;
	}


}
