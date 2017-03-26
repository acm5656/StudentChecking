package com.example.checkingsystem;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.dao.StudentDao;
import com.example.checkingsystem.dao.TeacherDao;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;

public class IndexActivity extends AppCompatActivity {

    public static StudentDao studentDao;
    public static TeacherDao teacherDao;
    private Student student;
    private Teacher teacher;
    public String roleStr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initDB();

    }
    private void initDB() {
        teacherDao = new TeacherDao(this);
        studentDao = new StudentDao(this);
        List<Student> studentList = studentDao.queryStudent();
        if(studentList.size()!=0)
        {
            Log.e("mainActivity","------1");
            student = studentList.get(0);
            roleStr = "学生";
        }
        List<Teacher> teacherList = teacherDao.queryTeacher();
        if(teacherList.size()!=0)
        {
            Log.e("mainActivity","------2");
            teacher = teacherList.get(0);
            roleStr = "教师";
        }

    }
}
