package com.example.checkingsystem.beans;

/**
 * Created by 那年.盛夏 on 2017/4/8.
 */

public class TeacherCheckingStudentItem {
    private String studentNo;
    private String studentName;
    private String studentState;
    private String studentID;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentState() {
        return studentState;
    }

    public void setStudentState(String studentState) {
        this.studentState = studentState;
    }

    @Override
    public String toString() {
        return "TeacherCheckingStudentItem{" +
                "studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentState='" + studentState + '\'' +
                '}';
    }

    public TeacherCheckingStudentItem(String studentNo, String studentName, String studentState) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentState = studentState;
    }
    public TeacherCheckingStudentItem()
    {

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
