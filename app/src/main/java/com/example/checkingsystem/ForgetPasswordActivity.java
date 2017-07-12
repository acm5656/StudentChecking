package com.example.checkingsystem;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.checkingsystem.net.ChangePasswordNet;
import com.example.checkingsystem.net.SendVerifyCodeNet;

import util.Md5Util;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    //选择身份的控件
    private RadioGroup roleRadioGroup;
    private RadioButton radioButton;
    //输入手机号的控件
    private EditText inputTel;
    //输入密码的控件
    private EditText inputPassword;
    //输入确认密码的控件
    private EditText inputCheckingPassword;
    //输入验证码的控件
    private EditText verifyCodeEdit;
    //获取验证码的控件
    private Button getVerifyCode;
    //提交注册的控件
    private Button submit;
    //临时存放的上述输入的资源
    private String tel;
    private String password;
    private String checkingPassword;
    private String roleStr;
    private String verifyStr;
    //同修改密码的一样，判断是否获取验证码的常量
    public static final int IS_GET_VERIFYCODE = 1;
    public static final int IS_AFTER_TIME = 2;
    Thread thread;
    //同修改密码逻辑相同，不再陈述
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case IS_GET_VERIFYCODE:
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
        setContentView(R.layout.activity_forget_password);
        initSourse();
        getVerifyCode.setOnClickListener(this);
        submit.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    private void initSourse() {
        iv_back = (ImageView) findViewById(R.id.iv_activity_forget_password_back);
        roleRadioGroup = (RadioGroup) findViewById(R.id.activity_forget_password_radioGroup);
        inputTel = (EditText)findViewById(R.id.activity_forget_password_tel);
        inputPassword = (EditText)findViewById(R.id.activity_forget_password_input_password);
        inputCheckingPassword = (EditText)findViewById(R.id.activity_forget_password_input_checking_password);
        verifyCodeEdit= (EditText)findViewById(R.id.activity_forget_password_verifycode);
        getVerifyCode = (Button)findViewById(R.id.activity_forget_password_get_verifycode_button);
        submit = (Button)findViewById(R.id.acticity_forget_password_submit);
    }

    @Override
    public void onClick(View v) {
        roleStr = ((RadioButton)findViewById(roleRadioGroup.getCheckedRadioButtonId())).getText().toString();
        switch (v.getId())
        {
            case R.id.activity_forget_password_get_verifycode_button:
                tel = inputTel.getText().toString();
                if(tel!=null&&!tel.trim().equals("")) {
                    changeGetVerifyCodeStyle();
                    if(roleStr.equals("学生")) {

                        SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                        sendVerifyCodeNet.sendStudentChangePasswordVerifyCode(tel, this);
                    }
                    if(roleStr.equals("教师")){
                        SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                        sendVerifyCodeNet.sendTeacherChangePasswordVerifyCode(tel,this);
                    }
                    if(roleStr.equals("导员"))
                    {
                        SendVerifyCodeNet sendVerifyCodeNet = new SendVerifyCodeNet();
                        sendVerifyCodeNet.sendAssistantChangePasswordVerifyCode(tel,this);
                    }

                }else {
                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.acticity_forget_password_submit:
                boolean judge = true;
                password = inputPassword.getText().toString();
                checkingPassword = inputCheckingPassword.getText().toString();
                verifyStr = verifyCodeEdit.getText().toString();

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
                if(roleStr==null||roleStr.trim().equals(""))
                {
                    judge = false;
                    Toast.makeText(this,"请选择角色",Toast.LENGTH_SHORT).show();
                }
                if(verifyStr==null||verifyStr.trim().equals(""))
                {
                    judge = false;
                    Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }
                password = Md5Util.EncoderByMd5(password);
                if(roleStr.equals("学生")&&judge)
                {
                    ChangePasswordNet changePasswordNet = new ChangePasswordNet();
                    changePasswordNet.studentChangePassword(this,tel,password,verifyStr);
                }
                if(roleStr.equals("教师")&&judge)
                {
                    ChangePasswordNet changePasswordNet = new ChangePasswordNet();
                    changePasswordNet.teacherChangePassword(this,tel,password,verifyStr);
                }
                if(roleStr.equals("导员")&&judge)
                {
                    ChangePasswordNet changePasswordNet = new ChangePasswordNet();
                    changePasswordNet.assistantChangePassword(this,tel,password,verifyStr);
                }
                break;
            case R.id.iv_activity_forget_password_back:
                onBackPressed();
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

        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
