
/****************@author Simant Purohit*********************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;



public class WaitListMonitorDeptUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane waitListStudents;
	private JList<String> waitListStudentsJList;
	private JScrollPane emailedStudents;
	private JList<String> emailedStudentsJList;
	private JComboBox<Integer> courseOfferSelectForWaitListCombo;
	private JTextPane courseOfferDetailsText;
	
	private static Admin admin;
	private static Department adminDepartment;
	private static WaitListMonitorDeptUI waitLListMonitor;
	
	public static WaitListMonitorDeptUI getInstance(Admin a){
		try {
			waitLListMonitor = new WaitListMonitorDeptUI(a);
			return waitLListMonitor;
		} catch (Department.DepartmentDoesNotExistException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	private WaitListMonitorDeptUI(Admin a) throws Department.DepartmentDoesNotExistException {
		admin = a;
		adminDepartment = new Department(admin.getDeptID());
		
		waitListStudents = new JScrollPane();
		waitListStudentsJList  = new JList<String>();
		waitListStudents.getViewport().setView(waitListStudentsJList);
		setLayout(null);
		setBounds(10, 10, 800, 600);
		waitListStudents.setBounds(10, 59, 181, 217);
		add(waitListStudents);
		
		emailedStudents = new JScrollPane();
		emailedStudentsJList = new JList<String>();
		emailedStudentsJList.setSelectedIndex(0);
		emailedStudents.getViewport().setView(emailedStudentsJList);
		emailedStudents.setBounds(570, 59, 181, 217);
		add(emailedStudents);
		
		courseOfferSelectForWaitListCombo = new JComboBox<Integer>();
		courseOfferSelectForWaitListCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int offerID = (Integer)courseOfferSelectForWaitListCombo.getSelectedItem();
				
				DefaultListModel<String> waitList = new DefaultListModel<String>();
				for(Student s:WaitList.getStudentsOnWaitList(offerID)){
					waitList.addElement(s.getName());
				}
				
				waitListStudentsJList.setModel(waitList);
				
				DefaultListModel<String> emailedList = new DefaultListModel<String>();
				for(Student s:WaitList.getStudentsOnEmailList(offerID)){
					emailedList.addElement(s.getName());
				}
				
				emailedStudentsJList.setModel(emailedList);
				
				//setting the text box for details of the coures
				try {
					CourseOffered co = new CourseOffered(offerID);
					String s = "";
					s+="Course Name:"+co.getCourseName();
					s+="\nClassroom Location:"+co.getClassRoomLocation();
					s+="\nClasstroom Name:"+co.getClassRoomName();
					s+="\nTimings:"+co.getTiming();
					s+="\nTaught by professor:"+co.getProfessorName();
					courseOfferDetailsText.setText(s);
					
				} catch (Course.CourseDoesNotExistException
						| CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		courseOfferSelectForWaitListCombo.setBounds(306, 59, 147, 20);
		add(courseOfferSelectForWaitListCombo);
		
		JLabel lblStudentsOnWait = new JLabel("Students on Wait List");
		lblStudentsOnWait.setBounds(10, 34, 181, 14);
		add(lblStudentsOnWait);
		
		JLabel lblStudentsEmailedTo = new JLabel("Students emailed to register");
		lblStudentsEmailedTo.setBounds(570, 34, 181, 14);
		add(lblStudentsEmailedTo);
		
		courseOfferDetailsText = new JTextPane();
		courseOfferDetailsText.setBounds(261, 99, 250, 177);
		add(courseOfferDetailsText);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(10, 313, 181, 131);
		add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(570, 313, 181, 131);
		add(textPane_2);
		
		JLabel lblSelectCourseOffering = new JLabel("Select course offering");
		lblSelectCourseOffering.setBounds(327, 34, 115, 14);
		add(lblSelectCourseOffering);
		initializeWaitListMonitor();
		Thread refreshWaitList = new Thread(new MonitorWaitList());
		refreshWaitList.start();
	}
	
	public synchronized void initializeWaitListMonitor(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		ArrayList<CourseSchedule> coursesOffered = CourseSchedule.getAllScheduledCourses(adminDepartment);
		
		for(CourseSchedule cs: coursesOffered){
			model.addElement(cs.getOfferID());
		}
		courseOfferSelectForWaitListCombo.setModel(model);
		
		if(model.getSize()>0){
			int offerID = model.getElementAt(0);
			
			DefaultListModel<String> waitList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnWaitList(offerID)){
				waitList.addElement(s.getName());
			}
			
			waitListStudentsJList.setModel(waitList);
			
			DefaultListModel<String> emailedList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnEmailList(offerID)){
				emailedList.addElement(s.getName());
			}
			
			emailedStudentsJList.setModel(emailedList);
		}
		
		
	}

	public synchronized void reInitializeWaitList(){
		if(courseOfferSelectForWaitListCombo.getModel().getSize()>0){
			int offerID = (Integer)courseOfferSelectForWaitListCombo.getSelectedItem();
			
			DefaultListModel<String> waitList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnWaitList(offerID)){
				waitList.addElement(s.getName());
			}
			
			waitListStudentsJList.setModel(waitList);
			
			DefaultListModel<String> emailedList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnEmailList(offerID)){
				emailedList.addElement(s.getName());
			}
			
			emailedStudentsJList.setModel(emailedList);
		}
	}
	
	private class MonitorWaitList implements Runnable{

		@Override
		public void run() {
			while(true){
				reInitializeWaitList();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
