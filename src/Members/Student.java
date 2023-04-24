package Members;

import java.util.List;

public class Student {
    private String firstName = null;
    private String lastName = null;
    private String gender;
    private String semester = null;
    private int semYear = 0;
    private String program = null;
    private String school = null;
    private String date = null;
    private List<String> personalCharsList = null;
    private List<String> academicCharsList = null;
    private List<List<String>> courseList = null;

    public Student(){
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getSemYear() {
        return semYear;
    }

    public void setSemYear(int semYear) {
        this.semYear = semYear;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getPersonalCharsList() {
        return personalCharsList;
    }

    public void setPersonalCharsList(List<String> personalCharsList) {
        this.personalCharsList = personalCharsList;
    }

    public List<String> getAcademicCharsList() {
        return academicCharsList;
    }

    public void setAcademicCharsList(List<String> academicCharsList) {
        this.academicCharsList = academicCharsList;
    }

    public List<List<String>> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<List<String>> courseList) {
        this.courseList = courseList;
    }



}
