
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JLabel;

public class StudentMarksUI extends JFrame {

	private JPanel contentPane;
	private JButton updateButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMarksUI frame = new StudentMarksUI(new CourseOffered(423), "Assgn2");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentMarksUI(CourseOffered offeredCourse, String examName) {
		
		
		HashMap<Integer,Double> studentExamMarks = CourseExams.getStudents(offeredCourse, examName);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		System.out.println("Hash set size:"+studentExamMarks.size());
		for(int UIN : studentExamMarks.keySet()){
			System.out.println("Student UIN:"+UIN);
			double marks = studentExamMarks.get(UIN);
			JPanel panel = makePanel(UIN,marks, offeredCourse, examName);
			contentPane.add(panel);
		}
		
	}
	
	// Makes a panel to display the UIN and marks of each student
	public JPanel makePanel(final int UIN, final double marks, final CourseOffered courseOffered, final String examName){
		JPanel studentmarks = new JPanel();
		studentmarks.setLayout(new GridLayout(1,3,0,0));
		
		JLabel lblUIN = new JLabel(""+UIN);
		studentmarks.add(lblUIN);
		
		final JTextField lblmarks = new JTextField(""+marks);
		studentmarks.add(lblmarks);
		
		updateButton = new JButton("Update score");
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Student, Double> marksMap = new HashMap<Student, Double>();
				Student s;
				try {
					s = new Student(UIN);
					double newMarks = Double.parseDouble(lblmarks.getText());
					marksMap.put(s,newMarks);
					CourseExams ce = new CourseExams(courseOffered.getOfferID(), examName, marksMap);
					boolean updateSuccess = ce.addStudentMarks();
					
					if(updateSuccess){
						JOptionPane.showMessageDialog(updateButton, "Successfully update");
					}
					
				} catch (People.PersonDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		studentmarks.add(updateButton);
		
		return studentmarks;
	}

}
