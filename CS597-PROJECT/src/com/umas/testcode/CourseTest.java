package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

/************************@author Simant Purohit*************************/

public class CourseTest {

	public static Course course;
	public static Department department;
	
	@Before
	public void setUp() throws Exception {
		/*
		 * Initializing existing course with the corresponding department
		 */
		course = new Course(102);
		department = course.getDepartment();
		course.updateCourse("CS300", department);
		course = new Course(102);
		
	}
	
	public void testCourseID(){
		/*
		 * initialize a course which is existing the database using ID
		 */
		Course c = null;
		try {
			c = new Course(2);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		}
		assertNotNull(c);
	}
	
	@Test(expected = Course.CourseDoesNotExistException.class)
	public void testCourseID2() throws Course.CourseDoesNotExistException{
		/*
		 * initialize a course which is not existing the database using ID
		 */
		Course c = new Course(5000);
		assertNull(c);
	}
	
	public void testCourseString(){
		/*
		 * initialize a course which is existing the database using course name
		 */
		Course c = null;
		try {
			c = new Course("CS422");
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		}
		assertNotNull(c);
	}
	
	@Test(expected = Course.CourseDoesNotExistException.class)
	public void testCourseString2() throws Course.CourseDoesNotExistException{
		/*
		 * initialize a course which is not existing the database using String
		 */
		Course c = new Course("calkfjdalkdjaszjkhkjn");
	}
	
	@Test(expected = Course.CourseDoesNotExistException.class)
	public void testCourseString3() throws Course.CourseDoesNotExistException{
		/*
		 * initialize a course with a null parameter
		 */
		Course c = new Course(null);
	}
	
	@Test
	public void testGetDepartment() {
		/*
		 * Retrieve the departments
		 */
		Department d = course.getDepartment();
		assertNotNull(d);
	}

	@Test
	public void testGetCourseID() {
		/*
		 * Retrieve course id
		 */
		int id = course.getCourseID();
		assertEquals(102, id);
	}


	
	@Test(expected = Course.CourseAlreadyExistsException.class)
	public void testAddCourse() throws Course.CourseAlreadyExistsException {
		/*
		 * Adding a new course which is existing in the database
		 */
		
		Course.addCourse("CS422", department);
		
	}

	@Test(expected = Course.CourseAlreadyExistsException.class)
	public void testAddCourse2() throws Course.CourseAlreadyExistsException {
		/*
		 * Adding a new course which is not existing in the database
		 * Success at the first run, fails later
		 */
		
		Course.addCourse("CS425", department);
	}
	
	@Test
	public void testAddCourse3() throws Course.CourseAlreadyExistsException {
		/*
		 * Adding a new course which is not existing in the database
		 * Success at the first run, fails later
		 */
		
		boolean check = Course.addCourse(null, null);
		assertFalse(check);
	}
		
	@Test
	public void testUpdateCourse() {
		boolean check = course.updateCourse("CS359", department);
		assertTrue(check);
	}

	@Test
	public void testGetAllCourses() {
		HashMap<Integer, Course> courses = Course.getAllCourses();
		assertNotNull(courses);
	}

	@Test
	public void testGetCurrentOfferings() {
		LinkedHashMap<Integer, CourseOffered> courses = course.getCurrentOfferings();
		assertNotNull(courses);
	}

	@Test
	public void testGetCoursesOfDepartment() {
		ArrayList<Course> courses = Course.getCoursesOfDepartment(department);
		assertNotNull(courses);
	}

}
