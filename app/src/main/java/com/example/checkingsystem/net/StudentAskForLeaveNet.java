package com.example.checkingsystem.net;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourseAttendance;
import com.example.checkingsystem.entity.VirtualCourseLeave;

import java.sql.Timestamp;
import java.util.Date;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/7.
 */

public class StudentAskForLeaveNet {
    private Activity activity;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case TRUE:
                    ResultObj resultObj = (ResultObj) msg.obj;
                    Toast.makeText(activity,resultObj.getMeta().getMsg(),Toast.LENGTH_SHORT).show();
                    break;
                case FALSE:
                    Toast.makeText(activity,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {

            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            Message message = new Message();
            message.what = TRUE;
            message.obj = resultObj;
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handler.sendMessage(message);
        }
    };

    public void studentAskForLeave(Activity activity, String studentID, String askForLeaverReason, Date beginDate,Date endDate)
    {
        this.activity = activity;
        VirtualCourseLeave virtualCourseLeave = new VirtualCourseLeave();
        virtualCourseLeave.setVirtualCourseLeaveBegin(new Timestamp(beginDate.getTime()));
        virtualCourseLeave.setVirtualCourseLeaveEnd(new Timestamp(endDate.getTime()));
        virtualCourseLeave.setVirtualCourseLeaveReason(askForLeaverReason);
        virtualCourseLeave.setVirtualCourseLeaveStuId(studentID);
        String address = HttpUtil.urlIp+PathUtil.STUDENT_ASK_FOR_LEAVE;
        String data = ChangeTypeUtil.getJSONString(virtualCourseLeave);
        HttpUtil.sendHttpPostRequest(address,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }

}
