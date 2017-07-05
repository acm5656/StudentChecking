package com.example.checkingsystem.net;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.entity.Attendance;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourseAttendance;
import com.example.checkingsystem.entity.VirtualCourseAttendanceItem;
import com.example.checkingsystem.teacher.activity.TeacherCheckingStudentAttendanceListActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Timestamp;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/6.
 */

public class OpenCheckingNet {
    final static int ERROR = 0;
    final static int SUCCESS = 1;
    String courseID;
    public static final int START_TEACHER_CHECKING_STUDENT_ATTENTANCE_LIST = 1;
    VirtualCourseAttendance virtualCourseAttendance;
    private ResultObj<VirtualCourseAttendance> resultObj;
    private ObjectMapper objectMapper = new ObjectMapper();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ERROR:
                    Toast.makeText(activity,"开启失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    String attentanceID = virtualCourseAttendance.getVirtualCourseAttendanceId();
                    Intent intent = new Intent(activity, TeacherCheckingStudentAttendanceListActivity.class);
                    intent.putExtra("attentanceID",attentanceID);
                    intent.putExtra("endTime",new Long(virtualCourseAttendance.getVirtualCourseAttendanceGmtEnd().getTime()));
                    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    while (!bluetoothAdapter.isEnabled()) {}
                    if(bluetoothAdapter.isEnabled()) {

                        activity.startActivityForResult(intent, START_TEACHER_CHECKING_STUDENT_ATTENTANCE_LIST);
                    }
                    break;
            }
        }
    };
    int i = 0;
    private HttpCallbackListener secondeHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<VirtualCourseAttendance>>() {});
                if(resultObj.getMeta().getMsg().equals("考勤开启成功"))
                {
                    Message message = new Message();
                    message.what = SUCCESS;

                    handler.sendMessage(message);
                }else {
                    if(i<4) {
                        Thread.sleep(500);
                        i++;
                        String data = ChangeTypeUtil.getJSONString(virtualCourseAttendance);
                        String path = HttpUtil.urlIp+ PathUtil.TEACHER_OPEN_CHECKING;
                        HttpUtil.sendHttpPostRequest(path,secondeHttpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
                    }
                    else {
                        Message message = new Message();
                        message.what = ERROR;
                        handler.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };

    private HttpCallbackListener initHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<Integer> integerResultObj = null;
            try {

                integerResultObj = objectMapper.readValue(response, new TypeReference<ResultObj < Integer >>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(integerResultObj.getMeta().getResult())
            {
                String data = ChangeTypeUtil.getJSONString(virtualCourseAttendance);
                String path = HttpUtil.urlIp+ PathUtil.TEACHER_OPEN_CHECKING;
                HttpUtil.sendHttpPostRequest(path,secondeHttpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
            }else {
                Message message = new Message();
                message.what = ERROR;
                handler.sendMessage(message);
            }

        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };

    private HttpCallbackListener firstHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<VirtualCourseAttendance>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                virtualCourseAttendance=resultObj.getData();
                String url = HttpUtil.urlIp+PathUtil.TEACHER_INIT_OPEN_CHECKING+"?virtualCourseAttendanceId="+virtualCourseAttendance.getVirtualCourseAttendanceId();
                HttpUtil.sendHttpPostRequest(url,initHttpCallbackListener,"",HttpUtil.NO_STATUS);
            }else {
                Message message = new Message();
                message.what = ERROR;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };
    private Activity activity;
    public void OpenTeacherChecking(Activity activity, String courseID, String macAdress, Timestamp beginTime, Timestamp endTime)
    {
        this.courseID = courseID;
        this.activity = activity;
        String path = HttpUtil.urlIp+ PathUtil.TEACHER_OPEN_CHECKING;
        VirtualCourseAttendance virtualCourseAttendance = new VirtualCourseAttendance();
        virtualCourseAttendance.setVirtualCourseAttendanceGmtBegin(beginTime);
        virtualCourseAttendance.setVirtualCourseAttendanceGmtEnd(endTime);
        virtualCourseAttendance.setVirtualCourseAttendanceCourseId(courseID);
        virtualCourseAttendance.setVirtualCourseAttendanceMac(macAdress);
        this.virtualCourseAttendance = virtualCourseAttendance;
        String data = ChangeTypeUtil.getJSONString(virtualCourseAttendance);
        HttpUtil.sendHttpPostRequest(path,firstHttpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
}
