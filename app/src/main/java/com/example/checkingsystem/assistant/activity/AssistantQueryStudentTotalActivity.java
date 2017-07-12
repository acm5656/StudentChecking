package com.example.checkingsystem.assistant.activity;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.assistant.fragments.AssistantInquireFragment;
import com.example.checkingsystem.entity.ClassShow;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.StudentAttendanceCount;
import com.example.checkingsystem.entity.StudentAttendanceCountShow;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class AssistantQueryStudentTotalActivity extends AppCompatActivity {
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    List<Student> studentList;
    private PullToRefreshListView listView;
    private ClassShow classShow;
    List<StudentAttendanceCount> studentAttendanceCountList;
    List<StudentAttendanceCountShow> studentAttendanceCountShowList;
    ProgressDialog progressDialog;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            switch (msg.what)
            {
                case SUCCESS:
                    updateUI();
                    break;
                case FAIL:
                    Toast.makeText(getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
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
                for(int i = 0 ;i<studentAttendanceCountShowList.size();i++)
                {
                    GetPictureNet getPictureNet = new GetPictureNet();
                    final int finalI = i;
                    getPictureNet.getPicture(studentAttendanceCountShowList.get(i).getStudentImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                        @Override
                        public void onFinish(InputStream inputStream) {
                            studentAttendanceCountShowList.get(finalI).setBitmap(BitmapFactory.decodeStream(inputStream));
                            Message message = new Message();
                            message.what = SUCCESS;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }

                Message message = new Message();
                message.what = SUCCESS;
                handler.sendMessage(message);
            }else {
                Message message = new Message();
                message.what = FAIL;
                message.obj = resultObjStu.getMeta().getMsg();
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


    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResultObj<List<StudentAttendanceCount>> resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<StudentAttendanceCount>>>() {});
                if(resultObj.getMeta().getResult())
                {
                    studentAttendanceCountList = resultObj.getData();
                    GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                    List<String> studentIDList = new ArrayList<>();
                    for(int i = 0 ;i<studentAttendanceCountList.size() ;i++)
                    {
                        studentIDList.add(studentAttendanceCountList.get(i).getStudentId());
                    }
                    getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo,studentIDList);
                }
                else
                {
                    Message message = new Message();
                    message.what = FAIL;
                    message.obj = resultObj.getMeta().getMsg();
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_query_student_total);
        initUI();
    }

    private void initUI() {
        listView = (PullToRefreshListView) findViewById(R.id.activity_assistant_query_student_total_list_view);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getDate();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
    }
    public void getDate()
    {
        classShow = AssistantInquireFragment.classShow;
        String url = HttpUtil.urlIp + PathUtil.ASSISTANT_GET_CLASS_ATTENTANCE_TOTAL;
        String data = "?classId="+classShow.getClassId();
        HttpUtil.sendHttpGetRequest(url+data,httpCallbackListener);
    }
    public void updateUI()
    {

    }

}
