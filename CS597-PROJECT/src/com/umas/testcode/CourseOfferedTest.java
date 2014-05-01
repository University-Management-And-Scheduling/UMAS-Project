package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

public class CourseOfferedTest {

	CourseOffered courseOffered;
	@Before
	public void setUp() throws Exception {
		/*
		 * Initializing a currently offered course offering object
		 * SOME TESTS RELATING TO THIS OBJECT WILL FAIL IF THE OFFERING THE
		 * OFFERING IS NOT FOR CURRENT SEMESTER
		 */
		courseOffered = new CourseOffered(448);
	}

	@Test
	public void testHashCode() {
		int hash = courseOffered.hashCode();
		assertTrue(hash>=0);
	}

	@Test
	public void testCourseOffered() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * Initialize a course offering object which exists
		 */
		CourseOffered c = new CourseOffered(448);
		assertNotNull(c);
	}
	
	@Test(expected = CourseOffered.CourseOfferingDoesNotExistException.class)
	public void testCourseOffered2() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * Initialize a course offering object which does not exists
		 */
		CourseOffered c = new CourseOffered(9594654);
		assertNull(c);
	}

	@Test
	public void testGetCourseName() {
		String courseName = courseOffered.getCourseName();
		assertNotNull(courseName);
	}

	@Test
	public void testGetProfessorName() {
		String profName = courseOffered.getProfessorName();
		assertNotNull(profName);
	}

	@Test
	public void testGetDepartmentName() {
		assertNotNull(courseOffered.getDepartmentName());
	}

	@Test
	public void testGetClassRoomName() {
		assertNotNull(courseOffered.getClassRoomName());
	}

	@Test
	public void testGetClassRoomLocation() {
		assertNotNull(courseOffered.getClassRoomLocation());
	}

	@Test
	public void testGetTiming() {
		assertNotNull(courseOffered.getTiming());
	}

	@Test
	public void testGetAllCurrentlyOfferedCourses() {
		ArrayList<CourseOffered> offers = CourseOffered.getAllCurrentlyOfferedCourses();
		assertNotNull(offers);
	}

	@Test
	public void testGetAllOfferedIDAndCourseOffered() {
		HashMap<Integer, CourseOffered> offers = CourseOffered.getAllOfferedIDAndCourseOffered();
		assertNotNull(offers);
	}

	@Test
	public void testGetAllOfferedCourses() {
		ArrayList<CourseOffered> offersAll = CourseOffered.getAllOfferedCourses();
		assertNotNull(offersAll);
	}

	@Test(expected = CourseOffered.CourseOfferingAlreadyExistsException.class)
	public void testAddCourseOfferingToDatabase() throws CourseOffered.CourseOfferingAlreadyExistsException, CourseOffered.CourseOfferingNotSchedulable {
		/*
		 * Try to add a new course offering to the database
		 * addition works only first use
		 * Fails after that.
		 * THrows a Course already exists exception
		 * The function also throws a Course not schedulable exception but that will occur
		 * only if the classrooms are full which is not the case here
		 */
		
		CourseOffered.addCourseOfferingToDatabase(courseOffered.getCourse(), courseOffered.getProfessor(), 10);
		
	}
	
	@Test
	public void testUpdateCourseOffering() throws CourseOffered.CourseOfferingDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		/*
		 * Try to update the professor of the course offering
		 * The professor is present in the database
		 */
		boolean check = courseOffered.updateCourseOffering(new Professor(550));
		assertTrue(check);
		
	}
	
	@Test(expected = Student.AccessDeniedException.class)
	public void testUpdateCourseOffering2() throws CourseOffered.CourseOfferingDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		/*
		 * Try to update the professor of the course offering
		 * The parameter passed is a student UIN
		 * Hence throws a Access Denied exception
		 */
		courseOffered.updateCourseOffering(new Professor(549));		
	}
	
	@Test
	public void testUpdateCourseOffering3() throws CourseOffered.CourseOfferingDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException, Course.CourseDoesNotExistException {
		/*
		 * Try to update the professor of the course offering which is not current
		 */
		CourseOffered c = new CourseOffered(444);
		boolean check = c.updateCourseOffering(new Professor(550));	
		assertFalse(check);
	}

	@Test
	public void testGetStudentCourses() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException, People.PersonDoesNotExistException {
		/*
		 * Gets all the courses taken by the student presently or in the past
		 * Student passed is a valid student
		 */
		ArrayList<CourseOffered> courses = CourseOffered.getStudentCourses(new Student(549));
		assertNotNull(courses);
	}
	
	@Test(expected = People.PersonDoesNotExistException.class)
	public void testGetStudentCourses2() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException, People.PersonDoesNotExistException {
		/*
		 * Gets all the courses taken by the student presently or in the past
		 * Passed parameter is a Professor UIN
		 * Throws a people does not exist exception
		 */
		Student s = new Student(550);
		CourseOffered.getStudentCourses(s);
	}

	@Test(expected = NullPointerException.class)
	public void testGetStudentCourses3() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException, People.PersonDoesNotExistException {
		/*
		 * Gets all the courses taken by the student presently or in the past
		 * Student passed is a valid student
		 */
		CourseOffered.getStudentCourses(null);
	}
	
	@Test
	public void testGetAllStudentsInCourse() {
		ArrayList<Student> student = CourseOffered.getAllStudentsInCourse(courseOffered);
		assertNotNull(student);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetAllStudentsInCourse2() {
		CourseOffered.getAllStudentsInCourse(null);
	}

	@Test
	public void testGetCurrentProfessorCourses() throws Student.AccessDeniedException, People.PersonDoesNotExistException {
		/*
		 * Passed professor is initialized with a professor UIN only
		 */
		ArrayList<CourseOffered> professorCourses = CourseOffered.getCurrentProfessorCourses(new Professor(550));
		assertNotNull(professorCourses);
	}
	
	@Test(expected = Student.AccessDeniedException.class)
	public void testGetCurrentProfessorCourses2() throws Student.AccessDeniedException, People.PersonDoesNotExistException {
		/*
		 * Here the UIN passed is not a professor but a student
		 * Throws a Access denied exception
		 */
		CourseOffered.getCurrentProfessorCourses(new Professor(549));
	}
	
	@Test
	public void testGetCurrentProfessorCourses3() throws Student.AccessDeniedException, People.PersonDoesNotExistException {
		/*
		 * Passed professor is null
		 */
		ArrayList<CourseOffered> professorCourses = CourseOffered.getCurrentProfessorCourses(null);
		assertNull(professorCourses);
	}

	@Test
	public void testGetAllCurrentCoursesTAedBy() throws People.PersonDoesNotExistException {
		/*
		 * Valid TA UIN is passed
		 */
		ArrayList<CourseOffered> taEd = CourseOffered.getAllCurrentCoursesTAedBy(new TA(553));
		assertNotNull(taEd);
	}
	
	@Test(expected = People.PersonDoesNotExistException.class)
	public void testGetAllCurrentCoursesTAedBy2() throws People.PersonDoesNotExistException {
		/*
		 * Invalid TA UIN is passed
		 */
		CourseOffered.getAllCurrentCoursesTAedBy(new TA(549));
	}

	@Test
	public void testGetAllCurrentCoursesTakenBy() throws People.PersonDoesNotExistException {
		/*
		 * Gets all the courses that the ta has taken
		 */
		ArrayList<CourseOffered> taEd = CourseOffered.getAllCurrentCoursesTakenBy(new TA(553));
		assertNotNull(taEd);
		
	}
	
	@Test(expected = People.PersonDoesNotExistException.class)
	public void testGetAllCurrentCoursesTakenBy2() throws People.PersonDoesNotExistException {
		/*
		 * Gets all the courses that the ta has taken
		 * Parameter passed is not a valid TA UIN
		 */
		ArrayList<CourseOffered> taEd = CourseOffered.getAllCurrentCoursesTakenBy(new TA(549));
		assertNotNull(taEd);
		
	}

	@Test
	public void testIsCourseFull() throws CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * Check if the course offering is full or not
		 * The course offered object passed is not full
		 * The test fails if the course was full
		 */
		boolean check = courseOffered.isCourseFull();
		assertFalse(check);
	}

	@Test
	public void testAddOneSeatFilledToCourseOffered() throws CourseOffered.CourseOfferingNotCurrentException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * This function is called when a student registers
		 * Should it be called multiple times, the course might become full
		 * and this test will fail
		 */
		courseOffered.removeOneSeatFromCourseOffered();
		boolean check = courseOffered.addOneSeatFilledToCourseOffered();
		assertTrue(check);
	}

	@Test
	public void testRemoveOneSeatFromCourseOffered() throws CourseOffered.CourseOfferingNotCurrentException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * This function is called when a student unregisters from a course
		 * Will fail if the course offering is empty
		 */
		courseOffered.addOneSeatFilledToCourseOffered();
		boolean check = courseOffered.removeOneSeatFromCourseOffered();
		assertTrue(check);
	}

	@Test
	public void testCheckIfScheduled() throws CourseOffered.CourseOfferingNotCurrentException {
		/*
		 * Check a currently offered course
		 */
		boolean check = courseOffered.checkIfScheduled();
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfScheduled2() throws CourseOffered.CourseOfferingNotCurrentException, Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * Check a previously offered course
		 */
		CourseOffered c = new CourseOffered(444);
		boolean check = c.checkIfScheduled();
		assertFalse(check);
	}

	@Test
	public void testCheckIfExists() {
		/*
		 * Run for a existing course
		 */
		boolean check = CourseOffered.checkIfExists(448);
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfExists2() {
		/*
		 * Run for a existing course
		 */
		boolean check = CourseOffered.checkIfExists(446548);
		assertFalse(check);
	}

//	@Test
//	public void testGetCurrentSemesterID() {
//		int semID = CourseOffered.getCurrentSemesterID();
//		assertNotEquals(-1, semID);
//		assertNotEquals(0, semID);
//	}
//	

	@Test
	public void testCheckIfCurrent() {
		/*
		 * Checking a current course
		 */
		boolean check = courseOffered.checkIfCurrent();
		assertTrue(check);
	}
		
	

	@Test
	public void testIsCourseRegistrableBy() throws People.PersonDoesNotExistException {
		/*
		 * Using a student UIN i.e a valid student to check if the student can
		 * register for the course offer
		 */
		boolean check = courseOffered.isCourseRegistrableBy(new Student(553));
		assertTrue(check);
	}
	

	@Test
	public void testSendCourseFilesToStudent() throws People.PersonDoesNotExistException {
		boolean check = courseOffered.sendCourseFilesToStudent(new Student(553));
		assertTrue(check);
	}
	

	@Test
	public void testEqualsObject() {
		boolean check = courseOffered.equals(courseOffered);
		assertTrue(check);
	}

}
