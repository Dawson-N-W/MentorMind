package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Professor_Database extends DB{
    private final Connection conn;
    private PreparedStatement pstmt;


    private final static Professor_Database professor_database = new Professor_Database();

    public static Professor_Database getProfessor_database() {
    	return professor_database;
    }

    private Professor_Database() {
    	conn = DB.conn;
    }


    public void insert(String name, String title, String school, String department,
                       String email, String phone, List<String> semesters,
                       List<String> courses, List<String> programs,
                       List<String> personalChars, List<String> academicChars) {


        String sql = "INSERT INTO Professor(name, title, school, department, email, phone) VALUES(?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, title);
            pstmt.setString(3, school);
            pstmt.setString(4, department);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            int profId = -1;
            if (rs.next()) {
                profId = rs.getInt(1);
            }

            sql = "INSERT INTO Semesters(semester, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String semester : semesters) {
                pstmt.setString(1, semester);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }

            sql = "INSERT INTO Programs(program_names, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String program : programs) {
                pstmt.setString(1, program);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }

            sql = "INSERT INTO Courses(courses, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String course : courses) {
                pstmt.setString(1, course);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }

            sql = "INSERT INTO Academic_Characteristics(academic_characteristics, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String a_char : academicChars) {
                pstmt.setString(1, a_char);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }

            sql = "INSERT INTO Personal_Characteristics(personal_characteristics, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String p_char : personalChars) {
                pstmt.setString(1, p_char);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteProf(int id) {
    	String sql = "DELETE FROM Professor WHERE id = ?";
    	try{
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, id);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }

    public void deleteEntry(String table, int id) {
    	String sql = "DELETE FROM " + table + " WHERE profID = ?";
    	try{
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, id);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }

}
