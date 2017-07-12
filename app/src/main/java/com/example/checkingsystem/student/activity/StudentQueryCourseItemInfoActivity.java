package com.example.checkingsystem.student.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.ItemCountGroupByCidAndIStatus;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourseAttendanceItem;
import com.example.checkingsystem.net.StudentQueryCourseLeaveNet;
import com.example.checkingsystem.student.fragment.StudentInquireFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class StudentQueryCourseItemInfoActivity extends AppCompatActivity {
    static final int SUCCESS = 1;
    static final int ERROR = 0;
    ProgressDialog progressDialog;
    private ImageView iv_back;
    PullToRefreshListView listView;

    List<VirtualCourseAttendanceItem> listVirtualCourseAttentanceItem;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    listVirtualCourseAttentanceItem = (List<VirtualCourseAttendanceItem>) msg.obj;
                    UpdateUI();
                    break;
                case ERROR:
                    Toast.makeText(StudentQueryCourseItemInfoActivity.this,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }

            progressDialog.dismiss();
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {

        @Override
        public void onFinish(String response) {
            ResultObj resultObj;
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseAttendanceItem>>>(){});
                if(resultObj.getMeta().getResult())
                {
                    Message message = new Message();
                    message.what = SUCCESS;
                    message.obj = resultObj.getData();
                    handler.sendMessage(message);

                }else {
                    Message message = new Message();
                    message.what = ERROR;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = ERROR;
            handler.sendMessage(message);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_query_course_item_info);

        progressDialog = ProgressDialog.show(this,"查询请假信息", "请稍等", true, true);
        String url = HttpUtil.urlIp+ PathUtil.STUDENT_GET_EVERY_COURSE_ATTENTANCE_INFO;
        String courseID = StudentInquireFragment.courseShow.getDbID();
        String studentID = LoginActivity.studentStatic.getStudentId();
        initUI();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        HttpUtil.sendHttpGetRequest(url+"/"+courseID+"/"+studentID,httpCallbackListener);

    }

    private void initUI() {
        iv_back= (ImageView) findViewById(R.id.iv_activity_student_query_course_item_info_back);
        listView = (PullToRefreshListView) findViewById(R.id.activity_student_query_course_item_info_list_view);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String url = HttpUtil.urlIp+ PathUtil.STUDENT_GET_EVERY_COURSE_ATTENTANCE_INFO;
                String courseID = StudentInquireFragment.courseShow.getDbID();
                String studentID = LoginActivity.studentStatic.getStudentId();
                HttpUtil.sendHttpGetRequest(url+"/"+courseID+"/"+studentID,httpCallbackListener);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
    }
    class ViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listVirtualCourseAttentanceItem.size();
        }

        @Override
        public Object getItem(int position) {
            return listVirtualCourseAttentanceItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            VirtualCourseAttendanceItem virtualCourseAttentanceItem = listVirtualCourseAttentanceItem.get(position);
            View view = null;
            ViewHoder viewHoder;
            if(convertView!=null)
            {
                view = convertView;
                viewHoder = (ViewHoder) view.getTag();
            }else {
                view = LayoutInflater.from(StudentQueryCourseItemInfoActivity.this).inflate(R.layout.student_query_every_attentance_item,null);
                viewHoder = new ViewHoder();
                viewHoder.timeTextView = (TextView) view.findViewById(R.id.student_query_every_attentance_item_date);
                viewHoder.stateTextView = (TextView)view.findViewById(R.id.student_query_every_attentance_item_state);
                view.setTag(viewHoder);
            }
            Date date = new Date(virtualCourseAttentanceItem.getVirtualCourseAttendanceItemGmtCreated().getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String dateStr = sdf.format(date);
            viewHoder.timeTextView.setText(dateStr);
            viewHoder.stateTextView.setText(virtualCourseAttentanceItem.getChineseShow());
            return view;
        }

        class ViewHoder{
            public TextView timeTextView;
            public TextView stateTextView;
        }
    }

    public void UpdateUI()
    {
        ViewAdapter viewAdapter = new ViewAdapter();
        listView.setAdapter(viewAdapter);
        listView.onRefreshComplete();
    }

}
