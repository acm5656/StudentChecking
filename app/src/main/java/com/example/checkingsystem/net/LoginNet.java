package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentVo;
import com.example.checkingsystem.entity.Teacher;
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
    private Activity activity;
    private ObjectMapper objectMapper = new ObjectMapper();
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
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

                            Log.e("test","----"+msg.obj.toString());
                            Log.e("test","----"+EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())));
                            String token = null;
                            try {
                                token = AES.decode(
                                        EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())),
                                        resultObj.getData().getUuid());
                            } catch (Exception e) {
                                Log.e("test----",e.getMessage());
                                e.printStackTrace();

                            }
                            Log.e("test","----"+token);
                            LoginActivity.token = token;
                            LoginActivity.studentStatic.setStudentPassword(token);
                            Log.e("test--",LoginActivity.studentStatic.toString());
                            LoginActivity.studentDao.addStudent(LoginActivity.studentStatic);

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

                            Log.e("test","----"+msg.obj.toString());
                            Log.e("test","----"+EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())));
                            String token = null;
                            try {
                                token = AES.decode(
                                        EncodeRuleProvider.getRuleByTimestamp(new Timestamp(date.getTime())),
                                        resultObj.getData().getUuid());
                            } catch (Exception e) {
                                Log.e("test----",e.getMessage());
                                e.printStackTrace();

                            }
                            Log.e("test","----"+token);
                            LoginActivity.token = token;
                            LoginActivity.teacherStatic.setTeacherPassword(token);
                            Log.e("test--",LoginActivity.studentStatic.toString());
                            LoginActivity.teacherDao.addTeacher(LoginActivity.teacherStatic);

                            Intent intent = new Intent(activity, TeacherIndexActivity.class);
                            activity.startActivity(intent);
                        }
                        else {
                            Toast.makeText(activity,"账号密码错误",Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
            }
        }
    };
    public HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Message message = new Message();
            message.what = 0;
            message.obj = response;
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {
        }
    };
    public void studentSendInfo(Activity activity, String username, String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp+ PathUtil.STUDENT_LOGIN;

        String data = loginEncode(address,username,password);
        Log.e("sendInfo","-----------1");
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

    public void teacherLogin(Activity activity,String username,String password)
    {
        this.activity = activity;
        final String address = HttpUtil.urlIp + PathUtil.TEACHER_LOGIN;
        String data = loginEncode(address,username,password);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);


    }


    String loginEncode(String url,String username,String password)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String md5Password = Md5Util.EncoderByMd5(password);
        String param = "username="+username+"&password="+md5Password+"&timestamp="+timestamp.toString();
        String urlCon = url+"?"+param;

        String sign = Md5Util.EncoderByMd5(urlCon);

        String urlFinal = param+"&sign="+sign;
        Log.e("netData",urlFinal);

        return urlFinal;
    }

}
