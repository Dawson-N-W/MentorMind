package Application;
import DAL.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Professor_Database professor_database = Professor_Database.getProfessor_database();

        List<String> semesters = new ArrayList<>(); semesters.add("Summer"); semesters.add("Fall");
        List<String> courses = new ArrayList<>(); courses.add("CS151 - OOP"); courses.add("CS171 - ML");
        List<String> programs = new ArrayList<>(); programs.add("Master of Science (MS)"); programs.add("Master of Business Administration (MBA)");
        List<String> personalChars = new ArrayList<>(); personalChars.add("Honest"); personalChars.add("Kind");
        List<String> academicChars = new ArrayList<>(); academicChars.add("Hard Studier"); academicChars.add("Worked Hard");
        professor_database.insert("John Doe", "Lecturer", "SJSU", "CS",
                "john.doe@sjsu.edu", "408-867-5309", semesters, courses, programs, personalChars, academicChars);

        /*
        professor_database.deleteProf(2);
        professor_database.deleteEntry("Semesters", 2);
        professor_database.deleteEntry("Programs", 2);
        professor_database.deleteEntry("Courses", 2);
        professor_database.deleteEntry("Personal_Characteristics", 2);
        professor_database.deleteEntry("Academic_Characteristics", 2);
        */

    }
}
//login in for the first time
//login with non-default password
//resetting password
//logging out
//write initial data to SQLLite DB for items 13 to 18 in problem statement
//data entry for items 7.1 to 7.8 of problem statement (saving is optional)