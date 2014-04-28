
//import java.lang.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	String username;
	String password;
	
//	@Target({ElementType.LOCAL_VARIABLE})
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface DBAnnotation {
//	 String[] variable () default "";
//	 String[] table () default "";
//	 String[] column () default "";
//	 boolean[] isSource () default false; 
//	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	// Constructor
	public Login(String username, char[] password) {
		this.username = username;
		this.password = new String(password);
	}

	// To authenticate the username and password during login
	public boolean authenticate(){
		String username = this.getUsername();
		String password =  this.getPassword();
		boolean isValidUser = false; 
//		@DBAnnotation (
//				variable = {"username","password"},  
//				table = "logindetails", 
//				column = {"Username","Password"}, 
//				isSource = true)
		String SQLLoginSelect = "SELECT Username, Password FROM logindetails WHERE username = ? AND Password = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					// Check if file is already present. 
					PreparedStatement statement = conn.prepareStatement(SQLLoginSelect);
					DBAnnotation.annoate("username", "logindetails", "Username", false);
					statement.setString(1, username);
					DBAnnotation.annoate("password", "logindetails", "Password", false);
					statement.setString(2, password);
					ResultSet rs = statement.executeQuery();
					if (rs.next()) {
						// Retrieve by column name
						DBAnnotation.annoate("tableUsername", "logindetails", "Username", true);
						String tableUsername = rs.getString("Username");
						DBAnnotation.annoate("tablePassword", "logindetails", "Password", true);
						String tablePassword = rs.getString("Password");
						
						if((tableUsername.equals(username)) && (tablePassword.equals(password))){ 
								isValidUser = true;
							//break;
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isValidUser;
	}
	
	// Adds username and password into the database table when a person is added to the people table
	public boolean addLoginInfoToDB(String username, String password){
		boolean loginInfoAdded = false;
		boolean isUsernamePresent = checkUsernameInDatabase(username);
		
		if (isUsernamePresent == false){
//			@DBAnnotation (
//				variable = {"username","password"},  
//				table = "logindetails", 
//				column = {"Username","Password"}, 
//				isSource = false)
		
			String SQLLoginInsert = "INSERT INTO logindetails (Username,Password) VALUES(?,?);";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
			
						PreparedStatement statement = conn.prepareStatement(SQLLoginInsert);
						DBAnnotation.annoate("username", "logindetails", "Username", false);
						statement.setString(1, username);
						DBAnnotation.annoate("password", "logindetails", "Password", false);
						statement.setString(2, password);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						loginInfoAdded = true;
					}
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);;
				}

			} catch (Exception e) {
				System.out.println(e);
			}			
		}
		
		return loginInfoAdded;
	}
	
	// Checks whether the username is already present in the logindetails table before 
	// addLoginInfoToDB() adds it in the logindetails table
	private static boolean checkUsernameInDatabase(String username) {
		boolean isUsernamePresent = false;
		
//		@DBAnnotation (
//				variable = "username",  
//				table = "logindetails", 
//				column = "Username", 
//				isSource = true)
		String SQLLoginSelect = "SELECT Username FROM logindetails WHERE username = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					// Check if file is already present. 
					PreparedStatement statement = conn.prepareStatement(SQLLoginSelect);
					DBAnnotation.annoate("username", "logindetails", "Username", false);
					statement.setString(1, username);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						// Retrieve by column name
						DBAnnotation.annoate("tableUsername", "logindetails", "Username", true);
						String tableUsername = rs.getString("Username");
						if(tableUsername.equals(username)){
							isUsernamePresent = true;
							break;
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isUsernamePresent;
	}

	// To change the password for a user who is logged in
	public static boolean changePassword(String username, String newPassword){
		
		boolean passwordChanged = false;
	//	String username = this.getUsername();
		
//		@DBAnnotation (
//			variable = {"username","newPassword"},  
//			table = "logindetails", 
//			column = {"Username","Password"}, 
//			isSource = false)
		
		String SQLLoginUpdate = "UPDATE logindetails SET Password = ? WHERE Username = ?;";
			
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
		
					PreparedStatement statement = conn.prepareStatement(SQLLoginUpdate);
					DBAnnotation.annoate("newPassword", "logindetails", "Password", false);
					statement.setString(1, newPassword);
					DBAnnotation.annoate("username", "logindetails", "Username", false);
					statement.setString(2, username);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					passwordChanged = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);;
			}
			} catch (Exception e) {
			System.out.println(e);
		}
		return passwordChanged;
	}

	// To recover a user's password
	public static boolean recoverPassword (String username){
		boolean passwordrecovered = false;
		boolean isUserPresent = checkUsernameInDatabase(username);
		if (isUserPresent == false){
			System.out.println("Username not present");
		}
		else {
//			@DBAnnotation (
//					variable = {"username","password"},  
//					table = "logindetails",
//					column = {"Username", "Password"}, 
//					isSource = true)
			
			String SQLFileSelect = "SELECT Password FROM logindetails WHERE Username = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						
						// Check if file is already present. 
						PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
						DBAnnotation.annoate("username", "logindetails", "Username", false);
						statement.setString(1, username);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
							// Retrieve by column name
							DBAnnotation.annoate("password", "logindetails", "Password", false);
							String password = rs.getString("Password");
							//this.setPassword(password);
							Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
							String subject = "UMAS Password";
							String body = "Your password is " + password + 
											"Please change your password after you login";
							
							boolean mailSent = email.sendEmail(username+"@gmail.com", subject, body);
							if (mailSent == true){
								System.out.println("Mail containing password sent to the user.");
								passwordrecovered = true;
							}
						}
					}	
				} catch (SQLException e) {
					System.out.println(e);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		
		return passwordrecovered;
		
	}
	
 	public static void main(String[] args){
		
 		// Test authenticate function when user is present
// 		Login user = new Login("aky1","pra");
// 		String username = user.getUsername();
//		boolean isPresent = user.authenticate();
//		if(isPresent == true){
//			System.out.println("User Present");
//		} else {
//			System.out.println("User Absent");
//		}
// 		
//		// Test authenticate function when user is absent
//		user = new Login("aky1","pra");
//		isPresent = user.authenticate();
//		if(isPresent == true){
//			System.out.println("User Present");
//		} else {
//			System.out.println("User Absent");
//		}
//		
		
		// Test checkUsernameInDatabase function when user is present
// 		String username = "aky";
// 		boolean isPresent = Login.checkUsernameInDatabase(username);
//		if(isPresent == true){
//			System.out.println("User Present");
//		} else {
//			System.out.println("User Absent");
//		}
// 		
//		// Test checkUsernameInDatabase function when user is absent
//		username = "aky1";
//		isPresent = Login.checkUsernameInDatabase(username);
//		if(isPresent == true){
//			System.out.println("User Present");
//		} else {
//			System.out.println("User Absent");
//		}
		
	}

}
