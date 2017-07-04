package com.example.checkingsystem.net;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.Course;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentCourseTimeTable;
import com.example.checkingsystem.entity.TeacherCourseTimeTable;
import com.example.checkingsystem.entity.VirtualCourse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/17.
 */

public class GetVirtualCourseInfoNet {

    static ObjectMapper objectMapper = new ObjectMapper();
    static HttpCallbackListener studentHttpCallbackListener = new HttpCallbackListener() {

        @Override
        public void onFinish(String response) {
            ResultObj<List<VirtualCourse>> resultObj = null;
            try {
                resultObj = objectMapper.readValue(response, new TypeReference<ResultObj<List<VirtualCourse>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj!=null&&resultObj.getMeta().getResult())
            {
                LoginActivity.studentVirtualList = resultObj.getData();

                LoginActivity.studentCourseShow = new ArrayList<>();

                for(int i = 0 ; i<resultObj.getData().size();i++)
                {
                    VirtualCourse virtualCourse = LoginActivity.studentVirtualList.get(i);
                    final CourseShow courseShow = new CourseShow();
                    courseShow.setDbID(virtualCourse.getVirtualCourseId());
                    courseShow.setId(virtualCourse.getVirtualCourseCode());
                    courseShow.setName(virtualCourse.getVirtualCourseName());
                    courseShow.setImgUrl(virtualCourse.getVirtualCourseImgUrl());
                    LoginActivity.studentCourseShow.add(courseShow);
                }
                GetStudetnCourseAttentanceTotalNet.getStudentCourseAttentanceTotal();

            }

        }

        @Override
        public void onError(Exception e) {

        }
    };

    static HttpCallbackListener teacherHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<List<VirtualCourse>> resultObj = null;

            try {
                resultObj = objectMapper.readValue(response, new TypeReference<ResultObj<List<VirtualCourse>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (resultObj!=null&&resultObj.getMeta().getResult())
            {
                LoginActivity.teacherVirtualList = resultObj.getData();

                LoginActivity.teacherCourseShow = new ArrayList<>();

                for(int i = 0 ; i<resultObj.getData().size();i++)
                {
                    VirtualCourse virtualCourse = LoginActivity.teacherVirtualList.get(i);
                    final CourseShow courseShow = new CourseShow();
                    courseShow.setDbID(virtualCourse.getVirtualCourseId());
                    courseShow.setId(virtualCourse.getVirtualCourseCode());
                    courseShow.setName(virtualCourse.getVirtualCourseName());
                    courseShow.setImgUrl(virtualCourse.getVirtualCourseImgUrl());
                    LoginActivity.teacherCourseShow.add(courseShow);
                }
                GetTeacherCourseAttentanceTotalNet.getTeacherCourseAttentanceTotal();
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
