package com.example.checkingsystem.beans;

/**
 * Created by 那年盛夏 on 2017/2/5.
 */

public class Student {
    private String username;
    private String password;

    private static Student student = null;

    private Student()
    {

    }

    public static Student getStudent()
    {
        if(student == null)
            student = new Student();
        return student;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
