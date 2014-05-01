package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.umas.code.*;

public class ProfessorTest {

	@Test
	public void testProfessorInt() {
		
		Professor prof;
		try {
			prof = new Professor(582);
			assertEquals(582, prof.getUIN());
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testProfessorInt2() {
		
		Professor prof;
		try {
			prof = new Professor(451);
			assertEquals(0, prof.getUIN());
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	@Test
	public void testProfessorString() {
		
		Professor prof=new Professor("camer");
		assertEquals(582, prof.getUIN());
	}
	
	@Test
	public void testProfessorString2() {
		
		Professor prof=new Professor("alibaba");
		assertEquals(0, prof.getUIN());
	}
	
	@Test
	public void testProfessorString3() {
		
		Professor prof=new Professor("");
		System.out.println("================================="+prof.getName());
		assertEquals(null,prof.getName());
	}

	@Test
	public void testProfessorString4() {
		
		Professor prof=new Professor(null);
		assertEquals(0, prof.getUIN());
	}

	@Test
	public void testAddProfToDb() {
		
		//this function cannot be tested now here as this would cause inconsistency in the database
		
	}

	@Test
	public void testCheckIfProfessorInt1() {
		
		Boolean check=Professor.checkIfProfessor(582);
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfProfessorInt2() {
		
		Boolean check=Professor.checkIfProfessor(272);
		assertFalse(check);
	}
	
	@Test
	public void testCheckIfProfessorInt3() {
		
		Boolean check=Professor.checkIfProfessor(10000);
		assertFalse(check);
	}


	@Test
	public void testCheckIfProfessorString() {
		
		boolean check=Professor.checkIfProfessor("camer");
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfProfessorString2() {
		
		boolean check=Professor.checkIfProfessor("japoo");
		assertFalse(check);
	}
	
	@Test
	public void testCheckIfProfessorString3() {
		
		boolean check=Professor.checkIfProfessor("");
		assertFalse(check);
	}
	
	@Test
	public void testCheckIfProfessorString4() {
		
		boolean check=Professor.checkIfProfessor(null);
		assertFalse(check);
	}

	@Test
	public void testRetrieveProfDetailsByUIN1() {
		
		Professor check=Professor.retrieveProfDetailsByUIN(582);
		assertEquals(582, check.getUIN());
	}
	
	@Test
	public void testRetrieveProfDetailsByUIN2() {
		
		Professor check=Professor.retrieveProfDetailsByUIN(100);
		System.out.println("------///------------------------"+check);
		assertEquals(null,check);
	}
	
	@Test
	public void testRetrieveProfDetailsByUIN3() {
		
		Professor check=Professor.retrieveProfDetailsByUIN(1);
		System.out.println("------------------------------"+check);
		assertEquals(null, null, check);
	}

	@Test
	public void testRetrieveProfDetailsByUserName1() {
		
		Professor check=Professor.retrieveProfDetailsByUserName("camer");
		assertEquals(582, check.getUIN());
	}
	
	@Test
	public void testRetrieveProfDetailsByUserName2() {
		
		Professor check=Professor.retrieveProfDetailsByUserName("alibaba");
		System.out.println("------------------------------"+check);
		assertEquals(null, null, check);
	}

	@Test
	public void testDeleteProfFromDbUsingUIN() {
		//this function is not tested in here as it would cause inconsistency in the database.
	}

	@Test
	public void testDeleteProfFromDbUsingUserName() {
		//this function is not tested in here as it would cause inconsistency in the database.
	}


	@Test
	public void testGetAllProfInADeptInt() throws Professor.ProfessorDoesNotExistException {
		
		ArrayList<Professor> check=Professor.getAllProfInADept(1);
		assertNotNull(check);
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testGetAllProfInADeptInt2() throws Professor.ProfessorDoesNotExistException, Department.DepartmentDoesNotExistException{
		
		ArrayList<Professor> check;
		Department d=new Department(100);
			check = Professor.getAllProfInADept(d.getDepartmentID());;
			System.out.println("---------------------------------------------"+check.get(1));
			//assertNotNull(check);

		
	}

	@Test
	public void testGetAllProfInADeptString1() throws Professor.ProfessorDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Department d=new Department("computer Science");
		ArrayList<Professor> check=Professor.getAllProfInADept(d.getDepartmentName());
		assertNotNull(check);
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testGetAllProfInADeptString2() throws Professor.ProfessorDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Department d=new Department("xyz");
		ArrayList<Professor> check=Professor.getAllProfInADept(d.getDepartmentName());
		assertNull(check);
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testGetAllProfInADeptString3() throws Professor.ProfessorDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Department d=new Department("");
		ArrayList<Professor> check=Professor.getAllProfInADept(d.getDepartmentName());
		assertNull(check);
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testGetAllProfInADeptString4() throws Professor.ProfessorDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Department d=new Department(null);
		ArrayList<Professor> check=Professor.getAllProfInADept(d.getDepartmentName());
		assertNull(check);
	}

	@Test
	public void testGetAllProf() {
		
		ArrayList<Professor> check=Professor.getAllProf();
		assertNotNull(check);
	}

	@Test
	public void testUpdateProfUserName1() {
		
		
		Professor p;
		try {
			p = new Professor(592);
			boolean check=p.updateProfUserName("borri");
			assertTrue(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateProfUserName2() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfUserName("borri");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName3() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfUserName("alibaba");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateProfUserName4() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfUserName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateProfUserName5() {
		
		Professor p;
		try {
			p = new Professor(582);
			boolean check=p.updateProfUserName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName6() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfUserName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName7() {
		
		Professor p;
		try {
			p = new Professor(582);
			boolean check=p.updateProfUserName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName8() {
		
		Professor p;
		try {
			p = new Professor(10000);
			boolean check=p.updateProfUserName("borri");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName9() {
		
		Professor p;
		try {
			p = new Professor(272);
			boolean check=p.updateProfUserName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfUserName10() {
		
		Professor p;
		try {
			p = new Professor(272);
			boolean check=p.updateProfUserName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testUpdateProfName1() {
		
		
		Professor p;
		try {
			p = new Professor(592);
			boolean check=p.updateProfName("borri");
			assertTrue(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateProfName2() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfName("borri");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName3() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfName("alibaba");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateProfName4() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateProfName5() {
		
		Professor p;
		try {
			p = new Professor(582);
			boolean check=p.updateProfName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName6() {
		
		Professor p;
		try {
			p = new Professor(584);
			boolean check=p.updateProfName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName7() {
		
		Professor p;
		try {
			p = new Professor(582);
			boolean check=p.updateProfName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName8() {
		
		Professor p;
		try {
			p = new Professor(10000);
			boolean check=p.updateProfName("borri");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName9() {
		
		Professor p;
		try {
			p = new Professor(272);
			boolean check=p.updateProfName("");
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProfName10() {
		
		Professor p;
		try {
			p = new Professor(272);
			boolean check=p.updateProfName(null);
			assertFalse(check);
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testUpdateProfDept() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Professor p=new Professor(592);
		boolean check=p.updateProfDept(d.getDepartmentID());
		assertTrue(check);
	}

	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateProfDept2() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(100);
		Professor p=new Professor(592);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}

	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateProfDept3() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Professor p=new Professor(272);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateProfDept4() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Professor p=new Professor(584);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateProfDept5() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(null);
		Professor p=new Professor(582);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateProfDept6() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department("");
		Professor p=new Professor(584);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}
	
	@Test
	public void testUpdateProfDept7() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department("Computer Science");
		Professor p=new Professor(583);
		boolean check=p.updateProfDept(d.getDepartmentID());
	}

}
