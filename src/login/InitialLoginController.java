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
import mainMenu.MainMenuController;
import signUp.SignUpController;

public class InitialLoginController{

	@FXML private PasswordField pass;
	@FXML private Button submitButton;
	@FXML private Button resetP;
	@FXML private Label testLabel;
	
	DAL.Login_Database ld = DAL.Login_Database.getLogin_database();
	
	@FXML
	public void login(ActionEvent event) throws Exception {
		String user = pass.getText();
		//System.out.println(ld.isEmpty());
		if(ld.isEmpty()) {
			System.out.println(ld.isEmpty());
			if(user.contentEquals("p")) {
				Stage stage = (Stage)submitButton.getScene().getWindow();
				stage.close();
				signupStage();
			}
		}
		else {
		try {
			if(ld.isLogin(user)) {
				Stage stage = (Stage)this.submitButton.getScene().getWindow();
				stage.close();
				mainMenu();
			}
			}catch(Exception localException) {
				System.out.println(localException);
			}
		}
	}
	
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
	public void loginStage() {

		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/resetPass/resetPassFXML.fxml"));
			
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
	
	public void mainMenu() {
		try {
			Stage signUpStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/mainMenu/MainMenuFXML.fxml"));
			
			MainMenuController menuC = (MainMenuController)loader.getController();
			
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
