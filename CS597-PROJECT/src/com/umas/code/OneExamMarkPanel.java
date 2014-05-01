package com.umas.code;


import javax.swing.JPanel;
import javax.swing.JLabel;


public class OneExamMarkPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public OneExamMarkPanel(double examMarks) {
		JLabel ExamMarks = new JLabel(""+examMarks);
		add(ExamMarks);
		
	}
	
		
}
