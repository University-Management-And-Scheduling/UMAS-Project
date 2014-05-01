package com.umas.testcode;
import com.umas.code.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTestCase {

	@Test
	public void testauthenticate() {
		// char[] password = ;
		
		Login user = new Login("autum","IOoSlwBV".toCharArray());
		boolean isPresent = user.authenticate();
		assertEquals(true,isPresent);
	}
	
	@Test
	public void testauthenticateFail() {
		// char[] password = ;
		
		Login user = new Login("autum2","IOoSlwBV".toCharArray());
		boolean isPresent = user.authenticate();
		assertEquals(false,isPresent);
	}
	
	// To test this function add new username
	
	@Test
	public void addLoginInfoToDB() {
		// char[] password = ;
		
		Login user = new Login("USERNAME","Password".toCharArray());
		boolean isPresent = user.addLoginInfoToDB("USERNAME", "Password");
		assertEquals(true,isPresent);
	}
	
	@Test
	public void changePassword() {
		// char[] password = ;
		
		boolean isChanged = Login.changePassword("Prasad","Nair");
		assertEquals(true,isChanged);
	}
	
	// To test this function recover the password for a 
	// valid username. It sends the recovered password as 
	// an email. The username is the email id.
	@Test
	public void recoverPassword() {
		// char[] password = ;
		boolean isRecovered = Login.recoverPassword("USERNAME");
		assertEquals(true,isRecovered);
	}
	
}
