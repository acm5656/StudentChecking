package com.example.checkingsystem.net;

import android.app.Activity;

import com.example.checkingsystem.entity.Student;

import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/8.
 */

public class GetStudentInfoByID {


    public void teacherGetStudentInfoByID(HttpCallbackListener httpCallbackListener,List<String> studentID)
    {
        String path = HttpUtil.urlIp + PathUtil.GET_STUDENT_INFO_BY_STUDENT_ID;
        String data = "studentsId=";
        for(int i = 0 ; i<studentID.size() ;i++)
        {
            data+=studentID.get(i);
            if(i!=(studentID.size()-1))
            {
                data+=",";
            }
        }

        HttpUtil.sendHttpGetRequest(path+"?"+data,httpCallbackListener);

    }



}
