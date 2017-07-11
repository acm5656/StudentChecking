package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.teacher.activity.TeacherAddCourseActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/6/29.
 */

public class TeacherAddCourseNet {
    final int ERROR = 0;
    final int SUCCESS = 1;
    Activity activity;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ERROR:
                    Toast.makeText(activity,msg.obj+"",Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    Intent intent = new Intent();
                    VirtualCourse virtualCourse = (VirtualCourse) msg.obj;
                    intent.putExtra("course",virtualCourse);
                    intent.putExtra("bitmap",((TeacherAddCourseActivity)activity).bitmap);
                    activity.setResult(Activity.RESULT_OK,intent);
                    activity.finish();
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
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    ResultObj<VirtualCourse> virtualCourseResultObj = objectMapper.readValue(response, new TypeReference<ResultObj<VirtualCourse>>(){});
                    Message message = new Message();
                    message.what = SUCCESS;
                    message.obj = virtualCourseResultObj.getData();
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resultObj.getData();
            }else {
                Message message = new Message();
                message.what = ERROR;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };
    public void addCourseNet(Activity activity, VirtualCourse virtualCourse)
    {
        this.activity = activity;
        String data = ChangeTypeUtil.getJSONString(virtualCourse);
        String address = HttpUtil.urlIp+PathUtil.TEACHER_ADD_COURSE;
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
}
