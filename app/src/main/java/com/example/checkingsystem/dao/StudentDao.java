package com.example.checkingsystem.dao;

import android.content.Context;
import android.util.Log;

import com.example.checkingsystem.entity.Student;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBHelperUtil;

/**
 * Created by 那年.盛夏 on 2017/3/14.
 */

public class StudentDao {
    private Dao studentDao;
    private DBHelperUtil dbHelperUtil;
    public StudentDao(Context context)
    {
        dbHelperUtil = DBHelperUtil.getHelper(context);

        try {
            studentDao = dbHelperUtil.getDao(Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student)
    {
        try {
            studentDao.create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Student student)
    {
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentDao.queryForAll();
            for (Student student1:studentList)
            {
                Log.e("studentDao1--",student1.toString());
            }
            studentDao.delete(student);
            studentList = studentDao.queryForAll();
            if(studentList.size()==0)
            {
                Log.e("studentDao2--","null");
            }
            for (Student student1:studentList)
            {
                Log.e("studentDao2--",student1.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> queryStudent()
    {
        List<Student> studentList = null;
        try {
            studentList = studentDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public void updateStudent(Student student)
    {
        try {
            studentDao.updateBuilder().updateColumnValue("studentFacecode",student.getStudentFacecode()).where().eq("studentId",student.getStudentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
