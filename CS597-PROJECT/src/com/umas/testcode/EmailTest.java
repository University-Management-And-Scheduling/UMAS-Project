package com.umas.testcode;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

/************************@author Simant Purohit*************************/

public class EmailTest {

	public static Email email;
	@Before
	public void setUp() throws Exception {
		email = Email.getInstance("umas.uic@gmail.com", "cs597project");
	}

	@Test
	public final void testGetInstance() {
		assertNotNull(Email.getInstance("umas.uic@gmail.com", "cs597project"));
	}

	@Test
	public final void testSendEmail() {
		boolean check = email.sendEmail("xyzdkajasd@umas.edu", "Test Mail from JUnit", "Test success");
		assertTrue(check);
	}
	
	@Test
	public final void testSendEmail2() {
		/*
		 * Sending a email to a id that is not formatted email address (abc@xyz.com/edu)
		 */
		try{
			email.sendEmail("xyzdkajasd", "Test Mail from JUnit", "Test success");
		}
		catch(Exception e){
		}
	}
}
