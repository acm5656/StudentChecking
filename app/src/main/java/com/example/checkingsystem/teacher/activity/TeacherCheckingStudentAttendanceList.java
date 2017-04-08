package com.example.checkingsystem.teacher.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.TeacherCheckingStudentItem;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import util.HttpUtil;
import util.PathUtil;

public class TeacherCheckingStudentAttendanceList extends AppCompatActivity {

    private PullToRefreshListView refresh_lv;
    private List<TeacherCheckingStudentItem> list;
    private DataAdapter adapter;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_checking_student_attendance_list);
        refresh_lv = (PullToRefreshListView) findViewById(R.id.main_pull_refresh_lv);
        refresh_lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        //设置刷新时显示的文本
        ILoadingLayout startLayout = refresh_lv.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        refresh_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    private void queryInfo()
    {
        String path = HttpUtil.urlIp + PathUtil.TEACHER_GET_CHECKING_COURSE_ATTENTANCE;
        adapter = new DataAdapter(this,list);
        refresh_lv.setAdapter(adapter);
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
}
