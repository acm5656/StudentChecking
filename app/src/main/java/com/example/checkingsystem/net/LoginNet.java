package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.AssistantVo;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentVo;
import com.example.checkingsystem.entity.TeacherVo;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.Date;


import util.AES;
import util.EncodeRuleProvider;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.Md5Util;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/3/21.
 */

public class LoginNet {
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    private Activity activity;
    private ObjectMapper objectMapper = new ObjectMapper();
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
//                    Toast.makeText(activity,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    if(LoginActivity.roleStr.equals("学生")) {
                        ResultObj<StudentVo> resultObj = new ResultObj();
                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(),new TypeReference<ResultObj<StudentVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(resultObj.getMeta().getResult()) {
                            LoginActivity.studentStatic = resultObj.getData().getStudent();

                            Long longTime = new Long(resultObj.getData().getTimestamp());

                            Date date = new Date(longTime);
                            String token = null;
                            try {
                                token = AES.decode(
                                        EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())),
                                        resultObj.getData().getUuid());
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            if(LoginActivity.studentStatic.getStudentSchoolUsername()==null||LoginActivity.studentStatic.getStudentSchoolUsername().trim().equals(""))
                            {
                                LoginActivity.studentStatic.setStudentSchoolUsername("无");
                            }
                            if(LoginActivity.studentStatic.getStudentName()==null||LoginActivity.studentStatic.getStudentName().trim().equals(""))
                            {
                                LoginActivity.studentStatic.setStudentName("无");
                            }
                            LoginActivity.token = token;
                            LoginActivity.studentStatic.setStudentPassword(token);
                            LoginActivity.studentDao.addStudent(LoginActivity.studentStatic);
                            GetHeadPictureNet.getPicture(LoginActivity.studentStatic.getStudentHeadimageUrl());
                            GetVirtualCourseInfoNet.studentGetCourseTimeInfo();
                            Intent intent = new Intent(activity, StudentIndexActivity.class);
                            activity.startActivity(intent);
                        }
                        else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(LoginActivity.roleStr.equals("教师")) {
                        ResultObj<TeacherVo> resultObj = new ResultObj<>();
                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(),new TypeReference<ResultObj<TeacherVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(resultObj.getMeta().getResult()) {
                            LoginActivity.teacherStatic = resultObj.getData().getTeacher();

                            Long longTime = new Long(resultObj.getData().getTimestamp());

                            Date date = new Date(longTime);

                            String token = null;
                            try {
                                token = AES.decode(
                                        EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())),
                                        resultObj.getData().getUuid());
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                            LoginActivity.token = token;
                            LoginActivity.teacherStatic.setTeacherPassword(token);

                            if(LoginActivity.teacherStatic.getTeacherSchoolUsername()==null||LoginActivity.teacherStatic.getTeacherSchoolUsername().trim().equals(""))
                            {
                                LoginActivity.teacherStatic.setTeacherSchoolUsername("无");
                            }
                            if(LoginActivity.teacherStatic.getTeacherName()==null||LoginActivity.teacherStatic.getTeacherName().trim().equals(""))
                            {
                                LoginActivity.teacherStatic.setTeacherName("无");
                            }

                            LoginActivity.teacherDao.addTeacher(LoginActivity.teacherStatic);
                            GetHeadPictureNet.getPicture(LoginActivity.teacherStatic.getTeacherHeadimageUrl());
                            GetVirtualCourseInfoNet.teacherGetCourseTimeInfo();
                            Intent intent = new Intent(activity, TeacherIndexActivity.class);
                            activity.startActivity(intent);
                        }
                        else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(LoginActivity.roleStr.equals("导员"))
                    {
                        //登录成功的操作
                        /*
                        缺
                         */
                        ResultObj <AssistantVo> resultObj = null;

                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(), new TypeReference<ResultObj<AssistantVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(resultObj.getMeta().getResult())
                        {
                            LoginActivity.assistantStatic = resultObj.getData().getAssistant();
                            Long longTime = new Long(resultObj.getData().getTimestamp());

                            Date date = new Date(longTime);

                            String token = null;
                            try {
                                token = AES.decode(
                                        EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())),
                                        resultObj.getData().getUuid());
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                            LoginActivity.token = token;
                            LoginActivity.assistantStatic.setAssistantPassword(token);
                            LoginActivity.assistantDao.addAssistant(LoginActivity.assistantStatic);
                            GetHeadPictureNet.getPicture(LoginActivity.assistantStatic.getAssistantHeadimageUrl());


                            Intent intent = new Intent(activity, AssistantIndexActivity.class);
                            activity.startActivity(intent);

                        }else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }


                    }
                    break;
                case FALSE:
                    Toast.makeText(activity,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Message message = new Message();
            message.what = TRUE;
            message.obj = response;
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handler.sendMessage(message);
        }
    };
    public void studentLogin(Activity activity, String username, String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp+ PathUtil.STUDENT_LOGIN;

        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

    public void teacherLogin(Activity activity,String username,String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp + PathUtil.TEACHER_LOGIN;
        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
    public void assistantLogin(Activity activity,String username,String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp+PathUtil.ASSISTANT_LOGIN;
        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }


    String loginEncode(String url,String username,String password)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String md5Password = Md5Util.EncoderByMd5(password);
        String param = "username="+username+"&password="+md5Password+"&timestamp="+new Long(timestamp.getTime()).toString();
        String urlCon = url+"?"+param;

        String sign = Md5Util.EncoderByMd5(urlCon);

        String urlFinal = param+"&sign="+sign;

        return urlFinal;
    }

}
