package com.umas.testcode;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


import com.mysql.jdbc.UpdatableResultSet;
import com.umas.code.People;
import com.umas.code.*;


public class TaTest {


	@Test
	public void testUpdateTaOfficeAddress1() {
		
		boolean check=TA.updateTaOfficeAddress(585, 410, "testing");
		assertTrue(check);
		
	}
	@Test
	public void testUpdateTaOfficeAddress2() {
		
		boolean check=TA.updateTaOfficeAddress(3, 300, "testing2");
		assertFalse(check);
		
	}
	@Test
	public void testUpdateTaOfficeAddress3() {
		
		
		boolean check=TA.updateTaOfficeAddress(6, 295, "testing3");
		assertFalse(check);
		
		
	}
	
	@Test
	public void testUpdateTaOfficeAddress4() {
		
		
		boolean check=TA.updateTaOfficeAddress(6, 295, "");
		assertFalse(check);
		
		
	}
	
	@Test
	public void testUpdateTaOfficeAddress5() {
		
		
		boolean check=TA.updateTaOfficeAddress(6, 295, null);
		assertFalse(check);
		
		
	}

	@Test
	public void testUpdateTaOfficeAddress6() {
		
		
		boolean check=TA.updateTaOfficeAddress(6, 400, null);
		assertFalse(check);
		
		
	}
	
	@Test
	public void testUpdateTaOfficeHours1() {
		
		boolean check=TA.updateTaOfficeHours(585, 410, "testing");
		assertTrue(check);
		
	}
	@Test
	public void testUpdateTaOfficeHours2() {
		
		boolean check=TA.updateTaOfficeHours(3, 300, "testing2");
		assertFalse(check);
		
	}
	@Test
	public void testUpdateTaOfficeHours3() {
		
		
		boolean check=TA.updateTaOfficeHours(6, 295, "testing3");
		assertFalse(check);
		
		
	}
	
	@Test
	public void testUpdateTaOfficeHours4() {
		
		
		boolean check=TA.updateTaOfficeHours(6, 295, "");
		assertFalse(check);
		
		
	}
	
	@Test
	public void testUpdateTaOfficeHours5() {
		
		
		boolean check=TA.updateTaOfficeHours(6, 295, null);
		assertFalse(check);
		
		
	}
	@Test
	public void testUpdateTaOfficeHours6() {
		
		boolean check=TA.updateTaOfficeHours(6, 400, null);
		assertFalse(check);
		
		
	}

	@Test
	public void testGetTAOfficeAddress1() {
		
		String check=TA.getTAOfficeAddress(585, 410);
		assertNotNull(check);
	}
	
	@Test
	public void testGetTAOfficeAddress2() {
		
		String check=TA.getTAOfficeAddress(5, 295);
		assertNull(check);
	}
	
	@Test
	public void testGetTAOfficeAddress3() {
		
		String check=TA.getTAOfficeAddress(3, 400);
		assertNull(check);
	}
	
	@Test
	public void testGetTAOfficeAddress4() {
		
		String check=TA.getTAOfficeAddress(6, 400);
		assertNull(check);
	}

	@Test
	public void testGetTAOfficeHours1() {
		
		String check=TA.getTAOfficeHours(585, 410);
		assertNotNull(check);
	}
	
	@Test
	public void testGetTAOfficeHours2() {
		
		String check=TA.getTAOfficeHours(5, 295);
		assertNull(check);
	}
	
	@Test
	public void testGetTAOfficeHours3() {
		
		String check=TA.getTAOfficeHours(3, 400);
		assertNull(check);
	}
	
	@Test
	public void testGetTAOfficeHours4() {
		
		String check=TA.getTAOfficeHours(6, 400);
		assertNull(check);
	}

	@Test
	public void testAddTAtoTAtable() {

		//the add function is not tested here. As it causes inconsistency to the database. 
	}

	@Test
	public void testAddTAtoTAtableCheck1() {
		
		boolean check=TA.addTAtoTAtableCheck(585, 410);
		assertTrue(check);
	}

	@Test
	public void testAddTAtoTAtableCheck2() {
		
		boolean check=TA.addTAtoTAtableCheck(6, 295);
		assertFalse(check);
	}
	
	@Test
	public void testAddTAtoTAtableCheck3() {
		
		boolean check=TA.addTAtoTAtableCheck(6, 400);
		assertFalse(check);
	}
	
	@Test
	public void testAddTAtoTAtableCheck4() {
		
		
		boolean check=TA.addTAtoTAtableCheck(3, 400);
		assertFalse(check);
	}
	
	
	@Test
	public void testGetAllTAs() {
	
		ArrayList<TA> testGetAllTAs=TA.getAllTAs();
		assertNotNull(testGetAllTAs);	
		
	}

	@Test
	public void testUpdateTAUserName1() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentUserName("test for TA");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAUserName2() {
	
		TA ta;
		try {
			ta = new TA(6);
			boolean check=ta.updateStudentUserName("test for TA");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAUserName3() {
	
		TA ta;
		try {
			ta = new TA(6);
			boolean check=ta.updateStudentUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAUserName4() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAUserName5() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Test
	public void testUpdateTAName1() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentName("test for TA");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAName2() {
	
		TA ta;
		try {
			ta = new TA(6);
			boolean check=ta.updateStudentName("test for TA");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAName3() {
	
		TA ta;
		try {
			ta = new TA(6);
			boolean check=ta.updateStudentName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAName4() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateTAName5() {
	
		TA ta;
		try {
			ta = new TA(3);
			boolean check=ta.updateStudentName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testUpdateTADept1() {
	
		try {
			TA ta=new TA(585);
			boolean check=ta.updateStudentDept(1);
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateTADept2() {
	
		try {
			TA ta=new TA(6);
			boolean check=ta.updateStudentDept(16);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateTADept3() {
	
		try {
			TA ta=new TA(3);
			boolean check=ta.updateStudentDept(100);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateTADept4() {
	
		try {
			TA ta=new TA(6);
			boolean check=ta.updateStudentDept(100);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateStudentToTA1() {
	
		boolean check=TA.updateStudentToTA(584);
		assertTrue(check);
	}

	
	@Test
	public void testUpdateTAtoStudent1() {
		
		boolean check=TA.updateTAtoStudent(584);
		assertTrue(check);

	}

}
