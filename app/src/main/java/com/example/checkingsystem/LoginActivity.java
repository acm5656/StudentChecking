package com.example.checkingsystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.checkingsystem.entity.StudentCourseTimeTable;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.entity.TeacherCourseTimeTable;
import com.example.checkingsystem.net.LoginNet;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import java.util.List;

import util.ActivityColectorUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
//登录的活动
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //获取身份的控件
    Spinner rolesSpinner;
    //输入用户名的控件
    EditText usernameText;
    //输入密码的控件
    EditText passwordText;
    //提交的控件
    Button submitButton;
    //用来存储学生信息的dao
    public static StudentDao studentDao;
    //用来存储教师信息的dao
    public static TeacherDao teacherDao;
    //用来存储登录学生的各种信息
    public static Student studentStatic;
    //用来存储登录教师的各种信息
    public static Teacher teacherStatic;
    //用来记录令牌
    public static String token;
    //忘记密码的控件
    private TextView forgetPassword;
    //注册的控件
    private TextView regist;
    //用来存储登录后的头像的bitmap
    public static Bitmap headPictureBitmap = null;
    //本手机创建项目的根目录，本地存储图片用
    public static final String path="/com.acm.checkingsystem";
    //注册时候传过去的参数，用来判断回显后是哪个活动的回显
    public static final int REGIST_RESULT = 1;
    //用来记录角色身份，是哪个用户
    public static String roleStr;
    //用来记录学生课表
    public static List<StudentCourseTimeTable> studentCourseTimeTableList = null;
    //用来记录教师课表
    public static List<TeacherCourseTimeTable> teacherCourseTimeTableList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //资源初始化
        ActivityColectorUtil.addActivity(this);
        teacherStatic = new Teacher();
        studentStatic = new Student();
        studentDao = new StudentDao(this);
        teacherDao = new TeacherDao(this);
        roleStr = "";
        initResourse();

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
            //登录点击事件，做判断账号密码是否为空，不为空则根据身份选择调用不同的登录
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
                        loginNet.studentSendInfo(this,username,password);

                    }
                    if("教师".equals(roleStr))
                    {
                        loginNet.teacherLogin(this,username,password);
                    }
                }
                break;
            //注册点击事件，启动另一个活动
            case R.id.activity_login_regist:
                Intent intent = new Intent(this,RegistActivity.class);
                startActivityForResult(intent,REGIST_RESULT);
                break;
            //忘记密码点击事件，暂时还没做
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
