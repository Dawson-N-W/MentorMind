package login;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import signUp.SignUpController;

public class InitialLoginController{

	@FXML private TextField username;
	@FXML private PasswordField pass;
	@FXML private Button submitButton;
	private boolean signedUp = false;
	
	@FXML
	public void login(ActionEvent event) {
		if(signedUp == false) {
			String user = pass.getText();
			if(username.getText().trim().isEmpty() && user.contentEquals("p")) {
				Stage stage = (Stage)submitButton.getScene().getWindow();
				stage.close();
				signupStage();
			}
		}

	}
	
	public void signedUp() {
		signedUp = true;
	}
	
	public void signupStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/signUp/signUpFXML.fxml"));
			
			SignUpController signUpController = (SignUpController)loader.getController();
			
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
