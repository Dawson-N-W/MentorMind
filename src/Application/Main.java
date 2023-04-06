package Application;
import DAL.*;

public class Main {
    public static void main(String[] args) {
        DAL.Login_Database login_database = DAL.Login_Database.getLogin_database();
        login_database.createTable("login");
        login_database.insert("password");



    }
}
//login in for the first time
//login with non-default password
//resetting password
//logging out
//write initial data to SQLLite DB for items 13 to 18 in problem statement
//data entry for items 7.1 to 7.8 of problem statement (saving is optional)