package com.umas.testcode;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.umas.code.*;
import com.umas.code.Department.DepartmentAlreadyExistsException;

/************************@author Simant Purohit*************************/

public class DepartmentTest {

	public static Department department1;
	public static Department department2;
	@Before
	public void setUp() throws Exception {
		department1 = new Department(1);
		department2 = new Department("Computer Science");
	}

	@Test
	public final void testDepartmentInt() {
		assertNotNull(department1);
	}

	@Test
	public final void testDepartmentString() {
		assertNotNull(department2);
	}

	@Test(expected = Department.DepartmentAlreadyExistsException.class)
	public final void testAddNewDepartment() throws Department.DepartmentAlreadyExistsException {
		/*
		 * Success the first time
		 * Fails later
		 */
		Department.addNewDepartment("Geology");
	}

	@Test
	public final void testUpdateDepartment() throws Department.DepartmentDoesNotExistException {
		department1.setDepartmentName("Computer");
		boolean check;
		try {
			check = department1.updateDepartment();
			assertTrue(check);
			department1.setDepartmentName("Computer Science");
			department1.updateDepartment();
		} catch (Department.DepartmentAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public final void testGetAllDepartments() {
		try{
			Department.getAllDepartments();
		}
		catch(Exception e){
			fail("Failed to get all the departments");
		}
	}

	@Test
	public final void testGetDepartmentCourses() {
		try{
			department1.getDepartmentCourses();
		}
		catch(Exception e){
			fail("Failed to get all the department courses");
		}
	}

	@Test
	public final void testGetDepartmentCourseOffered() {
		try{
			department1.getDepartmentCourseOffered();
		}
		catch(Exception e){
			fail("Failed to get all teh department course offerings");
		}
	}

}
