package com.example.checkingsystem;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.checkingsystem.beans.Student;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import util.HttpCallbackListener;
import util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    Spinner rolesSpinner;
    EditText usernameText;
    EditText passwordText;
    Button submitButton;

    String roleStr;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    Toast.makeText(MainActivity.this,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    if(roleStr.equals("学生")) {
                        Intent intent = new Intent(MainActivity.this, StudentIndexActivity.class);
                        startActivity(intent);
                    }
                    if(roleStr.equals("教师")) {
                        Intent intent = new Intent(MainActivity.this, TeacherIndexActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    };

    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Message message = new Message();
            message.what = 0;
            message.obj = response;
            handler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResourse();

        rolesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleStr = (String)rolesSpinner.getSelectedItem();
                Toast.makeText(MainActivity.this,roleStr,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                Student student = Student.getStudent();
                student.setPassword(password);
                student.setUsername(username);
                if(username.equals("123")&&password.equals("123"))
                {
                    final String address = "http://115.159.196.97:8080/AndroidTest/Login";

                    HttpUtil.sendHttpPostRequest(address,httpCallbackListener,"acm666");
                }

            }
        });

    }


    private void initResourse()
    {
        rolesSpinner = (Spinner) findViewById(R.id.login_roles);
        usernameText = (EditText)findViewById(R.id.login_usernameEdit);
        passwordText = (EditText)findViewById(R.id.login_passwordEdit);
        submitButton = (Button)findViewById(R.id.login_submit);
    }



}
