package com.umas.testcode;
import static org.junit.Assert.*;

import org.junit.Test;

import com.umas.code.*;


public class PeopleTest {

	@Test
	public void testPeopleInt1() {
		
		try {
			People p=new People(1);
			assertNotNull(p);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testPeopleInt2() {
		
		try {
			People p=new People(1000);
			assertEquals(0, p.getUIN(), 0);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testPeopleString1() {
		
		People p=new People("leona");
		assertEquals(581, p.getUIN(), 0);
		
		
	}
	
	@Test
	public void testPeopleString2() {
		
		People p=new People("alibaba");
		assertEquals(0, p.getUIN(), 0);
		
		
	}
	
	@Test
	public void testPeopleString3() {
		
		People p=new People(null);
		assertEquals(0, p.getUIN(), 0);
		
		
	}
	
	@Test
	public void testPeopleString4() {
		
		People p=new People("");
		assertEquals(0, p.getUIN(), 0);
		
		
	}
	

	@Test
	public void testGeneratePassword1() {
		
		String check=People.generatePassword(8);
		assertEquals(8, check.length(), 0);
	}
	
	@Test
	public void testGeneratePassword2() {
		
		String check=People.generatePassword(0);
		assertEquals(0, check.length(), 0);
	}


	@Test
	public void testCheckIfUserNameExists1() {
		
		boolean check=People.checkIfUserNameExists("leona");
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfUserNameExists2() {
		
		boolean check=People.checkIfUserNameExists("alibaba");
		assertFalse(check);
	}
	
	@Test
	public void testCheckIfUserNameExists3() {
		
		boolean check=People.checkIfUserNameExists("");
		assertFalse(check);
	}
	
	
	@Test
	public void testCheckIfUserNameExists4() {
		
		boolean check=People.checkIfUserNameExists(null);
		assertFalse(check);
	}

	@Test
	public void testAddUserDetailsIntoLoginTable() {
		
		boolean check=People.addUserDetailsIntoLoginTable("leona", "aaaaaaa");
		assertFalse(check);
	}

	@Test
	public void testUpdateUserNameIntoLoginTable() {
		
		boolean check=People.updateUserNameIntoLoginTable("leona", "leonard");
		assertTrue(check);
	}

	@Test
	public void testUpdateNameIntoPeopleTable() {
		
		boolean check=People.updateNameIntoPeopleTable("leonard", 581);
		assertTrue(check);
	}

	@Test
	public void testUpdateDeptIntoPeopleTable() {
		
		boolean check=People.updateDeptIntoPeopleTable(26, 581);
		if(check){
			boolean check1=People.updateDeptIntoPeopleTable(1, 581);
			assertTrue(check1);
		}
		
		
	}

	@Test
	public void testAddUserDetailsIntoLoginTableCheck1() {
		
		boolean check=People.addUserDetailsIntoLoginTableCheck("leona");
		assertTrue(check);
	}
	
	@Test
	public void testAddUserDetailsIntoLoginTableCheck2() {
		
		boolean check=People.addUserDetailsIntoLoginTableCheck("alibaba");
		assertFalse(check);
	}
	


	@Test
	public void testAddIntoDatabase() {
		
		//this test cannot be performed as it would cause inconsistency into the database
	}

	@Test
	public void testAddIntoDatabaseCheck() {
		
		try {
			boolean check=People.addIntoDatabaseCheck("leona");
			assertTrue(check);
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddIntoDatabaseCheck2() {
		
		try {
			boolean check=People.addIntoDatabaseCheck("alibaba");
			assertFalse(check);
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddIntoDatabaseCheck3() {
		
		try {
			boolean check=People.addIntoDatabaseCheck("");
			assertFalse(check);
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddIntoDatabaseCheck4() {
		
		try {
			boolean check=People.addIntoDatabaseCheck(null);
			assertFalse(check);
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDeleteFromDatabaseByUIN() {
		
		//this test cannot be performed as it would cause inconsistency into the database
	}

	@Test
	public void testDeleteFromDatabaseByUserName() {
		
		//this test cannot be performed as it would cause inconsistency into the database
	}

	@Test
	public void testDeleteFromDatabaseByUserNameCheck1() {
		
		boolean check=People.deleteFromDatabaseByUserNameCheck("leona");
		assertTrue(check);
	}
	
	@Test
	public void testDeleteFromDatabaseByUserNameCheck2() {
		
		boolean check=People.deleteFromDatabaseByUserNameCheck("alibaba");
		assertFalse(check);
	}
	
	@Test
	public void testDeleteFromDatabaseByUserNameCheck3() {
		
		boolean check=People.deleteFromDatabaseByUserNameCheck("");
		assertFalse(check);
	}
	
	@Test
	public void testDeleteFromDatabaseByUserNameCheck4() {
		
		boolean check=People.deleteFromDatabaseByUserNameCheck(null);
		assertFalse(check);
	}

	@Test
	public void testDeleteFromDatabaseByUINCheck1() {
		
		boolean check=People.deleteFromDatabaseByUINCheck(581);
		assertTrue(check);
	}
	
	@Test
	public void testDeleteFromDatabaseByUINCheck2() {
		
		boolean check=People.deleteFromDatabaseByUINCheck(1000);
		assertFalse(check);
	}


	@Test
	public void testRetireveDetailsByUIN1() {
		
		try {
			People p=People.retireveDetailsByUIN(581);
			assertNotNull(p);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRetireveDetailsByUIN2() {
		
		try {
			People p=People.retireveDetailsByUIN(1000);
			assertNull(p);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRetireveDetailsByuserName() {

		People p=People.retireveDetailsByuserName("leona");
		assertNotNull(p);
	}
	
	@Test
	public void testRetireveDetailsByuserName2() {

		People p=People.retireveDetailsByuserName("alibaba");
		assertNull(p);
	}
	

}
