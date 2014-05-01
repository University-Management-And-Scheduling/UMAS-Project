package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class CourseCurveTest {

	
	@Test
	public void testCalculatePercentageCurve() {
		int offerID = 410;
		List<Integer> curvingCriteria = new ArrayList<Integer>();
		curvingCriteria.add(30);
		curvingCriteria.add(40);
		curvingCriteria.add(30);
				
		CourseCurve curve = CourseCurve.calculatePercentageCurve(offerID, curvingCriteria);
		
		HashMap<Student,String> courseCurve = curve.getCourseCurve();
		
		for(Student students:courseCurve.keySet()){
			int UIN = students.getUIN();
			String grade = courseCurve.get(students);
			
			System.out.println("UIN: " + UIN + " Grade: " + grade);
		}
	
		assertTrue(courseCurve.size() > 0);
	}

	@Test
	public void testCalculateAbsoluteCurve() {
		int offerID = 410;
		List<Integer> curvingCriteria = new ArrayList<Integer>();
		curvingCriteria.add(80);
		curvingCriteria.add(70);
		curvingCriteria.add(20);
				
		CourseCurve curve = CourseCurve.calculateAbsoluteCurve(offerID, curvingCriteria);
		
		HashMap<Student,String> courseCurve = curve.getCourseCurve();
		
		for(Student students:courseCurve.keySet()){
			int UIN = students.getUIN();
			String grade = courseCurve.get(students);
			
			System.out.println("UIN: " + UIN + " Grade: " + grade);
		}
		// assertNotNull(curve);
		assertTrue(courseCurve.size() > 0);
	}
 
	@Test
	public void testCalculateAbsoluteCurveFail() {
		int offerID = 410;
		List<Integer> curvingCriteria = new ArrayList<Integer>();
		curvingCriteria.add(80);
		curvingCriteria.add(70);
		curvingCriteria.add(40);
		
		CourseCurve curve = CourseCurve.calculateAbsoluteCurve(offerID, curvingCriteria);
		assertNull(curve.getCourseCurve());
	}
	
	@Test
	public void testCalculateMaxGapCurve() {
		int offerID = 410;
		List<Integer> curvingCriteria = new ArrayList<Integer>();
		curvingCriteria.add(2);
		curvingCriteria.add(1);
		curvingCriteria.add(1);
				
		CourseCurve curve = CourseCurve.calculateMaxGapCurve(offerID, curvingCriteria);
		
		HashMap<Student,String> courseCurve = curve.getCourseCurve();
		assertNotNull(curve);
	}

	
	@Test
	public void testUpdateGrades() {
		int offerID = 410;
		List<Integer> curvingCriteria = new ArrayList<Integer>();
		curvingCriteria.add(80);
		curvingCriteria.add(70);
		curvingCriteria.add(20);
		CourseOffered offered = null;
		try {
			offered = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		CourseCurve curve = CourseCurve.calculateAbsoluteCurve(offerID, curvingCriteria);
		HashMap<Student,String> courseCurve = curve.getCourseCurve();
		StudentEnrollment.updateAllStudentGrade(courseCurve, offered);
	}

}
