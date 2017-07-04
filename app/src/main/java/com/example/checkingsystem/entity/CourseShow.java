package com.example.checkingsystem.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class CourseShow implements Parcelable{
    private String id;
    private String dbID;
    private String name;
    private String teahcerName;
    private Bitmap imgBitmap;
    private String courseAttentanceCount;
    private String courseAskForLeaveCount;
    private String courseAbsentCount;
    private String courseLateCount;
    private String studentNumber;
    private String imgUrl;
    private String studentID;

    public String getCourseLateCount() {
        return courseLateCount;
    }

    public void setCourseLateCount(String courseLateCount) {
        this.courseLateCount = courseLateCount;
    }

    public String getCourseAbsentCount() {
        return courseAbsentCount;
    }

    public void setCourseAbsentCount(String courseAbsentCount) {
        this.courseAbsentCount = courseAbsentCount;
    }

    protected CourseShow(Parcel in) {
        id = in.readString();
        dbID = in.readString();
        name = in.readString();
        teahcerName = in.readString();
        imgBitmap = in.readParcelable(Bitmap.class.getClassLoader());
        courseAttentanceCount = in.readString();
        courseAskForLeaveCount = in.readString();
        studentNumber = in.readString();
        imgUrl = in.readString();
    }

    public static final Creator<CourseShow> CREATOR = new Creator<CourseShow>() {
        @Override
        public CourseShow createFromParcel(Parcel in) {
            return new CourseShow(in);
        }

        @Override
        public CourseShow[] newArray(int size) {
            return new CourseShow[size];
        }
    };

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dbID);
        dest.writeString(name);
        dest.writeString(teahcerName);
        dest.writeParcelable(imgBitmap, flags);
        dest.writeString(courseAttentanceCount);
        dest.writeString(courseAskForLeaveCount);
        dest.writeString(studentNumber);
        dest.writeString(imgUrl);
    }
}
