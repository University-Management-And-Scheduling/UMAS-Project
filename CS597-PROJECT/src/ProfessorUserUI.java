
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import java.awt.Font;
import java.awt.Color;



public class ProfessorUserUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ProfessorUserUI profUserUI;
	JComboBox<Integer> getByJobID;
	LinkedHashMap<Integer,Job> retreiveJobs= new LinkedHashMap<Integer, Job>();	
	private JTextField profName;
	private JTextField profUIn;
	private JTextField profUserName;
	private JTextField profDept;
	private JTextField profPosition;
	
	static Professor prof;
	static Department dept;
	static int UIN;
	private JTextField newName;
	private JLabel presentName;
	private JTextField newUserName;
	private JLabel currentUserName;
	private JTextField currentPasswordTxt;
	private JTextField newPasswordTxt;
	private JLabel currentPasswordTxtLbl;
	private JButton changePassword;
	private JLabel newPasswordTxtLbl;
	private JLabel retGPA;
	private JLabel retWorkExp;
	private JCheckBox retSkill2;
	private JCheckBox retSkill3;
	private JCheckBox retSkill4;
	private JCheckBox retSkill5;
	private JCheckBox retSkill1;
	private JPanel jobDetailsPanel;
	private JLabel currentOfficeHours;
	private JTextField newOfficeHours;
	private JTextField newOfficeAddress;
	private JLabel CurrentOfficeAddress;
	private JTextField officeAddress;
	private JTextField officeHours;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorUserUI frame = new ProfessorUserUI(new Professor(3));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ProfessorUserUI getInstance(Professor p){
		try {
			profUserUI = new ProfessorUserUI(p);
		} catch (Department.DepartmentDoesNotExistException e) {
			
			e.printStackTrace();
		}
		
		return profUserUI;
	}
	
	
	private ProfessorUserUI(Professor p) throws Department.DepartmentDoesNotExistException {
		
		ProfessorUserUI.prof=p;
		ProfessorUserUI.dept=new Department(prof.getDeptID());
		ProfessorUserUI.UIN=prof.getUIN();
		setBounds(100, 100, 675, 473);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel viewDetails = new JPanel();
		tabbedPane.addTab("View Details", null, viewDetails, null);
		viewDetails.setLayout(null);
		
		profName = new JTextField();
		profName.setDisabledTextColor(Color.BLACK);
		profName.setFont(new Font("Arial", Font.BOLD, 11));
		profName.setEnabled(false);
		profName.setBounds(224, 57, 101, 20);
		viewDetails.add(profName);
		profName.setColumns(10);
		profName.setText(prof.getName());
		
		profUIn = new JTextField();
		profUIn.setDisabledTextColor(Color.BLACK);
		profUIn.setFont(new Font("Arial", Font.BOLD, 11));
		profUIn.setEnabled(false);
		profUIn.setBounds(224, 97, 101, 20);
		viewDetails.add(profUIn);
		profUIn.setColumns(10);
		profUIn.setText(""+prof.getUIN());
		
		profUserName = new JTextField();
		profUserName.setDisabledTextColor(Color.BLACK);
		profUserName.setFont(new Font("Arial", Font.BOLD, 11));
		profUserName.setEnabled(false);
		profUserName.setBounds(224, 140, 101, 20);
		viewDetails.add(profUserName);
		profUserName.setColumns(10);
		profUserName.setText(prof.getUserName());
		
		profDept = new JTextField();
		profDept.setDisabledTextColor(Color.BLACK);
		profDept.setFont(new Font("Arial", Font.BOLD, 11));
		profDept.setEnabled(false);
		profDept.setBounds(224, 180, 101, 20);
		viewDetails.add(profDept);
		profDept.setColumns(10);
		profDept.setText(dept.getDepartmentName());
		
		profPosition = new JTextField();
		profPosition.setDisabledTextColor(Color.BLACK);
		profPosition.setFont(new Font("Arial", Font.BOLD, 11));
		profPosition.setEnabled(false);
		profPosition.setBounds(224, 222, 101, 20);
		viewDetails.add(profPosition);
		profPosition.setColumns(10);
		profPosition.setText("Professor");
		
		JLabel profName_1 = new JLabel("Name");
		profName_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		profName_1.setBounds(155, 60, 32, 14);
		viewDetails.add(profName_1);
		
		JLabel profUINLbl = new JLabel("UIN");
		profUINLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		profUINLbl.setBounds(166, 100, 20, 14);
		viewDetails.add(profUINLbl);
		
		JLabel userName = new JLabel("User Name");
		userName.setFont(new Font("Tahoma", Font.BOLD, 11));
		userName.setBounds(126, 143, 61, 14);
		viewDetails.add(userName);
		
		JLabel deptOfProf = new JLabel("Department");
		deptOfProf.setFont(new Font("Tahoma", Font.BOLD, 11));
		deptOfProf.setBounds(118, 183, 69, 14);
		viewDetails.add(deptOfProf);
		
		JLabel positionProf = new JLabel("Position");
		positionProf.setFont(new Font("Tahoma", Font.BOLD, 11));
		positionProf.setBounds(143, 225, 46, 14);
		viewDetails.add(positionProf);
		
		JLabel officeAddressLbl = new JLabel("Office Address");
		officeAddressLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		officeAddressLbl.setBounds(108, 265, 81, 14);
		viewDetails.add(officeAddressLbl);
		
		officeAddress = new JTextField();
		officeAddress.setFont(new Font("Arial", Font.BOLD, 11));
		officeAddress.setDisabledTextColor(Color.BLACK);
		officeAddress.setEnabled(false);
		officeAddress.setBounds(224, 262, 101, 20);
		viewDetails.add(officeAddress);
		officeAddress.setColumns(10);
		officeAddress.setText(prof.getOfficeAddress());
		
	
		JLabel officeHoursLbl = new JLabel("Office Hours");
		officeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		officeHoursLbl.setBounds(118, 306, 71, 14);
		viewDetails.add(officeHoursLbl);
		
		officeHours = new JTextField();
		officeHours.setFont(new Font("Arial", Font.BOLD, 11));
		officeHours.setDisabledTextColor(Color.BLACK);
		officeHours.setEnabled(false);
		officeHours.setBounds(224, 303, 101, 20);
		viewDetails.add(officeHours);
		officeHours.setColumns(10);
		officeHours.setText(prof.getOfficeHours());
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Edit Details", null, panel_2, null);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JTabbedPane editDetailsPanel = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(editDetailsPanel);
		
		JPanel EditNamePanel = new JPanel();
		editDetailsPanel.addTab("Change Name", null, EditNamePanel, null);
		EditNamePanel.setLayout(null);
		
		JLabel currentNameLbl = new JLabel("Current Name");
		currentNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentNameLbl.setBounds(96, 90, 78, 14);
		EditNamePanel.add(currentNameLbl);
		
		presentName = new JLabel(prof.getName());
		presentName.setFont(new Font("Arial", Font.BOLD, 11));
		presentName.setBounds(202, 90, 96, 14);
		EditNamePanel.add(presentName);
		
		JLabel newNameLbl = new JLabel("New Name");
		newNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newNameLbl.setBounds(116, 134, 58, 14);
		EditNamePanel.add(newNameLbl);
		
		newName = new JTextField();
		newName.setBounds(202, 131, 96, 20);
		EditNamePanel.add(newName);
		newName.setColumns(10);
		
		JButton btnUpdateName = new JButton("Update Name");
		btnUpdateName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(newName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter new name ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				
				boolean check=prof.updateProfName(newName.getText());
				try {
					prof= new Professor(UIN);
					if(check)
					{
						JOptionPane.showMessageDialog(null, "Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeViewDetails(prof);
						newName.setText("");	
					}
					else{	
						JOptionPane.showMessageDialog(null, "Name Not Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Student.AccessDeniedException e) {
					JOptionPane.showMessageDialog(null, "Access denied");
					e.printStackTrace();
				} catch (People.PersonDoesNotExistException e) {
					JOptionPane.showMessageDialog(null, "This person does not exist in the database");
					e.printStackTrace();
				}
				
			}
		});
		btnUpdateName.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateName.setBounds(190, 212, 118, 23);
		EditNamePanel.add(btnUpdateName);
		
		JPanel edituserNamePanel = new JPanel();
		editDetailsPanel.addTab("Change User Name", null, edituserNamePanel, null);
		edituserNamePanel.setLayout(null);
		
		JLabel currentuserNameLbl = new JLabel("Current UserName");
		currentuserNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentuserNameLbl.setBounds(117, 92, 104, 14);
		edituserNamePanel.add(currentuserNameLbl);
		
		currentUserName = new JLabel(prof.getUserName());
		currentUserName.setFont(new Font("Arial", Font.BOLD, 11));
		currentUserName.setBounds(244, 92, 86, 14);
		edituserNamePanel.add(currentUserName);
		
		newUserName = new JTextField();
		newUserName.setBounds(244, 136, 86, 20);
		edituserNamePanel.add(newUserName);
		newUserName.setColumns(10);
		
		JLabel newUserNameLbl = new JLabel("Enter New Username");
		newUserNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newUserNameLbl.setBounds(117, 139, 117, 14);
		edituserNamePanel.add(newUserNameLbl);
		
		JButton btnNewButton = new JButton("Update User Name");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(newUserName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter new Username ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				
				boolean check=prof.updateProfUserName(newUserName.getText());
				try {
					prof= new Professor(UIN);
					if(check){
						JOptionPane.showMessageDialog(null, "User Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeViewDetails(prof);
						newUserName.setText("");
					}
					else{
					JOptionPane.showMessageDialog(null, "Username Not Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (Student.AccessDeniedException e1) {
					JOptionPane.showMessageDialog(null, "Access denied");
					e1.printStackTrace();
				} catch (People.PersonDoesNotExistException e2) {
					JOptionPane.showMessageDialog(null, "This person does not exist in the database");
					e2.printStackTrace();
				}
				
							}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(224, 212, 146, 23);
		edituserNamePanel.add(btnNewButton);
		
		JPanel editOfficeDetails = new JPanel();
		editDetailsPanel.addTab("Edit Office Details", null, editOfficeDetails, null);
		editOfficeDetails.setLayout(null);
		
		JLabel currentOfficeHoursLbl = new JLabel("Current Office Hours");
		currentOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeHoursLbl.setBounds(66, 61, 114, 14);
		editOfficeDetails.add(currentOfficeHoursLbl);
		
		currentOfficeHours = new JLabel(prof.getOfficeHours());
		currentOfficeHours.setFont(new Font("Arial", Font.BOLD, 11));
		currentOfficeHours.setBounds(214, 61, 127, 14);
		editOfficeDetails.add(currentOfficeHours);
		
		JLabel newOfficeHoursLbl = new JLabel("New Office Hours");
		newOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newOfficeHoursLbl.setBounds(86, 201, 94, 14);
		editOfficeDetails.add(newOfficeHoursLbl);
		
		newOfficeHours = new JTextField();
		newOfficeHours.setBounds(214, 198, 127, 20);
		editOfficeDetails.add(newOfficeHours);
		newOfficeHours.setColumns(10);
		
		JButton btnUpdateOfficeHours = new JButton("Update Office Details");
		btnUpdateOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(newOfficeHours.getText().equals("")||newOfficeAddress.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter both details ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				try{
					boolean check=Employee.updateEmpDetails(prof.getUIN(), newOfficeAddress.getText(), newOfficeHours.getText());
					prof= new Professor(UIN);
					if(check)
					{
						JOptionPane.showMessageDialog(null, "Employee Details Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeViewDetails(prof);
						newOfficeAddress.setText("");
						newOfficeHours.setText("");
					}
					else{	
						JOptionPane.showMessageDialog(null, "Employee Details Not Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Student.AccessDeniedException e1) {
					JOptionPane.showMessageDialog(null, "Access denied");
					e1.printStackTrace();
				} catch (People.PersonDoesNotExistException e2) {
					JOptionPane.showMessageDialog(null, "This person does not exist in the database");
					e2.printStackTrace();
				}
				
			}
		});
		btnUpdateOfficeHours.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateOfficeHours.setBounds(155, 269, 159, 23);
		editOfficeDetails.add(btnUpdateOfficeHours);
		
		JLabel lblNewOfficeAddress = new JLabel("New Office Address");
		lblNewOfficeAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewOfficeAddress.setBounds(73, 151, 107, 14);
		editOfficeDetails.add(lblNewOfficeAddress);
		
		newOfficeAddress = new JTextField();
		newOfficeAddress.setBounds(214, 148, 127, 20);
		editOfficeDetails.add(newOfficeAddress);
		newOfficeAddress.setColumns(10);
		
		JLabel currentOfficeAddressLbl = new JLabel("Current Office Address");
		currentOfficeAddressLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeAddressLbl.setBounds(53, 98, 127, 14);
		editOfficeDetails.add(currentOfficeAddressLbl);
		
		CurrentOfficeAddress = new JLabel(prof.getOfficeAddress());
		CurrentOfficeAddress.setFont(new Font("Arial", Font.BOLD, 11));
		CurrentOfficeAddress.setBounds(214, 98, 127, 14);
		editOfficeDetails.add(CurrentOfficeAddress);
		
		
		
		
		JPanel changePswdPanel = new JPanel();
		editDetailsPanel.addTab("Change Password", null, changePswdPanel, null);
		changePswdPanel.setLayout(null);
		
		currentPasswordTxt = new JPasswordField();
		currentPasswordTxt.setText("");
		currentPasswordTxt.setBounds(245, 86, 100, 20);
		changePswdPanel.add(currentPasswordTxt);
		currentPasswordTxt.setColumns(10);
		
		newPasswordTxt = new JPasswordField();
		newPasswordTxt.setBounds(245, 137, 100, 20);
		changePswdPanel.add(newPasswordTxt);
		newPasswordTxt.setColumns(10);
		newPasswordTxt.setEnabled(false);
		
		currentPasswordTxtLbl = new JLabel("Enter Current Password");
		currentPasswordTxtLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentPasswordTxtLbl.setBounds(85, 89, 133, 14);
		changePswdPanel.add(currentPasswordTxtLbl);
		
		newPasswordTxtLbl = new JLabel("Enter New Password");
		newPasswordTxtLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newPasswordTxtLbl.setBounds(105, 140, 113, 14);
		changePswdPanel.add(newPasswordTxtLbl);
		newPasswordTxtLbl.setEnabled(false);
		
		
		final JButton checkButton = new JButton("Check");
		checkButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(currentPasswordTxt.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter current Password", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Login loginDetails=new Login(prof.getUserName(), currentPasswordTxt.getText().toCharArray());
				boolean check=loginDetails.authenticate();
				System.out.println(check);
					if(check)
						
					{
						JOptionPane.showMessageDialog(null, "Password OK", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
						currentPasswordTxtLbl.setEnabled(false);
						currentPasswordTxt.setEnabled(false);
						currentPasswordTxt.setText("");
						changePassword.setEnabled(true);
						newPasswordTxtLbl.setEnabled(true);
						newPasswordTxt.setEnabled(true);
						checkButton.setEnabled(false);
						
					}
				
					else{
						JOptionPane.showMessageDialog(null, "Password NOT OK", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
					}
				
			}
		});
		checkButton.setBounds(378, 85, 89, 23);
		changePswdPanel.add(checkButton);
		
		changePassword = new JButton("Change Password");
		changePassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean ifChanged=Login.changePassword(prof.getUserName(),newPasswordTxt.getText());
				if(ifChanged){
					
					JOptionPane.showMessageDialog(null, "Password Updated", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
					
					currentPasswordTxt.setEnabled(true);
					newPasswordTxt.setText("");
					checkButton.setEnabled(true);
					changePassword.setEnabled(false);
					newPasswordTxtLbl.setEnabled(false);
					newPasswordTxt.setEnabled(false);
					
				
					}
				else{
					JOptionPane.showMessageDialog(null, "Password NOT Updated", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		changePassword.setBounds(226, 199, 140, 23);
		changePswdPanel.add(changePassword);
		changePassword.setEnabled(false);
		
		JPanel studentJobs = new JPanel();
		tabbedPane.addTab("Student Jobs", null, studentJobs, null);
		studentJobs.setLayout(new BorderLayout(0, 0));
		
		final JTabbedPane jobTabPane = new JTabbedPane(JTabbedPane.TOP);
		studentJobs.add(jobTabPane, BorderLayout.CENTER);
		
		final JPanel postJobPanel = new JPanel();
		jobTabPane.addTab("Post new Job", null, postJobPanel, null);
		postJobPanel.setLayout(null);
		
		final JComboBox<Double> workExp = new JComboBox<Double>();
		workExp.setBounds(254, 112, 88, 20);
		
		workExp.setModel(new DefaultComboBoxModel<Double>(new Double[] {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0}));
		
		postJobPanel.add(workExp);
		workExp.setSelectedIndex(0);
		
		JLabel lblWorkEx = new JLabel("Work Ex");
		lblWorkEx.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWorkEx.setBounds(163, 115, 54, 14);
		postJobPanel.add(lblWorkEx);
		
		final JComboBox<Double> reqdminimumGPA = new JComboBox<Double>();
		reqdminimumGPA.setBounds(254, 61, 88, 20);
		
		reqdminimumGPA.setModel(new DefaultComboBoxModel<Double>(new Double[] {2.5, 3.0, 3.5, 3.6, 3.7, 3.8, 4.0}));
		
		postJobPanel.add(reqdminimumGPA);
		reqdminimumGPA.setSelectedIndex(0);
		
		JLabel lblMinimumGpa = new JLabel("Minimum GPA");
		lblMinimumGpa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMinimumGpa.setBounds(133, 64, 84, 14);
		postJobPanel.add(lblMinimumGpa);
		
		final JCheckBox reqdSkill1 = new JCheckBox("Skill1");
		reqdSkill1.setBounds(274, 153, 97, 23);
		postJobPanel.add(reqdSkill1);
		
		final JCheckBox reqdSkill2 = new JCheckBox("Skill2");
		reqdSkill2.setBounds(274, 179, 97, 23);
		postJobPanel.add(reqdSkill2);
		
		final JCheckBox reqdSkill3 = new JCheckBox("Skill3");
		reqdSkill3.setBounds(274, 205, 97, 23);
		postJobPanel.add(reqdSkill3);
		
		final JCheckBox reqdSkill4 = new JCheckBox("Skill4");
		reqdSkill4.setBounds(274, 231, 97, 23);
		postJobPanel.add(reqdSkill4);
		
		final JCheckBox reqdSkill5 = new JCheckBox("Skill5");
		reqdSkill5.setBounds(274, 257, 97, 23);
		postJobPanel.add(reqdSkill5);
		
		JLabel selectReqdSkills = new JLabel("Please select the skills required for the job");
		selectReqdSkills.setFont(new Font("Tahoma", Font.BOLD, 11));
		selectReqdSkills.setBounds(10, 209, 246, 14);
		postJobPanel.add(selectReqdSkills);
		
		JButton postJob = new JButton("Post Job");
		postJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int check=Job.postJob(prof.getUIN(),dept.getDepartmentID(),(Double)reqdminimumGPA.getSelectedItem()
							,(Double) workExp.getSelectedItem(), reqdSkill1.isSelected(), reqdSkill2.isSelected(), reqdSkill3.isSelected(), 
							reqdSkill4.isSelected(), reqdSkill5.isSelected());
					if(check!=-1){
							JOptionPane.showMessageDialog(null, "Job posted ", "Job Posted", JOptionPane.INFORMATION_MESSAGE);
							initializeCurrentJobPostiongs();
							LinkedHashMap<Integer, Student> retreivedList=JobApplication.retreiveMatchingStudents((Double)reqdminimumGPA.getSelectedItem(), 
									(Double) workExp.getSelectedItem(), reqdSkill1.isSelected(), reqdSkill2.isSelected(), reqdSkill3.isSelected(), 
									reqdSkill4.isSelected(), reqdSkill5.isSelected());
							
							int returned=retreivedList.size();
							if(returned==0){
								JOptionPane.showMessageDialog(null, "No students match the requirements ", "Job Posted", JOptionPane.INFORMATION_MESSAGE);
							}
							
//							jobTabPane.remove(retreiveStudentsPanel);
//							jobTabPane.addTab("Retrieved Students",null,getRetrievedStudentsPanel(retreivedList),null);
							
							JFrame retrievedFrame = getRetrievedStudentsPanel(retreivedList,check);
							retrievedFrame.setBounds(10, 10, 800, 600);
							retrievedFrame.setVisible(true);
							prof=new Professor(prof.getUIN());
						}
			
					
					
				} catch (NumberFormatException  e) {
					
					JOptionPane.showMessageDialog(null, "Error in adding", "Job not Posted", JOptionPane.INFORMATION_MESSAGE);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Job.NoPermissionException e) {
					
					JOptionPane.showMessageDialog(null, "Error in adding", "Job not Posted", JOptionPane.INFORMATION_MESSAGE);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Student.AccessDeniedException e1) {
					JOptionPane.showMessageDialog(null, "Access denied");
					e1.printStackTrace();
				} catch (People.PersonDoesNotExistException e2) {
					JOptionPane.showMessageDialog(null, "This person does not exist in the database");
					e2.printStackTrace();
				}

			}
		});
		postJob.setBounds(234, 300, 137, 44);
		postJobPanel.add(postJob);		
		
		JLabel lblPleaseEnter = new JLabel("Please Enter the Minimum Requirements");
		lblPleaseEnter.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPleaseEnter.setBounds(10, 29, 240, 14);
		postJobPanel.add(lblPleaseEnter);
		
		JPanel rePostPanel = new JPanel();
		jobTabPane.addTab("Re post Job", null, rePostPanel, null);
		rePostPanel.setLayout(null);
		
		getByJobID = new JComboBox<Integer>();
		getByJobID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int jobId=getByJobID.getItemAt(getByJobID.getSelectedIndex());
				Job j=new Job(jobId);
				initializeJobDetails(j);
				jobDetailsPanel.setVisible(true);
				
				
				
			}
		});
		getByJobID.setBounds(182, 51, 129, 20);
		rePostPanel.add(getByJobID);
		
		JLabel lblRepostJobBy = new JLabel("Repost Job By Job ID");
		lblRepostJobBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRepostJobBy.setBounds(40, 54, 132, 14);
		rePostPanel.add(lblRepostJobBy);
		
		JButton reselectBtn = new JButton("Re-select Students");
		reselectBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		reselectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int jobID=(Integer)(getByJobID.getSelectedItem());
				
				Job job=retreiveJobs.get(jobID);
				
				LinkedHashMap<Integer, Student> reSelect=JobApplication.rePost(job.getReqdMinimumGPA(), job.getReqdMinimumWorkExperience(), 
																job.isSkillset1(), job.isSkillset2(), job.isSkillset3(),job.isSkillset4(),job.isSkillset5(),job);
				
//				jobTabPane.addTab("Re Select",null,getRetrievedStudentsPanel(reSelect, getByJobID.getItemAt(getByJobID.getSelectedIndex())),null);
				JFrame retrievedFrame = getRetrievedStudentsPanel(reSelect,jobID);
				retrievedFrame.setBounds(10, 10, 800, 600);
				retrievedFrame.setVisible(true);
				//jobPost=new Job(jobID);
				initializeCurrentJobPostiongs();
				initializeViewDetails(prof);
				
			}
		
		
		
		
		});
		
		reselectBtn.setBounds(417, 121, 141, 75);
		rePostPanel.add(reselectBtn);
		
		jobDetailsPanel = new JPanel();
		jobDetailsPanel.setBounds(29, 82, 282, 250);
		rePostPanel.add(jobDetailsPanel);
		jobDetailsPanel.setLayout(null);
		jobDetailsPanel.setVisible(false);
		
		JLabel lblRequiredWorkExperience = new JLabel("Required Work Experience");
		lblRequiredWorkExperience.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRequiredWorkExperience.setBounds(0, 21, 151, 14);
		jobDetailsPanel.add(lblRequiredWorkExperience);
		
		JLabel lblRequiredGpa = new JLabel("Required GPA");
		lblRequiredGpa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRequiredGpa.setBounds(68, 46, 83, 14);
		jobDetailsPanel.add(lblRequiredGpa);
		
		retSkill1 = new JCheckBox("Skill 1");
		retSkill1.setBounds(154, 66, 97, 23);
		jobDetailsPanel.add(retSkill1);
		
		retSkill2 = new JCheckBox("Skill 2");
		retSkill2.setBounds(154, 95, 97, 23);
		jobDetailsPanel.add(retSkill2);
		
		retSkill3 = new JCheckBox("Skill 3");
		retSkill3.setBounds(154, 124, 97, 23);
		jobDetailsPanel.add(retSkill3);
		
		retSkill4 = new JCheckBox("Skill 4");
		retSkill4.setBounds(154, 151, 97, 23);
		jobDetailsPanel.add(retSkill4);
		
		retSkill5 = new JCheckBox("Skill 5");
		retSkill5.setBounds(154, 177, 97, 23);
		jobDetailsPanel.add(retSkill5);
		
		JLabel lblRequiredSetOf = new JLabel("Required set of Skills");
		lblRequiredSetOf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRequiredSetOf.setBounds(18, 124, 118, 14);
		jobDetailsPanel.add(lblRequiredSetOf);
		
		retWorkExp = new JLabel("aa");
		retWorkExp.setFont(new Font("Arial", Font.PLAIN, 11));
		retWorkExp.setBounds(161, 21, 46, 14);
		jobDetailsPanel.add(retWorkExp);
		
		retGPA = new JLabel("aa");
		retGPA.setFont(new Font("Arial", Font.PLAIN, 11));
		retGPA.setBounds(161, 46, 46, 14);
		jobDetailsPanel.add(retGPA);
		
		initializeCurrentJobPostiongs();
		
	
	
	}

	
	
	
	
