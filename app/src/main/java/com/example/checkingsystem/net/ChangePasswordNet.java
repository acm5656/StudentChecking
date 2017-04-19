package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/3/28.
 */

public class ChangePasswordNet {
    private Activity activity;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
                    Toast.makeText(activity,"恭喜你,修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = null;
                    if(LoginActivity.roleStr.equals("教师")) {
                        intent = new Intent(activity, TeacherIndexActivity.class);
                    }
                    if(LoginActivity.roleStr.equals("学生")){
                        intent = new Intent(activity, StudentIndexActivity.class);
                    }
                    intent.putExtra("data", "修改成功");
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                    break;
                case FALSE:
                    Toast.makeText(activity,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
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
                message.what = TRUE;
                handler.sendMessage(message);
            }
            else {
                Message message = new Message();
                message.what = FALSE;
                handler.sendMessage(message);
            }

        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handler.sendMessage(message);
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
