package com.example.rpcum.studentdirectory.Surveys;

public class StudentGeneral {
    // fields
    private String username;
    private String pwd;
    private String firstName;
    private String gender;
    private String age;
    private String phoneNumber;
    private String email;

    // constructors
    public StudentGeneral() {}

    public StudentGeneral(String username,String pwd, String studentname, String gender, String age, String phoneNumber, String email) {
        this.username = username;
        //will need to change password management, just have it like this for now
        this.pwd = pwd;
        this.firstName = studentname;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
