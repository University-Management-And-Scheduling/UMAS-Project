package com.umas.code;


/****************@author Simant Purohit*********************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;


public class ProfessorMainUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ProfessorUserUI profUser;
	private static Professor professor;
	private static ProfessorCourseUI profCourseUI;
	private static ProfessorMainUI professorMain;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorMainUI frame = new ProfessorMainUI(new Professor(289));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ProfessorMainUI getInstance(Professor p){
		professorMain = new ProfessorMainUI(p);
		return professorMain;
	}
	
	/**
	 * Create the frame.
	 */
	private ProfessorMainUI(Professor p) {
		professor = p;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		profUser = ProfessorUserUI.getInstance(professor);
		JPanel profUserPanel = profUser;
		tabbedPane.addTab("Manage my details",profUserPanel);
		
		profCourseUI = ProfessorCourseUI.getInstance(professor);
		JPanel profCoursePanel = profCourseUI;
		tabbedPane.addTab("Manage my courses",profCoursePanel);
		
		JPanel examPanel = new ProfessorExamPanel(professor);
		tabbedPane.addTab("Manage course exams", examPanel);
		
		JPanel logOut = new LogOutUI();
		tabbedPane.addTab("Log out", logOut);		
	}

}
