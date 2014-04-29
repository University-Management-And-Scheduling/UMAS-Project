
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


public class CourseExamsUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newExamNameText;
	private JTextField newExamTotalMarksText;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JButton btnAddExam;
	private final JPanel gradeDisplayPanel;
	private final JPanel panel;
	private JPanel panel_2;
//	static private JButton btnCancel;
//	static private JPanel allExamsPanel;
	private JButton btnCancel;
	private JPanel allExamsPanel;
	private CourseOffered courseOffered;
	private static HashMap<JLabel,JTextField> studentExamMarksHashMap = new HashMap<JLabel, JTextField>();
	private static boolean isTA = false;
	public static boolean deleteFlag = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseExamsUI frame = new CourseExamsUI(new CourseOffered(423), false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	// Paints the UI for the CourseExams
	public CourseExamsUI(CourseOffered courseOffer, boolean taFlag) {
		courseOffered = courseOffer;
		isTA = taFlag;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		tabbedPane.addTab("View Course", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblCourseDetails = new JLabel("Course Details");
		lblCourseDetails.setBounds(184, 11, 92, 14);
		panel.add(lblCourseDetails);
		
		JLabel lblExamName = new JLabel("Exam Name ");
		lblExamName.setBounds(42, 40, 86, 14);
		panel.add(lblExamName);
		
		JLabel lblTotalMarks = new JLabel("Total Marks");
		lblTotalMarks.setBounds(194, 40, 76, 14);
		panel.add(lblTotalMarks);
		
		JLabel lblAddmodifyMarks = new JLabel("Add/Modify Marks");
		lblAddmodifyMarks.setBounds(376, 40, 112, 14);
		panel.add(lblAddmodifyMarks);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setBounds(562, 40, 46, 14);
		panel.add(lblDelete);
		
		btnAddExam = new JButton("Add Exam");
		btnAddExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonText = btnAddExam.getText();
				if(buttonText.equals("Add Exam")){
					newExamNameText.setEditable(true);
					newExamNameText.setEnabled(true);
					newExamTotalMarksText.setEditable(true);
					newExamTotalMarksText.setEnabled(true);
					btnAddExam.setText("Done");
					btnCancel.setVisible(true);
				} else {
					
					String mks = newExamTotalMarksText.getText();
					String newExamName = newExamNameText.getText();
					if((mks.matches("[0-9]{1,3}")) && !(newExamName.isEmpty())){
						int marks =  Integer.parseInt(newExamTotalMarksText.getText());
						CourseExamStructure newExamStruct = new CourseExamStructure(courseOffered,newExamName,marks);
						
						newExamStruct.addNewExam();
						
						newExamNameText.setText("");
						newExamTotalMarksText.setText("");
						newExamNameText.setEditable(false);
						newExamNameText.setEnabled(false);
						newExamTotalMarksText.setEditable(false);
						newExamTotalMarksText.setEnabled(false);
						btnAddExam.setText("Add Exam");
						panel.revalidate();
						panel.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect Data Entered","Error",JOptionPane.ERROR_MESSAGE);
					}
				
				initialize();
			}
		}
		});
		btnAddExam.setBounds(26, 422, 102, 23);
		if(isTA)
			btnAddExam.setEnabled(false);
		panel.add(btnAddExam);
		
		JLabel lblExamName_1 = new JLabel("Exam Name:");
		lblExamName_1.setBounds(156, 403, 73, 14);
		panel.add(lblExamName_1);
		
		newExamNameText = new JTextField();
		newExamNameText.setEnabled(false);
		newExamNameText.setEditable(false);
		newExamNameText.setBounds(239, 400, 92, 20);
		panel.add(newExamNameText);
		newExamNameText.setColumns(10);
		
		JLabel lblExamTotalMarks = new JLabel("Exam Total Marks");
		lblExamTotalMarks.setBounds(137, 445, 92, 14);
		panel.add(lblExamTotalMarks);
		
		newExamTotalMarksText = new JTextField();
		newExamTotalMarksText.setEnabled(false);
		newExamTotalMarksText.setEditable(false);
		newExamTotalMarksText.setBounds(245, 442, 86, 20);
		panel.add(newExamTotalMarksText);
		newExamTotalMarksText.setColumns(10);
		
		allExamsPanel = new JPanel();
		allExamsPanel.setBounds(26, 65, 800, 327);
		panel.add(allExamsPanel);
		allExamsPanel.setLayout(new BoxLayout(allExamsPanel, BoxLayout.Y_AXIS));
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newExamNameText.setText("");
				newExamTotalMarksText.setText("");
				newExamNameText.setEditable(false);
				newExamNameText.setEnabled(false);
				newExamTotalMarksText.setEditable(false);
				newExamTotalMarksText.setEnabled(false);
				btnAddExam.setText("Add Exam");
				initialize();
				panel.revalidate();
				panel.repaint();
				
			}
		});
		btnCancel.setBounds(347, 422, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblAddeditStudentMarks = new JLabel("Add/Edit student marks");
		lblAddeditStudentMarks.setBounds(689, 40, 126, 14);
		panel.add(lblAddeditStudentMarks);
		
		HashMap<String,Integer> examDetails = CourseExamStructure.viewExams(courseOffered);
		Set<String> keys = examDetails.keySet();
		Iterator<String> keyIterator = keys.iterator();
		
		for(String exam: examDetails.keySet()) {
			int examMarks = (int) examDetails.get(exam);
			CourseExamStructure examStruct = new CourseExamStructure(courseOffered, exam,examMarks) ;
			panel.add(makePanel(examStruct));
		}
		
		JPanel panel_3 = new JPanel();
		if(!isTA){
			tabbedPane.addTab("Calculate Curve", null, panel_3, null);
			panel_3.setLayout(null);
		}
		
		else{
			panel_3.setVisible(false);
			panel_3.setEnabled(false);
		}
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 11, 329, 112);
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JButton btnCalculateByPercentage = new JButton("Calculate By Percentage of Students in Each Grade");
		btnCalculateByPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Input comma seperated grading criteria\n example: 30,40,30 (total 100%)");
				String[] percentages = parseGradeCriteriaString(input);
				if(percentages!=null){
					List<Integer> percentArray = new ArrayList<Integer>();
					int total = 0;
					int count=0;
					//-------------processing string begins-----------//
					for(int i=0;i<percentages.length;i++){
						total+=Integer.parseInt(percentages[i]);
						if(total<=100){
							percentArray.add(Integer.parseInt(percentages[i]));
							count++;
						}
						
						else{
							total-=Integer.parseInt(percentages[i]);
							count--;
							break;
						}
					}
					
					if(count==percentages.length-1){
						if(total<100){
							percentArray.remove(count-1);
							percentArray.add(100-total);
							total = 100;
						}
					}
					
					if(total<100){
						percentArray.add(100-total);
					}
					
					//-----------------processing string ends------//
					
					CourseCurve curve = CourseCurve.calculatePercentageCurve(courseOffered.getOfferID(), percentArray);
					final HashMap<Student, String> curvedMarks = curve.getCourseCurve();
					gradeDisplayPanel.removeAll();
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
					for(Student s:curvedMarks.keySet()){
						String name = s.getName();
						String grade = curvedMarks.get(s);
						JPanel newPanel = makeGradePanel(s.getUIN(), grade);
						gradeDisplayPanel.add(newPanel);
					}
					
					JButton postbutton = new JButton("Post grades");
					postbutton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							StudentEnrollment.updateAllStudentGrade(curvedMarks, courseOffered);						
						}
					});
					
					gradeDisplayPanel.add(postbutton);
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
				}
				
			}
		});
		panel_4.add(btnCalculateByPercentage);
		
		JButton btnCalculateByAbsolute = new JButton("Calculate By Absolute Marks");
		btnCalculateByAbsolute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Input comma seperated numbers in decreasing order");
				String[] percentages = parseGradeCriteriaStringAbsolute(input);
				if(percentages== null)
					return;
				
				Set<Integer> tempSet = new LinkedHashSet<Integer>();
				for(String s:percentages){
					tempSet.add(Integer.parseInt(s));
				}
				
				List<Integer> percentArray = new ArrayList<Integer>();
				
				for(Integer i:tempSet){
					percentArray.add(i);
				}
				
				Collections.sort(percentArray);
				Collections.reverse(percentArray);
				
				
				if(percentages!=null){					
					CourseCurve curve = CourseCurve.calculateAbsoluteCurve(courseOffered.getOfferID(), percentArray);
					final HashMap<Student, String> curvedMarks = curve.getCourseCurve();
					gradeDisplayPanel.removeAll();
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
					for(Student s:curvedMarks.keySet()){
						String name = s.getName();
						String grade = curvedMarks.get(s);
						JPanel newPanel = makeGradePanel(s.getUIN(), grade);
						gradeDisplayPanel.add(newPanel);
					}
					
					JButton postbutton = new JButton("Post grades");
					postbutton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							StudentEnrollment.updateAllStudentGrade(curvedMarks, courseOffered);						
						}
					});
					
					gradeDisplayPanel.add(postbutton);
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
				}
			}
		});
		panel_4.add(btnCalculateByAbsolute);
		
		JButton btnCurveByMax = new JButton("Curve By Max Difference Between Marks");
		btnCurveByMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Input comma seperated numbers in decreasing order");
				String[] percentages = parseGradeCriteriaStringAbsolute(input);
				if(percentages== null)
					return;
				

				List<Integer> percentArray = new ArrayList<Integer>();
				for(String s:percentages){
					percentArray.add(Integer.parseInt(s));
				}
				
				
				if(percentages!=null){					
					CourseCurve curve = CourseCurve.calculateMaxGapCurve(courseOffered.getOfferID(), percentArray);
					final HashMap<Student, String> curvedMarks = curve.getCourseCurve();
					gradeDisplayPanel.removeAll();
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
					for(Student s:curvedMarks.keySet()){
						String name = s.getName();
						String grade = curvedMarks.get(s);
						JPanel newPanel = makeGradePanel(s.getUIN(), grade);
						gradeDisplayPanel.add(newPanel);
					}
					
					JButton postbutton = new JButton("Post grades");
					postbutton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							StudentEnrollment.updateAllStudentGrade(curvedMarks, courseOffered);						
						}
					});
					
					gradeDisplayPanel.add(postbutton);
					
					gradeDisplayPanel.revalidate();
					gradeDisplayPanel.repaint();
				}
			}
		});
		panel_4.add(btnCurveByMax);
		
		gradeDisplayPanel = new JPanel();
		gradeDisplayPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		gradeDisplayPanel.setBounds(349, 11, 480, 625);
		gradeDisplayPanel.setLayout(new GridLayout(20,1));
		panel_3.add(gradeDisplayPanel);
		initialize();
				
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(deleteFlag){
					initialize();
					System.out.println("Reinitializing");
					deleteFlag=false;
				}
				
			}
		}, 100, 1000);
	}
	
	// To parse and evalute the grading criteria input entered by the user
	public String[] parseGradeCriteriaString(String input){
		String[] percentages = null;
		if(input.matches("^[0-9]{1,3}(,[0-9]{1,3})*")){
			percentages = input.split(",");
		}
		
		return percentages;
	}
	
	// To parse and evalute a different type of grading criteria input entered by the user
	public String[] parseGradeCriteriaStringAbsolute(String input){
		String[] percentages = null;
		if(input.matches("^[0-9]{1,2}(,[0-9]{1,2})*")){
			percentages = input.split(",");
		}
		
		return percentages;
	}
	
	// Creates a panel for each exam
	public JPanel makePanel(final CourseExamStructure examStruc){
		String exam = examStruc.getExamName();
		int examMarks = examStruc.getExamTotal();
		System.out.println("Exam: " + exam);
		System.out.println("Exam Marks: " + examMarks);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2));
		JLabel ExamName = new JLabel();
		
		ExamName.setText(exam);
		p.add(ExamName);
		
		//p.setLayout(new FlowLayout(4, 3, 4));
		JLabel Marks = new JLabel();
		Marks.setText(""+examMarks);
		p.add(Marks);
		
		final JButton addMarks = new JButton("Add Marks");
		p.add(addMarks);
		
		return p;
	}

	// To initialize the page and paint it with the current values
	public void initialize(){
		int offerID = courseOffered.getOfferID();
		CourseExams exam = new CourseExams(offerID);
		ArrayList<String> allExams = exam.viewAllExams();
		allExamsPanel.removeAll();
		allExamsPanel.revalidate();
		allExamsPanel.repaint();
		
		for(String oneExamName: allExams){
			CourseExamStructure oneExam = new CourseExamStructure(courseOffered,oneExamName);
			allExamsPanel.add(new SingleExamPanel(oneExam,isTA));
		}
		
		allExamsPanel.revalidate();
		allExamsPanel.repaint();
		
		
		ArrayList<Student> allStudents = StudentEnrollment.getStudentsInCourse(courseOffered);
		
		
		// Add Exam Names as Titles for the columns
		int offerID2 = courseOffered.getOfferID();
		CourseExams courseExam =  new CourseExams(offerID2);
		ArrayList<String> exams = courseExam.viewAllExams();
		
		btnCancel.setVisible(false);
	}
	
	// Creates a panel to display grades of each student.
	public JPanel makeGradePanel(int UIN, String grade){
		
		JPanel singleGradePanel = new JPanel();
		singleGradePanel.setBounds(0, 0, 200, 50);
		
		JLabel uinLabel = new JLabel(""+UIN);
		singleGradePanel.add(uinLabel);
		
		JLabel gradeLabel = new JLabel(grade);
		singleGradePanel.add(gradeLabel);
		
		return singleGradePanel;		
	}
		
}

