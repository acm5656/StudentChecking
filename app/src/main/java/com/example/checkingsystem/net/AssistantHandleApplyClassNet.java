package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.ClassGrade;
import com.example.checkingsystem.entity.ResultObj;
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

public class AssistantHandleApplyClassNet {
    Activity activity;
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    ClassGrade classGrade = (ClassGrade) msg.obj;
                    Intent intent = new Intent(activity, AssistantIndexActivity.class);
                    intent.putExtra("classGrade",classGrade);
                    activity.setResult(Activity.RESULT_OK,intent);
                    activity.finish();
                    break;
                case FAIL:
                    Toast.makeText(activity,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultObj<ClassGrade> resultObj = null;
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<ClassGrade>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                message.what = SUCCESS;
                message.obj = resultObj.getData();
                handler.sendMessage(message);
            }else {
                Message message = new Message();
                message.what = FAIL;

                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

            Message message = new Message();
            message.what = FAIL;
            handler.sendMessage(message);
        }
    };
    public void assistantHandleApplyClass(Activity activity, ClassGrade classGrade)
    {
        this.activity = activity;
        String url = HttpUtil.urlIp+ PathUtil.ASSISTANT_HANDLER_STUDENT_CLASS;
        String data = ChangeTypeUtil.getJSONString(classGrade);
        HttpUtil.sendHttpPutRequest(url,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }


}
