package search;

import DAL.Student_Database;
import DAL.RecLetter_Database;
import Members.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainMenu.MainMenuController;
import recommendation.RecommendationController;

public class SearchController {
	@FXML private TextField searchField;
	@FXML private Label promptLabel;
	@FXML private Button logoutButton;
	@FXML private Button resetButton;
	@FXML private Button recLButton;
	@FXML private Button newStudentButton;
	@FXML private Button findButton;
	@FXML private Button detailsButton;
	@FXML private Button opener;
	@FXML private ListView<String> studentList;
	private List<Student> students = new ArrayList<>();
	private Student chosenOne = new Student();
	
	private Student_Database sd = Student_Database.getStudent_database();
	private RecLetter_Database rd = RecLetter_Database.getRecLetter_database();
	
	
	/**
	 * Obtains the data entered into the search bar,
	 * queries the database and returns all the potential students matching the 
	 * search parameters and displays them
	 */
	@FXML public void search() {
		if(searchField.getText().trim().isEmpty() == false) {
			studentList.getItems().clear();
			students = sd.searchStudent(searchField.getText());
			ArrayList<String> names = new ArrayList<>();
			
			for(Student s: students) {
				names.add(s.getFirstName() + " " + s.getLastName() + ", " +s.getSemYear());
			}
			//System.out.println(students);
			studentList.getItems().addAll(names);
		}
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
	
	// Compares the list of possible students and locates the selected student
	private void chosenSetter() {
		String temp = studentList.getSelectionModel().getSelectedItem();
		for(Student s: students) {
			if(temp.contentEquals(s.getFirstName() + " " + s.getLastName() + ", " +s.getSemYear())) {
				chosenOne = s;
				break;
			}
		}
	}
	
	/*
	 * Ensures that the student variable isn't empty and then
	 * closes the current stage in order to transition over to the
	 * recommendation page whilst also deleting the current recommendation letter if the
	 * selected Student possesses one
	 */
	@FXML public void recLetter() {
		if(studentList.getSelectionModel().isEmpty()) {
			promptLabel.setText("Please select a student");
		}
		else {
			chosenSetter();
			if(rd.checkLetter(sd.getStudentID(chosenOne))) {
				rd.deleteLetter(sd.getStudentID(chosenOne));
			}
			Stage stage = (Stage)recLButton.getScene().getWindow();
			stage.close();
			recLStage();
		}

	}
	/*
	 * Ensures that the student variable isn't empty and then
	 * closes the current stage in order to transition over to the
	 * recommendation page for students who already possess a recommendation letter
	 * Otherwise we notify the user that the selected student doesn't have a letter saved 
	 */
	@FXML public void recLComfirm() {
		if(studentList.getSelectionModel().isEmpty()) {
			promptLabel.setText("Please select a student");
		}
		else {
			chosenSetter();
			if(rd.checkLetter(sd.getStudentID(chosenOne)) == false) {
				promptLabel.setText("Selected student currently does not have a letter");
			}
			else {
				Stage stage = (Stage)opener.getScene().getWindow();
				stage.close();
				recLStage();
			}
		}
	}
	
	/*
	 * Button functionality that redirects the user to a page where they could
	 * reset their password
	 */
	@FXML
	public void resetPassword() {
		Stage stage = (Stage)this.resetButton.getScene().getWindow();
		stage.close();
		resetPStage();
	}
	
	/**
	 * Closes the current window, redirecting the user to a blank Student form
	 */
	@FXML public void newStudent() {
		Stage stage = (Stage)this.newStudentButton.getScene().getWindow();
		stage.close();
		studentForm();
	}
	
	/**
	 * Checks that the student object isn't empty and if it isn't, closes
	 * the current window, redirecting the user to the Student form
	 */
	@FXML public void updateStudent() {
		if(studentList.getSelectionModel().isEmpty()) {
			promptLabel.setText("Please select a student");
			
		}
		else {
			chosenSetter();
			Stage stage = (Stage)detailsButton.getScene().getWindow();
			stage.close();
			updateStudentForm();
		}

	}
	
	/*
	 * Opens up the new stage for the Login page
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
	
	/**
	 * Opens up the new stage for a blank student form page
	 */
	private void studentForm() {
		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			
			MainMenuController mc = new MainMenuController();
			loader.setController(mc);
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
	/**
	 * Opens up the new stage for the student form page
	 * passing in the new student in the process in order to populate
	 * the form
	 */
	
	private void updateStudentForm() {
		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			
			MainMenuController mc = new MainMenuController(chosenOne);
			loader.setController(mc);
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
	
	/**
	 * Opens up the new stage for the recommendation page
	 * passing in the new student in the process in order to compile the 
	 * recommendation letter
	 */
	public void recLStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/recommendation/RecommendationFXML.fxml"));
			
			RecommendationController rc = new RecommendationController(chosenOne);
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
