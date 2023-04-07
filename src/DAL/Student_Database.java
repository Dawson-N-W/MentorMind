package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Student_Database extends DB{
    private final String tableName = "students";
    private final Connection conn;
    private PreparedStatement pstmt;

    //Singleton pattern
    private final static Student_Database student_database = new Student_Database();

    public static Student_Database getLogin_database() {
        return student_database;
    }

    private Student_Database() {
        conn = DB.conn;
    }

    public void insert(String firstName, String lastName, String gender,
                       String school, String date, String program, String semester,
                       String semYear, List<String> personalCharsList, List<String> academicCharsList) {
        String delimiter = ", "; // choose a delimiter for separating the strings
        String personalChars = "'" + String.join(delimiter, personalCharsList) + "'";
        String academicChars = "'" + String.join(delimiter, academicCharsList) + "'";
        String sql = "INSERT INTO students(firstName, lastName, gender, school, date, program, semester, semYear, personalChars, academicChars) VALUES(?,?,?,?,?,?,?,?,?,?)";
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
            pstmt.setString(9, personalChars);
            pstmt.setString(10, academicChars);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
