package Application;
import DAL.DB;
import DAL.RecLetter_Database;
import DAL.Student_Database;
import Members.RecLetter;
import Members.Student;
import javafx.application.Application;
import javafx.stage.Stage;
import login.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//Creates an instance of the login page and runs it for display.
        InitialLogin initialLogin = new InitialLogin();
        Stage loginStage = new Stage();
        initialLogin.start(loginStage);
	}
}