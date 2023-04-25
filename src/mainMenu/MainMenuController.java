package mainMenu;

import DAL.Professor_Database;
import Members.Student;
import DAL.Student_Database;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recommendation.RecommendationController;

public class MainMenuController implements Initializable{

	@FXML private Label titleLabel;
	
    @FXML
    private TextField fName;

    @FXML
    private TextField lName;
    
    @FXML private Button searchButton;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private TextField schoolName;

    @FXML
    private DatePicker schoolDate;

    @FXML
    private TextField year;

    @FXML
    private ListView<String> programs;

    @FXML
    private ListView<String> semesters;

    @FXML
    private ListView<String> courses;

    @FXML
    private ListView<String> personalChars;

    @FXML
    private ListView<String> academicChars;
    
    @FXML
    private VBox textFs;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Button resetP;
    
    @FXML private Button compile;
    
    @FXML private Label errorLabel;
    
    
    private Professor_Database pd = Professor_Database.getProfessor_database();
    private Student_Database sd = Student_Database.getStudent_database();
    
    // Set of lists where the column data will be stored
    
    private List<String> programL = pd.queryTableColumns(1);
    
    private List<String> semesterL = pd.queryTableColumns(2);
    
    private List<String> courseL = pd.queryTableColumns(3);
    
    private List<String> pcL = pd.queryTableColumns(4);
    
    private List<String> acL = pd.queryTableColumns(5);
    
    private List<TextField> grades = new ArrayList<>();
    
    private ArrayList<String> finalGrades = new ArrayList<>();
    
    private Student finalStudent = new Student();
    
    private Student initialStudent = new Student();
    private boolean currStudent = false;
    
    public MainMenuController() {
    	
    }
    
    public MainMenuController(Student initialStudent) {
    	this.initialStudent = initialStudent;
    	currStudent = true;
    }
    
    /*
     * initializes all of the drop-down menus with the columns
     * of data from their respective tables
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] genders = {"Male","Female"};
		genderCombo.getItems().addAll(genders);
		
		programs.getItems().addAll(programL);
		
		
		semesters.getItems().addAll(semesterL);
		
		courses.getItems().addAll(courseL);
		courses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		for(int i = 0; i < courseL.size(); i++) {
			TextField grade = new TextField();
			grade.setPromptText("Student Grade");
			grades.add(grade);
			textFs.getChildren().add(grade);
		}
		
		personalChars.getItems().addAll(pcL);
		personalChars.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		academicChars.getItems().addAll(acL);
		academicChars.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		if(currStudent) {
			titleLabel.setText("Update Student Details");
			fName.setText(initialStudent.getFirstName());
			lName.setText(initialStudent.getLastName());
			genderCombo.getSelectionModel().select(initialStudent.getGender());
			schoolName.setText(initialStudent.getSchool());
			year.setText(String.valueOf(initialStudent.getSemYear()));
			programs.getSelectionModel().select(initialStudent.getProgram());
			semesters.getSelectionModel().select(initialStudent.getSemester());
			
			//LocalDate conversion
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
			String date = initialStudent.getDate();
			LocalDate localDate = LocalDate.parse(date, formatter);
			schoolDate.setValue(localDate);
			
			autoSelect(personalChars, pcL, initialStudent.getPersonalCharsList());
			autoSelect(academicChars, acL, initialStudent.getAcademicCharsList());
			
			//courses and respective grades
			List<List<String>> list = initialStudent.getCourseList();
			
			for(List<String> s : list) {
				int courseIndex = courseL.indexOf(s.get(0));
				if( courseIndex > -1) {
					courses.getSelectionModel().select(s.get(0));
					grades.get(courseIndex).setText(s.get(1));
				}
			}
			
		}
	}
	
	private void autoSelect(ListView<String> l, List<String> selection, List<String> list) {
		for(String s: selection) {
			if(list.contains(s)) {
				l.getSelectionModel().select(s);
			}
		}
	}
	
	@FXML public void searchPage() {
		Stage stage = (Stage)logoutButton.getScene().getWindow();
		stage.close();
		searchStage();
	}
	/*
	 * Button functionality that takes the user back to the login page
	 */
	@FXML
	public void logout() {
		Stage stage = (Stage)logoutButton.getScene().getWindow();
		stage.close();
		logoutStage();
	}
	
	
	/*
	 * Checks that form is completed and contains valid input
	 */
	@FXML
	public void checker() {
		 LocalDate theDate = schoolDate.getValue();
		
		if(fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty()) {
			//
			errorLabel.setText("First Name or Last Name has not been filled");
		}
		else if(schoolName.getText().trim().isEmpty() || year.getText().trim().isEmpty()) {
			errorLabel.setText("School Name or Year attended has been left blank");
		}
		else if(theDate == null){
			//label
			errorLabel.setText("Please enter in a Date");
		}
		else if(genderCombo.getSelectionModel().isEmpty()) {
			//have a label print something out to the screen
			errorLabel.setText("Please select a gender");
		}
		else if(programs.getSelectionModel().isEmpty()) {
			//have the label print something
			errorLabel.setText("Please select at least one program");
		}
		else if(semesters.getSelectionModel().isEmpty() || courses.getSelectionModel().isEmpty()){
			//label
			errorLabel.setText("Either the semester attended or courses has been left blank");
		}
		else if(personalChars.getSelectionModel().isEmpty() || academicChars.getSelectionModel().isEmpty()) {
			//label
			errorLabel.setText("Either Personal or Academic characteristics has been left blank");
		}
		else {
			ObservableList<Integer> courseIndices = courses.getSelectionModel().getSelectedIndices();
			boolean lastCheck = true;
			for(int i = 0; i < courseIndices.size(); i++) {
				if(grades.get(courseIndices.get(i)).getText().trim().isEmpty()) {
					//label
					errorLabel.setText("Course Grades have not been entered or do not match");
					lastCheck = false;
				}else {
					finalGrades.add(grades.get(courseIndices.get(i)).getText());
				}
			}
			if(lastCheck) {
				createStudent(theDate);
			}
			
		}
		
	}
	
