package com.example.checkingsystem.teacher.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.VirtualCourseAttendanceItem;
import com.example.checkingsystem.student.activity.StudentQueryCourseItemInfoActivity;
import com.example.checkingsystem.student.fragment.StudentInquireFragment;
import com.example.checkingsystem.teacher.fragment.TeacherInquireFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;


public class TeacherQueryCourseStudentInfoActivity extends AppCompatActivity {
    final int ERROR = 0;
    final int SUCCESS = 1;
    ListView listView;
    ProgressDialog progressDialog;
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
                    Toast.makeText(TeacherQueryCourseStudentInfoActivity.this,"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }

            progressDialog.dismiss();
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        Message message = new Message();
        @Override
        public void onFinish(String response) {
            ResultObj resultObj;
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseAttendanceItem>>>(){});
                if(resultObj.getMeta().getResult())
                {
                    message.what = SUCCESS;
                    message.obj = resultObj.getData();

                }else {
                    message.what = ERROR;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            handler.sendMessage(message);
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
        setContentView(R.layout.activity_teacher_query_course_student_info);
        listView = (ListView) findViewById(R.id.acivity_teacher_query_course_student_info_listview);
        List<VirtualCourseAttendanceItem> listVirtualCourseAttentanceItem;
        progressDialog = ProgressDialog.show(this,"查询请假信息", "请稍等", true, true);
        getdata();
    }

    private void getdata() {
        String url = HttpUtil.urlIp+ PathUtil.TEACHER_GET_COURSE_STUDENT_ATTENTANCE_INFO;
        String courseID = TeacherQueryCourseStudentTotalActivity.studentAttendanceCountShow.getStudentId();
        String studentID = TeacherInquireFragment.courseShow.getDbID();
        HttpUtil.sendHttpGetRequest(url+"/"+courseID+"/"+studentID,httpCallbackListener);
    }
    public void UpdateUI()
    {
        ViewAdapter viewAdapter = new ViewAdapter();
        listView.setAdapter(viewAdapter);
    }
    class ViewAdapter extends BaseAdapter {

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
                view = LayoutInflater.from(TeacherQueryCourseStudentInfoActivity.this).inflate(R.layout.student_query_every_attentance_item,null);
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
}
