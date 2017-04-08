package com.example.checkingsystem.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.entity.ResultObj;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/7.
 */

public class StudentAskForLeaveNet {
    private Activity activity;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ResultObj resultObj = (ResultObj) msg.obj;
            Toast.makeText(activity,resultObj.getMeta().getMsg(),Toast.LENGTH_SHORT).show();
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {

            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            Message message = new Message();
            message.obj = resultObj;
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {

        }
    };

    public void studentAskForLeave(Activity activity,String studentID,String askForLeaverReason,String courseID)
    {
        this.activity = activity;
        String address = HttpUtil.urlIp+PathUtil.STUDENT_ASK_FOR_LEAVE;
        String data = "courseLeaveCtimeId="+courseID+"&courseLeaveStuId="+studentID+"&courseLeaveReason="+askForLeaverReason;
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }

}
