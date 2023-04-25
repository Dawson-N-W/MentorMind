package Application;
import DAL.DB;
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
        /*Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setGender("male");
        student.setSemester("Fall");
        student.setSemYear(2019);
        student.setProgram("Computer Science");
        student.setSchool("School of Computing and Engineering");
        student.setDate("2019-12-12");
        List<String> personalCharsList = new ArrayList<>();
        personalCharsList.add("Hardworking");
        personalCharsList.add("Dedicated");

        List<String> academicCharsList = new ArrayList<>();
        academicCharsList.add("Good at math");
        academicCharsList.add("Good at science");

        List<List<String>> courseList = new ArrayList<>();
        List<String> course1 = new ArrayList<>();
        course1.add("CSEE 3838");
        course1.add("A");
        courseList.add(course1);
        List<String> course2 = new ArrayList<>();
        course2.add("CSEE 5193");
        course2.add("B");
        courseList.add(course2);

        student.setPersonalCharsList(personalCharsList);
        student.setAcademicCharsList(academicCharsList);
        student.setCourseList(courseList);
        */
        Student_Database student_database = Student_Database.getStudent_database();
        Student student = student_database.searchStudent("Doe");
        System.out.println(student.getFirstName());
        System.out.println(student.getCourseList());
        RecLetter recLetter = new RecLetter();
        recLetter.compile(student);
        String letter = recLetter.getText();
        System.out.println(letter);
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