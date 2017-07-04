package com.example.checkingsystem.teacher.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.QueryCourseStudentAttentanceInfoItemAdapter;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.StudentAttendanceCount;
import com.example.checkingsystem.entity.StudentAttendanceCountShow;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.example.checkingsystem.teacher.fragment.TeacherInquireFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class TeacherQueryCourseStudentTotalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static final int SUCCESS = 1;
    static final int ERROR = 0;
    ListView listView;
    List<StudentAttendanceCount> studentAttendanceCountList;
    List<StudentAttendanceCountShow> studentAttendanceCountShowList;
    public static StudentAttendanceCountShow studentAttendanceCountShow;
    Message message = new Message();
    List<Student> studentList;
    ProgressDialog progressDialog;
    Handler updateUIHandlerUI = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            updateUI();
        }
    };
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ERROR:
                    progressDialog.dismiss();
                    Toast.makeText(TeacherQueryCourseStudentTotalActivity.this,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();

                    break;
                case SUCCESS:
                    studentAttendanceCountShowList = new ArrayList<>();
                    for(int i = 0 ;i <studentAttendanceCountList.size() ; i++)
                    {
                        for(int j = 0 ;j <studentList.size() ;j++)
                        {
                            if(studentAttendanceCountList.get(i).getStudentId().equals(studentList.get(j).getStudentId()))
                            {
                                StudentAttendanceCountShow studentAttendanceCountShow = new StudentAttendanceCountShow(studentAttendanceCountList.get(i));
                                studentAttendanceCountShow.setStudentName(studentList.get(j).getStudentName());
                                studentAttendanceCountShow.setStudentImgUrl(studentList.get(j).getStudentHeadimageUrl());
                                studentAttendanceCountShowList.add(studentAttendanceCountShow);
                            }
                        }
                    }
                    updateUI();
                    for(int i = 0 ;i<studentAttendanceCountShowList.size();i++)
                    {
                        GetPictureNet getPictureNet = new GetPictureNet();
                        final int finalI = i;
                        getPictureNet.getPicture(studentAttendanceCountShowList.get(i).getStudentImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                            @Override
                            public void onFinish(InputStream inputStream) {
                                studentAttendanceCountShowList.get(finalI).setBitmap(BitmapFactory.decodeStream(inputStream));
                                message = new Message();
                                updateUIHandlerUI.sendMessage(message);
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
                    }
                    progressDialog.dismiss();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListenerGetStudentInfo = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultObj<List<Student>> resultObjStu = null;
            try {
                resultObjStu = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Student>>>() {});
                studentList = resultObjStu.getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObjStu.getMeta().getResult()) {
                Log.e("activityTest","----------0");
                message.what = SUCCESS;
                handler.sendMessage(message);
            }else {
                message.what = ERROR;
                handler.sendMessage(message);
                Log.e("activityTest","----------error1");
            }
        }

        @Override
        public void onError(Exception e) {
            Log.e("activityTest","----------error2");
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {

        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResultObj<List<StudentAttendanceCount>> resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<StudentAttendanceCount>>>() {});
                if(resultObj.getMeta().getResult())
                {
                    Log.e("activityTest","----------1---error3");
                    studentAttendanceCountList = resultObj.getData();
                    GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                    List<String> studentIDList = new ArrayList<>();
                    for(int i = 0 ;i<studentAttendanceCountList.size() ;i++)
                    {
                        studentIDList.add(studentAttendanceCountList.get(i).getStudentId());
                    }
                    Log.e("activityTest","----------1---error4");
                    getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo,studentIDList);
                }
                else
                {
                    Log.e("activityTest","----------1---error0");
                    message.what = ERROR;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {

                Log.e("activityTest","----------1---error2");
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_query_course_student_total);
        listView = (ListView) findViewById(R.id.acivity_teacher_query_course_student_total_listview);
        progressDialog = ProgressDialog.show(this,"查询请假信息", "请稍等", true, true);
        getData();


        listView.setOnItemClickListener(this);
    }

    public void getData()
    {
        String url = HttpUtil.urlIp+ PathUtil.TEACHER_GET_COURSE_STUDENT_ATTENTANCE_LIST_TOTAL;
        String date = "/"+ TeacherInquireFragment.courseShow.getDbID();
        HttpUtil.sendHttpGetRequest(url+date,httpCallbackListener);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        studentAttendanceCountShow = studentAttendanceCountShowList.get(position);
        Intent intent = new Intent(this,TeacherQueryCourseStudentInfoActivity.class);
        startActivity(intent);
    }

    public void updateUI()
    {
        QueryCourseStudentAttentanceInfoItemAdapter queryCourseStudentAttentanceInfoItemAdapter = new QueryCourseStudentAttentanceInfoItemAdapter(this,studentAttendanceCountShowList,R.layout.list_view_teacher_query_course_student_attentance_item);
        listView.setAdapter(queryCourseStudentAttentanceInfoItemAdapter);
    }


}
