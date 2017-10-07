package ewu.lict.model;

/**
 * Created by Faculty on 9/23/2017.
 */

public class Student {
    private int id;
    private String userName;
    private String password;
    private String phoneNo;
    private Float cgpa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Float getCGPA() {
        return cgpa;
    }

    public void setCGPA(Float cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userName='" + userName + "\n" +
                ", phoneNo='" + phoneNo + "\n" +
                ", cgpa=" + cgpa +
                '}';
    }
}
