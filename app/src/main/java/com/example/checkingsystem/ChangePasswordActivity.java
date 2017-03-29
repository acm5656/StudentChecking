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

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText changePassword;
    private EditText changePasswordChecking;
    private Button getVerifyCode;
    private EditText inputVerifyCode;
    private Button submitButton;
    public static final int IS_GET_VERIFYCODE = 1;
    public static final int IS_AFTER_TIME = 2;
    String tel = null;
    Thread thread;

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case IS_GET_VERIFYCODE:
                    Log.e("test","---------------do");
                    getVerifyCode.setText((60-new Integer(msg.obj.toString()))+"s");
                    break;
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.change_password_get_verifycode_button:
                changeGetVerifyCodeStyle();
                SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                sendVerifyCodeNet.sendStudentChangePasswordVerifyCode(tel,this);
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
                }
                break;
        }
    }
    private void changeGetVerifyCodeStyle()
    {
        getVerifyCode.setClickable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getVerifyCode.setBackground(getResources().getDrawable(R.drawable.unclick_button));
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long beginTime = System.currentTimeMillis();
                long endTime = System.currentTimeMillis();
                while (((endTime - beginTime) / 1000) < 60) {
                    Log.e("test", "--------do1");
                    endTime = System.currentTimeMillis();
                    long result = (endTime - beginTime) / 1000;
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
        if(thread!=null)
        {
            thread.stop();
        }

        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
