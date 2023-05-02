package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
    * This class is used to access the database for the Professor class
    * It contains methods to insert, delete, and update the Professor table
    * It is a singleton class
    * It extends the DB class
    * It is used by the Professor class
    * It is used in the ProfessorController class
    * It is used in the ProfessorListController class
    * It is used in the ProfessorSearchController class
    * It is used in the ProfessorUpdateController class
    * It is used in the ProfessorViewController class
    * It is used in the ProfessorViewListController class
    * It is used in the ProfessorViewSearchController class
    * It is used in the ProfessorViewUpdateController class
 */
public class Professor_Database extends DB{
    private final Connection conn;
    private PreparedStatement pstmt;

    //singleton pattern
    private final static Professor_Database professor_database = new Professor_Database();

    public static Professor_Database getProfessor_database() {
    	return professor_database;
    }

    private Professor_Database() {
    	conn = DB.conn;
    }

    /**
        * Inserts a professor into the professor table
        * Inserts the professor's semesters, courses, programs, personal characteristics, and academic characteristics into their respective tables
        * The professor's id is used as a reference in the other tables
        * This class is used during the initial setup of the professor info, it isn't used if the professor is to stay the same
     */
    public void insert(String name, String title, String school, String department,
                       String email, String phone, List<String> semesters,
                       List<String> courses, List<String> programs,
                       List<String> personalChars, List<String> academicChars) {

        //insert into professor table
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

            //get the id of the professor that was just inserted to use as reference for the other tables
            ResultSet rs = pstmt.getGeneratedKeys();
            int profId = -1;
            if (rs.next()) {
                profId = rs.getInt(1);
            }
            //insert into semesters table
            sql = "INSERT INTO Semesters(semester, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String semester : semesters) {
                pstmt.setString(1, semester);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }
            //insert into programs table
            sql = "INSERT INTO Programs(program_names, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String program : programs) {
                pstmt.setString(1, program);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }
            //insert into courses table
            sql = "INSERT INTO Courses(courses, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String course : courses) {
                pstmt.setString(1, course);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }
            //insert into academic characteristics table
            sql = "INSERT INTO Academic_Characteristics(academic_characteristics, profID) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            for (String a_char : academicChars) {
                pstmt.setString(1, a_char);
                pstmt.setInt(2, profId);
                pstmt.executeUpdate();

            }
            //insert into personal characteristics table
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
    /**
        * Deletes a professor from the professor table
     * @param id the id of the professor to be deleted
     */
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
    /**
        * Deletes a professor's semesters from the semesters table
     * @param id the id of the professor whose entries are to be deleted
     */
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

    /**
     * Gets professor data and returns it into a list
     * Used to populate the professor view
     * @param id the id of the professor whose data is to be retrieved
     */
    public List<String> getProfData(int id){
    	List<String> data = new ArrayList<>();
    	String sql = "SELECT * FROM Professor WHERE id = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, id);
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			data.add(rs.getString("name"));
    			data.add(rs.getString("title"));
    			data.add(rs.getString("school"));
    			data.add(rs.getString("department"));
    			data.add(rs.getString("email"));
    			data.add(rs.getString("phone"));
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return data;
    }
    /**
        * Gets the semesters, courses, programs, personal characteristics, and academic characteristics of a professor
     * @param id the id of the professor whose data is to be retrieved
     */
    public List<String> queryTableColumns(int id){
    	List<String> programs = new ArrayList<>();
    	String sql = null;
    	String col = null;
    	// 1 - programs
    	// 2 - semester
    	// 3 - courses
    	// 4 - personal
    	// 5 - academic
    	switch(id) {
    	case 1:
    		sql = "SELECT program_names FROM programs";
    		col = "program_names";
    		break;
    		
    	case 2:
    		sql = "SELECT semester FROM semesters";
    		col = "semester";
    		break;
    	
    	case 3:
    		sql = "SELECT courses FROM Courses";
    		col = "courses";
    		break;
    		
    	case 4:
    		sql = "SELECT personal_characteristics FROM Personal_Characteristics";
    		col = "personal_characteristics";
    		break;
    		
    	case 5:
    		sql = "SELECT academic_characteristics FROM Academic_Characteristics";
    		col = "academic_characteristics";
    		break;
    	}
    	
    	try {
    		pstmt = conn.prepareStatement(sql);
    		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				programs.add(rs.getString(col));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return programs;
    }

}
