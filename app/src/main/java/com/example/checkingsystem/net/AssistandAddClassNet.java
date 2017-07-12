package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.ResultObj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/7/9.
 */

public class AssistandAddClassNet {
    Activity activity;
    final static int ERROR = 0;
    final static int SUCCESS = 1;
    ResultObj<Class> resultObj;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            String msgStr = (String) msg.obj;
            switch (msg.what)
            {
                case SUCCESS:
                    Intent intent = new Intent(activity, AssistantIndexActivity.class);
                    intent.putExtra("class",resultObj.getData());
                    intent.putExtra("msg",msgStr);
                    activity.setResult(Activity.RESULT_OK,intent);
                    activity.finish();
                    break;
                case ERROR:
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
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<Class>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                message.what = SUCCESS;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
            else {
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
    public void assistantAddClass(Activity activity,Class assistantClass)
    {
        this.activity = activity;
        String url = HttpUtil.urlIp+PathUtil.ASSISTANT_ADD_CLASS;
        String data = ChangeTypeUtil.getJSONString(assistantClass);
        HttpUtil.sendHttpPostRequest(url,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
}
