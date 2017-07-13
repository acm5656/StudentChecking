package com.example.checkingsystem.teacher.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.TeacherCourseItemAdapter;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.entity.VirtualCourse;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetVirtualCourseInfoNet;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class TeacherCourseIndexFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener {
    public final static int TEACHER_ADD_COURSE = 3;
    View view;
    TeacherCourseItemAdapter teacherCourseItemAdapter;
    PullToRefreshListView listView;
    List<CourseShow> courseList;
    int length;
    static CourseShow courseShow;


    Handler updateUIHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            updateListView();
        }
    };
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            updateListView();
            for (int i = 0;i<courseList.size();i++)
            {
                GetPictureNet getPictureNet = new GetPictureNet();
                final int finalI = i;
                getPictureNet.getPicture(courseList.get(i).getImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                    @Override
                    public void onFinish(InputStream inputStream) {
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        courseList.get(finalI).setImgBitmap(bitmap);
                        Message message = new Message();
                        updateUIHandler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        }
    };

    public TeacherCourseIndexFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_teacher_course_index,container,false);
        initUI();
        setClickListener();
        return view;
    }

    private void initUI() {
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_teacher_course_index_list_view);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                LoginActivity.teacherCourseShow=null;
                LoginActivity.teacherVirtualList = null;
                System.gc();
                GetVirtualCourseInfoNet.teacherGetCourseTimeInfo();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (LoginActivity.teacherCourseShow==null) {

                        }
                        length = LoginActivity.teacherCourseShow.size();
                        while (length<LoginActivity.teacherVirtualList.size())
                        {
                            length = LoginActivity.teacherCourseShow.size();
                        }
                        courseList = LoginActivity.teacherCourseShow;
                        Message message = new Message();
                        handler.sendMessage(message);
                    }
                }).start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (LoginActivity.teacherCourseShow==null) {

                }
                length = LoginActivity.teacherCourseShow.size();
                while (length<LoginActivity.teacherVirtualList.size())
                {
                    length = LoginActivity.teacherCourseShow.size();
                }
                courseList = LoginActivity.teacherCourseShow;
                Message message = new Message();
                handler.sendMessage(message);
            }
        }).start();
    }
    private void setClickListener()
    {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("test","------course1");
        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case TEACHER_ADD_COURSE:
                    VirtualCourse virtualCourse = (VirtualCourse) data.getSerializableExtra("course");
                    Bitmap bitmap = data.getParcelableExtra("bitmap");
                    Toast.makeText(getContext(),"恭喜你，添加成功,课程编号为："+virtualCourse.getVirtualCourseCode(),Toast.LENGTH_SHORT).show();

                    CourseShow courseShow = new CourseShow();
                    courseShow.setId(virtualCourse.getVirtualCourseCode());
                    courseShow.setDbID(virtualCourse.getVirtualCourseId());
                    courseShow.setName(virtualCourse.getVirtualCourseName());
                    courseShow.setTeahcerName("");
                    courseShow.setImgBitmap(bitmap);
                    courseList.add(0,courseShow);
                    updateListView();
                    break;
            }
        }
    }

    private void updateListView() {
        teacherCourseItemAdapter = new TeacherCourseItemAdapter(getContext(),courseList,R.layout.teacher_list_view_course_item);
        listView.setAdapter(teacherCourseItemAdapter);
        listView.onRefreshComplete();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), TeacherCheckingActivity.class);
        courseShow = courseList.get(position-1);
        startActivity(intent);
    }


}
