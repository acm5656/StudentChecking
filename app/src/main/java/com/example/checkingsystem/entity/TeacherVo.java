package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/3/27.
 */
public class TeacherVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String timestamp;
    private Teacher teacher;

    public TeacherVo() {}

    public TeacherVo(String uuid, String timestamp, Teacher teacher) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
