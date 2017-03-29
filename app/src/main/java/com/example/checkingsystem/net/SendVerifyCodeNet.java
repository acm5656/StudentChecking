package com.example.checkingsystem.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iflytek.msc.MSC;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/3/26.
 */

public class SendVerifyCodeNet {
    public ObjectMapper objectMapper = new ObjectMapper();
    Activity activity;
    public final int RESULT_TRUE = 1;
    public final int RESULT_FALSE = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case RESULT_TRUE:
                    ResultObj resultObj = ChangeTypeUtil.getResultObj(msg.obj.toString());
                    if(resultObj.getMeta().getResult()) {
                        Toast.makeText(activity, "发送成功", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(activity,"该手机号已被注册",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RESULT_FALSE:
                    Toast.makeText(activity,"发送失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Message message = new Message();
            message.what = RESULT_TRUE;

            message.obj = response;
            handler.sendMessage(message);

        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = RESULT_FALSE;
            handler.sendMessage(message);
        }
    };
    public void sendStudentRegistVerifyCode(String tel, Activity activity)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.GET_STUDENT_REGIST_TEL_VERIFY_CODE;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,"registerTel="+tel,HttpUtil.NO_STATUS);
    }
    public void sendStudentChangePasswordVerifyCode(String tel, Activity activity)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.GET_STUDENT_CHANGE_PASSWORD_TEL_VERIFY_CODE;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,"tel="+tel,HttpUtil.NO_STATUS);
    }

}