//	public JPanel getRetrievedStudentsPanel(LinkedHashMap<Integer, Student> getStudents){
//		JPanel panel = new JPanel();
//		panel.setLayout(new GridLayout(10, 1, 0, 3));
//		
//		for(Integer i: getStudents.keySet()){
//			Student s= getStudents.get(i);
//			panel.add(makePanel(s));
//		}
//		
//		return panel;
//	}
//	
	
	public JFrame getRetrievedStudentsPanel(LinkedHashMap<Integer, Student> getStudents, int jodID){
		JFrame jFramePop = new JFrame();
		jFramePop.getContentPane().setLayout(new GridLayout(10, 1, 0, 3));
		
		for(Integer i: getStudents.keySet()){
			Student s= getStudents.get(i);
			jFramePop.getContentPane().add(makePanel(s, jodID));
		}
		
		return jFramePop;
	}
	

	public JPanel makePanel(final Student student, final int jobID){
		
		//LinkedHashMap<Integer, Student> retreive1= new LinkedHashMap<Integer, Student>();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		JLabel t = new JLabel();
		//Object key = retreive.keySet().iterator().next();
		
		t.setText("The name is: "+student.getName() );
		p.add(t);
		final JButton sendEmail = new JButton("Send Email to "+student.getName());
		p.add(sendEmail);
		
		
		sendEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				// TODO Auto-generated method stub
				
				boolean isSent=Job.sendEmail(jobID,student);
				
				if(isSent){
					sendEmail.setEnabled(false);
				}
				
			}
		});
		
		
		
		return p;
	}


	public void initializeCurrentJobPostiongs(){
		
		retreiveJobs=Job.getAllJobsBySingleProfessor(prof);
		
		ArrayList<Integer> jobIDs=new ArrayList<Integer>();
		
		DefaultComboBoxModel<Integer> model=new DefaultComboBoxModel<Integer>();
		
		for(Integer i: retreiveJobs.keySet()){
			jobIDs.add(i);
			model.addElement(i);
			
		}
		
		getByJobID.setModel(model);
	}
	

	public void initializeViewDetails(Professor prof){
		
		profName.setText(prof.getName());
		profUIn.setText(""+prof.getUIN());
		profUserName.setText(prof.getUserName());
		profDept.setText(dept.getDepartmentName());
		presentName.setText(prof.getName());
		currentUserName.setText(prof.getUserName());
		currentOfficeHours.setText(prof.getOfficeHours());
		CurrentOfficeAddress.setText(prof.getOfficeAddress());
		
		

		
	}
	
	
	public void initializeJobDetails(Job jobPost){
		
		retGPA.setText(""+jobPost.getReqdMinimumGPA());
		retWorkExp.setText(""+jobPost.getReqdMinimumWorkExperience());
		retSkill1.setSelected(jobPost.isSkillset1());
		retSkill2.setSelected(jobPost.isSkillset2());
		retSkill3.setSelected(jobPost.isSkillset3());
		retSkill4.setSelected(jobPost.isSkillset4());
		retSkill5.setSelected(jobPost.isSkillset5());
		
		

		
	}
}
