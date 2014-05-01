package com.umas.frontend;


/****************@author Simant Purohit*********************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import com.umas.code.Student;


public class StudentMainUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Student student;
	private static StudentUIDetails stdDetails;
	private static StudentUI studentCoursesUI;
	private static StudentMainUI studentMainUI;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMainUI frame = new StudentMainUI(new Student(451));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static StudentMainUI getInstance(Student s){
		studentMainUI = new StudentMainUI(s);		
		return studentMainUI;
	}
	
	private StudentMainUI(Student s) {
		student = s;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		stdDetails = StudentUIDetails.getInstance(student);
		JPanel detailsPanel = stdDetails;
		tabbedPane.addTab("Manage my details",detailsPanel);
		
		studentCoursesUI = StudentUI.getInstance(student);
		JPanel coursesPanel = studentCoursesUI;
		tabbedPane.addTab("Manage my courses", coursesPanel);
		
		JPanel logOutPanel = new LogOutUI();
		tabbedPane.addTab("Log Out", logOutPanel);
		
	}

}
