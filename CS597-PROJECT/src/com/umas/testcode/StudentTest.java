package com.umas.testcode;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Test;
import com.umas.code.*;

public class StudentTest {

	@Test
	public void testStudentInt() throws People.PersonDoesNotExistException {
		
		Student s=new Student(584);
		assertEquals("kamal", s.getName());
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testStudentInt2() throws People.PersonDoesNotExistException {
		
		Student s=new Student(592);
		assertEquals("kamal", s.getName());
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testStudentInt3() throws People.PersonDoesNotExistException {
		
		Student s=new Student(10000);
		assertEquals("kamal", s.getName());
	}

	@Test
	public void testAddStudentToDb() {
	//	this function cannot be tested here as it would cause inconsistency in the database/ It can be tested through the UI
	}

	@Test
	public void testDeleteStudentInt() {
//		this function cannot be tested here as it would cause inconsistency in the database/ It can be tested through the UI
	}

	@Test
	public void testDeleteStudentString() {
//		this function cannot be tested here as it would cause inconsistency in the database/ It can be tested through the UI
	}

	@Test
	public void testCheckIfStudent() {

		boolean check=Student.checkIfStudent(584);
		assertTrue(check);
	}
	
	@Test
	public void testCheckIfStudent2() {

		boolean check=Student.checkIfStudent(592);
		assertFalse(check);
	}

	@Test
	public void testUpdateGPA() {
		
		//update GPA cannot be tested here as it would cause inconsistency in the database.
	}

	@Test
	public void testUpdateGPACheck() {
		
		boolean check=Student.updateGPACheck(584);
		assertTrue(check);
	}
	
	@Test
	public void testUpdateGPACheck2() {
		
		boolean check=Student.updateGPACheck(592);
		assertFalse(check);
	}

	@Test
	public void testGetAllStudents() {
		
		ArrayList<Student> check=Student.getAllStudents();
		assertNotNull(check);
	}

	@Test
	public void testGetStudentCourses() throws People.PersonDoesNotExistException, Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException  {
		
		Student s=new Student(584);
		LinkedHashMap<Integer,CourseOffered> check=s.getStudentCourses();
		assertNotNull(check);
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testGetStudentCourses2() throws People.PersonDoesNotExistException, Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {
		
		Student s=new Student(592);
		LinkedHashMap<Integer,CourseOffered> check=s.getStudentCourses();
		assertNull(check);
	}



	@Test
	public void testCalculateGPA() {
		
		//testing calculate grades would cause inconsistency in the database. This would update automatically.

	}

	
	@Test
	public void testUpdateStudentUserName1() {
		
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentUserName("kamal");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateStudentUserName2() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentUserName("borri");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentUserName3() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentUserName("alibaba");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected=NullPointerException.class)
	public void testUpdateStudentUserName4() {
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateStudentUserName5() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateStudentUserName6() {
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentUserName7() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentUserName8() {
		
		Student p;
		try {
			p = new Student(10000);
			boolean check=p.updateStudentUserName("borri");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentUserName9() {
		
		Student p;
		try {
			p = new Student(272);
			boolean check=p.updateStudentUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentUserName10() {
		
		Student p;
		try {
			p = new Student(272);
			boolean check=p.updateStudentUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testUpdateStudentName1() {
		
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentName("kamal");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateStudentName2() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentName("borri");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentName3() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentName("alibaba");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected=NullPointerException.class)
	public void testUpdateStudentName4() {
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateStudentName5() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateStudentName6() {
		
		Student p;
		try {
			p = new Student(584);
			boolean check=p.updateStudentName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentName7() {
		
		Student p;
		try {
			p = new Student(592);
			boolean check=p.updateStudentName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentName8() {
		
		Student p;
		try {
			p = new Student(10000);
			boolean check=p.updateStudentName("borri");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentName9() {
		
		Student p;
		try {
			p = new Student(272);
			boolean check=p.updateStudentName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateStudentName10() {
		
		Student p;
		try {
			p = new Student(272);
			boolean check=p.updateStudentName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testUpdateStudentDept() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Student p=new Student(584);
		boolean check=p.updateStudentDept(d.getDepartmentID());
		assertTrue(check);
	}

	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateStudentDept2() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(100);
		Student p=new Student(592);
		boolean check=p.updateStudentDept(d.getDepartmentID());
	}

	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateStudentDept3() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Student p=new Student(592);
		boolean check=p.updateStudentDept(d.getDepartmentID());
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateStudentDept4() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(1);
		Student p=new Student(592);
		boolean check=p.updateStudentDept(d.getDepartmentID());
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateStudentDept5() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department(null);
		Student p=new Student(584);
		boolean check=p.updateStudentDept(d.getDepartmentID());
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateStudentDept6() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department("");
		Student p=new Student(592);
		boolean check=p.updateStudentDept(d.getDepartmentID());
	}
	
	@Test
	public void testUpdateStudentDept7() throws Department.DepartmentDoesNotExistException, Student.AccessDeniedException, People.PersonDoesNotExistException {
		
		Department d=new Department("Computer Science");
		Student p=new Student(584);
		boolean check=p.updateStudentDept(d.getDepartmentID());
		assertTrue(check);
	}
	
	
}
