package Application;
import DAL.DB;
import javafx.application.Application;
import javafx.stage.Stage;
import login.*;

public class Main extends Application{
    public static void main(String[] args) {
        DB.addColumn("Student_Courses", "Grade", "TEXT");
        //launch(args);
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