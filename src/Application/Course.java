package Application;

public class Course {
    private String courseTag;
    private String courseName;
    private char grade;

    public Course(String courseTag, String courseName, char grade) {
        this.courseTag = courseTag;
        this.courseName = courseName;
        this.grade = grade;
    }
    //this is Ej's first git test from eclipse

    public String getCourseTag() {
        return courseTag;
    }

    public void setCourseTag(String courseTag) {
        this.courseTag = courseTag;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
