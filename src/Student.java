import java.util.List;

public class Student implements Uni_Member{
    //instance variables
    private String firstName;
    private String lastName;
    private String school;
    private String gender;
    private String program;
    private String semester;
    private List<Course> courses;
    private List<String> personalChar;
    private List<String> academicChar;

    //constructor
    public Student(String firstName, String lastName, String school, String gender, String program, String semester, List<Course> courses, List<String> personalChar, List<String> academicChar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.school = school;
        this.gender = gender;
        this.program = program;
        this.semester = semester;
        this.courses = courses;
        this.personalChar = personalChar;
        this.academicChar = academicChar;
    }

    //default constructor
    public Student(){};

    @Override
    public void setFirstName(String name) {this.firstName = name;}

    @Override
    public String getLastName() {return lastName;}

    @Override
    public void setLastName(String name) {this.lastName = name;}

    @Override
    public String getFirstName() {return firstName;}

    @Override
    public void setSchool(String school) {this.school = school;}

    @Override
    public String getSchool() {return school;}

    //getters and setters
    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getProgram() {return program;}

    public void setProgram(String program) {this.program = program;}

    public String getSemester() {return semester;}

    public void setSemester(String semester) {this.semester = semester;}

    public List<Course> getCourses() {return courses;}

    public void setCourses(List<Course> courses) {this.courses = courses;}

    public List<String> getPersonalChar() {return personalChar;}

    public void setPersonalChar(List<String> personalChar) {this.personalChar = personalChar;}

    public List<String> getAcademicChar() {return academicChar;}

    public void setAcademicChar(List<String> academicChar) {this.academicChar = academicChar;}

    //toString method
    @Override
    public String toString(){
        return String.format("Name: %s %s\nGender: %s\nSchool: %s\nProgram: %s\nSemester: %s\n",
                firstName, lastName, gender, school, program, semester);
    }

    //method to add a course to the list of courses
    public void addCourse(Course course){
        courses.add(course);
    }

    //method to add a personal characteristic to the list of personal characteristics
    public void addPersonalChar(String personalChar){
        this.personalChar.add(personalChar);
    }

    //method to add an academic characteristic to the list of academic characteristics
    public void addAcademicChar(String academicChar){
        this.academicChar.add(academicChar);
    }

    //method to remove a course from the list of courses
    public void removeCourse(Course course){
        courses.remove(course);
    }

    //method to remove a personal characteristic from the list of personal characteristics
    public void removePersonalChar(String personalChar){
        this.personalChar.remove(personalChar);
    }

    //method to remove an academic characteristic from the list of academic characteristics
    public void removeAcademicChar(String academicChar){
        this.academicChar.remove(academicChar);
    }

    //method to print the list of courses
    public void printCourses(){
        for(Course course : courses){
            System.out.println(course);
        }
    }

    //method to print the list of personal characteristics
    public void printPersonalChar(){
        for(String personalChar : personalChar){
            System.out.println(personalChar);
        }
    }

    //method to print the list of academic characteristics
    public void printAcademicChar(){
        for(String academicChar : academicChar){
            System.out.println(academicChar);
        }
    }

}
