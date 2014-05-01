package com.umas.code;

import java.util.ArrayList;

/**
 * @author Akshay
 * 
 */

/*************** TALISTSCANNER.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class TAListScanner  implements Runnable{

	@Override
	public void run() {
		ArrayList<TA> getAllTAs= TA.getAllTAs();
		
		for(TA ta: getAllTAs){
			
			ArrayList<CourseOffered> getTACourses=CourseOffered.getAllCurrentCoursesTAedBy(ta);
			if(getTACourses.size()<=0){
				System.out.println("Dwongrading TA to student with UIN:"+ta.getUIN());
				DBAnnotation.annoate("uin", "teachingassistant", "TaUIN", true);
				int uin = ta.getUIN();
				TA.updateTAtoStudent(uin);
			}
		}
		// TODO Auto-generated method stub
		
	}

}
