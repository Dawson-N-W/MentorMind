package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public void insert(String firstName, String lastName, String gender,
                       String school, String date, String program, String semester,
                       String semYear, List<String> personalCharsList, List<String> academicCharsList, List<String> courseList) {
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

            sql = "INSERT INTO Student_Courses(courses, studentID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String courses : courseList) {
                pstmt.setString(1, courses);
                pstmt.setInt(2, studentId);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudentPChar(int studentId) {
        String sql = "DELETE FROM Student_PChar WHERE studentID = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudentAChar(int studentId) {
        String sql = "DELETE FROM Student_AChar WHERE studentID = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudentCourses(int studentId) {
        String sql = "DELETE FROM Student_Courses WHERE studentID = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
