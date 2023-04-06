package login;
import DAL.*;
//package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class InitialLogin extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	        //DAL.Login_Database login_database = DAL.Login_Database.getLogin_database();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("InitialLoginFXML.fxml"));
			Scene scene = new Scene(root,600,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mentor Mind");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}