package com.example.checkingsystem.dao;

import android.content.Context;
import android.util.Log;

import com.example.checkingsystem.entity.Teacher;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import util.DBHelperUtil;

/**
 * Created by 那年.盛夏 on 2017/3/14.
 */

public class TeacherDao {
    private Dao dao;
    private DBHelperUtil dbHelperUtil;

    public TeacherDao(Context context)
    {
        dbHelperUtil = DBHelperUtil.getHelper(context);
        try {
            dao = dbHelperUtil.getDao(Teacher.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher)
    {
        try {
            dao.create(teacher);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(Teacher teacher)
    {
        List<Teacher> teacherList = null;
        try {
            teacherList = dao.queryForAll();

            dao.delete(teacher);

            teacherList = dao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> queryTeacher()
    {
        List<Teacher> teacherList = null;

        try {
            teacherList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherList;
    }

}
