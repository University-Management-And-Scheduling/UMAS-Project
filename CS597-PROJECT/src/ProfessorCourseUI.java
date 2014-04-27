
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import java.awt.Color;

import javax.swing.JLabel;


/****************@author Simant Purohit*********************************/

public class ProfessorCourseUI extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private static Professor professor;
	final private JComboBox<Integer> courseOfferingCombo;
	private JList<String> existingFilesList;
	private final JList<String> newFilesList;
	private static HashMap<CourseOffered, ArrayList<File>> courseFiles = new HashMap<CourseOffered, ArrayList<File>>();
	private static LinkedHashSet<String> addFileList = new LinkedHashSet<String>();
	private JButton btnNewButton_3;
	private JSeparator separator;
	private JLabel lblCurrentCourseDocuments;
	private JLabel lblAddNewDocuments;
	private JSeparator separator_1;
	private JLabel lblSelectCourse;
	private static ProfessorCourseUI profCourseUI;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorCourseUI frame = new ProfessorCourseUI(new Professor(289));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static ProfessorCourseUI getInstance(Professor p){
		profCourseUI = new ProfessorCourseUI(p);
		return profCourseUI;
	}
	
	private ProfessorCourseUI(Professor p) {
		professor = p;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		courseOfferingCombo = new JComboBox<Integer>();
		courseOfferingCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int offerID = courseOfferingCombo.getItemAt(courseOfferingCombo.getSelectedIndex());
				addFileList.removeAll(addFileList);
				initializeAddFileList();
				try {
					CourseOffered co = new CourseOffered(offerID);
					initializeCurrentFilesList(co);
				} catch (Course.CourseDoesNotExistException
						| CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		courseOfferingCombo.setBounds(288, 11, 205, 20);
		panel.add(courseOfferingCombo);
		
		newFilesList = new JList<String>();
		newFilesList.setBounds(10, 71, 645, 91);
		newFilesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(newFilesList);
		
		existingFilesList = new JList<String>();
		existingFilesList.setBounds(10, 235, 754, 271);
		existingFilesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(existingFilesList);
		
		JButton btnaddNewFile = new JButton("Add New");
		btnaddNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int retVal = fc.showDialog(ProfessorCourseUI.this,
				        "Attach");
				if(retVal == JFileChooser.APPROVE_OPTION){
					java.io.File newFile = fc.getSelectedFile();
					System.out.println(newFile.getAbsolutePath());
					addFileList.add(newFile.getAbsolutePath());
					initializeAddFileList();						
				}
			}
		});
		btnaddNewFile.setBounds(665, 68, 99, 23);
		panel.add(btnaddNewFile);
		
		JButton btnNewButton_1 = new JButton("Delete file from course");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingFilesList.getModel().getSize()<=0 || courseOfferingCombo.getItemCount()<=0){
					//show a message
					return;
				}
				
				String file = existingFilesList.getSelectedValue();
				int index = file.lastIndexOf("\\");
				String path = file.substring(0, index);
				String fileName = file.substring(index+1);
				File f = new File(fileName, path, courseOfferingCombo.getItemAt(courseOfferingCombo.getSelectedIndex()));
				int offerID = courseOfferingCombo.getItemAt(courseOfferingCombo.getSelectedIndex());
				boolean deleteFlag = f.deleteFileFromDB();
				
				if(deleteFlag){					
					try {
						Path p = Paths.get(file);
						boolean deleted = Files.deleteIfExists(p);
						if(deleted){
							JOptionPane.showMessageDialog(null, "Successfully deleted file from course", null, JOptionPane.INFORMATION_MESSAGE);
						}
						
						initializeCurrentFilesList(new CourseOffered(offerID));
						
					} catch (Course.CourseDoesNotExistException
							| CourseOffered.CourseOfferingDoesNotExistException | IOException e1) {
						e1.printStackTrace();
						System.out.println(e1.getMessage());
					}
					
				}
			}
		});
		btnNewButton_1.setBounds(231, 517, 325, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add all to course");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = courseOfferingCombo.getSelectedIndex();
				if(courseOfferingCombo.getSelectedIndex()<0){
					JOptionPane.showMessageDialog(null, "Select a course first", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(newFilesList.getModel().getSize()<=0){
					JOptionPane.showMessageDialog(null, "Add a file first", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					CourseOffered co = new CourseOffered(courseOfferingCombo.getItemAt(courseOfferingCombo.getSelectedIndex()));
					int offerID = co.getOfferID();
					for(String s:addFileList){
						System.out.println("File:"+s);
						copyFile(new java.io.File(s), offerID, co.getCourseName());
					}
					

					JOptionPane.showMessageDialog(null, "Successfully added files to course", null, JOptionPane.INFORMATION_MESSAGE);
					addFileList.removeAll(addFileList);
					co= new CourseOffered(offerID);
					initializeCurrentFilesList(co);
					initializeCoursesAndFiles();
					courseOfferingCombo.setSelectedIndex(index);
					
				} catch (Course.CourseDoesNotExistException
						| CourseOffered.CourseOfferingDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(10, 173, 645, 20);
		panel.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Remove");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = newFilesList.getSelectedIndex();
				if(index<0){
					JOptionPane.showMessageDialog(null, "Not file selected", null, JOptionPane.ERROR_MESSAGE);
					return;
				}
				addFileList.remove(newFilesList.getSelectedValue());
				initializeAddFileList();
			}
		});
		btnNewButton_3.setBounds(665, 139, 99, 23);
		panel.add(btnNewButton_3);
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 204, 754, 2);
		panel.add(separator);
		
		lblCurrentCourseDocuments = new JLabel("Current course documents");
		lblCurrentCourseDocuments.setBounds(269, 210, 171, 14);
		panel.add(lblCurrentCourseDocuments);
		
		lblAddNewDocuments = new JLabel("Add new documents list");
		lblAddNewDocuments.setBounds(269, 46, 155, 14);
		panel.add(lblAddNewDocuments);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 35, 754, 2);
		panel.add(separator_1);
		
		lblSelectCourse = new JLabel("Select Course");
		lblSelectCourse.setBounds(197, 14, 81, 14);
		panel.add(lblSelectCourse);
		
		initializeCoursesAndFiles();
	}
	
	public void initializeCoursesAndFiles(){
		ArrayList<CourseOffered> courses = CourseOffered.getCurrentProfessorCourses(professor);
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		courseFiles.clear();
		
		for(CourseOffered co:courses){
			model.addElement(co.getOfferID());
			ArrayList<File> files = co.getFiles();
			courseFiles.put(co, files);
		}
		
		courseOfferingCombo.setModel(model);
		
		if(model.getSize()>0)
			courseOfferingCombo.setSelectedIndex(0);
		
	}
	
	public void initializeCurrentFilesList(CourseOffered co){
		ArrayList<File> files = co.getFiles();
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		for(File f:files){
			model.addElement(f.getFileLocation()+"\\"+f.getFileName());
		}
		
		existingFilesList.setModel(model);
		
		
	}

	public void initializeAddFileList(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String f:addFileList){
			model.addElement(f);
		}
		newFilesList.setModel(model);		
	}
	
	public void copyFile(java.io.File file, int offerID, String courseName){
		String dir = System.getProperty("user.dir");
		String fileLocation = dir+"/Files/"+courseName+"-"+offerID;
		String destDir = dir+"/Files/"+courseName+"-"+offerID+"/"+file.getName();
		System.out.println("Dest:"+destDir);
		System.out.println("File:"+file.getPath());
		
		try {
			boolean flag = File.addFileToDB(file.getName(), fileLocation, offerID);
			if(flag){
				Files.copy(file.toPath(), new java.io.File(destDir).toPath(), StandardCopyOption.REPLACE_EXISTING);
				Database.commitTransaction(Database.getConnection());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
