package com.example.checkingsystem.teacher.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.TeacherCheckingStudentItem;
import com.example.checkingsystem.entity.AttendanceItem;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.VirtualCourseAttendance;
import com.example.checkingsystem.entity.VirtualCourseAttendanceItem;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class TeacherCheckingStudentAttendanceListActivity extends AppCompatActivity {

    public final static int RIGHT = 1;
    public final static int FALSE = 0;
    ObjectMapper objectMapper= new ObjectMapper();
    ResultObj<List<VirtualCourseAttendanceItem>> resultObjAtteantanceItem;
    ResultObj<List<Student>> resultObjStu;
    private String attentanceID;
    private PullToRefreshListView refresh_lv;
    private DataAdapter adapter;
    List<TeacherCheckingStudentItem> list;
    String endTimeStr;
    Long endTime;
    long currentTime;
    TextView timeTextView;
    Thread timeThread;
    Button closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_checking_student_attendance_list);
        refresh_lv = (PullToRefreshListView) findViewById(R.id.main_pull_refresh_lv);
        refresh_lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        timeTextView = (TextView)findViewById(R.id.activity_teacher_checking_student_attendance_list_time);
        closeButton = (Button)findViewById(R.id.activity_teacher_checking_student_attendance_list_close_button);
        Intent intent = getIntent();
        attentanceID = intent.getStringExtra("attentanceID");
        endTime = intent.getLongExtra("endTime",0);
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                currentTime = System.currentTimeMillis();
                while (currentTime<endTime)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    updateTimeHandler.sendMessage(message);
                    currentTime = System.currentTimeMillis();
                }
                closeChecking();
            }
        });
        timeThread.start();
        //设置刷新时显示的文本
        ILoadingLayout startLayout = refresh_lv.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        refresh_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                updateInfo();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        refresh_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TeacherCheckingStudentItem teacherCheckingStudentItem = list.get(position-1);
                showDialog(teacherCheckingStudentItem);
                return false;
            }
        });
        queryInfo();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeChecking();
            }
        });
    }




    Handler updateTimeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int remainTime = (int) (endTime - currentTime)/1000;
            if(remainTime>0) {
                timeTextView.setText(remainTime + "s");
            }else {
                timeTextView.setText("考勤结束");
            }
        }
    };
    Handler closeHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            endTime = currentTime;
            timeTextView.setText("考勤结束");
            Toast.makeText(TeacherCheckingStudentAttendanceListActivity.this,"考勤结束",Toast.LENGTH_SHORT).show();
        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case RIGHT :
                    List<Student> listStu = resultObjStu.getData();
                    list = new ArrayList<>();
                    for(Student stu:listStu)
                    {
                        Log.e("checking","----------queryfor");
                        TeacherCheckingStudentItem studentItem = new TeacherCheckingStudentItem();
                        studentItem.setStudentName(stu.getStudentName());
                        studentItem.setStudentNo(stu.getStudentNo());
                        studentItem.setStudentID(stu.getStudentId());
                        for(VirtualCourseAttendanceItem virtualCourseAttendanceItem:resultObjAtteantanceItem.getData()) {
                            if(virtualCourseAttendanceItem.getVirtualCourseAttendanceItemStudentId().equals(stu.getStudentId())) {
                                studentItem.setStudentState(virtualCourseAttendanceItem.getChineseShow());
                                studentItem.setItemID(virtualCourseAttendanceItem.getVirtualCourseAttendanceItemId());
                            }
                        }
                        list.add(studentItem);
                    }

                    adapter = new DataAdapter(getApplicationContext(),list);
                    refresh_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    refresh_lv.onRefreshComplete();//刷新完成
                    break;
            }
        }
    };

    Handler updateHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            adapter = new DataAdapter(getApplicationContext(),list);
            refresh_lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            refresh_lv.onRefreshComplete();//刷新完成
        }
    };

    HttpCallbackListener httpCallbackListenerGetStudentInfo = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObjStu = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Student>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObjStu.getMeta().getResult())
            {
                Log.e("checking","----------2");
                Message message = new Message();
                message.what = RIGHT;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    HttpCallbackListener httpCallbackListenerQueryStudentID = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObjAtteantanceItem = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseAttendanceItem>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObjAtteantanceItem.getMeta().getResult())
            {

                GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                List<String> list = new ArrayList<>();
                for(VirtualCourseAttendanceItem virtualCourseAttendanceItem : resultObjAtteantanceItem.getData())
                {
                    list.add(virtualCourseAttendanceItem.getVirtualCourseAttendanceItemStudentId());
                }
                Log.e("checking","----------1");
                getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo,list);

            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    HttpCallbackListener httpCallbackListenerUpdateStudentID = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObjAtteantanceItem = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseAttendanceItem>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObjAtteantanceItem.getMeta().getResult())
            {

                for(VirtualCourseAttendanceItem virtualCourseAttendanceItem : resultObjAtteantanceItem.getData())
                {
                    for(int i = 0 ;i<list.size() ;i++)
                    {
                        if(list.get(i).getStudentID().equals(virtualCourseAttendanceItem.getVirtualCourseAttendanceItemStudentId()))
                        {
                            list.get(i).setStudentState(virtualCourseAttendanceItem.getChineseShow());
                        }
                    }
                }
                Message message = new Message();
                updateHandler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    HttpCallbackListener closeCheckHttpCallBackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ResultObj resultObj = null;
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<VirtualCourseAttendance>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                Message message = new Message();
                closeHandler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {

        }
    };
    HttpCallbackListener changeStatusHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultObj<VirtualCourseAttendanceItem> resultObj = null;
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<VirtualCourseAttendanceItem>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                for(int i = 0;i<list.size() ;i++)
                {
                    if(list.get(i).getItemID().equals(resultObj.getData().getVirtualCourseAttendanceItemId()))
                    {
                        list.get(i).setStudentState(resultObj.getData().getChineseShow());
                    }
                }
            }
            Message message = new Message();
            updateHandler.sendMessage(message);
        }

        @Override
        public void onError(Exception e) {

        }
    };

    public void updateInfo(){
        String path = HttpUtil.urlIp + PathUtil.TEACHER_GET_CHECKING_COURSE_ATTENTANCE;
        String data = attentanceID;
        HttpUtil.sendHttpGetRequest(path+"/"+data,httpCallbackListenerUpdateStudentID);
    }
    private void queryInfo()
    {
        String path = HttpUtil.urlIp + PathUtil.TEACHER_GET_CHECKING_COURSE_ATTENTANCE;
        String data = attentanceID;
        HttpUtil.sendHttpGetRequest(path+"/"+data,httpCallbackListenerQueryStudentID);
    }

    public void closeChecking()
    {
        String url = HttpUtil.urlIp+PathUtil.TEACHER_CLOSE_CHECK;
        String data = attentanceID;
        HttpUtil.sendHttpPutRequest(url+"/"+data,closeCheckHttpCallBackListener,"",HttpUtil.NO_STATUS);
    }

    private static class DataAdapter extends BaseAdapter {

        private Context context;
        private List<TeacherCheckingStudentItem> list;

        public DataAdapter(Context context, List<TeacherCheckingStudentItem> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null){
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.teacher_checking_student_list_item,parent,false);
                vh = new ViewHolder();
                vh.studentNo = (TextView)convertView.findViewById(R.id.tacher_checking_student_list_student_no);
                vh.studentName = (TextView)convertView.findViewById(R.id.teacher_checking_student_list_student_name);
                vh.studentState = (TextView)convertView.findViewById(R.id.teacher_checking_student_list_student_state);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            TeacherCheckingStudentItem teacherCheckingStudentItem = list.get(position);
            vh.studentNo.setText(teacherCheckingStudentItem.getStudentNo());
            vh.studentName.setText(teacherCheckingStudentItem.getStudentName());
            vh.studentState.setText(teacherCheckingStudentItem.getStudentState());
            return convertView;
        }

        class ViewHolder{
            TextView studentNo;
            TextView studentName;
            TextView studentState;
        }
    }

    public void showDialog(final TeacherCheckingStudentItem teacherCheckingStudentItem)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("更改"); //设置标题
        builder.setMessage("是否更改该学生考勤状态?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("迟到", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 //关闭dialog
                String url = HttpUtil.urlIp+PathUtil.TEACHER_CHANGE_STUDENT_LATE;
                String data = "/"+teacherCheckingStudentItem.getItemID();
                HttpUtil.sendHttpPutRequest(url+data,changeStatusHttpCallbackListener,"",HttpUtil.NO_STATUS);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("到课", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String url = HttpUtil.urlIp+PathUtil.TEACHER_CHANGE_STUDENT_ATTENTANCE;
                String data = "/"+teacherCheckingStudentItem.getItemID();
                HttpUtil.sendHttpPutRequest(url+data,changeStatusHttpCallbackListener,"",HttpUtil.NO_STATUS);
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {//设置忽略按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

}
