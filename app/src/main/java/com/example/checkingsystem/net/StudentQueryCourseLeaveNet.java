package com.example.checkingsystem.net;

import com.example.checkingsystem.LoginActivity;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/11.
 */

public class StudentQueryCourseLeaveNet {

    public void studentQueryCourseLeaveNet(HttpCallbackListener httpCallbackListener)
    {
        String url = HttpUtil.urlIp + PathUtil.STUDENT_GET_COURSE_LEAVE_INFO;
        String date = "studentId="+ LoginActivity.studentStatic.getStudentId();
        HttpUtil.sendHttpGetRequest(url+"?"+date,httpCallbackListener);
    }
}
