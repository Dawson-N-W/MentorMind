package DAL;

import Members.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to access the database for the Student class
 * It contains methods to insert, delete, and update the Student table
 * It is a singleton class
 * It extends the DB class
 * It is used by the Student class
 * It is used in the StudentController class
 * It is used in the StudentListController class
 */
public class Student_Database extends DB{
    private final String tableName = "students";
    private final Connection conn;
    private PreparedStatement pstmt;

    //Singleton pattern
    private final static Student_Database student_database = new Student_Database();

    public static Student_Database getStudent_database() {
        return student_database;
    }

    private Student_Database() {
        conn = DB.conn;
    }

    /**
     * inserts the provided student into the database
     * @param student is a Student object
     */
    public void insert(Student student) {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String gender = student.getGender();
        String school = student.getSchool();
        String date = student.getDate();
        String program = student.getProgram();
        String semester = student.getSemester();
        String semYear = student.getSemYear() + "";
        List<String> personalCharsList = student.getPersonalCharsList();
        List<String> academicCharsList = student.getAcademicCharsList();
        List<List<String>> courseList = student.getCourseList();



        String sql = "INSERT INTO students(firstName, lastName, gender, school, date, program, semester, semYear) VALUES(?,?,?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, gender);
            pstmt.setString(4, school);
            pstmt.setString(5, date);
            pstmt.setString(6, program);
            pstmt.setString(7, semester);
            pstmt.setString(8, semYear);
            pstmt.executeUpdate();

            //get the id of the student that was just inserted to use as reference for the other tables
            ResultSet rs = pstmt.getGeneratedKeys();
            int studentId = -1;
            if (rs.next()) {
                studentId = rs.getInt(1);
            }

            sql = "INSERT INTO Student_PChar(personal_characteristics, studentID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String personalChars : personalCharsList) {
                pstmt.setString(1, personalChars);
                pstmt.setInt(2, studentId);
                pstmt.executeUpdate();
            }

            sql = "INSERT INTO Student_AChar(academic_characteristics, studentID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String academicChars : academicCharsList) {
                pstmt.setString(1, academicChars);
                pstmt.setInt(2, studentId);
                pstmt.executeUpdate();
            }

            sql = "INSERT INTO Student_Courses(courses, studentID, Grade) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            for (List<String> strings : courseList) {
                pstmt.setString(1, strings.get(0));
                pstmt.setInt(2, studentId);
                pstmt.setString(3, strings.get(1));
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    /**
     * updates the provided student in the database
     * @param id is the id of the student to be updated
     */
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            sql = "DELETE FROM Student_PChar WHERE studentID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            sql = "DELETE FROM Student_AChar WHERE studentID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            sql = "DELETE FROM Student_Courses WHERE studentID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            sql = "DELETE FROM recLetters WHERE studentID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Searches for a student via last name, first name, or semester year
     * @param val is the value to be searched for
     * @return a list of student ids that match the search
     */
    public List<Integer> searchID(String val){
    	String sql = "SELECT * FROM students WHERE ? IN (firstName, lastName, semYear)";
    	ArrayList<Integer> studentID = new ArrayList<>();
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, val);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
					studentID.add(rs.getInt("id"));
				
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return studentID;
    }


    /**
     * Searches for a student via last name, first name, or semester year
     * @param val is the value to be searched for
     * @return a list of students that match the search
     */
    public List<Student> searchStudent(String val) {
    	List<Integer> studentIDs = searchID(val);
    	List<Student> students = new ArrayList<>();
    	
    	for(Integer sID : studentIDs) {
    		String sql = "SELECT * FROM students WHERE id = ?";
    		String studentID = "";
    		Student student = new Student();
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, String.valueOf(sID));
                ResultSet rs = pstmt.executeQuery();
                String firstName = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                String gender = rs.getString("gender");
                String school = rs.getString("school");
                String semester = rs.getString("semester");
                String date = rs.getString("date");
                String program = rs.getString("program");
                String semYear = rs.getString("semYear");
                student.setFirstName(firstName);
                student.setLastName(lastname);
                student.setGender(gender);
                student.setSchool(school);
                student.setSemester(semester);
                student.setDate(date);
                student.setProgram(program);
                student.setSemYear(Integer.parseInt(semYear));

                studentID = String.valueOf(rs.getInt("id"));

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String sql2 = "SELECT * FROM Student_PChar WHERE studentID = ?";
            List<String> personalCharList = new ArrayList<String>();

            try {
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, studentID);
                ResultSet rs = pstmt.executeQuery();

                // Loop through the result set and add each personal characteristic to the list
                while (rs.next()) {
                    String personalChar = rs.getString("personal_characteristics");
                    personalCharList.add(personalChar);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //add personal characteristics to student object
            student.setPersonalCharsList(personalCharList);

            String sql3 = "SELECT * FROM Student_AChar WHERE studentID = ?";
            List<String> academicCharList = new ArrayList<String>();

            try {
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, studentID);
                ResultSet rs = pstmt.executeQuery();

                // Loop through the result set and add each academic characteristic to the list
                while (rs.next()) {
                    String academicChar = rs.getString("academic_characteristics");
                    academicCharList.add(academicChar);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //add academic characteristics to student object
            student.setAcademicCharsList(academicCharList);

            String sql4 = "SELECT * FROM Student_Courses WHERE studentID = ?";
            List<List<String>> courseList = new ArrayList<List<String>>();

            try {
                pstmt = conn.prepareStatement(sql4);
                pstmt.setString(1, studentID);
                ResultSet rs = pstmt.executeQuery();

                // Loop through the result set and add each course to the list
                while (rs.next()) {
                    List<String> course = new ArrayList<String>();
                    course.add(rs.getString("courses"));
                    course.add(rs.getString("Grade"));
                    courseList.add(course);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //add courses to student object
            student.setCourseList(courseList);
    		
            students.add(student);
    		
    	}

        return students;
    }

    /**
     * Gets the student id of a student
     * @param student is the student whose id is being searched for
     * @return the student id
     */
    public int getStudentID(Student student){
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String sql = "SELECT * FROM students WHERE firstName = ? AND lastName = ?";
        String studentID = "";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            studentID = String.valueOf(rs.getInt("id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(studentID);
    }
}
