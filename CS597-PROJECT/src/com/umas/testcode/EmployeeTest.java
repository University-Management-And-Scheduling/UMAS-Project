package com.umas.testcode;
import com.umas.code.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;



public class EmployeeTest {

	@Test
	public void testEmployeeInt1() throws People.PersonDoesNotExistException {
		
		Employee e=new Employee(1);
		assertNotNull(e);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testEmployeeInt2(){
		
		Employee e;
		try {
			e = new Employee(18000);
			assertNull(e);
		} catch (People.PersonDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//fail("Not yet implemented");
	}
	

	@Test
	public void testAddEmployee() {
		
		//cannot test this function as this would create inconsistency in the database

	}

	@Test
	public void testAddEmployeeCheck1() {
		
		boolean check=Employee.addEmployeeCheck(581);
		assertTrue(check);
	}
	
	@Test
	public void testAddEmployeeCheck2() {
		
		boolean check=Employee.addEmployeeCheck(584);
		assertFalse(check);
	}


	@Test
	public void testUpdateEmpDetails() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(581, "testing", "testing");
		assertTrue(check);
	}
	
	@Test
	public void testUpdateEmpDetails1() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(581,"TBD","TBD");
		assertTrue(check);
	}
	
	@Test
	public void testUpdateEmpDetails2() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(272, null, "testing");
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails3() throws Student.AccessDeniedException {
		
		
		boolean check=Employee.updateEmpDetails(600, "testing", null);
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails4() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(272, "", "testing");
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails5() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(600, "testing", "");
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails6() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(600, "", "");
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails7() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(600,null, null);
		assertFalse(check);
	}
	
	@Test
	public void testUpdateEmpDetails8() throws Student.AccessDeniedException {
		
		boolean check=Employee.updateEmpDetails(272,null, null);
		assertFalse(check);
	}

	@Test
	public void testUpdateEmpDetailscheck1() {
		
		boolean check=Employee.updateEmpDetailscheck(581);
		assertTrue(check);
	}
	
	@Test
	public void testUpdateEmpDetailscheck2() {
		
		boolean check=Employee.updateEmpDetailscheck(600);
		assertFalse(check);
	}

	@Test
	public void testGiveBonus1() throws Employee.bonusNotValidException, Student.AccessDeniedException {
		
		boolean check=Employee.giveBonus(581, 10);
		assertTrue(check);
	}
	
	@Test
	public void testGiveBonus2() {
		
		try {
			boolean check=Employee.giveBonus(600, 10);
			assertFalse(check);
		} catch (Employee.bonusNotValidException | Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGiveBonus3() {
		
		try {
			
			boolean check=Employee.giveBonus(600, 3);
			assertFalse(check);
		} catch (Employee.bonusNotValidException | Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGiveBonus4() {
		
		try {
			boolean check=Employee.giveBonus(600, 35);
			assertFalse(check);
		} catch (Employee.bonusNotValidException | Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGiveBonus5() {
		
		try {
			boolean check=Employee.giveBonus(581, 40);
			assertFalse(check);
		} catch (Employee.bonusNotValidException | Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testGetsalary1() {
		
		double check=Employee.getsalary(581);
		assertEquals(0,77948.68400000001, 77948.68400000001);
	}
	
	@Test
	public void testGetsalary2() {
		
		double check=Employee.getsalary(600);
		assertEquals(-1, -1);
	}

	@Test
	public void testGiveBonusCheck() {
		
		boolean check=Employee.giveBonusCheck(581);
		assertTrue(check);
	}
	
	@Test
	public void testGiveBonusCheck2() {
		
		boolean check=Employee.giveBonusCheck(600);
		assertFalse(check);
	}

	@Test
	public void testDeleteFromEmployeeByUIN() {

		//cannot test this function as this would create inconsistency in the database

	}

	@Test
	public void testDeleteFromEmployeeByUINCheck() {
		
		boolean check=Employee.deleteFromEmployeeByUINCheck(581);
		assertTrue(check);
	}
	
	@Test
	public void testDeleteFromEmployeeByUINCheck2() {
		
		boolean check=Employee.deleteFromEmployeeByUINCheck(600);
		assertFalse(check);
	}

	@Test
	public void testDeleteFromEmployeeByUserName() {

		//cannot test this function as this would create inconsistency in the database

	}

	@Test
	public void testGetEmployeeUIN1() {
		
		int check=Employee.getEmployeeUIN("leona");
		assertEquals(581, 581);
	}
	
	@Test
	public void testGetEmployeeUIN2() {
		
		int check=Employee.getEmployeeUIN("alibaba");
		assertEquals(-1, -1);
	}

	@Test
	public void testDeleteFromEmployeeByUserNameCheck1() {
		
		boolean check=Employee.deleteFromEmployeeByUserNameCheck("leona");
		assertTrue(check);
	}
	
	@Test
	public void testDeleteFromEmployeeByUserNameCheck2() {
		
		boolean check=Employee.deleteFromEmployeeByUserNameCheck("alibaba");
		assertFalse(check);
	}

	@Test
	public void testGetAllEmployeesByDepartment1() {
		
		try {
			ArrayList<Employee> getAllEmpsCheck=Employee.getAllEmployeesByDepartment("Computer Science");
			assertNotNull(getAllEmpsCheck);
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testGetAllEmployeesByDepartment2() {
		
		try {
			ArrayList<Employee> getAllEmpsCheck=Employee.getAllEmployeesByDepartment("alibaba");
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCheckIfEmployeeInt() {
		
		boolean check=Employee.checkIfEmployee(581);
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfEmployeeInt2() {
		
		boolean check=Employee.checkIfEmployee(451);
		assertFalse(check);
	}

	@Test
	public void testCheckIfEmployeeString1() {
		boolean check=Employee.checkIfEmployee("leona");
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfEmployeeString2() {
		
		boolean check=Employee.checkIfEmployee("alibaba");
		assertFalse(check);
	}

}
