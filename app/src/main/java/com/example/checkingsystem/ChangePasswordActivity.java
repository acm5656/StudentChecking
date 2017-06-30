package com.example.checkingsystem;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkingsystem.net.ChangePasswordNet;
import com.example.checkingsystem.net.SendVerifyCodeNet;

import util.Md5Util;
//修改密码用的活动
public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    //密码的输入框
    private EditText changePassword;
    //确认密码的输入框
    private EditText changePasswordChecking;
    //获得验证码的按钮
    private Button getVerifyCode;
    //验证码的输入框
    private EditText inputVerifyCode;
    //提交按钮
    private Button submitButton;
    //常亮，用来判断当前是否可以获得验证码用
    public static final int IS_GET_VERIFYCODE = 1;
    public static final int IS_AFTER_TIME = 2;
    //用来记录电话号码
    String tel = null;
    //线程用来管理获取验证码的线程
    Thread thread;
    //用来处理线程的结果
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                //第一个是用来显示点击获取验证码后的读秒
                case IS_GET_VERIFYCODE:
                    getVerifyCode.setText((60-new Integer(msg.obj.toString()))+"s");
                    break;
                //用来重新获取点击事件
                case IS_AFTER_TIME:
                    getVerifyCode.setClickable(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        getVerifyCode.setBackground(getResources().getDrawable(R.drawable.input_login));
                        getVerifyCode.setText("获取验证码");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initSourse();
        getVerifyCode.setOnClickListener(this);
        submitButton.setOnClickListener(this);

    }

    //初始化资源
    private void initSourse() {

        changePassword = (EditText) findViewById(R.id.change_password_input_password);
        changePasswordChecking = (EditText)findViewById(R.id.change_password_checking_input_password);
        getVerifyCode = (Button)findViewById(R.id.change_password_get_verifycode_button);
        inputVerifyCode = (EditText)findViewById(R.id.change_password_input_verifycode);
        submitButton = (Button)findViewById(R.id.change_password_submit);
        if("教师".equals(LoginActivity.roleStr))
        {
            tel = LoginActivity.teacherStatic.getTeacherTel();
        }
        if("学生".equals(LoginActivity.roleStr))
        {
            tel = LoginActivity.studentStatic.getStudentTel();
        }
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.change_password_get_verifycode_button:
                changeGetVerifyCodeStyle();
                if(LoginActivity.roleStr.equals("学生")) {
                    SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                    sendVerifyCodeNet.sendStudentChangePasswordVerifyCode(tel, this);
                }
                if(LoginActivity.roleStr.equals("教师"))
                {
                    SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                    sendVerifyCodeNet.sendTeacherChangePasswordVerifyCode(tel, this);
                }
                break;
            case R.id.change_password_submit:
                boolean judge = true;
                String password = changePassword.getText().toString();
                String checkingPassword = changePasswordChecking.getText().toString();
                String verifycode = inputVerifyCode.getText().toString();
                if(password.trim().equals("")||checkingPassword.trim().equals(""))
                {
                    judge = false;
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                }
                if(!password.trim().equals(checkingPassword.trim()))
                {
                    judge = false;
                    Toast.makeText(this,"密码不一致",Toast.LENGTH_SHORT).show();
                }
                if(judge)
                {
                    if(LoginActivity.roleStr.equals("学生"))
                    {
                        ChangePasswordNet changePasswordNet = new ChangePasswordNet();
                        password = Md5Util.EncoderByMd5(password);
                        changePasswordNet.studentChangePassword(this,tel,password,verifycode);
                    }
                    if(LoginActivity.roleStr.equals("教师"))
                    {
                        ChangePasswordNet changePasswordNet = new ChangePasswordNet();
                        password = Md5Util.EncoderByMd5(password);
                        changePasswordNet.teacherChangePassword(this,tel,password,verifycode);
                    }
                }
                break;
        }
    }
    private void changeGetVerifyCodeStyle()
    {
        //设置按钮不可点击
        getVerifyCode.setClickable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getVerifyCode.setBackground(getResources().getDrawable(R.drawable.unclick_button));
        }
        //开启线程读秒
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long beginTime = System.currentTimeMillis();
                long endTime = System.currentTimeMillis();
                //用来判断是否达到60秒的循环
                while (((endTime - beginTime) / 1000) < 60) {
                    endTime = System.currentTimeMillis();
                    long result = (endTime - beginTime) / 1000;
                    //发送消息，进行倒计时处理
                    Message message = new Message();
                    message.what = IS_GET_VERIFYCODE;
                    message.obj = result;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //发送消息给handler设置为可点击
                Message message = new Message();
                message.what = IS_AFTER_TIME;
                message.obj = "true";
                handler.sendMessage(message);
            }
        });
        thread.start();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
