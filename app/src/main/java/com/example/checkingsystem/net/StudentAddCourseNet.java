package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/6/29.
 */

public class StudentAddCourseNet {
    final int ERROR = 0;
    final int SUCCESS = 1;
    Activity activity;
    ResultObj<VirtualCourse> virtualCourseResultObj = null;
    Bitmap bitmap = null;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ERROR:
                    Toast.makeText(activity,msg.obj+"",Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    VirtualCourse virtualCourse = (VirtualCourse) msg.obj;
                    ((StudentIndexActivity)activity).studentCourseIndexFragment.addCourse(virtualCourse,bitmap);
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
                    virtualCourseResultObj = objectMapper.readValue(response, new TypeReference<ResultObj<VirtualCourse>>(){});
                    GetPictureNet getPictureNet = new GetPictureNet();
                    getPictureNet.getPicture(virtualCourseResultObj.getData().getVirtualCourseImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                        @Override
                        public void onFinish(InputStream inputStream) {
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            Message message = new Message();
                            message.what = SUCCESS;
                            message.obj = virtualCourseResultObj.getData();
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onError(Exception e) {
                            Message message = new Message();
                            message.what = SUCCESS;
                            message.obj = virtualCourseResultObj.getData();
                            handler.sendMessage(message);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    public void studentAddCourse(Activity activity,String code,String studentID)
    {

        this.activity = activity;
        String data = "code="+code+"&studentId="+studentID;
        String address = HttpUtil.urlIp+ PathUtil.STUDENT_ADD_COURSE;
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
}
