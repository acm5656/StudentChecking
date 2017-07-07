package com.example.checkingsystem.dao;

import android.content.Context;

import com.example.checkingsystem.entity.Assistant;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import util.DBHelperUtil;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class AssistantDao {
    private Dao assistantDao;
    private DBHelperUtil dbHelperUtil;

    public AssistantDao(Context context)
    {
        dbHelperUtil = DBHelperUtil.getHelper(context);

        try {
            assistantDao = dbHelperUtil.getDao(Assistant.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addAssistant(Assistant assistant)
    {
        try {
            assistantDao.create(assistant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssistant(Assistant assistant)
    {
        try {
            assistantDao.delete(assistant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
