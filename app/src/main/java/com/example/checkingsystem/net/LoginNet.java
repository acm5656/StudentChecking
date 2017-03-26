package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.StudentVo;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.security.MessageDigest;

import java.sql.Timestamp;
import java.util.Date;


import Decoder.BASE64Encoder;
import util.AES;
import util.EncodeRuleProvider;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.SimpleCrypto;
import util.SymmetricEncoderUtil;

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
                        /**
                         *
                         *用来存放将要得到的数据
                         *
                         */
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
                        ResultObj<Teacher> resultObj = new ResultObj<>();


                        LoginActivity.teacherDao.addTeacher(LoginActivity.teacherStatic);

                        Intent intent = new Intent(activity, TeacherIndexActivity.class);
                        activity.startActivity(intent);
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
        final String address = HttpUtil.urlIp+"api/attendance-provider-user/student/login";

        String data = loginEncode(address,username,password);
        Log.e("sendInfo","-----------1");
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data);
    }

    String loginEncode(String url,String username,String password)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String md5Password = EncoderByMd5(password);
        String param = "username="+username+"&password="+md5Password+"&timestamp="+timestamp.toString();
        String urlCon = url+"?"+param;

        String sign = EncoderByMd5(urlCon);

        String urlFinal = param+"&sign="+sign;
        Log.e("netData",urlFinal);

        return urlFinal;
    }
    public String EncoderByMd5(String str)  {
        //确定计算方法
        MessageDigest md5= null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr =base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newstr;
    }
}
