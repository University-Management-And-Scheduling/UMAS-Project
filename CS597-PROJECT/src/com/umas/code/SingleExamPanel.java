package com.umas.code;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;


public class SingleExamPanel extends JPanel {
	private JTextField totalMarks;
	//private Professor professor;
	final CourseExamStructure courseExam;
	private JLabel lblExamname;
	private JButton btnModifyMarks;
	private JButton btnDeleteExam;
	private JButton btnAddStudentMarks;
	private final boolean isTA;
	
	/**
	 * Create the panel.
	 */
	public SingleExamPanel(CourseExamStructure exam, boolean TAFlag) {
		this.courseExam = exam;
		isTA = TAFlag;
		
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		//this.professor = prof;
		setBounds(new Rectangle(0, 0, 700, 50));
		setLayout(new GridLayout(0, 5, 0, 1));
		
		lblExamname = new JLabel("ExamName");
		add(lblExamname);
		
		totalMarks = new JTextField();
		totalMarks.setPreferredSize(new Dimension(20, 10));
		totalMarks.setBounds(new Rectangle(0, 0, 20, 20));
		totalMarks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		totalMarks.setDisabledTextColor(Color.BLACK);
		totalMarks.setEditable(false);
		totalMarks.setEnabled(false);
		add(totalMarks);
		totalMarks.setColumns(10);
		
		btnModifyMarks = new JButton("Modify Marks");
		btnModifyMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String currentMarks =  totalMarks.getText();
						
				String buttonText = btnModifyMarks.getText();
				if(buttonText.equals("Modify Marks")){
					totalMarks.setEditable(true);
					totalMarks.setEnabled(true);
					btnModifyMarks.setText("Done");
				} else {
					String value =  totalMarks.getText();
					if(value.matches("[0-9]{1,3}")){
						courseExam.setExamTotal(Integer.parseInt(value));
						courseExam.modifyExistingExamTotalMarks(Integer.parseInt(value));
						
						totalMarks.setEditable(false);
						totalMarks.setEnabled(false);
						btnModifyMarks.setText("Modify Marks");
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect Marks Entered","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
		});
		add(btnModifyMarks);
		
		btnDeleteExam = new JButton("Delete Exam");
		btnDeleteExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmation = JOptionPane.showInternalConfirmDialog(btnDeleteExam, "Are You Sure", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				System.out.println(confirmation);
				if (confirmation == 0){
					courseExam.deleteExistingExam();
					CourseExamsUI.deleteFlag = true;
					System.out.println("Delete successfull");
				} 
				
			}
		});
		if(isTA)
			btnDeleteExam.setEnabled(false);
		add(btnDeleteExam);
		
		btnAddStudentMarks = new JButton("Add Student Marks");
		btnAddStudentMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseOffered offeredCourse = courseExam.getOfferedCourse();
				String examName = courseExam.getExamName();
				JFrame frame = new StudentMarksUI(offeredCourse, examName);
				frame.setVisible(true);
			}
		});
		add(btnAddStudentMarks);
		initialize();
	}
	
	public void initialize(){
		lblExamname.setText(this.courseExam.getExamName());  
		int total = courseExam.getExamTotal();
		totalMarks.setText(""+total);
		
	}
	
	
	
}
