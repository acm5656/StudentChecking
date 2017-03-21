package com.example.checkingsystem;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.checkingsystem.dao.StudentDao;
import com.example.checkingsystem.dao.TeacherDao;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.student.activity.StudentIndexActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

import java.util.List;

import util.ActivityColectorUtil;
import util.HttpCallbackListener;
import util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    Spinner rolesSpinner;
    EditText usernameText;
    EditText passwordText;
    Button submitButton;
    public static StudentDao studentDao;
    public static TeacherDao teacherDao;
    public static Student studentStatic;
    public static Teacher teacherStatic;

    public static final String path="/com.acm.checkingsystem";

    static String roleStr;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    Toast.makeText(MainActivity.this,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    if(roleStr.equals("学生")) {
                        Log.e("mainActivity","------3");
                        studentStatic.setStudentPassword("123");
                        studentStatic.setStudentNo("123");
                        studentStatic.setStudentId("123");
                        studentStatic.setStudentFacecode("201421431811113");
                        studentDao.addStudent(studentStatic);

                        Intent intent = new Intent(MainActivity.this, StudentIndexActivity.class);
                        startActivity(intent);
                    }
                    if(roleStr.equals("教师")) {
                        Log.e("mainActivity","------4");
                        teacherStatic.setTeacherNo("123");
                        teacherStatic.setTeacherId("123");
                        teacherStatic.setTeacherPassword("123");
                        teacherDao.addTeacher(teacherStatic);

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
        ActivityColectorUtil.addActivity(this);
        teacherStatic = new Teacher();
        studentStatic = new Student();
        roleStr = "";
        initResourse();
        initDB();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                roleStr = (String)rolesSpinner.getSelectedItem();
                if((username!=null)&&(password!=null)&&(!username.trim().equals(""))&&(!password.trim().equals("")))
                {

                    final String address = "http://115.159.196.97:8080/AndroidTest/Login";

                    HttpUtil.sendHttpPostRequest(address,httpCallbackListener,"acm666");
                }

            }
        });

    }

    private void initDB() {
        teacherDao = new TeacherDao(this);
        studentDao = new StudentDao(this);
        List<Student> studentList = studentDao.queryStudent();
        if(studentList.size()!=0)
        {
            Log.e("mainActivity","------1");
            studentStatic = studentList.get(0);
            final String address = "http://115.159.196.97:8080/AndroidTest/Login";
            roleStr = "学生";
            HttpUtil.sendHttpPostRequest(address,httpCallbackListener,"acm666");
        }
        List<Teacher> teacherList = teacherDao.queryTeacher();
        if(teacherList.size()!=0)
        {
            Log.e("mainActivity","------2");
            teacherStatic = teacherList.get(0);
            final String address = "http://115.159.196.97:8080/AndroidTest/Login";
            roleStr = "教师";
            HttpUtil.sendHttpPostRequest(address,httpCallbackListener,"acm666");
        }

    }


    private void initResourse()
    {
        rolesSpinner = (Spinner) findViewById(R.id.login_roles);
        usernameText = (EditText)findViewById(R.id.login_usernameEdit);
        passwordText = (EditText)findViewById(R.id.login_passwordEdit);
        submitButton = (Button)findViewById(R.id.login_submit);

    }

    @Override
    public void onBackPressed() {
        ActivityColectorUtil.finishAll();
    }
}
