

import java.util.ArrayList;

public class TAListScanner  implements Runnable{

	@Override
	public void run() {
		ArrayList<TA> getAllTAs= TA.getAllTAs();
		
		for(TA ta: getAllTAs){
			
			ArrayList<CourseOffered> getTACourses=CourseOffered.getAllCurrentCoursesTAedBy(ta);
			if(getTACourses.size()<=0){
				System.out.println("Dwongrading TA to student with UIN:"+ta.getUIN());
				TA.updateTAtoStudent(ta.getUIN());
			}
		}
		// TODO Auto-generated method stub
		
	}

}
