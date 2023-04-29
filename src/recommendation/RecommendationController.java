package recommendation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAL.RecLetter_Database;
import DAL.Student_Database;
import Members.RecLetter;
import Members.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainMenu.MainMenuController;

public class RecommendationController implements Initializable{
	@FXML private TextArea recLetter;
	@FXML private Button logoutButton;
	@FXML private Button resetP;
	@FXML private Button saveButton;
	@FXML private Button deleteButton;
	@FXML private Button detailsButton;
	@FXML private Button searchButton;
	private Student recStudent;
	private Student_Database sd = Student_Database.getStudent_database();
	private RecLetter_Database rd = RecLetter_Database.getRecLetter_database();
	private RecLetter rc = new RecLetter();
	
	public RecommendationController() {
		
	}
	
	public RecommendationController(Student recStudent) {
		this.recStudent = recStudent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(rd.checkLetter(sd.getStudentID(recStudent)) == false) {
			rc.compile(recStudent);
			rd.addLetter(rc, sd.getStudentID(recStudent));
		}
		
		recLetter.setText(rd.getLetter(sd.getStudentID(recStudent)));
	}
	
	@FXML public void searchPage() {
		Stage stage = (Stage)searchButton.getScene().getWindow();
		stage.close();
		searchStage();
	}
	
	@FXML public void saveChanges() {
		rc.updateText(recLetter.getText());
		
		rd.replaceLetter(rc, sd.getStudentID(recStudent));
	}
	
	@FXML public void deleteStudent() {
		rd.deleteLetter(sd.getStudentID(recStudent));
		searchPage();
	}
	
	@FXML public void updateStudent() {
		Stage stage = (Stage)detailsButton.getScene().getWindow();
		stage.close();
		studentForm();
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
	 * Button functionality that redirects the user to a page where they could
	 * reset their password
	 */
	@FXML
	public void resetPassword() {
		Stage stage = (Stage)this.resetP.getScene().getWindow();
		stage.close();
		resetPStage();
	}
	
	private void studentForm() {
		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			
			MainMenuController mc = new MainMenuController(recStudent);
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
	
	private void logoutStage() {
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
	private void resetPStage() {

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
