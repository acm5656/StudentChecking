package com.example.checkingsystem.entity;

import android.graphics.Bitmap;

/**
 * Created by 那年.盛夏 on 2017/7/3.
 */

public class StudentAttendanceCountShow {
    private String studentId;
    private String stduentSchoolID;
    private String attendanceCount;
    private String absentCount;
    private String lateCount;
    private String leaveCount;
    private String studentName;
    private String studentImgUrl;
    private Bitmap bitmap;

    public StudentAttendanceCountShow(StudentAttendanceCount studentAttendanceCount) {
        studentId = studentAttendanceCount.getStudentId();
        attendanceCount = String.valueOf(studentAttendanceCount.getAttendanceCount());
        absentCount = String.valueOf(studentAttendanceCount.getAbsentCount());
        lateCount = String.valueOf(studentAttendanceCount.getLateCount());
        leaveCount = String.valueOf(studentAttendanceCount.getLeaveCount());
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStduentSchoolID() {
        return stduentSchoolID;
    }

    public void setStduentSchoolID(String stduentSchoolID) {
        this.stduentSchoolID = stduentSchoolID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(String attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public String getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(String absentCount) {
        this.absentCount = absentCount;
    }

    public String getLateCount() {
        return lateCount;
    }

    public void setLateCount(String lateCount) {
        this.lateCount = lateCount;
    }

    public String getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(String leaveCount) {
        this.leaveCount = leaveCount;
    }

    public String getStudentImgUrl() {
        return studentImgUrl;
    }

    public void setStudentImgUrl(String studentImgUrl) {
        this.studentImgUrl = studentImgUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public StudentAttendanceCountShow()
    {

    }
}
