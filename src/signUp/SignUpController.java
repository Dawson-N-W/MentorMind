package signUp;

import java.io.IOException;

import javafx.event.ActionEvent;
import DAL.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.InitialLoginController;

public class SignUpController {
	@FXML private PasswordField dPassword;
	@FXML private PasswordField firstPassword;
	@FXML private PasswordField confirmPassword;
	@FXML private Label promptLabel;
	
	@FXML private Button loginButton;
	@FXML private Button createAcc;
	
	DAL.Login_Database ld = DAL.Login_Database.getLogin_database();
	
	
	@FXML
	public void backToLogin(ActionEvent event) {
		Stage stage = (Stage)loginButton.getScene().getWindow();
		stage.close();
		signupStage();

	}
	
	
	@FXML
	public void submit(ActionEvent event) {
		
		if(dPassword.getText().contentEquals("p") == false) {
			promptLabel.setText("Default Password is incorrect");
		}
		else if(firstPassword.getText().trim().isEmpty() || confirmPassword.getText().trim().isEmpty() ||firstPassword.getText().contentEquals(confirmPassword.getText()) == false) {
			promptLabel.setText("Passwords do not match or not filled!");
		}
		else if(firstPassword.getText().contentEquals("p") && confirmPassword.getText().contentEquals("p")) {
			promptLabel.setText("Cannot set password to default!");
		}
		else {

			ld.insert(confirmPassword.getText());
			Stage stage = (Stage)createAcc.getScene().getWindow();
			stage.close();
			try {

				Stage signUp;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/InitialLoginFXML.fxml"));
				AnchorPane root = loader.load();
				
				InitialLoginController initC = loader.getController();
				
				
				signUp = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				signUp.setScene(scene);
				signUp.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	public void signupStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/login/InitialLoginFXML.fxml"));
			
			InitialLoginController initC = (InitialLoginController)loader.getController();
			
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
