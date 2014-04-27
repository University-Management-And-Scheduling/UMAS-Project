
/****************@author Simant Purohit*********************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;


public class DepartmentAdminUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Admin admin;
	private static Department adminDepartment;
	private static DepartmentAdminUI deptAdminUI;
	private static CourseScheduleUI courseScheduleUI;
	private static manageDeptCourses deptCoursesUI;
	private static OfferCoursesUI courseOfferedUI;
	private static WaitListMonitorDeptUI deptWaitList;
	private static ManageDeptPeople managePeople;
	private static AdminUseUI  adminDetailsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentAdminUI frame = DepartmentAdminUI.getInstance(new Admin(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static DepartmentAdminUI getInstance(Admin admin) throws Department.DepartmentDoesNotExistException{
		deptAdminUI = new DepartmentAdminUI(admin);
		
		if(admin == null || adminDepartment == null)
			return null;
		else
			return deptAdminUI;
	}

	private DepartmentAdminUI(Admin a) throws Department.DepartmentDoesNotExistException {
		DepartmentAdminUI.admin = a;
		DepartmentAdminUI.adminDepartment = new Department(admin.getDeptID());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 800);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//contentPane.add(tabbedPane, BorderLayout.CENTER);
		add(tabbedPane, BorderLayout.CENTER);
		
		courseScheduleUI = CourseScheduleUI.getInstance(admin);
		JPanel panel = courseScheduleUI;
		tabbedPane.addTab("Manage Schedule", null, panel, null);
		
		deptCoursesUI = manageDeptCourses.getInstance(admin);
		JTabbedPane tabbedPane_1 = deptCoursesUI;
		tabbedPane.addTab("Manage Courses", null, tabbedPane_1, null);
		
		courseOfferedUI = OfferCoursesUI.getInstance(admin);
		JPanel offerCourses = courseOfferedUI;
		tabbedPane_1.addTab("Offer Courses", offerCourses);
		
		deptWaitList = WaitListMonitorDeptUI.getInstance(admin);
		JPanel waitListPanel = deptWaitList;
		tabbedPane.addTab("Wait List Monitor", waitListPanel);
		
		managePeople = ManageDeptPeople.getInstance(admin);
		JPanel managePeoplePanel = managePeople;
		tabbedPane.addTab("Manage Dept People", managePeoplePanel);
		
		
		adminDetailsPanel = AdminUseUI.getInstance(admin);
		JPanel adminDetails = adminDetailsPanel;
		tabbedPane.addTab("Manage my details", adminDetails);
		
		JPanel logOut = new LogOutUI();
		tabbedPane.addTab("Log out", logOut);
		
//		Thread waitListThread = new Thread(new WaitListScan());
//		waitListThread.start();
	}

	public static synchronized void initializeAllTabs(){
		courseScheduleUI.initializeBackgroundData(adminDepartment);
		deptCoursesUI.initializeData();
		courseOfferedUI.initializeEveryThing();
		managePeople.initializeValues();
		deptWaitList.initializeWaitListMonitor();		
	}
}
