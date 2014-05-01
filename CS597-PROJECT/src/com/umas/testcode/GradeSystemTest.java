package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class GradeSystemTest {

	// To insert New Grades when it is Not present
	
	@Test
	public void insertNewGrade() {
		String grade = "E";
		int gradeLevel = 8;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		studentGrade.deleteGrade();
		boolean gradeAdded = studentGrade.insertNewGrade();
		assertEquals(true,gradeAdded);
	}
	
	// To insert New Grades when it is already present
	@Test
	public void insertPresentGrade() {
		String grade = "A";
		int gradeLevel = 1;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		boolean gradeAdded = studentGrade.insertNewGrade();
		assertEquals(false,gradeAdded);
		
	}
	
	// To delete Grades when it is present
	@Test
	public void deleteGrade() {
		String grade = "F";
		int gradeLevel = 9;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		studentGrade.insertNewGrade();
		boolean gradeDeleted = studentGrade.deleteGrade();
		assertEquals(true,gradeDeleted);
		
	}
	
	// To delete Grades when it is Not present
	@Test
	public void deleteAbsentGrade() {
		String grade = "F";
		int gradeLevel = 9;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		boolean gradeDeleted = studentGrade.deleteGrade();
		assertEquals(false,gradeDeleted);
		
	}
	
	// To modify the grade
	@Test
	public void modifyGrade() {
		String grade = "E";
		int gradeLevel = 8;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		studentGrade.deleteGrade();
		studentGrade.insertNewGrade();
		System.out.println("--");
		boolean gradeModified = studentGrade.modifyGrade("E+");
		assertEquals(true,gradeModified);
		studentGrade = new GradeSystem("E+",gradeLevel);
		studentGrade.modifyGrade("E");
	}
	
	// To modify the level of the grade
	@Test
	public void modifyGradeLevel() {
		String grade = "E";
		int gradeLevel = 8;
		GradeSystem studentGrade = new GradeSystem(grade,gradeLevel);
		studentGrade.deleteGrade();
		studentGrade.insertNewGrade();
		System.out.println("--");
		boolean gradeLevelModified = studentGrade.modifyGradeLevel(9);
		assertEquals(true,gradeLevelModified);
		studentGrade = new GradeSystem(grade,9);
		studentGrade.modifyGradeLevel(8);
	}
	
	// To get a gradelevel for a grade when grade is present
	@Test
	public void getGradeLevelForGrade() {
		String grade = "D";
		GradeSystem studentGrade = new GradeSystem(grade);
		studentGrade = studentGrade.getGradeLevelForGrade();
		System.out.println("GradeLevel: "+studentGrade.getGradeLevel());
		System.out.println("--");
		
		assertEquals(7,studentGrade.getGradeLevel());
	}
	
	// To get a gradelevel for a grade when grade is absent
	@Test
	public void getGradeLevelForGradeAbsent() {
		String grade = "Z";
		GradeSystem studentGrade = new GradeSystem(grade);
		studentGrade = studentGrade.getGradeLevelForGrade();
		System.out.println("GradeLevel: "+studentGrade.getGradeLevel());
		System.out.println("--");
		assertEquals(0,studentGrade.getGradeLevel());
	}
	
	// To get a grade for a gradelevel when gradelevel is present
	@Test
	public void getGradeForGradeLevel() {
		int gradeLevel = 5;
		//GradeSystem studentGrade = new GradeSystem(grade);
		GradeSystem studentGrade = GradeSystem.getGradeForGradeLevel(gradeLevel);
		System.out.println("Grade: "+studentGrade.getGrade());
		System.out.println("--");
		assertEquals("B-",studentGrade.getGrade());
	}
	
	// To get a grade for a gradelevel when gradelevel is absent

	@Test
	public void getGradeForGradeLevelAbsent() {
		int gradeLevel = 10;
		//GradeSystem studentGrade = new GradeSystem(grade);
		GradeSystem studentGrade = GradeSystem.getGradeForGradeLevel(gradeLevel);
		assertNull(studentGrade);
	}
}
