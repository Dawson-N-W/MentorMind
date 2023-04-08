package login;



import java.io.IOException;
import DAL.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InitialLoginController{

	@FXML private PasswordField pass;
	@FXML private Button submitButton;
	@FXML private Button resetP;
	@FXML private Label testLabel;
	
	Login_Database ld = Login_Database.getLogin_database();
	
	/*
	 * Redirects the user to a different page depending on the input.
	 */
	@FXML
	public void login(ActionEvent event) throws Exception {
		/*
		 * checks if the password table is empty, and if so 
		 * takes the user to the signUp page depending on the input
		 * 
		 */
		String user = pass.getText();
		if(ld.isEmpty()) {
			if(user.contentEquals("p")) {
				Stage stage = (Stage)submitButton.getScene().getWindow();
				stage.close();
				signupStage();
			}
		}
		
		/*
		 * Checks if password is within the database and either redirects 
		 * or prompts them for a different password
		 */
		else {
		try {
			if(ld.isLogin(user)) {
				Stage stage = (Stage)this.submitButton.getScene().getWindow();
				stage.close();
				mainMenu();
			}
			else {
				testLabel.setText("Wrong Password");
			}
			}catch(Exception localException) {
				System.out.println(localException);
			}
		}
	}
	
	/*
	 * Button functionality that redirects the user to a page where they could
	 * reset their password
	 */
	@FXML
	public void resetPass(ActionEvent event) {
		if(ld.isEmpty()) {
			testLabel.setText("Need to use Default Password!");
		}else {
			Stage stage = (Stage)this.resetP.getScene().getWindow();
			stage.close();
			loginStage();
		}
	}
	
	/*
	 * Opens up the new stage for the reset Password page
	 */
	public void loginStage() {

		try {
			Stage signUpStage = new Stage();
			//FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/resetPass/resetPassFXML.fxml"));
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setTitle("Sign Up");
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Opens up the new stage for the signUp page
	 */
	public void signupStage() {

		try {
			Stage signUpStage = new Stage();
			//FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/signUp/signUpFXML.fxml"));
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setTitle("Sign Up");
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Opens up the new stage for the Main Menu/ Student Forms
	 * and redirects the user to that page
	 */
	public void mainMenu() {
		try {
			Stage signUpStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			

			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			signUpStage.setTitle("Sign Up");
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
