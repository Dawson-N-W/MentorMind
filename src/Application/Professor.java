package Application;

public class Professor implements Uni_Member{
    //instance variables

    //im uploading this as a test

    private String firstName;
    private String lastName;

    private String school;
    DAL dal;
    //constructor
    public Professor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        dal = new DAL();
    }
    //default constructor
    public Professor(){};
    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String getSchool() {
        return school;
    }
}
