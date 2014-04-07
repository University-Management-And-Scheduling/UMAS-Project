

public class Login {
	String username;
	String password;
	
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

	public boolean authenticate(){
		String username = this.getUsername();
		String password =  this.getPassword();
		boolean isValidUser = false; 
		
		
		// DB code to authenticate user
		
		return isValidUser;
	}
	
	public static void main(String[] args){
		
	}

}
