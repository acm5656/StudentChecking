package com.example.checkingsystem.entity;

import android.graphics.Bitmap;

/**
 * Created by 那年.盛夏 on 2017/7/10.
 */

public class ClassGradeShow{
    private ClassGrade classGrade;
    private Student student;
    private Bitmap bitmap;

    public ClassGrade getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(ClassGrade classGrade) {
        this.classGrade = classGrade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
