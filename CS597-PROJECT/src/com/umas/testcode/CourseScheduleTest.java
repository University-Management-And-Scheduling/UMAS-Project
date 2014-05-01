package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import com.mysql.jdbc.UpdatableResultSet;
import com.umas.code.*;

public class CourseScheduleTest {

	@Before
	public void setUp() throws Exception {
		boolean check = CourseSchedule.updateCourseSchedule(new CourseOffered(448), new Classroom(29), new Timeslots(30));
	}

	@Test
	public final void testCourseSchedule() {
		/*
		 * Enter a existing course offering id
		 * Else the object initialization will fail
		 */
		CourseSchedule schedule = new CourseSchedule(448);
		assertNotNull(schedule);
	}
	
	@Test
	public final void testCourseSchedule2() {
		/*
		 * Enter a existing course offering id
		 * Else the object initialization will fail
		 */
		CourseSchedule schedule = new CourseSchedule(44435348);
		assertNull(schedule.getClassroom());
	}

	@Test
	public final void testUpdateCourseSchedule() throws CourseOffered.CourseOfferingNotCurrentException, Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		boolean check = CourseSchedule.updateCourseSchedule(new CourseOffered(448), new Classroom(30), new Timeslots(30));
		assertTrue(check);
	}

	@Test
	public final void testGetHaspMapForSchedule() {
		HashMap<Integer, CourseSchedule> schedule = CourseSchedule.getHaspMapForSchedule();
		assertNotNull(schedule);
	}

	@Test
	public final void testGetAllScheduledCourses() {
		ArrayList<CourseSchedule> schedule = CourseSchedule.getAllScheduledCourses();
		assertNotNull(schedule);
	}

	@Test
	public final void testGetAllScheduledCoursesDepartment() throws Department.DepartmentDoesNotExistException {
		ArrayList<CourseSchedule> schedule = CourseSchedule.getAllScheduledCourses(new Department(1));
		assertNotNull(schedule);
	}

	@Test
	public final void testScheduleCourse() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		/*
		 * Scheduling a already scheduled course
		 */
		boolean check = CourseSchedule.scheduleCourse(new CourseOffered(448));
		assertFalse(check);
	}

	@Test
	public final void testScheduleCourseUsingID() {
		/*
		 * Scheduling a already scheduled course using offer id and capacity
		 */
		boolean check = CourseSchedule.scheduleCourseUsingID(448, 10);
		assertFalse(check);
	}

	@Test
	public final void testIsScheduled() {
		boolean check = CourseSchedule.isScheduled(448);
		assertTrue(check);
	}

	@Test
	public final void testScheduleAllCurrentCourses() {
		try{
			CourseSchedule.scheduleAllCurrentCourses();
		}
		catch(Exception e){
			fail("The test failed");
		}
		
	}

	@Test
	public final void testScheduleAllCurrentCoursesDepartment() {
		try{
			CourseSchedule.scheduleAllCurrentCourses(new Department(1));
		}
		catch(Exception e){
			fail("The test failed");
		}
	}

	@Test
	public final void testDeleteAllCourseSchedule() {
		try{
			CourseSchedule.deleteAllCourseSchedule();
			CourseSchedule.scheduleAllCurrentCourses();
		}
		catch(Exception e){
			fail("Deleting test failed");
		}
	}

	@Test
	public final void testDeleteAllCourseScheduleDepartment() {
		try{
			CourseSchedule.deleteAllCourseSchedule(new Department(1));
			CourseSchedule.scheduleAllCurrentCourses(new Department(1));
		}
		catch(Exception e){
			fail("Deleting by department test failed");
		}
	}

	@Test
	public final void testIsAnotherCourseSchedulable() {
		boolean check = CourseSchedule.isAnotherCourseSchedulable(10);
		assertTrue(check);
	}
	
	@Test
	public final void testIsAnotherCourseSchedulable2() {
		/*
		 * Checking for a capacity for which no class room is available
		 */
		boolean check = CourseSchedule.isAnotherCourseSchedulable(100);
		assertFalse(check);
	}

}
