package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;

import com.example.checkingsystem.VerifyFaceActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.student.activity.StudentIndexActivity;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/7.
 */

public class StudentPutCheckingVerifyNet {
    private HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta().getResult()) {
                Intent intent = new Intent(activity, StudentIndexActivity.class);
                intent.putExtra("data_return", "考勤成功，请好好听课");
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            }else {
                Intent intent = new Intent(activity, StudentIndexActivity.class);
                intent.putExtra("data_return", "考勤失败，请重试");
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            }
        }

        @Override
        public void onError(Exception e) {
            Intent intent = new Intent(activity, StudentIndexActivity.class);
            intent.putExtra("data_return", "考勤失败，请重试");
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    };
    private Activity activity;
    public void sendStudentCheckingVerify(Activity activity,String studentID,String mac)
    {
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.STUDENT_CHECKING_VERIFY_FACE;
        String data = "mac="+mac+"&studentId="+studentID;
        HttpUtil.sendHttpPostRequest(path,httpCallbackListener,data,HttpUtil.NO_STATUS);
    }
}
