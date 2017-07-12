package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourseLeave;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/10.
 */


public class AssistantHandlerLeaveNet {
    Activity activity;
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    ResultObj<VirtualCourseLeave> resultObj;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    Intent intent = new Intent(activity, AssistantIndexActivity.class);
                    intent.putExtra("virtualCourseLeave",(VirtualCourseLeave)msg.obj);
                    activity.setResult(Activity.RESULT_OK,intent);
                    activity.finish();
                    break;
                case FAIL:
                    String msgStr = (String) msg.obj;
                    Toast.makeText(activity,msgStr,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<VirtualCourseLeave>>() {});
                if(resultObj.getMeta().getResult())
                {

                    Message message = new Message();
                    message.what = SUCCESS;
                    message.obj = resultObj.getData();
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = FAIL;
                    message.obj = resultObj.getMeta().getMsg();
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FAIL;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };
    public void assistantHnadlerLeave(Activity activity, VirtualCourseLeave virtualCourseLeave)
    {
        this.activity = activity;
        String data = ChangeTypeUtil.getJSONString(virtualCourseLeave);
        String url = HttpUtil.urlIp+ PathUtil.ASSISTANT_HANDLER_STUDENT_LEAVE;
        HttpUtil.sendHttpPutRequest(url,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
}
