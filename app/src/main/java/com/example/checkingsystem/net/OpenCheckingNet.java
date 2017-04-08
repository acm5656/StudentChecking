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
 * Created by 那年.盛夏 on 2017/4/6.
 */

public class OpenCheckingNet {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {


        }
    };
    private HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Log.e("test",response);
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    private Activity activity;
    public void OpenTeacherChecking(Activity activity,String macAdress,String courseID,String teacherID,String endTime)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.TEACHER_OPEN_CHECKING;
        String data = "courseAttendanceCourseTimeId="+courseID+"&courseAttendanceMac="+macAdress+"&courseAttendanceGmtEnd="+endTime+"&teacherId="+teacherID;

        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);


    }
}
