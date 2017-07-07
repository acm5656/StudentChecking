package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.RegistActivity;
import com.example.checkingsystem.entity.ResultObj;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.Md5Util;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/3/26.
 */

public class RegistNet {
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
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.setResult(Activity.RESULT_OK, intent);
                        activity.finish();
                    }else {
                        Toast.makeText(activity,resultObj.getMeta().getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                case RESULT_FALSE:
                    Toast.makeText(activity,"注册失败，请稍后再试",Toast.LENGTH_SHORT).show();
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


    public void studentRegist(String tel, String verifyCode, String password, Activity activity)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.STUDENT_REGIST;
        password = Md5Util.EncoderByMd5(password);
        String data = "registerTel="+tel+"&verifycode="+verifyCode+"&password="+password;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

    public void teacherRegist(String tel,String verifyCode,String password,Activity activity)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp + PathUtil.TEACHER_REGIST;
        password = Md5Util.EncoderByMd5(password);
        String data = "registerTel="+tel+"&verifycode="+verifyCode+"&password="+password;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

    public void assistantRegist(String tel, String verifyCode, String password, RegistActivity activity) {
        this.activity = activity;
        String path = HttpUtil.urlIp+PathUtil.ASSISTANT_REGIST;
        password = Md5Util.EncoderByMd5(password);
        String data = "registerTel="+tel+"&verifycode="+verifyCode+"&password="+password;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
}
