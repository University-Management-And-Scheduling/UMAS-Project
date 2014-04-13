import java.lang.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Login {
	String username;
	String password;
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
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

	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// To authenticate the username and password during login
	public boolean authenticate(){
		String username = this.getUsername();
		String password =  this.getPassword();
		boolean isValidUser = false; 
		@DBAnnotation (
				variable = {"username","password"},  
				table = "logindetails", 
				column = {"Username","Password"}, 
				isSource = true)
		String SQLLoginSelect = "SELECT Username, Password FROM logindetails WHERE username = ? AND Password = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					// Check if file is already present. 
					PreparedStatement statement = conn.prepareStatement(SQLLoginSelect);
					statement.setString(1, username);
					statement.setString(2, password);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						// Retrieve by column name
						String tableUsername = rs.getString("Username");
						String tablePassword = rs.getString("Password");
						
						if((tableUsername == username) && (tablePassword == password)){
							isValidUser = true;
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
		
		return isValidUser;
	}
	
	// Adds username and password into the database table when a person is added to the people table
	public boolean addLoginInfoToDB(String username, String password){
		boolean loginInfoAdded = false;
		boolean isUsernamePresent = checkUsernameInDatabase(username);
		
		if (isUsernamePresent == false){
			@DBAnnotation (
				variable = {"username","password"},  
				table = "logindetails", 
				column = {"Username","Password"}, 
				isSource = false)
		
			String SQLLoginInsert = "INSERT INTO logindetails (Username,Password) VALUES(?,?);";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
			
						PreparedStatement statement = conn.prepareStatement(SQLLoginInsert);
						statement.setString(1, username);
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
	private boolean checkUsernameInDatabase(String username) {
		boolean isUsernamePresent = false;
		
		@DBAnnotation (
				variable = "username",  
				table = "logindetails", 
				column = "Username", 
				isSource = true)
		String SQLLoginSelect = "SELECT Username FROM logindetails WHERE username = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					// Check if file is already present. 
					PreparedStatement statement = conn.prepareStatement(SQLLoginSelect);
					statement.setString(1, username);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						// Retrieve by column name
						String tableUsername = rs.getString("Username");
						if(tableUsername == username){
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
	public boolean changePassword(Login user, String newPassword){
		
		boolean passwordChanged = false;
		String username = user.getUsername();
		
		@DBAnnotation (
			variable = {"username","newPassword"},  
			table = "logindetails", 
			column = {"Username","Password"}, 
			isSource = false)
		
		String SQLLoginUpdate = "UPDATE logindetails SET Password = ? WHERE Username = ?;";
			
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
		
					PreparedStatement statement = conn.prepareStatement(SQLLoginUpdate);
					statement.setString(1, newPassword);
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


	public static void main(String[] args){
		
	}

}
