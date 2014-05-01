package com.umas.frontend;


/****************@author Simant Purohit*********************************/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.umas.code.Admin;
import com.umas.code.Department;
import com.umas.code.People;
import com.umas.code.Professor;
import com.umas.code.Semester;
import com.umas.code.Student;
import com.umas.code.TA;
import com.umas.code.TAListScanner;
import com.umas.code.WaitList;
import com.umas.code.Department.DepartmentDoesNotExistException;
import com.umas.code.People.PersonDoesNotExistException;
import com.umas.code.Student.AccessDeniedException;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class UMASAPP {

	private static JFrame frame;
	private static String userName;
	private static boolean loggedIn = false;
	private static int loggedInUIN= -1;
	private static int loggedInPersonPosition = -1;
	private static LoginUI loginui;
	private static JPanel panel;
	private static Semester semester = new Semester();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMASAPP window = new UMASAPP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UMASAPP() {
		frame = new JFrame();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UMAS Login");
		loginui = new LoginUI();
		JPanel loginPanel = loginui;
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		
		Thread taListMonitor = new Thread(new TAListScanner());
		taListMonitor.start();
		
//		Thread waitListMonitor = new Thread(new WaitListScan());
//		waitListMonitor.start();
//		waitListMonitor.setPriority(Thread.MIN_PRIORITY);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Starting a new scan");
				WaitList.scanWaitList();
				System.out.println("Scanning cycle complete");
				
			}
		}, 100, 10000);

	}
	
	public static void initializeLoggedInUser(String username, boolean isLoggedIn){
		UMASAPP.userName = username;
		UMASAPP.loggedIn = isLoggedIn;
		People loggedInPerson = new People(UMASAPP.userName);
		UMASAPP.loggedInUIN = loggedInPerson.getUIN();
		UMASAPP.loggedInPersonPosition = loggedInPerson.getPositionID();
		initializeNewUser(loggedInUIN, loggedInPersonPosition);		
	}
	
	private static void initializeNewUser(int UIN, int positionID){
		if(positionID == 1){
			JOptionPane.showMessageDialog(null, "Admin logged in");
			frame.remove(loginui);
			frame.revalidate();
			frame.repaint();
			try {
				Admin admin = new Admin(UIN);
				Department d = new Department(admin.getDeptID());
				panel = DepartmentAdminUI.getInstance(admin);
				frame.setBounds(50, 50, 1024, 600);
				frame.add(panel);
				frame.setTitle("UMAS "+d.getDepartmentName()+" Administrator: "+admin.getName()+",    "+semester.toString());
				frame.revalidate();
				frame.repaint();
				
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(positionID == 2){
			JOptionPane.showMessageDialog(null, "Professor logged in");
			frame.remove(loginui);
			frame.revalidate();
			frame.repaint();
			try {
				Professor prof = new Professor(UIN);
				Department d = new Department(prof.getDeptID());
				panel = ProfessorMainUI.getInstance(prof);
				frame.setBounds(50, 50, 1024, 600);
				frame.add(panel);
				frame.setTitle("UMAS "+d.getDepartmentName()+" Professor: "+prof.getName()+",    "+semester.toString());
				frame.revalidate();
				frame.repaint();
				
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Student.AccessDeniedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(positionID == 3){
			ArrayList<Student> students = Student.getAllStudents();
			for(Student s :students){
				Student.calculateGPA(s.getUIN());
			}
			JOptionPane.showMessageDialog(null, "Student logged in");
			frame.remove(loginui);
			frame.revalidate();
			frame.repaint();
			
			try {
				Student newStudent = new Student(UIN);
				Department d = new Department(newStudent.getDeptID());
				panel = StudentMainUI.getInstance(newStudent);
				frame.setBounds(50, 50, 1024, 600);
				frame.add(panel);
				frame.setTitle("UMAS "+d.getDepartmentName()+" Student: "+newStudent.getName()+",    "+semester.toString());
				frame.revalidate();
				frame.repaint();
				
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(positionID == 4){
			ArrayList<Student> students = Student.getAllStudents();
			for(Student s :students){
				Student.calculateGPA(s.getUIN());
			}
			
			ArrayList<TA> tas = TA.getAllTAs();
			for(TA ta: tas){
				TA.calculateGPA(ta.getUIN());
			}
			JOptionPane.showMessageDialog(null, "TA logged in");
			frame.remove(loginui);
			frame.revalidate();
			frame.repaint();
			try {
				TA ta = new TA(UIN);
				Department d = new Department(ta.getDeptID());
				panel = TaUI.getInstance(ta);
				frame.setBounds(50, 50, 1024, 600);
				frame.add(panel);
				frame.setTitle("UMAS "+d.getDepartmentName()+" TA: "+ta.getName()+",    "+semester.toString());
				frame.revalidate();
				frame.repaint();
				
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(positionID == 5){
			JOptionPane.showMessageDialog(null, "Super admin logged in");
			frame.remove(loginui);
			frame.revalidate();
			frame.repaint();						
			try {
				Admin admin = new Admin(UIN);
				Department d = new Department(admin.getDeptID());
				panel = AdminUI.getInstance(admin);
				frame.setBounds(50, 50, 1024, 600);
				frame.add(panel);
				frame.setTitle("UMAS SUPER ADMIN :"+admin.getName()+",    "+semester.toString());
				frame.revalidate();
				frame.repaint();
				
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void logOutUser(){
		frame.remove(panel);
		frame.revalidate();
		frame.repaint();
		panel = new LoginUI();
		frame.add(loginui);
		frame.setTitle("UMAS Login");
		frame.setBounds(100, 100, 500, 300);
		frame.revalidate();
		frame.repaint();
		
	}
}
