package com.example.checkingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.dao.StudentDao;
import com.example.checkingsystem.dao.TeacherDao;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.net.LoginNet;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import java.util.List;

import util.ActivityColectorUtil;
import util.HttpCallbackListener;
import util.HttpUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner rolesSpinner;
    EditText usernameText;
    EditText passwordText;
    Button submitButton;
    public static StudentDao studentDao;
    public static TeacherDao teacherDao;
    public static Student studentStatic;
    public static Teacher teacherStatic;
    public static String token;
    private LoginActivity loginActivity;
    private TextView forgetPassword;
    private TextView regist;

    public static final String path="/com.acm.checkingsystem";
    public static final int REGIST_RESULT = 1;

    public static String roleStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityColectorUtil.addActivity(this);
        teacherStatic = new Teacher();
        studentStatic = new Student();
        studentDao = new StudentDao(this);
        teacherDao = new TeacherDao(this);
        roleStr = "";
        initResourse();
        loginActivity = this;

        submitButton.setOnClickListener(this);
        regist.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);

    }



    private void initResourse()
    {
        rolesSpinner = (Spinner) findViewById(R.id.login_roles);
        usernameText = (EditText)findViewById(R.id.login_usernameEdit);
        passwordText = (EditText)findViewById(R.id.login_passwordEdit);
        submitButton = (Button)findViewById(R.id.login_submit);
        regist = (TextView) findViewById(R.id.activity_login_regist);
        forgetPassword = (TextView)findViewById(R.id.activity_login_forget_password);

    }

    @Override
    public void onBackPressed() {
        ActivityColectorUtil.finishAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_submit:
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                roleStr = (String)rolesSpinner.getSelectedItem();
                if((username!=null)&&(password!=null)&&(!username.trim().equals(""))&&(!password.trim().equals("")))
                {
                    LoginNet loginNet = new LoginNet();
                    if("学生".equals(roleStr))
                    {
                        Log.e("netLogin",roleStr+" "+username+" "+password);
                        loginNet.studentSendInfo(loginActivity,username,password);

                    }
                    if("教师".equals(roleStr))
                    {
                        Intent intent = new Intent(LoginActivity.this,TeacherIndexActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.activity_login_regist:
                Intent intent = new Intent(this,RegistActivity.class);
                startActivityForResult(intent,REGIST_RESULT);
                break;
            case R.id.activity_login_forget_password:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case REGIST_RESULT:
                    Toast.makeText(this,"恭喜你，注册成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