	/*
	 * Creates student object and transitions to new recommendation letter
	 */
	
	private void createStudent(LocalDate theDate) {
		
		List<String> coursesFinal = courses.getSelectionModel().getSelectedItems();
		
		List<List<String>> courseList = new ArrayList<>();
		
		for(int i = 0; i < coursesFinal.size(); i++) {
			ArrayList<String> temp = new ArrayList<>();
			temp.add(coursesFinal.get(i));
			temp.add(finalGrades.get(i));
			courseList.add(temp);
		}
		

//		System.out.println(genderCombo.getValue());
//		System.out.println(fName.getText());
//		System.out.println(lName.getText());
//		System.out.println(schoolName.getText());
//		System.out.println(semesters.getSelectionModel().getSelectedItem());
//		System.out.println(Integer.parseInt(year.getText()));
//		System.out.println(theDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")));
//		System.out.println(personalChars.getSelectionModel().getSelectedItems());
//		System.out.println(academicChars.getSelectionModel().getSelectedItems());
//		System.out.println(courseList);
		
		finalStudent.setGender(genderCombo.getValue());
		finalStudent.setFirstName(fName.getText());
		finalStudent.setLastName(lName.getText());
		finalStudent.setSchool(schoolName.getText());
		finalStudent.setSemester(semesters.getSelectionModel().getSelectedItem());
		finalStudent.setSemYear(Integer.parseInt(year.getText()));
		finalStudent.setProgram(programs.getSelectionModel().getSelectedItem());
		finalStudent.setDate(theDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")));
		finalStudent.setPersonalCharsList(personalChars.getSelectionModel().getSelectedItems());
		finalStudent.setAcademicCharsList(academicChars.getSelectionModel().getSelectedItems());
		finalStudent.setCourseList(courseList);
		
		if(currStudent) {
			//finalStudent = initialStudent;
			sd.deleteStudent(sd.getStudentID(initialStudent));
		}

		sd.insert(finalStudent);
		
		Stage stage = (Stage)this.compile.getScene().getWindow();
		stage.close();
		recLStage();
		
		
//		String gender = genderCombo.getValue();
//		String firstN = fName.getText();
//		String lastN = lName.getText();
//		String schoolN = schoolName.getText();
//		String yearsFinal = year.getText();
//		String dateFinal = theDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
//		String programFinal = programs.getSelectionModel().getSelectedItem();
//		String semesterFinal = semesters.getSelectionModel().getSelectedItem();

		
	}
	
	/*
	 * Button functionality that redirects the user to a page where they could
	 * reset their password
	 */
	@FXML
	public void resetPassword() {
		Stage stage = (Stage)this.resetP.getScene().getWindow();
		stage.close();
		resetPStage();
	}
	
	/*
	 * Opens up the new stage for the login page
	 */
	public void logoutStage() {
		try {
			Stage signUpStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/login/InitialLoginFXML.fxml"));
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens up the new stage for the Reset Password page
	 */
	public void resetPStage() {

		try {
			Stage signUpStage = new Stage();
			//FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/resetPass/resetPassFXML.fxml"));
			
			//InitialLoginController initC = (InitialLoginController)loader.getController();
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setTitle("Reset Password");
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void searchStage() {
		try {
			Stage signUpStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/search/SearchFXML.fxml"));
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recLStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/recommendation/RecommendationFXML.fxml"));
			
			RecommendationController rc = new RecommendationController(finalStudent);
			loader.setController(rc);

			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

