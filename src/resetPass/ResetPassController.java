package resetPass;

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
//import login.InitialLoginController;

public class ResetPassController {
	@FXML private PasswordField oldP;
	@FXML private PasswordField newP;
	@FXML private PasswordField confirmP;
	@FXML private Button submitButton;
	@FXML private Button logoutButton;
	@FXML private Label testLabel;
	
	DAL.Login_Database ld = DAL.Login_Database.getLogin_database();
	
	@FXML
	public void backToLogin(ActionEvent event) {
		Stage stage = (Stage)logoutButton.getScene().getWindow();
		stage.close();
		loginStage();
	}
	
	@FXML
	public void submit() {
		try {
			if(ld.isLogin(oldP.getText()) == false) {
				testLabel.setText("Old Password is incorrect");
			}
			else if(newP.getText().trim().isEmpty() || confirmP.getText().trim().isEmpty() ||newP.getText().contentEquals(confirmP.getText()) == false) {
				testLabel.setText("Passwords are empty or don't match");
			}
			else if(newP.getText().contentEquals("p") && confirmP.getText().contentEquals("p")){
				testLabel.setText("Can't set password to default password");
			}
			else {
				ld.updatePassword(newP.getText(), oldP.getText());
				Stage stage = (Stage)logoutButton.getScene().getWindow();
				stage.close();
				
				loginStage();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loginStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/login/InitialLoginFXML.fxml"));
			
			//InitialLoginController initC = (InitialLoginController)loader.getController();
			
			Scene scene = new Scene(root);
			signUpStage.setScene(scene);
			//signUpStage.setTitle("Sign Up");
			signUpStage.setResizable(false);
			signUpStage.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
