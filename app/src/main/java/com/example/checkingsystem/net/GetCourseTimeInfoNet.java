package com.example.checkingsystem.net;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentCourseTimeTable;
import com.example.checkingsystem.entity.TeacherCourseTimeTable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/17.
 */

public class GetCourseTimeInfoNet {

    static ObjectMapper objectMapper = new ObjectMapper();
    static HttpCallbackListener studentHttpCallbackListener = new HttpCallbackListener() {

        @Override
        public void onFinish(String response) {
            ResultObj<List<StudentCourseTimeTable>> resultObj = null;
            try {
                 resultObj = objectMapper.readValue(response, new TypeReference<ResultObj<List<StudentCourseTimeTable>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj!=null&&resultObj.getMeta().getResult())
            {
                LoginActivity.studentCourseTimeTableList = resultObj.getData();
            }

        }

        @Override
        public void onError(Exception e) {

        }
    };

    static HttpCallbackListener teacherHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<List<TeacherCourseTimeTable>> resultObj = null;

            try {
                resultObj = objectMapper.readValue(response, new TypeReference<ResultObj<List<TeacherCourseTimeTable>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (resultObj!=null&&resultObj.getMeta().getResult())
            {
                LoginActivity.teacherCourseTimeTableList = resultObj.getData();
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    static void studentGetCourseTimeInfo()
    {
        String address = HttpUtil.urlIp+ PathUtil.STUDENT_GET_COURSE_TIME_INFO+"?studentId="+ LoginActivity.studentStatic.getStudentId();
        HttpUtil.sendHttpGetRequest(address,studentHttpCallbackListener);
    }

    static void teacherGetCourseTimeInfo()
    {
        String address = HttpUtil.urlIp + PathUtil.TEACHER_GET_COURSE_TIME_INFO + "?teacherId="+LoginActivity.teacherStatic.getTeacherId();
        HttpUtil.sendHttpGetRequest(address,teacherHttpCallbackListener);
    }


}
