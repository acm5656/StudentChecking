package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.assistant.activity.AssistantChangeSensitiveInfoActivity;
import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.AssistantVo;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentVo;
import com.example.checkingsystem.entity.TeacherVo;
import com.example.checkingsystem.student.activity.StudentChangeSensitiveInfoActivity;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherChangeSensitiveInfoActivity;
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
    //操作成功和失败的两个常量的标志
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    private Activity activity;
    private ObjectMapper objectMapper = new ObjectMapper();
    //登录获取数据后进行的操作
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
                    //学生登录进来进行的操作
                    if(LoginActivity.roleStr.equals("学生")) {
                        ResultObj<StudentVo> resultObj = new ResultObj();
                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(),new TypeReference<ResultObj<StudentVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //如果登录成功，进行的操作
                        if(resultObj.getMeta().getResult()) {
                            LoginActivity.studentStatic = resultObj.getData().getStudent();
                            //生成口令，在今后做跳转用
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
                            //将存储密码的字段，暂时用来存储token
                            LoginActivity.studentStatic.setStudentPassword(token);
                            //将字段存入本地数据库
                            LoginActivity.studentDao.addStudent(LoginActivity.studentStatic);
                            //获取头像照片
                            GetHeadPictureNet.getPicture(LoginActivity.studentStatic.getStudentHeadimageUrl());
                            //获取课表信息
                            GetVirtualCourseInfoNet.studentGetCourseTimeInfo();
                            //判断是不是还没有填写关键信息，如果没有，跳转到填写的页面，如果填写了，跳入主页面
                            if(
                                    LoginActivity.studentStatic.getStudentName()==null||
                                    LoginActivity.studentStatic.getStudentName().trim().equals("")||
                                    LoginActivity.studentStatic.getStudentNo()==null||
                                    LoginActivity.studentStatic.getStudentNo().trim().equals("")
                                    )
                            {
                                Intent intent = new Intent(activity, StudentChangeSensitiveInfoActivity.class);
                                activity.startActivity(intent);
                            }else {
                                Intent intent = new Intent(activity, StudentIndexActivity.class);
                                activity.startActivity(intent);
                            }

                        }
                        else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                    //教师登录进来进行的操作
                    if(LoginActivity.roleStr.equals("教师")) {
                        ResultObj<TeacherVo> resultObj = new ResultObj<>();
                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(),new TypeReference<ResultObj<TeacherVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //如果登录成功，进行的操作
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

                            LoginActivity.teacherDao.addTeacher(LoginActivity.teacherStatic);
                            //获取头像照片
                            GetHeadPictureNet.getPicture(LoginActivity.teacherStatic.getTeacherHeadimageUrl());
                            //获取课表信息
                            GetVirtualCourseInfoNet.teacherGetCourseTimeInfo();
                            //判断是不是还没有填写关键信息，如果没有，跳转到填写的页面，如果填写了，跳入主页面
                            if(
                                    LoginActivity.teacherStatic.getTeacherNo()==null||
                                    LoginActivity.teacherStatic.getTeacherNo().equals("")||
                                    LoginActivity.teacherStatic.getTeacherName()==null||
                                    LoginActivity.teacherStatic.getTeacherName().trim().equals("")
                                    )
                            {
                                Intent intent = new Intent(activity, TeacherChangeSensitiveInfoActivity.class);
                                activity.startActivity(intent);
                            }else {
                                Intent intent = new Intent(activity, TeacherIndexActivity.class);
                                activity.startActivity(intent);
                            }

                        }
                        else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }

                    }
                    //导员登录进来进行的操作
                    if(LoginActivity.roleStr.equals("导员"))
                    {
                        ResultObj <AssistantVo> resultObj = null;

                        try {
                            resultObj = objectMapper.readValue(msg.obj.toString().getBytes(), new TypeReference<ResultObj<AssistantVo>>() {});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //如果登录成功，进行的操作
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
                            //将存储密码的字段，暂时用来存储token
                            LoginActivity.token = token;
                            LoginActivity.assistantStatic.setAssistantPassword(token);

                            LoginActivity.assistantDao.addAssistant(LoginActivity.assistantStatic);
                            GetHeadPictureNet.getPicture(LoginActivity.assistantStatic.getAssistantHeadimageUrl());
                            //判断是不是还没有填写关键信息，如果没有，跳转到填写的页面，如果填写了，跳入主页面
                            if(
                                    LoginActivity.assistantStatic.getAssistantNo()==null||
                                    LoginActivity.assistantStatic.getAssistantNo().trim().equals("")||
                                    LoginActivity.assistantStatic.getAssistantName()==null||
                                    LoginActivity.assistantStatic.getAssistantName().trim().equals("")
                                    )
                            {
                                Intent intent = new Intent(activity, AssistantChangeSensitiveInfoActivity.class);
                                activity.startActivity(intent);
                            }else {
                                Intent intent = new Intent(activity, AssistantIndexActivity.class);
                                activity.startActivity(intent);
                            }


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
    //登录成功后获取到的数据
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
    //学生登录的请求数据和url处理
    public void studentLogin(Activity activity, String username, String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp+ PathUtil.STUDENT_LOGIN;

        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
    //教师登录的请求数据和url处理
    public void teacherLogin(Activity activity,String username,String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp + PathUtil.TEACHER_LOGIN;
        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
    //辅导员登录的请求数据和url处理
    public void assistantLogin(Activity activity,String username,String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp+PathUtil.ASSISTANT_LOGIN;
        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

    //对密码等登录数据进行的处理
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
