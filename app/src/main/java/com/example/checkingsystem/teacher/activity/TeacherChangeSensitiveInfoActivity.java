package com.example.checkingsystem.teacher.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class TeacherChangeSensitiveInfoActivity extends AppCompatActivity implements View.OnClickListener{
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    private ImageView sensitiveBack;
    private EditText sensitiveName;
    private EditText sensitiveId;
    private RadioGroup sensitiveSex;
    private Button sensitiveSubmit;
    String name;
    String id;
    String sex;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    LoginActivity.teacherStatic.setTeacherNo(id);
                    LoginActivity.teacherStatic.setTeacherName(name);
                    LoginActivity.teacherStatic.setTeacherGender(sex);
                    Intent intent =  new Intent(TeacherChangeSensitiveInfoActivity.this,TeacherIndexActivity.class);
                    startActivity(intent);
                    break;
                case FAIL:
                    Toast.makeText(getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_change_sensitive_info);
        initUI();
    }
    private void initUI(){
        sensitiveBack= (ImageView) findViewById(R.id.iv_activity_teacher_change_sensitive_info_back);
        sensitiveName = (EditText) findViewById(R.id.activity_teacher_change_sensitive_name);
        sensitiveId= (EditText) findViewById(R.id.activity_teacher_change_sensitive_school_id);
        sensitiveSex= (RadioGroup) findViewById(R.id.activity_teacher_change_sensitive_sex_radioGroup);
        sensitiveSubmit= (Button) findViewById(R.id.acticity_teacher_change_sensitive_info_submit);
        sensitiveSubmit.setOnClickListener(this);
        sensitiveBack.setOnClickListener(this);
    }
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = ChangeTypeUtil.getResultObj(response);
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                message.what = SUCCESS;
                handler.sendMessage(message);
            }else {
                Message message = new Message();
                message.what = FAIL;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FAIL;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.acticity_teacher_change_sensitive_info_submit:
                name = sensitiveName.getText().toString();
                id = sensitiveId.getText().toString();
                sex = ((RadioButton)findViewById(sensitiveSex.getCheckedRadioButtonId())).getText().toString();
                if(name==null||name.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(id==null||id.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"请输入编号",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(sex==null||sex.trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"请选择性别",Toast.LENGTH_SHORT).show();
                    break;
                }
                String url = HttpUtil.urlIp + PathUtil.TEACHER_CHANGE_SENSITIVE_INFO;
                Teacher teacher = new Teacher();
                teacher.setTeacherId(LoginActivity.teacherStatic.getTeacherId());
                teacher.setTeacherName(name);
                teacher.setTeacherGender(sex);
                teacher.setTeacherNo(id);
                String data = ChangeTypeUtil.getJSONString(teacher);
                HttpUtil.sendHttpPutRequest(url,httpCallbackListener,data,HttpUtil.CONTENT_TYPE_IS_APPLICATION_JSON);
                break;
            case R.id.iv_activity_teacher_change_sensitive_info_back:
                onBackPressed();
                break;
        }
    }
}
