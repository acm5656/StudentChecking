package com.example.checkingsystem.entity;

import java.util.List;

/**
 * Created by eggyer on 2017/4/4.
 */
public class AuthorityInfo {
    private List<String> macList;
    private String courseAttendanceCourseTimeId;
    private String stuMac;

    public List<String> getMacList() {
        return macList;
    }

    public void setMacList(List<String> macList) {
        this.macList = macList;
    }

    public String getCourseAttendanceCourseTimeId() {
        return courseAttendanceCourseTimeId;
    }

    public void setCourseAttendanceCourseTimeId(String courseAttendanceCourseTimeId) {
        this.courseAttendanceCourseTimeId = courseAttendanceCourseTimeId;
    }

    public String getStuMac() {
        return stuMac;
    }

    public void setStuMac(String stuMac) {
        this.stuMac = stuMac;
    }
}
