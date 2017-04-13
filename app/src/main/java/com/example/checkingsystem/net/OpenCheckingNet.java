package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.entity.Attendance;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.teacher.activity.TeacherCheckingStudentAttendanceList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/6.
 */

public class OpenCheckingNet {
    public static final int START_TEACHER_CHECKING_STUDENT_ATTENTANCE_LIST = 1;
    private ResultObj<Attendance> resultObj;
    private ObjectMapper objectMapper = new ObjectMapper();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(activity, TeacherCheckingStudentAttendanceList.class);
            String attentanceID = resultObj.getData().getCourseAttendanceId();
            intent.putExtra("attentanceID",attentanceID);
            activity.startActivityForResult(intent,START_TEACHER_CHECKING_STUDENT_ATTENTANCE_LIST);

        }
    };
    private HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Log.e("test",response);
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<Attendance>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
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
