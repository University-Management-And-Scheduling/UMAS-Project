package com.umas.testcode;
import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import org.junit.Test;
import com.umas.code.*;

public class JobTest {

	@Test
	public void testJob() {

		Job job = new Job(15);
			assertNotNull(job);

		
	}
	
	@Test
	public void testJob1() {

		Job job= new Job(1);
			assertEquals(0, job.getPostedByUIN());

		
	}
	
	@Test
	public void testPostJob1() {

		try {
			int check=Job.postJob(451, 1, 2.0, 2.0, true, true, false, false, true);
			assertEquals(-1, check);
		} catch (Job.NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Test
	public void testUpdateJob1() {
		
		
		try {
			
			boolean check=Job.updateJob(597, 1, 4.0, 3, false, true, true, true, false);
			assertTrue(check);
		} catch (Job.NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateJob2() {
		
		try {
			boolean check=Job.updateJob(451, 1, 4.0, 3, false, true, true, true, false);
			assertFalse(check);
		} catch (Job.NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testCheckEligibility1() {

		boolean check=Job.checkEligibility(597);
		assertTrue(check);
	}
	
	@Test
	public void testCheckEligibility2() {

		boolean check=Job.checkEligibility(451);
		assertFalse(check);
	}

	@Test
	public void testAddToJobRoster() {
		//cannot test this functionality as it would cause inconsistency in the database.
	}


	@Test
	public void testGetAllJobsBySingleProfessor1() {

		Professor prof;
		try {
			prof = new Professor(597);
			LinkedHashMap<Integer, Job> getAllJobsByCheck=Job.getAllJobsBySingleProfessor(prof);
			assertNotNull(getAllJobsByCheck);
		} catch (Student.AccessDeniedException | People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void testGetAllJobsBySingleProfessor2() {

		Professor prof;
		try {
			prof = new Professor(1);
			LinkedHashMap<Integer, Job> getAllJobsByCheck=Job.getAllJobsBySingleProfessor(prof);
			assertNull(getAllJobsByCheck);
		} catch (Student.AccessDeniedException | People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
