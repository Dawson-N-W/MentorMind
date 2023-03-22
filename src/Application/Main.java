package Application;

public class Main {
    public static void main(String[] args) {
        DAL dal = new DAL();
        dal.createNewDatabase("test.db");
        System.out.println("Hello world!");
    }
}
//login in for the first time
//login with non-default password
//resetting password
//logging out
//write initial data to SQLLite DB for items 13 to 18 in problem statement
//data entry for items 7.1 to 7.8 of problem statement (saving is optional)