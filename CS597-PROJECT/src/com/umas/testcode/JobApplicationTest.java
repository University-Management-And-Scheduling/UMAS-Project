package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;







public class JobApplicationTest {

	@Test
	public void testJobApplication1() {

		try {
			JobApplication jobApp=new JobApplication(600);
			assertEquals(15, jobApp.getApplicationID());
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJobApplication2() {

		try {
			JobApplication jobApp=new JobApplication(451);
			assertEquals(0, jobApp.getApplicationID());
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJobApplication3() {

		try {
			JobApplication jobApp=new JobApplication(451);
			
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddApplicationDetails() {

	//this functionality cannot be tested as this would cause the test to fail when it is run more than once
		
	}
	@Test
	public void testAddApplicationDetails1() {

		boolean check;
		try {
			check = JobApplication.addApplicationDetails(519, 2, true, true, true, true, true);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddApplicationDetails2() {

		boolean check;
		try {
			check = JobApplication.addApplicationDetails(272, 2, true, true, true, true, true);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testAddApplicationDetailsCheck1() {
		
		boolean check=JobApplication.addApplicationDetailsCheck(600);
		assertTrue(check);
	}
	
	@Test
	public void testAddApplicationDetailsCheck2() {
		
		boolean check=JobApplication.addApplicationDetailsCheck(451);
		assertFalse(check);
	}

	@Test
	public void testCalculateScaledScore() {

		double check=JobApplication.calculateScaledScore(600, 2, true, false, false, false, false);
		assertEquals(5.5, check, 0);
	}
	

	@Test
	public void testRetreiveMatchingStudents() {
		
		LinkedHashMap<Integer , Student> retreiveCheck=JobApplication.retreiveMatchingStudents(2.5, 2, false, true, true, true, false);
		assertNotNull(retreiveCheck);
	}
	
	@Test
	public void testRetreiveMatchingStudents2() {
		
		LinkedHashMap<Integer , Student> retreiveCheck=JobApplication.retreiveMatchingStudents(4, 5, true, true, true, true, true);
		assertEquals(0, retreiveCheck.size(), 0);
	}

	@Test
	public void testRePost() {
		//this function cannot be tested here as it would create inconsistency in the database
	}
	
	@Test
	public void testRePost2() {
		
		Job job=new Job(15);

		LinkedHashMap<Integer , Student> retreiveCheck=JobApplication.rePost(2, 2, true, false, false, false, false, job);
		assertNotNull(retreiveCheck);
	}

	@Test
	public void testRePost3() {
		
		Job job=new Job(1);

		LinkedHashMap<Integer , Student> retreiveCheck=JobApplication.rePost(4, 5, false, true, true, true, false, job);
		assertEquals(0, retreiveCheck.size(), 0);
	}

	@Test
	public void testUpdateApplication() {
		
		try {
			boolean check=JobApplication.updateApplication(600, 4, true, true, true, true, true);
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateApplication2() {
		
		try {
			boolean check=JobApplication.updateApplication(451, 4, true, true, true, true, true);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateApplicationCheck() {
		
		boolean check=JobApplication.updateApplicationCheck(600);
		assertTrue(check);
	}
	
	@Test
	public void testUpdateApplicationCheck2() {
		
		boolean check=JobApplication.updateApplicationCheck(451);
		assertFalse(check);
	}

}

