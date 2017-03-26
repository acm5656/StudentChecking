package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/3/18.
 */
public class StudentVo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String timestamp;
    private Student student;
    public StudentVo() {}

    public StudentVo(String uuid, String timestamp, Student student) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.student = student;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
