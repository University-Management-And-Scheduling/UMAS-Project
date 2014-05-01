package com.umas.testcode;
import com.umas.code.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;





public class AdminTest {

	
	@Test
	public void testAddAdmin2() {
		
		Department d;
		try {
			d = new Department(100);
			boolean check=Admin.addAdmin("testing",d);
			assertFalse(check);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddAdmin3() {
		
		Department d;
		try {
			d = new Department(100);
			boolean check=Admin.addAdmin("",d);
			assertFalse(check);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddAdmin4() {
		
		Department d;
		try {
			d = new Department(100);
			boolean check=Admin.addAdmin(null,d);
			assertFalse(check);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddAdmin5() {
		
		Department d;
		try {
			d = new Department(1);
			boolean check=Admin.addAdmin(null,d);
			assertFalse(check);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAddAdmin6() {
		
		Department d;
		try {
			d = new Department(1);
			boolean check=Admin.addAdmin("",d);
			assertFalse(check);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Test
	public void testGetAllAdmin() {
		
		ArrayList<Admin> getAllAdminTest=Admin.getAllAdmin();
		assertNotNull(getAllAdminTest);
	}

	@Test
	public void testUpdateAdminUserName1() {
		
		Admin ad;
		try {
			ad = new Admin(581);
			boolean check=ad.updateAdminUserName("testing");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminUserName2() {
		
		Admin ad;
		try {
			ad = new Admin(581);
			boolean check=ad.updateAdminUserName("leona");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateAdminUserName3() throws People.PersonDoesNotExistException {
		
		Admin ad = new Admin(8);
		
		
	}
	
	@Test
	public void testUpdateAdminUserName4() {
		
		Admin ad;
		try {
			ad = new Admin(8);
			boolean check=ad.updateAdminUserName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminUserName5() {
		
		Admin ad;
		try {
			ad = new Admin(8);
			boolean check=ad.updateAdminUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateAdminUserName6() {
		
		Admin ad;
		try {
			ad = new Admin(2);
			boolean check=ad.updateAdminUserName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateAdminName1() {
		
		Admin ad;
		try {
			ad = new Admin(581);
			boolean check=ad.updateAdminName("testing");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminName2() {
		
		Admin ad;
		try {
			ad = new Admin(581);
			boolean check=ad.updateAdminName("leonard");
			assertTrue(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminName3() {
		
		Admin ad;
		try {
			ad = new Admin(8);
			boolean check=ad.updateAdminName("testing");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminName4() {
		
		Admin ad;
		try {
			ad = new Admin(8);
			boolean check=ad.updateAdminName("");
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testUpdateAdminName5() {
		
		Admin ad;
		try {
			ad = new Admin(8);
			boolean check=ad.updateAdminName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateAdminName6() {
		
		Admin ad;
		try {
			ad = new Admin(2);
			boolean check=ad.updateAdminName(null);
			assertFalse(check);
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testUpdateAdminDept1() throws People.PersonDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Admin ad=new Admin(581);
		Department d=new Department("Computer Science");
		boolean check=ad.updateAdminDept(d.getDepartmentID());
		assertTrue(check);
	}
	
	@Test
	public void testUpdateAdminDept2() throws People.PersonDoesNotExistException {
		
		Admin ad=new Admin(581);
		boolean check=ad.updateAdminDept(1);
		assertTrue(check);
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateAdminDept3() throws People.PersonDoesNotExistException {
		
		Admin ad=new Admin(8);
		boolean check=ad.updateAdminDept(2);
		assertFalse(check);
		
	}
	
	@Test(expected=People.PersonDoesNotExistException.class)
	public void testUpdateAdminDept4() throws People.PersonDoesNotExistException {
		
		Admin ad=new Admin(8);
		boolean check=ad.updateAdminDept(100);
		assertFalse(check);
		
	}
	
	@Test(expected=Department.DepartmentDoesNotExistException.class)
	public void testUpdateAdminDept5() throws People.PersonDoesNotExistException, Department.DepartmentDoesNotExistException {
		
		Admin ad=new Admin(581);
		Department d=new Department(100);
		boolean check=ad.updateAdminDept(d.getDepartmentID());
		
		
	}

}
