
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class singleCoursePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Student student;
	private CourseOffered courseOffered;
	private JTextArea txtrGrade;
	private JTextArea txtrCourseDetails;
	private JButton btnDrop;
	private boolean isWaitList = false;
	private JButton btnSendMeCourse;
	
	public singleCoursePanel(Student s, CourseOffered co) {
		setLayout(null);
		this.student = s;
		this.courseOffered = co;
		
		txtrCourseDetails = new JTextArea();
		txtrCourseDetails.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtrCourseDetails.setBackground(Color.BLACK);
		txtrCourseDetails.setForeground(Color.WHITE);
		txtrCourseDetails.setEditable(false);
		txtrCourseDetails.setBounds(10, 37, 290, 252);
		txtrCourseDetails.setText("Course Details");
		add(txtrCourseDetails);
		
		txtrGrade = new JTextArea();
		txtrGrade.setColumns(1);
		txtrGrade.setForeground(Color.WHITE);
		txtrGrade.setCaretColor(Color.RED);
		txtrGrade.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		txtrGrade.setFont(new Font("Monospaced", Font.PLAIN, 70));
		txtrGrade.setBackground(Color.BLACK);
		txtrGrade.setEditable(false);
		txtrGrade.setBounds(310, 69, 130, 136);
		txtrGrade.setText("A");
		add(txtrGrade);
		
		btnSendMeCourse = new JButton("Send me course files");
		btnSendMeCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean sent = courseOffered.sendCourseFilesToStudent(student);
				if(sent){
					showMessage("Files sent to you successufully", "Sent");
				}
				
				else{
					showMessage("Unable to send files", "Not sent");
				}
			}
		});
		btnSendMeCourse.setBounds(310, 216, 130, 73);
		add(btnSendMeCourse);
		
		btnDrop = new JButton("Drop");
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isWaitList){
					boolean flag = WaitList.removeFromWaitListAndCommit(student, courseOffered.getOfferID());
					if(flag){
						showMessage("Removed from wait list", "Success");
						initialize();
						StudentUI.emptyTabs();
					}
					
					else{
						showMessage("Not wait list", "Failure");
					}
				}
				
				else{
					StudentEnrollment se = new StudentEnrollment(courseOffered.getOfferID(), student.getUIN());
					boolean unregistered = se.unregisterStudent();
					if(unregistered){
						showMessage("Course droppped successfully", "Success");
						initialize();
						StudentUI.emptyTabs();
					}
					
					else{
						showMessage("Unable to drop", "Failure");
					}
				}
			}
		});
		btnDrop.setBounds(310, 0, 130, 38);
		add(btnDrop);
		
		JLabel lblCourseDetails = new JLabel("Course Details");
		lblCourseDetails.setBounds(10, 12, 290, 14);
		add(lblCourseDetails);
		
		JLabel lblCurrentGrade = new JLabel("Current Grade");
		lblCurrentGrade.setBounds(310, 49, 130, 14);
		add(lblCurrentGrade);
		
		initialize();
	}
	
	public void initialize(){
		
		if(WaitList.isStudentOnWaitList(student, courseOffered.getOfferID())){
			txtrGrade.setText("N/A");
			isWaitList = true;
			btnSendMeCourse.setEnabled(false);
			String courseDetails = "Taught by Prof. "+courseOffered.getProfessorName();
			try {
				if(courseOffered.checkIfScheduled()){
					courseDetails+="\nClass location: "+courseOffered.getClassRoomLocation();
					courseDetails+="\nClassroom name: "+courseOffered.getClassRoomName();
					courseDetails+="\nTimings: "+courseOffered.getTiming();
				}
			} catch (CourseOffered.CourseOfferingNotCurrentException e) {
				e.printStackTrace();
			}
			
			txtrCourseDetails.setText(courseDetails);
		}
		
		else{
			HashMap<CourseOffered, String> grades = StudentEnrollment.getAllGradesOfStudent(student);
			txtrGrade.setText(grades.get(courseOffered));
			
			String courseDetails = "Taught by Prof. "+courseOffered.getProfessorName();
			try {
				if(courseOffered.checkIfScheduled()){
					courseDetails+="\nClass location: "+courseOffered.getClassRoomLocation();
					courseDetails+="\nClassroom name: "+courseOffered.getClassRoomName();
					courseDetails+="\nTimings: "+courseOffered.getTiming();
				}
			} catch (CourseOffered.CourseOfferingNotCurrentException e) {
				e.printStackTrace();
			}
			
			txtrCourseDetails.setText(courseDetails);
		}
		
		if(courseOffered.checkIfCurrent())
			btnDrop.setEnabled(true);
		else
			btnDrop.setEnabled(false);
			
		
		
		
	}

	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}