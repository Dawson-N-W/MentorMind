package mainMenu;

import DAL.Professor_Database;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainMenuController implements Initializable{

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private TextField schoolName;

    @FXML
    private DatePicker schoolDate;

    @FXML
    private TextField year;

    @FXML
    private ListView<String> programs;

    @FXML
    private ListView<String> semesters;

    @FXML
    private ListView<String> courses;

    @FXML
    private ListView<String> personalChars;

    @FXML
    private ListView<String> academicChars;
    
    @FXML
    private VBox textFs;
    
    private Professor_Database pd = Professor_Database.getProfessor_database();
    
    private List<String> programL = pd.getList(1);
    
    private List<String> semesterL = pd.getList(2);
    
    private List<String> courseL = pd.getList(3);
    
    private List<String> pcL = pd.getList(4);
    
    private List<String> acL = pd.getList(5);
    
    private List<TextField> grades = new ArrayList<>();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String[] genders = {"Male","Female"};
		genderCombo.getItems().addAll(genders);
		
		programs.getItems().addAll(programL);
		//programs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		semesters.getItems().addAll(semesterL);
		//semesters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		courses.getItems().addAll(courseL);
		courses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		for(int i = 0; i < courseL.size(); i++) {
			TextField grade = new TextField();
			grade.setPromptText("Student Grade");
			grades.add(grade);
			textFs.getChildren().add(grade);
		}
		
		personalChars.getItems().addAll(pcL);
		personalChars.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		academicChars.getItems().addAll(acL);
		academicChars.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

}

