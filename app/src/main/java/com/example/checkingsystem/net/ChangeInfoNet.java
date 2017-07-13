package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.assistant.activity.AssistantIndexActivity;
import com.example.checkingsystem.entity.Assistant;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/1.
 */

public class ChangeInfoNet{
    private Activity activity;
    private String url;
    private String role ;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    String nickName;
    String email;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case FALSE:
                    String msgStr = (String) msg.obj;
                    Toast.makeText(activity,msgStr,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private HttpCallbackListener studentHttpCallListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta().getResult())
            {
                Intent intent = null;
                if("教师".equals(role))
                {
                    LoginActivity.teacherStatic.setTeacherHeadimageUrl(url);
                    LoginActivity.teacherStatic.setTeacherNickname(nickName);
                    LoginActivity.teacherStatic.setTeacherEmail(email);

                    intent = new Intent(activity, TeacherIndexActivity.class);

                }else if("学生".equals(role))
                {
                    LoginActivity.studentStatic.setStudentHeadimageUrl(url);
                    LoginActivity.studentStatic.setStudentNickname(nickName);
                    LoginActivity.studentStatic.setStudentEmail(email);
                    intent = new Intent(activity, StudentIndexActivity.class);
                }else if ("导员".equals(role))
                {
                    LoginActivity.assistantStatic.setAssistantHeadimageUrl(url);
                    LoginActivity.assistantStatic.setAssistantNickname(nickName);
                    LoginActivity.assistantStatic.setAssistantEmail(email);
                    intent = new Intent(activity, AssistantIndexActivity.class);
                }
                if(intent!=null) {
                    intent.putExtra("data", "修改成功");
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            }
            else {
                Message message = new Message();
                message.what = FALSE;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };


    public void studentChangeInfo(String nickName, String email, String headImagePath, Activity activity)
    {
        role = "学生";
        this.activity = activity;
        url = HttpUtil.urlIp + PathUtil.CHANGE_STUDENT_INFO;
        Student student  = new Student();
        student.setStudentId(LoginActivity.studentStatic.getStudentId());
        student.setStudentNickname(nickName);
        student.setStudentEmail(email);
        student.setStudentHeadimageUrl(headImagePath);
        this.nickName = nickName;
        this.email = email;
        HttpUtil.sendHttpPutRequest(url,studentHttpCallListener, ChangeTypeUtil.getJSONString(student),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);

    }
    public void teacherChangeInfo(String nickName, String email, String headImagePath, Activity activity)
    {
        role = "教师";
        this.activity = activity;
        url = HttpUtil.urlIp + PathUtil.TEACHER_CHANGE_INFO;
        Teacher teacher  = new Teacher();
        teacher.setTeacherId(LoginActivity.teacherStatic.getTeacherId());
        teacher.setTeacherNickname(nickName);
        teacher.setTeacherEmail(email);
        teacher.setTeacherHeadimageUrl(headImagePath);
        this.nickName = nickName;
        this.email = email;
        HttpUtil.sendHttpPutRequest(url,studentHttpCallListener, ChangeTypeUtil.getJSONString(teacher),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
    public void assistantChangeInfo(String email,String headImagePath, Activity activity){
        role = "导员";
        this.activity = activity;
        url= HttpUtil.urlIp+PathUtil.ASSISTANT_CHANGE_INFO;
        Assistant assistant = new Assistant();
        assistant.setAssistantId(LoginActivity.assistantStatic.getAssistantId());
        assistant.setAssistantEmail(email);
        assistant.setAssistantHeadimageUrl(headImagePath);
        this.email = email;
        HttpUtil.sendHttpPutRequest(url,studentHttpCallListener,ChangeTypeUtil.getJSONString(assistant),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }

}
