package Members;
import DAL.Professor_Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create a letter of recommendation for a student
 * It contains methods to compile the letter and send it to the database
 */
public class RecLetter {
    private StringBuilder sb;

    private final LocalDate currentDate = LocalDate.now();
    private final String dateString = currentDate.toString();

    public RecLetter() {
        sb = new StringBuilder();
    }

    /**
     * Compiles the letter of recommendation for the provided student
     * Uses a template to create the letter with a StringBuilder
     * @param student the student for whom the letter is being written
     */
    public void compile(Student student){
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String program = student.getProgram();
        String semester = student.getSemester();
        String gender = student.getGender();
        String semYear = student.getSemYear() + "";
        List<List<String>> courses = student.getCourseList();
        List<String> grades = new ArrayList<>();
        List<String> courseNames = new ArrayList<>();
        for(List<String> course : courses){
            courseNames.add(course.get(0));
            grades.add(course.get(1));
        }
        List<String> academicChars = student.getAcademicCharsList();
        List<String> personalChars = student.getPersonalCharsList();

        List<String> profData = getProfessorData();

        String pronoun;

        if(gender.equals("Male"))
            pronoun = "He";
            else
            pronoun = "She";

        sb.append("Letter of Recommendation\n\n");
        sb.append(String.format("For: %s %s\n\t\t\t\t\t\t\t\t\t\tDate: ", firstName, lastName));
        sb.append(dateString).append("\n\n");
        sb.append("To: Graduate Admissions Committee\n\n");
        sb.append("I am writing to recommend my former student ");
        sb.append(String.format("%s %s who is applying for the %s program in your school. \n\n", firstName, lastName, program));
        sb.append(String.format("I met %s in %s %s when they enrolled in my \"%s\" course. \n\n", firstName, semester, semYear, courseNames.get(0)));
        sb.append(String.format("%s earned %s from this tough course, and this shows how knowledgeable and hard worker %s is. \n\n", firstName, grades.get(0), pronoun.toLowerCase()));



        //lists all classes and their associated grades.
        for(int i = 0; i < courseNames.size(); i++)
        {
            if(i == 0);
            else if(i == 1)
                sb.append(String.format("%s also earned  \"%s\" from my \"%s\" course", pronoun, grades.get(i), courseNames.get(i)));
            else if(i == courseNames.size() - 1)
            {
                if(courseNames.size() > 3)
                    sb.append(String.format(","));
                sb.append(String.format(" and \"%s\" from my \"%s\" course", grades.get(i), courseNames.get(i)));
            }

            else
                sb.append(String.format(", \"%s\" from my \"%s\" course", grades.get(i), courseNames.get(i)));
        }
        sb.append(".\n\n");


        sb.append(String.format(firstName));
        for(int i = 0; i < academicChars.size(); i++){
            if(i == 0)
                sb.append(String.format(" %s", academicChars.get(i).toLowerCase()));
            else if (i == academicChars.size() - 1)
            {
                if(academicChars.size() > 2)
                    sb.append(String.format(","));
                sb.append(String.format(" and %s", academicChars.get(i).toLowerCase()));
            }
            else
                sb.append(String.format(", %s", academicChars.get(i).toLowerCase()));
        }
        sb.append(".\n\n");


        sb.append(String.format(pronoun));
        for(int i = 0; i < personalChars.size(); i++){
            if(i == 0)
                sb.append(String.format(" was always %s", personalChars.get(i).toLowerCase()));
            else if (i == personalChars.size() - 1)
            {
                if(personalChars.size() > 2)
                    sb.append(String.format(","));
                sb.append(String.format(" and %s", personalChars.get(i).toLowerCase()));
            }
            else
                sb.append(String.format(", %s", personalChars.get(i).toLowerCase()));
        }
        sb.append(".\n\n");



        sb.append(String.format("Furthermore, I noticed from the term project result, %s developed " +
                "leadership, time management, and problem-solving skills. %s worked effectively with the team member and delegated tasks " +
                "appropriately. They were able to deliver a successful project in a timely fashion. \n\n", pronoun.toLowerCase(), pronoun.substring(0, 1).toUpperCase() + pronoun.substring(1)));

        sb.append(String.format("I believe that %s has the capacity to excel at higher education program and this is my pleasure to highly recommend %s. \n", firstName, pronoun.toLowerCase().equals("he") ? "him" : "her"));

        sb.append("Please do not hesitate to contact me with further questions. \n\n\n");

        sb.append("Very Respectfully,\n\n");

        sb.append(profData.get(0)).append("\n\n");

        sb.append(profData.get(1)).append("\n");

        sb.append(String.format("%s, %s department\n", profData.get(2), profData.get(3)));

        sb.append(profData.get(4)).append("\n");

        sb.append(profData.get(5)).append("\n");



    }

    /**
     * Updates the text of the letter
     * @param text the new text to be used
     */
    public void updateText(String text){
        sb = new StringBuilder(text);
    }

    /**
     * Gets the data for the professor who is writing the letter
     * @return a list of strings containing the professor's data
     */
    private List<String> getProfessorData(){
        Professor_Database db = Professor_Database.getProfessor_database();
        return db.getProfData(1);
    }

    /**
     * Gets the text of the letter
     * @return the text of the letter
     */
    public String getText(){
        return sb.toString();
    }

}
