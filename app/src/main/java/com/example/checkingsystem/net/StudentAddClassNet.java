package com.example.checkingsystem.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.ClassGrade;
import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/8.
 */

public class StudentAddClassNet {
    final int SUCCESS = 0;
    final int ERROR = 1;
    Activity activity;
    ResultObj<ClassGrade> resultObj;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    Toast.makeText(activity,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(activity,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<ClassGrade>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            if(resultObj.getMeta().getResult())
            {

                message.what = SUCCESS;
                message.obj = resultObj.getMeta().getMsg();
            }else {
                message.what = ERROR;
                message.obj = resultObj.getMeta().getMsg();
            }
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };

    public void studentAddClass(Activity activity,String studentID,String classCode)
    {
        this.activity = activity;
        String url = HttpUtil.urlIp+PathUtil.STUDENT_ADD_CLASS;
        String data = "classCode="+classCode+"&studentId="+studentID;
        HttpUtil.sendHttpPostRequest(url,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

}
