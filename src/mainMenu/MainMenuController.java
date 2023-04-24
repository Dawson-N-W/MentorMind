package mainMenu;

import DAL.Professor_Database;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuController implements Initializable{

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

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
    
    
    private Professor_Database pd = Professor_Database.getProfessor_database();
    
    // Set of lists where the column data will be stored
    
    private List<String> programL = pd.queryTableColumns(1);
    
    private List<String> semesterL = pd.queryTableColumns(2);
    
    private List<String> courseL = pd.queryTableColumns(3);
    
    private List<String> pcL = pd.queryTableColumns(4);
    
    private List<String> acL = pd.queryTableColumns(5);
    
    private List<TextField> grades = new ArrayList<>();
    
    
    private ObservableList<String> coursesObservable;
    private ObservableList<String> pCharsObservable;
    private ObservableList<String> aCharsObservable;
    
    
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
	
	//DATE FORMAT FOR LATER
	// LocalDate theDate = year.getValue();
	//String formattedDate = theDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
	
	/*
	 * Checks that form is completed and contains valid input
	 */
	@FXML
	public void checker() {
		 LocalDate theDate = schoolDate.getValue();
		
		if(fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty()) {
			//
		}
		else if(schoolName.getText().trim().isEmpty() || year.getText().trim().isEmpty()) {
			
		}
		else if(theDate == null){
			//label
		}
		else if(genderCombo.getSelectionModel().isEmpty()) {
			//have a label print something out to the screen
		}
		else if(programs.getSelectionModel().isEmpty()) {
			//have the label print something
		}
		else if(semesters.getSelectionModel().isEmpty() || courses.getSelectionModel().isEmpty()){
			//label
		}
		else if(personalChars.getSelectionModel().isEmpty() || academicChars.getSelectionModel().isEmpty()) {
			//label
		}
		else {
			ObservableList<Integer> courseIndices = courses.getSelectionModel().getSelectedIndices();
			boolean lastCheck = true;
			for(int i = 0; i < courseIndices.size(); i++) {
				if(grades.get(courseIndices.get(i)).getText().trim().isEmpty()) {
					//label
					lastCheck = false;
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
		String gender = genderCombo.getValue();
		String firstN = fName.getText();
		String lastN = lName.getText();
		String schoolN = schoolName.getText();
		String yearsFinal = year.getText();
		String dateFinal = theDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
		String programFinal = programs.getSelectionModel().getSelectedItem();
		String semesterFinal = semesters.getSelectionModel().getSelectedItem();

		coursesObservable = courses.getSelectionModel().getSelectedItems();
		pCharsObservable = personalChars.getSelectionModel().getSelectedItems();
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

}

