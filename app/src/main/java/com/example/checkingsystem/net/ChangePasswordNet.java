package com.example.checkingsystem.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/3/28.
 */

public class ChangePasswordNet {
    private Activity activity;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    Toast.makeText(activity,"恭喜你,修改成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }

        }

        @Override
        public void onError(Exception e) {

        }
    };
    public void studentChangePassword(Activity activity, String tel, String password, String verifyCode)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.CHANGE_STUDENT_PASSWORD;
        String data = "studentNewPassword="+password+"&tel="+tel+"&verifycode="+verifyCode;
        HttpUtil.sendHttpPutRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);

    }
    public void teacherChangePassword(Activity activity, String tel, String password, String verifyCode)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.TEACHER_CHANGE_PASSWORD;
        String data = "teacherNewPassword="+password+"&tel="+tel+"&verifycode="+verifyCode;
        HttpUtil.sendHttpPutRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);

    }
}
