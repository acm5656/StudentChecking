package com.example.checkingsystem.student.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.StudentQueryLeaveInfoItemAdapter;
import com.example.checkingsystem.entity.CourseLeave;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.entity.VirtualCourseLeave;
import com.example.checkingsystem.net.StudentQueryCourseLeaveNet;
import com.example.checkingsystem.teacher.activity.TeacherCheckingStudentAttendanceListActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;

public class StudentQueryLeaveInfoListActivity extends AppCompatActivity {
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    PullToRefreshListView refresh_lv;
    List<VirtualCourseLeave> courseLeaveList = new ArrayList<>();
    StudentQueryLeaveInfoItemAdapter adapter;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    doRefreshListView();
                    break;
                case FAIL:
                    String msgStr = (String) msg.obj;

                    Toast.makeText(StudentQueryLeaveInfoListActivity.this,msgStr,Toast.LENGTH_SHORT).show();
                    refresh_lv.onRefreshComplete();//刷新完成
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultObj<List<VirtualCourseLeave>> resultObj = null;
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseLeave>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj!=null&&resultObj.getMeta().getResult())
            {
                courseLeaveList = resultObj.getData();
                Message message = new Message();
                message.what = SUCCESS;
                handler.sendMessage(message);
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
        setContentView(R.layout.activity_student_query_leave_info_list);
        refresh_lv = (PullToRefreshListView) findViewById(R.id.student_query_leave_info_list_view);
        refresh_lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = refresh_lv.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        refresh_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                StudentQueryCourseLeaveNet studentQueryCourseLeaveNet = new StudentQueryCourseLeaveNet();
                studentQueryCourseLeaveNet.studentQueryCourseLeaveNet(httpCallbackListener);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        StudentQueryCourseLeaveNet studentQueryCourseLeaveNet = new StudentQueryCourseLeaveNet();
        studentQueryCourseLeaveNet.studentQueryCourseLeaveNet(httpCallbackListener);
    }
    public void doRefreshListView()
    {
        adapter = new StudentQueryLeaveInfoItemAdapter(this,R.layout.item_student_leave_info,courseLeaveList);
        refresh_lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refresh_lv.onRefreshComplete();//刷新完成
    }

}
