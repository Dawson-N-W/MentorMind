package Application;
import DAL.*;
import javafx.application.Application;
import javafx.stage.Stage;
import login.*;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        InitialLogin initialLogin = new InitialLogin();
        Stage loginStage = new Stage();
        initialLogin.start(loginStage);
	}
}