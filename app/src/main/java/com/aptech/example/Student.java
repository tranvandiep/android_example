package com.aptech.example;

/**
 * Created by Diep.Tran on 5/5/18.
 */

public class Student {
    public static final String STUDENT_DB = "STUDENT";
    public static final String ID_FULL_NAME = "FULL_NAME";
    public static final String ID_EMAIL = "EMAIL";

    String fullname;
    String email;

    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE STUDENT (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "FULL_NAME TEXT," +
            "EMAIL TEXT" +
            ")";

    public Student() {
    }

    public Student(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
