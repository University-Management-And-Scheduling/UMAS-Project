package com.umas.testcode;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

/************************@author Simant Purohit*************************/

public class WaitListTest {

	static Student student;
	@Before
	public void setUp() throws Exception {
		student = new Student(584);
	}

	@Test
	public final void testAddStudentToWaitList() {
		/*
		 * Add the student to wait list
		 * The student does not get added on the list if the course is register-able
		 * Thus the success of this test is contingent on the conditions
		 */
		try {
			WaitList.removeFromWaitList(student, 448);
			boolean check = WaitList.addStudentToWaitList(student, 448);
			assertTrue(check);
			WaitList.removeFromWaitList(student, 448);
		} catch (Course.CourseDoesNotExistException e) {
			fail("Failed to add");
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			fail("Failed to add");
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetStudentsOnEmailList() {
		try{
			WaitList.getStudentsOnEmailList(448);
		}
		
		catch(Exception e){
			fail("Failed to retrieve students");
		}
	}

	@Test
	public final void testGetStudentsOnWaitList() {
		try{
			WaitList.getStudentsOnWaitList(448);
		}
		catch(Exception e){
			fail("Failed to retrieve students");
		}
	}

	@Test
	public final void testGetWaitListCoursesOfStudent() {
		try{
			WaitList.getWaitListCoursesOfStudent(student);
		}
		catch(Exception e){
			fail("Failed to retrieve courses");
		}
	}

	
	@Test
	public final void testRemoveFromWaitList() {
		/*
		 * Adding the student to be removed so that the test does not hamper the database consistency
		 */
		try {
			WaitList.addStudentToWaitList(student, 448);
			boolean check = WaitList.removeFromWaitList(student, 448);
			assertTrue(check);
		} catch (Course.CourseDoesNotExistException e) {
			fail("Failed to add");
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			fail("Failed to add");
			e.printStackTrace();
		}
	}

	@Test
	public final void testRemoveFromWaitListAndCommit() {
		/*
		 * Adding the student to be removed so that the test does not hamper the database consistency
		 * The earlier test function does not commit as it is a part of chain call which commits in the end of chain
		 */
		try {
			WaitList.addStudentToWaitList(student, 448);
			boolean check = WaitList.removeFromWaitListAndCommit(student, 448);
			assertTrue(check);
		} catch (Course.CourseDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		}
	}

	@Test
	public final void testRemoveFromEmailedList() {
		/*
		 * Adding the student to wait list
		 * Calling the emailFirstStudentOnWaitList to put student on email list
		 * Then calling the removing the student from the email list
		 */
		try {
			WaitList.addStudentToWaitList(student, 448);
			WaitList.emailFirstStudentOnWaitList(448);
			WaitList.removeFromEmailedList(student.getUIN(), 448);
		} catch (Course.CourseDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		}
	}

	
	@Test
	public final void testIsStudentRegistered() {
		/*
		 * As long as the student passed is not registered for the course this test will pass
		 */
		boolean check = WaitList.isStudentRegistered(student, 448);
		assertFalse(check);
	}

	@Test
	public final void testIsStudentOnWaitList() {
		/*
		 * Add a student to wait list
		 * Test the function for correct behavior
		 * Remove the student from wait list
		 */
		try {
			WaitList.addStudentToWaitList(student, 448);
			boolean check = WaitList.isStudentOnWaitList(student, 448);
			assertTrue(check);
			WaitList.removeFromEmailedList(student.getUIN(), 448);
		} catch (Course.CourseDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			fail("Failed to remove");
			e.printStackTrace();
		}
	}

	@Test
	public final void testIsWaitListEmpty() {
		/*
		 * Checks if the wait list is empty
		 * Passes only if the wait list is empty for the passed offer id
		 */
		boolean check = WaitList.isWaitListEmpty(447);
		assertTrue(check);
	}


}
