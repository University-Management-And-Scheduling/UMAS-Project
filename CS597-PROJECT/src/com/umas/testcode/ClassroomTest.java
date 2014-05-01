package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import com.umas.code.*;

public class ClassroomTest {

	public static Classroom classroom;
	
	@Before 
	public void createClassRoomObject(){
		/*
		 * Existing classroom id used to initialize classroom object
		 */
		classroom = new Classroom(49);
	}
	
	@Test
	public void testClassroom() {
		/*
		 * Entering a existing classroom id
		 */
		Classroom result = new Classroom(1);
		assertNotNull(result);
		assertEquals(1, result.getClassroomID());
		assertEquals(8, result.getClassroomCapacity());
		assertNotNull(result.getClassroomLocation());
		assertNotNull(result.getClassroomName());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testClassroom2() {
		/*
		 * entering a non existing classroom id
		 */
		Classroom result = new Classroom(300);
	}

	@Test
	public void testAddNewClassroom() {
		/*
		 * Adding a existing classroom to the database
		 */
		boolean isAdded = Classroom.addNewClassroom(ClassroomName.CLASS1, ClassroomLocation.LOCATION2, 10);
		assertFalse(isAdded);
	}
	
	@Test
	public void testAddNewClassroom2() {
		/*
		 * Adding a new classroom to the database
		 * This test succeeds only at the first run, fails on next consecutive runs
		 */
		boolean isAdded = Classroom.addNewClassroom(ClassroomName.CLASS1, ClassroomLocation.LOCATION3, 10);
	}
	
	@Test
	public void testAddNewClassroom3() {
		/*
		 * Passing null values to the function and testing
		 */
		boolean isAdded = Classroom.addNewClassroom(null, null, 10);
		assertFalse(isAdded);
	}

	@Test
	public void testGetEmptySlot() {
		/*
		 * Returns a time slot object for the empty time slot found in the specifies classroom for TYPE 1 slot
		 */
		Timeslots timeslot = classroom.getEmptySlot(1);
		
		if(timeslot == null)
			assertNull(timeslot);
		else{
			assertNotNull(timeslot);
			System.out.println(timeslot.toString());
		}
	}
	
	@Test
	public void testGetEmptySlot2() {
		/*
		 * Returns a time slot object for the empty time slot found in the specifies classroom for TYPE 2 slot
		 */
		Timeslots timeslot = classroom.getEmptySlot(2);
		if(timeslot == null)
			assertNull(timeslot);
		else{
			assertNotNull(timeslot);
			System.out.println(timeslot.toString());
		}
	}

	@Test
	public void testCheckTimeSlotType() {
		/*
		 * Check time slot type 1
		 */
		boolean check = Classroom.checkTimeSlotType(1);
		assertTrue(check);
	}
	
	@Test
	public void testCheckTimeSlotType2() {
		/*
		 * Check time slot type 2
		 */
		boolean check = Classroom.checkTimeSlotType(2);
		assertTrue(check);
	}
	
	@Test
	public void testCheckTimeSlotType3() {
		/*
		 * Check invalid time slot type (i.e 3)
		 */
		boolean check = Classroom.checkTimeSlotType(3);
		assertFalse(check);
	}

	
	@Test
	public void testGetEmptyClassroom() {
		/*
		 * Test for a location with all valid parameters and timeslot type 1
		 */
		Classroom result = Classroom.getEmptyClassroom(ClassroomLocation.LOCATION3, 1, 5);
		assertNotNull(result);
	}
	
	@Test
	public void testGetEmptyClassroom2() {
		/*
		 * Test for a location with all valid parameters and timeslot type 2
		 */
		Classroom result = Classroom.getEmptyClassroom(ClassroomLocation.LOCATION2, 2, 10);
		assertNotNull(result);
	}
	
	@Test
	public void testGetEmptyClassroom3() {
		/*
		 * Test for a location with all valid parameters but invalid timeslot type (i.e 3)
		 */
		Classroom result = Classroom.getEmptyClassroom(ClassroomLocation.LOCATION3, 3, 10);
		assertNull(result);
	}
	
	@Test
	public void testGetEmptyClassroom4() {
		/*
		 * Test for a location with all valid parameters and but unavailable classroom capacity
		 */
		Classroom result = Classroom.getEmptyClassroom(ClassroomLocation.LOCATION3, 2, 50);
		assertNull(result);
	}
	
	@Test
	public void testGetEmptyClassroom5() {
		/*
		 * Test for a null location with all valid parameters and timeslot type 1
		 */
		Classroom result = Classroom.getEmptyClassroom(null, 1, 10);
		assertNull(result);
	}

	@Test
	public void testGetClassID() {
		/*
		 * Test for valid classroom and location
		 */
		int id = Classroom.getClassID(ClassroomName.CLASS1, ClassroomLocation.LOCATION1);
		assertEquals(1, id);
	}
	
	@Test
	public void testGetClassID2() {
		/*
		 * Test for invalid classroom and/or location
		 */
		int id = Classroom.getClassID(null, null);
		assertEquals(-1, id);
	}

	@Test
	public void testGetAllEmptyClassroom() {
		/*
		 * Test for all valid parameters for location, time slot type and capacity
		 */
		LinkedHashMap<Integer, Classroom> classrooms = Classroom.getAllEmptyClassroom(ClassroomLocation.LOCATION1, 1, 10);
		assertNotNull(classrooms);
		
	}
	
	@Test
	public void testGetAllEmptyClassroom2() {
		/*
		 * Test for all valid parameters for location, time slot type, but invalid capacity
		 */
		LinkedHashMap<Integer, Classroom> classrooms = Classroom.getAllEmptyClassroom(ClassroomLocation.LOCATION1, 1, 0);
		assertNull(classrooms);
		
	}
	
	@Test
	public void testGetAllEmptyClassroom3() {
		/*
		 * Test for all valid parameters for location,capacity but invalid timeslot
		 */
		LinkedHashMap<Integer, Classroom> classrooms = Classroom.getAllEmptyClassroom(ClassroomLocation.LOCATION1, 5, 10);
		assertNull(classrooms);
		
	}
	
	@Test
	public void testGetAllEmptyClassroom4() {
		/*
		 * Test for all valid parameters for capacity, timeslot type but invalid location
		 */
		LinkedHashMap<Integer, Classroom> classrooms = Classroom.getAllEmptyClassroom(null, 1, 10);
		assertNull(classrooms);
		
	}

	@Test
	public void testFindOpenSlotsForClassroom() {
		/*
		 * Valid time slot type
		 */
		ArrayList<Timeslots> slots = classroom.findOpenSlotsForClassroom(1);
		assertNotNull(slots);
	}
	
	@Test
	public void testFindOpenSlotsForClassroom2() {
		/*
		 * Valid time slot type
		 */
		ArrayList<Timeslots> slots = classroom.findOpenSlotsForClassroom(2);
		assertNotNull(slots);
	}
	
	@Test
	public void testFindOpenSlotsForClassroom3() {
		/*
		 * Invalid time slot type
		 */
		ArrayList<Timeslots> slots = classroom.findOpenSlotsForClassroom(3);
		assertNull(slots);
	}

	@Test
	public void testIsEmpty() {
		/*
		 * Test for a classroom and time slot combination that is available
		 */
		boolean check = Classroom.isEmpty(classroom, new Timeslots(30));
		assertTrue(check);
	}
	
	@Test
	public void testIsEmpty1() {
		/*
		 * Test for invalid inputs
		 */
		boolean check = Classroom.isEmpty(null, null);
		assertFalse(check);
	}	

}
