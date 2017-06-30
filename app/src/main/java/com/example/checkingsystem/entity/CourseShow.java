package com.example.checkingsystem.entity;

import android.graphics.Bitmap;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class CourseShow {
    private String id;
    private String dbID;
    private String name;
    private String teahcerName;
    private Bitmap imgBitmap;
    private String courseAttentanceCount;
    private String courseAskForLeaveCount;
    private String studentNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeahcerName() {
        return teahcerName;
    }

    public void setTeahcerName(String teahcerName) {
        this.teahcerName = teahcerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public CourseShow(String id, String name, String teahcerName, Bitmap imgBitmap) {
        this.id = id;
        this.name = name;
        this.teahcerName = teahcerName;
        this.imgBitmap = imgBitmap;
    }

    public String getCourseAttentanceCount() {
        return courseAttentanceCount;
    }

    public void setCourseAttentanceCount(String courseAttentanceCount) {
        this.courseAttentanceCount = courseAttentanceCount;
    }

    public String getCourseAskForLeaveCount() {
        return courseAskForLeaveCount;
    }

    public void setCourseAskForLeaveCount(String courseAskForLeaveCount) {
        this.courseAskForLeaveCount = courseAskForLeaveCount;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public CourseShow(){

    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    public CourseShow(String id, String dbID, String name, String teahcerName, Bitmap imgBitmap, String courseAttentanceCount, String courseAskForLeaveCount, String studentNumber) {
        this.id = id;
        this.dbID = dbID;
        this.name = name;
        this.teahcerName = teahcerName;
        this.imgBitmap = imgBitmap;
        this.courseAttentanceCount = courseAttentanceCount;
        this.courseAskForLeaveCount = courseAskForLeaveCount;
        this.studentNumber = studentNumber;
    }

    public CourseShow(String id, String name, String teahcerName, Bitmap imgBitmap, String courseAttentanceCount, String courseAskForLeaveCount, String studentNumber) {
        this.id = id;
        this.name = name;
        this.teahcerName = teahcerName;
        this.imgBitmap = imgBitmap;
        this.courseAttentanceCount = courseAttentanceCount;
        this.courseAskForLeaveCount = courseAskForLeaveCount;
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "CourseShow{" +
                "id='" + id + '\'' +
                ", dbID='" + dbID + '\'' +
                ", name='" + name + '\'' +
                ", teahcerName='" + teahcerName + '\'' +
                ", imgBitmap=" + imgBitmap +
                ", courseAttentanceCount='" + courseAttentanceCount + '\'' +
                ", courseAskForLeaveCount='" + courseAskForLeaveCount + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }
}
