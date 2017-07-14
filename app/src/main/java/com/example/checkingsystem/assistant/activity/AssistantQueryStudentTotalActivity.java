package com.example.checkingsystem.assistant.activity;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.AssistantQueryClassItemAdapter;
import com.example.checkingsystem.adapter.QueryClassStudentAttentanceInfoItemAdapter;
import com.example.checkingsystem.assistant.fragments.AssistantInquireFragment;
import com.example.checkingsystem.entity.ClassShow;
import com.example.checkingsystem.entity.ItemCountGroupByCidAndIStatus;
import com.example.checkingsystem.entity.ItemCountGroupBySidAndStatus;
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
    private ImageView iv_back;
    List<Student> studentList;
    private PullToRefreshListView listView;
    ResultObj<List<Student>> resultObjStu = null;
    private ClassShow classShow;
    List<ItemCountGroupBySidAndStatus> itemCountGroupBySidAndStatusList;
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
                    listView.onRefreshComplete();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListenerGetCattentance = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResultObj<List<ItemCountGroupBySidAndStatus>> resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<ItemCountGroupBySidAndStatus>>>() {});
                if(resultObj.getMeta().getResult())
                {
                    itemCountGroupBySidAndStatusList = resultObj.getData();
                    studentAttendanceCountShowList = new ArrayList<>();
                    for(int i = 0;i<studentList.size();i++)
                    {
                        StudentAttendanceCountShow studentAttendanceCountShow = new StudentAttendanceCountShow();
                        studentAttendanceCountShow.setStudentId(studentList.get(i).getStudentId());
                        studentAttendanceCountShow.setStudentName(studentList.get(i).getStudentName());
                        studentAttendanceCountShow.setStudentImgUrl(studentList.get(i).getStudentHeadimageUrl());
                        studentAttendanceCountShow.setStduentSchoolID(studentList.get(i).getStudentNo());
                        studentAttendanceCountShow.setAbsentCount("0");
                        studentAttendanceCountShow.setAttendanceCount("0");
                        studentAttendanceCountShow.setLateCount("0");
                        studentAttendanceCountShow.setLeaveCount("0");
                        for(int j = 0 ;j<itemCountGroupBySidAndStatusList.size();j++)
                        {
                            if(itemCountGroupBySidAndStatusList.get(j).getStudentId().equals(studentList.get(i).getStudentId()))
                            {

                                if(itemCountGroupBySidAndStatusList.get(j).getItemStatus().equals("attendance"))
                                {
                                    studentAttendanceCountShow.setAttendanceCount(String.valueOf(itemCountGroupBySidAndStatusList.get(j).getCount()));
                                }
                                if(itemCountGroupBySidAndStatusList.get(j).getItemStatus().equals("leave"))
                                {
                                    studentAttendanceCountShow.setLeaveCount(String.valueOf(itemCountGroupBySidAndStatusList.get(j).getCount()));
                                }
                                if(itemCountGroupBySidAndStatusList.get(j).getItemStatus().equals("absence"))
                                {
                                    studentAttendanceCountShow.setAbsentCount(String.valueOf(itemCountGroupBySidAndStatusList.get(j).getCount()));
                                }
                                if(itemCountGroupBySidAndStatusList.get(j).getItemStatus().equals("late"))
                                {
                                    studentAttendanceCountShow.setLateCount(String.valueOf(itemCountGroupBySidAndStatusList.get(j).getCount()));
                                }

                            }
                        }
                        studentAttendanceCountShowList.add(studentAttendanceCountShow);
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
            Log.e("test",e.toString());
            Message message = new Message();
            message.what = FAIL;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };


    HttpCallbackListener httpCallbackListenerGetStudentInfo = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                resultObjStu = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Student>>>() {});
                studentList = resultObjStu.getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObjStu.getMeta().getResult()) {
                classShow = AssistantInquireFragment.classShow;
                String url = HttpUtil.urlIp + PathUtil.ASSISTANT_GET_CLASS_ATTENTANCE_TOTAL;
                String data = "?classId="+classShow.getClassId();
                HttpUtil.sendHttpGetRequest(url+data,httpCallbackListenerGetCattentance);
            }else {
                Message message = new Message();
                message.what = FAIL;
                message.obj = resultObjStu.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Log.e("test",e.toString());
            Message message = new Message();
            message.what = FAIL;
            message.obj = "操作失败，请稍后再试";
            handler.sendMessage(message);
        }
    };



    HttpCallbackListener getAllStudentListHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj<List<String>> resultObj = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<String>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                if(resultObj.getData()==null||resultObj.getData().size()==0)
                {
                    Message message = new Message();
                    message.what = FAIL;
                    message.obj = "暂无学生";
                    handler.sendMessage(message);
                }else {
                    GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                    getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo,resultObj.getData());
                }

            }else {
                Message message = new Message();
                message.what = FAIL;
                message.obj = resultObj.getMeta().getMsg();
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Log.e("test",e.toString());
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
        progressDialog = ProgressDialog.show(this,"查询请假信息", "请稍等", true, true);
        initUI();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getDate();
    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_activity_assistant_query_student_total_back);
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
        String url = HttpUtil.urlIp + PathUtil.ASSISTANT_GET_CLASS_STUDENT_LIST;
        String data = "?classId="+classShow.getClassId();
        HttpUtil.sendHttpGetRequest(url+data,getAllStudentListHttpCallbackListener);
    }
    public void updateUI()
    {
        QueryClassStudentAttentanceInfoItemAdapter assistantQueryClassItemAdapter = new QueryClassStudentAttentanceInfoItemAdapter(getApplicationContext(),studentAttendanceCountShowList,R.layout.list_view_assistant_query_class_student_attentance_item);
        listView.setAdapter(assistantQueryClassItemAdapter);
        listView.onRefreshComplete();
    }

}
