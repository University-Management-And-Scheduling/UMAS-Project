package com.umas.code;


/****************@author Simant Purohit*********************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class CourseScheduleUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Admin admin= null;
	private static Department adminDepartment = null;
	private static CourseScheduleUI courseScheduleUI;
	
	//private JPanel contentPane;
	private JList<String> courseNameList;
	private JList<Integer> courseOfferIDList;
	private JList<String> professorNameList;
	private JList<String> classLocationList;
	private JList<String> classNameList;
	private JList<String> classTimingList;	
	
	//----------------BACKGROUND DATA------------//
	ArrayList<CourseOffered> allDeptCourseOfferings;
	private ArrayList<CourseSchedule> allDeptCoursesSchedule;
	private JComboBox<Integer> courseScheduledCombo;
	private JComboBox<String> timeSlotTypeCombo;
	private JComboBox<String> classLocationCombo;
	private JComboBox<String> classRoomCombo;
	private JComboBox<String> timingAvailableCombo;
	private JTextPane courseScheduleTextPane;
	private LinkedHashMap<Classroom, ArrayList<Timeslots>> classroomAndTimeslots;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseScheduleUI frame = getInstance(new Admin(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static CourseScheduleUI getInstance(Admin ad) throws Department.DepartmentDoesNotExistException{
		courseScheduleUI = new CourseScheduleUI(ad);
		
		if(admin == null && adminDepartment == null)
			return null;
		
		else
			return courseScheduleUI;
	}
	
	private CourseScheduleUI(Admin admin) throws Department.DepartmentDoesNotExistException {
		CourseScheduleUI.admin = admin;
		CourseScheduleUI.adminDepartment = new Department(admin.getDeptID());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 800);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		final JTabbedPane courseSchedule = new JTabbedPane(JTabbedPane.TOP);
		add(courseSchedule, BorderLayout.CENTER);
		
		JPanel currentScheduleTable = new JPanel();
		currentScheduleTable.setSize(HEIGHT, WIDTH);
		courseSchedule.addTab("Current Schedule", null, currentScheduleTable, null);
		courseSchedule.setEnabledAt(0, true);
		currentScheduleTable.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(2, 0, 125, 72);
		currentScheduleTable.add(lblCourseName);
		
		JLabel lblCourseOfferid = new JLabel("Course OfferID");
		lblCourseOfferid.setBounds(129, 0, 125, 72);
		currentScheduleTable.add(lblCourseOfferid);
		
		JLabel lblTaughtBy = new JLabel("Taught By");
		lblTaughtBy.setBounds(256, 0, 125, 72);
		currentScheduleTable.add(lblTaughtBy);
		
		JLabel lblClassLocation = new JLabel("Class location");
		lblClassLocation.setBounds(383, 0, 125, 72);
		currentScheduleTable.add(lblClassLocation);
		
		JLabel lblClassName = new JLabel("Class name");
		lblClassName.setBounds(510, 0, 125, 72);
		currentScheduleTable.add(lblClassName);
		
		JLabel lblTiming = new JLabel("Timing");
		lblTiming.setBounds(637, 0, 125, 72);
		currentScheduleTable.add(lblTiming);
		
		JScrollPane courseNameScrollPane = new JScrollPane();
		courseNameList  = new JList<String>();
		courseNameList.setSelectedIndex(0);
		courseNameList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = courseNameList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		courseNameScrollPane.getViewport().setView(courseNameList);
		courseNameScrollPane.setBounds(2, 74, 125, 211);
		currentScheduleTable.add(courseNameScrollPane);
		
		JScrollPane courseOfferIDScrollPane = new JScrollPane();
		courseOfferIDList = new JList<Integer>();
		courseOfferIDList.setSelectedIndex(0);
		courseOfferIDList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = courseOfferIDList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		courseOfferIDScrollPane.getViewport().setView(courseOfferIDList);
		courseOfferIDScrollPane.setBounds(129, 74, 125, 211);
		currentScheduleTable.add(courseOfferIDScrollPane);
		
		JScrollPane taughtByScrollPane = new JScrollPane();
		professorNameList = new JList<String>();
		professorNameList.setSelectedIndex(0);
		professorNameList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = professorNameList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		taughtByScrollPane.getViewport().setView(professorNameList);
		taughtByScrollPane.setBounds(256, 74, 125, 211);
		currentScheduleTable.add(taughtByScrollPane);
		
		JScrollPane classLocationScrollPane = new JScrollPane();
		classLocationList = new JList<String>();
		classLocationList.setSelectedIndex(0);
		classLocationList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = classLocationList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		classLocationScrollPane.getViewport().setView(classLocationList);
		classLocationScrollPane.setBounds(383, 74, 125, 211);
		currentScheduleTable.add(classLocationScrollPane);
		
		JScrollPane classNameScrollPane = new JScrollPane();
		classNameList = new JList<String>();
		classNameList.setSelectedIndex(0);
		classNameList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = classNameList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		classNameScrollPane.getViewport().setView(classNameList);
		classNameScrollPane.setBounds(510, 74, 125, 211);
		currentScheduleTable.add(classNameScrollPane);
		
		JScrollPane timingScrollPane = new JScrollPane();
		classTimingList = new JList<String>();
		classTimingList.setSelectedIndex(0);
		classTimingList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = classTimingList.getSelectedIndex();
				setSelectionForCurrentSchedule(index);
				
			}
		});
		timingScrollPane.getViewport().setView(classTimingList);
		timingScrollPane.setBounds(637, 74, 150, 211);
		currentScheduleTable.add(timingScrollPane);
		
		final JButton btnNewButton = new JButton("Reschedule all courses");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setText("Refreshing and rescheduling");
				JDialog jd = new JDialog();
				jd.setTitle("Rescheduling, please wait");
				jd.setBounds(getBounds());
				jd.setVisible(true);				
				courseSchedule.setEnabled(false);
				CourseSchedule.scheduleAllCurrentCourses(adminDepartment);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//initializeBackgroundData(adminDepartment);
				DepartmentAdminUI.initializeAllTabs();
				jd.setVisible(false);
				btnNewButton.setEnabled(true);
				btnNewButton.setText("Reschedule all courses");
				courseSchedule.setEnabled(true);
			}
		});
		btnNewButton.setBounds(214, 305, 356, 41);
		currentScheduleTable.add(btnNewButton);
		
		
		//-----------------Schedule individual course-----------------//
		JPanel rescheduleIndividual = new JPanel();
		courseSchedule.addTab("Reschedule Single Course", null, rescheduleIndividual, null);
		rescheduleIndividual.setLayout(null);
		
		courseScheduledCombo = new JComboBox<Integer>();		
		courseScheduledCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initilizeSingleRescheduleTab();
				classRoomCombo.setModel(new DefaultComboBoxModel<String>());
				timingAvailableCombo.setModel(new DefaultComboBoxModel<String>());
			}
		});
		courseScheduledCombo.setBounds(30, 38, 164, 20);
		rescheduleIndividual.add(courseScheduledCombo);
		
		timeSlotTypeCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"M-W-F", "T-Th"}));
		timeSlotTypeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initilizeSingleRescheduleTab();
				classRoomCombo.setModel(new DefaultComboBoxModel<String>());
				timingAvailableCombo.setModel(new DefaultComboBoxModel<String>());
			}
		});
		timeSlotTypeCombo.setBounds(466, 84, 164, 20);
		rescheduleIndividual.add(timeSlotTypeCombo);
		
		classLocationCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(ClassroomLocation.getAllLocations()));
		classLocationCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initilizeSingleRescheduleTab();
				classRoomCombo.setModel(new DefaultComboBoxModel<String>());
				timingAvailableCombo.setModel(new DefaultComboBoxModel<String>());
			}
		});
		classLocationCombo.setBounds(466, 128, 164, 20);
		rescheduleIndividual.add(classLocationCombo);
		
		classRoomCombo = new JComboBox<String>();
		classRoomCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = classRoomCombo.getSelectedIndex();
				if(index >= 0){
					int count = 0;
					for(Classroom c:classroomAndTimeslots.keySet()){
						Classroom newClassroom = c;
						if(count == index){
							initializeEmptyTimeSlotCombo(newClassroom);
							break;
						}
						else
							count++;
					}
				}
			}
		});
		classRoomCombo.setBounds(466, 213, 164, 20);
		rescheduleIndividual.add(classRoomCombo);
		
		timingAvailableCombo = new JComboBox<String>();
		timingAvailableCombo.setBounds(466, 256, 164, 20);
		rescheduleIndividual.add(timingAvailableCombo);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(318, 87, 46, 14);
		rescheduleIndividual.add(lblType);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(318, 131, 46, 14);
		rescheduleIndividual.add(lblLocation);
		
		JLabel lblClassroom = new JLabel("Classroom");
		lblClassroom.setBounds(318, 216, 65, 14);
		rescheduleIndividual.add(lblClassroom);
		
		JLabel lblTimingsAvailable = new JLabel("Timings Available");
		lblTimingsAvailable.setBounds(318, 259, 81, 14);
		rescheduleIndividual.add(lblTimingsAvailable);
		
		courseScheduleTextPane = new JTextPane();
		courseScheduleTextPane.setBounds(30, 87, 278, 186);
		rescheduleIndividual.add(courseScheduleTextPane);
		
		JButton btnSearchAvailable = new JButton("Search available");
		btnSearchAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int locationSelectIndex = (Integer)classLocationCombo.getSelectedIndex();
				int selectedCourseOfferIndex = (Integer)courseScheduledCombo.getSelectedIndex();
				
				if(locationSelectIndex>=0 && selectedCourseOfferIndex>=0){
					ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
					int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
					try {
						CourseOffered co = new CourseOffered((Integer)courseScheduledCombo.getSelectedItem());
						int cap = co.getTotalCapacity();
						initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
					} catch (Course.CourseDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else{
					showMessage("Please select a course first", "Error");
				}
				
			}
		});
		btnSearchAvailable.setBounds(466, 159, 164, 23);
		rescheduleIndividual.add(btnSearchAvailable);
		
		JButton btnUpdateSelectedCourse = new JButton("Update selected course with above selected timings");
		btnUpdateSelectedCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int locationSelectIndex = (Integer)classLocationCombo.getSelectedIndex();
				int selectedCourseOfferIndex = (Integer)courseScheduledCombo.getSelectedIndex();
				int classRoomSelectedIndex = classRoomCombo.getSelectedIndex();
				int timeSlotSelectedIndex = timingAvailableCombo.getSelectedIndex();
				
				if(locationSelectIndex >= 0 && selectedCourseOfferIndex >= 0 && classRoomSelectedIndex >= 0 && timeSlotSelectedIndex >= 0){
					int OfferID = (Integer)courseScheduledCombo.getSelectedItem();
					try {
						CourseOffered c = new CourseOffered(OfferID);
						Classroom newClassroom = null;
						Timeslots newTimeslot = null;
						int count = 0;
						classRoomSelectedIndex = classRoomCombo.getSelectedIndex();
						timeSlotSelectedIndex = timingAvailableCombo.getSelectedIndex();
						
						for(Classroom classroom:classroomAndTimeslots.keySet()){
							newClassroom = classroom;
							if(count == classRoomSelectedIndex){
								break;
							}
							else
								count++;
						}
						
						count = 0;
						for(Timeslots t : classroomAndTimeslots.get(newClassroom)){
							newTimeslot = t;
							if(count == timeSlotSelectedIndex){
								break;
							}
							else
								count++;
						}
						
						updateCourseSchedule(c, newClassroom, newTimeslot);
						
					} catch (Course.CourseDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if (selectedCourseOfferIndex<0){
					showMessage("Please selesct a course first", "Error");
					return;
				}
				
				else if(classRoomSelectedIndex < 0){
					showMessage("Select a classroom first", "Error");
					return;
				}
				
				else if(timeSlotSelectedIndex < 0){
					showMessage("Select a time slot first", "Error");
					return;
				}
				
				
			}
		});
		btnUpdateSelectedCourse.setBounds(318, 298, 441, 48);
		rescheduleIndividual.add(btnUpdateSelectedCourse);
		
		
		//------------initialization of data--------//
		initializeBackgroundData(CourseScheduleUI.adminDepartment);
	}
	
	
	public void initializeBackgroundData(Department department){
		allDeptCourseOfferings = department.getDepartmentCourseOffered();
		allDeptCoursesSchedule = CourseSchedule.getAllScheduledCourses(department);
		initializeJListsForCourseSchedule();
		initilizeSingleRescheduleTab();
	}
	
	private void initializeJListsForCourseSchedule(){
		String[] courseNameList = new String[allDeptCoursesSchedule.size()];
		Integer[] courseOfferIDList = new Integer[allDeptCoursesSchedule.size()];
		String[] profNameList = new String[allDeptCoursesSchedule.size()];
		String[] classLocationList = new String[allDeptCoursesSchedule.size()];
		String[] classRoomNameList = new String[allDeptCoursesSchedule.size()];
		String[] classTimingList = new String[allDeptCoursesSchedule.size()];
		
		for(int i=0;i<allDeptCoursesSchedule.size();i++){
			CourseSchedule cs;
			try {
				cs = allDeptCoursesSchedule.get(i);
				
				DBAnnotation.annoate("oID", "courseschedule", "OfferID", true);
				int oID = cs.getOfferID();
				CourseOffered co = new CourseOffered(oID);
				
				DBAnnotation.annoate("courseName", "courses", "CourseName", true);
				String courseName = co.getCourseName();
				courseNameList[i] = courseName;
				
				DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
				int offerID = co.getOfferID();
				courseOfferIDList[i] = offerID;
				
				DBAnnotation.annoate("prof", "people", "Name", true);
				String prof = co.getProfessorName();
				profNameList[i] = prof;
				
				DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
				String classLoc = co.getClassRoomLocation();
				classLocationList[i] = classLoc;
				
				DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
				String className = co.getClassRoomName();
				classRoomNameList[i] = className;
				
				classTimingList[i] = co.getTiming();
				
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Setting the Jlists
		this.courseNameList.setListData(courseNameList);
		this.courseOfferIDList.setListData(courseOfferIDList);
		this.professorNameList.setListData(profNameList);
		this.classLocationList.setListData(classLocationList);
		this.classNameList.setListData(classRoomNameList);
		this.classTimingList.setListData(classTimingList);
		
		//also initializing the offer select combo in the single reschedule tab
		this.courseScheduledCombo.setModel(new DefaultComboBoxModel<Integer>(courseOfferIDList));
		setSelectionForCurrentSchedule(0);
		
	}
	
	private void initilizeSingleRescheduleTab() {
		CourseOffered co;
		int index = (Integer)courseScheduledCombo.getSelectedIndex();
		if(index>=0){
			try {
				int offerID = (Integer)courseScheduledCombo.getSelectedItem();
				co = new CourseOffered(offerID);
				String s = "";
				
				DBAnnotation.annoate("courseName", "courses", "CourseName", true);
				String courseName = co.getCourseName();
				s+="Course Name:"+courseName;
				
				DBAnnotation.annoate("deptName", "department", "DepartmentName", true);
				String deptName = co.getDepartmentName();
				s+="\nDepartment:"+deptName;
				
				DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
				String classLoc = co.getClassRoomLocation();
				s+="\nClassroom Location:"+classLoc;
				
				DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
				String className = co.getClassRoomName();
				s+="\nClasstroom Name:"+ className;
				
				s+="\nTimings:"+co.getTiming();
				
				DBAnnotation.annoate("prof", "people", "Name", true);
				String prof = co.getProfessorName();
				s+="\nTaught by professor:"+prof;
				courseScheduleTextPane.setText(s);
				
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateCourseSchedule(CourseOffered coursOffered, Classroom classroom, Timeslots timeslot){
		try {
			CourseSchedule.updateCourseSchedule(coursOffered, classroom, timeslot);
			showMessage("Updated successfully", "Success");
			initializeBackgroundData(CourseScheduleUI.adminDepartment);
			initializeJListsForCourseSchedule();
			ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
			int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
			CourseOffered c = new CourseOffered((Integer)courseScheduledCombo.getSelectedItem());
			int cap = c.getTotalCapacity();
			initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
			
		} catch (CourseOffered.CourseOfferingNotCurrentException e) {
			showMessage("Unable to update", "Unable to update");
			e.printStackTrace();
		} catch (Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initializeEmptyClassAndTimeSlot(ClassroomLocation location, int timeSlotType, int courseCapacity){
		LinkedHashMap<Integer, Classroom> emptyClassRooms = Classroom.getAllEmptyClassroom(location, timeSlotType, courseCapacity);
		classroomAndTimeslots = new LinkedHashMap<Classroom, ArrayList<Timeslots>>();		
		String[] classRoomStrings = new String[emptyClassRooms.size()];
		int index=0;
		for(Integer i : emptyClassRooms.keySet()){
			Classroom cr = emptyClassRooms.get(i);
			ArrayList<Timeslots> ts = cr.findOpenSlotsForClassroom(timeSlotType);
			classroomAndTimeslots.put(cr, ts);
			classRoomStrings[index] = cr.getClassroomName().toString();			
			if(index == 0)
				initializeEmptyTimeSlotCombo(cr);
			index++;
		}
		
		classRoomCombo.setModel(new DefaultComboBoxModel<String>(classRoomStrings));
		if(classRoomCombo.getModel().getSize()>0)
			classRoomCombo.setSelectedIndex(0);
		
		if(timingAvailableCombo.getModel().getSize()>0)
			timingAvailableCombo.setSelectedIndex(0);
		
	}
	
	private void initializeEmptyTimeSlotCombo(Classroom c){
		ArrayList<Timeslots> ts = classroomAndTimeslots.get(c);
		String[] timeSlotsStrings = new String[ts.size()];
		for(int i=0;i<ts.size();i++){
			timeSlotsStrings[i] = ts.get(i).getStartHour()+ "00 to "+ts.get(i).getEndHour()+"00";
		}
		
		timingAvailableCombo.setModel(new DefaultComboBoxModel<String>(timeSlotsStrings));
	}
	
	private void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private void setSelectionForCurrentSchedule(int index){
		courseNameList.setSelectedIndex(index);
		courseOfferIDList.setSelectedIndex(index);
		professorNameList.setSelectedIndex(index);
		classLocationList.setSelectedIndex(index);
		classNameList.setSelectedIndex(index);
		classTimingList.setSelectedIndex(index);
	}

}
