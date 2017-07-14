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
//学生用来加入课程的net
public class StudentAddCourseNet {
    final int ERROR = 0;
    final int SUCCESS = 1;
    Activity activity;
    ResultObj<VirtualCourse> virtualCourseResultObj = null;
    Bitmap bitmap = null;
    //因为监听器监听到的是在子线程中，不能更新UI，利用handler进行UI更新
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
    //当收到服务器返回数据后执行的监听接口
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            //解析数据
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
                //如果操作失败，执行的操作
                Message message = new Message();
                message.what = ERROR;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            //如果连接过程出现异常，执行的操作
            Message message = new Message();
            message.what = ERROR;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };
    //生成请求的url和请求的数据
    public void studentAddCourse(Activity activity,String code,String studentID)
    {

        this.activity = activity;
        String data = "code="+code+"&studentId="+studentID;
        String address = HttpUtil.urlIp+ PathUtil.STUDENT_ADD_COURSE;
        //发送数据
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
}
