package com.example.checkingsystem.net;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.student.activity.StudentIndexActivity;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 那年.盛夏 on 2017/4/1.
 */

public class ChangeInfoNet{
    private Activity activity;
    private HttpCallbackListener studentHttpCallListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            Log.e("test",resultObj.getMeta().getResult()+"");
            if(resultObj.getMeta().getResult())
            {
                Intent intent = new Intent(activity, StudentIndexActivity.class);
                intent.putExtra("data","修改成功");
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    public void studentChangeInfo(String nickName, String email, String headImagePath, Activity activity)
    {
        this.activity = activity;
        String url = HttpUtil.urlIp + PathUtil.CHANGE_STUDENT_INFO;
        Student student  = new Student();
        student.setStudentId(LoginActivity.studentStatic.getStudentId());
        student.setStudentNickname(nickName);
        student.setStudentEmail(email);
        student.setStudentHeadimageUrl(headImagePath);
        HttpUtil.sendHttpPutRequest(url,studentHttpCallListener, ChangeTypeUtil.getJSONString(student),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);

    }
    public void teacherChangeInfo(String nickName, String email, String headImagePath, Activity activity)
    {
        this.activity = activity;
        String url = HttpUtil.urlIp + PathUtil.TEACHER_CHANGE_INFO;
        Teacher teacher  = new Teacher();
        teacher.setTeacherId(LoginActivity.teacherStatic.getTeacherId());
        teacher.setTeacherNickname(nickName);
        teacher.setTeacherEmail(email);
        teacher.setTeacherHeadimageUrl(headImagePath);
        HttpUtil.sendHttpPutRequest(url,studentHttpCallListener, ChangeTypeUtil.getJSONString(teacher),HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
    }
}
