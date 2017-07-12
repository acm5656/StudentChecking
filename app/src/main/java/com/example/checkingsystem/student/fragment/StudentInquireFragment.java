package com.example.checkingsystem.student.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.QueryCourseAttentanceItemAdapter;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetVirtualCourseInfoNet;
import com.example.checkingsystem.student.activity.StudentQueryCourseItemInfoActivity;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.InputStream;
import java.util.List;

public class StudentInquireFragment extends Fragment implements AdapterView.OnItemClickListener{
    public static CourseShow courseShow;
    private OnFragmentInteractionListener mListener;
    private View view;
    private List<CourseShow> listItem;
    private PullToRefreshListView listView;
    Handler updateUIHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            updateUI();
        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            updateUI();
            for (int i = 0;i<listItem.size();i++)
            {
                GetPictureNet getPictureNet = new GetPictureNet();
                final int finalI = i;
                if(listItem.get(i).getImgBitmap()==null) {
                    getPictureNet.getPicture(listItem.get(i).getImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                        @Override
                        public void onFinish(InputStream inputStream) {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            listItem.get(finalI).setImgBitmap(bitmap);
                            Message message = new Message();
                            updateUIHandler.sendMessage(message);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }
            }
        }
    };
    int length;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student_inquire, container, false);
        initItem();

        return view;
    }

    private void initItem() {
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_student_inquire_list_view);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoginActivity.studentCourseShow=null;
                        LoginActivity.studentVirtualList = null;
                        GetVirtualCourseInfoNet.studentGetCourseTimeInfo();
                        while (LoginActivity.studentCourseShow==null) {

                        }
                        length = LoginActivity.studentCourseShow.size();
                        while (length<LoginActivity.studentVirtualList.size())
                        {
                            length = LoginActivity.studentCourseShow.size();
                        }
                        listItem = LoginActivity.studentCourseShow;
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
        listView.setOnItemClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (LoginActivity.studentCourseShow==null) {

                }
                length = LoginActivity.studentCourseShow.size();
                while (length<LoginActivity.studentVirtualList.size())
                {
                    length = LoginActivity.studentCourseShow.size();
                }
                listItem = LoginActivity.studentCourseShow;
                Message message = new Message();
                handler.sendMessage(message);
            }
        }).start();
    }
    public void updateUI()
    {
        QueryCourseAttentanceItemAdapter queryCourseAttentanceItemAdapter = new QueryCourseAttentanceItemAdapter(getContext(),listItem,R.layout.list_view_query_course_attentance_item);
        queryCourseAttentanceItemAdapter.notifyDataSetChanged();
        listView.setAdapter(queryCourseAttentanceItemAdapter);
        listView.onRefreshComplete();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        courseShow = listItem.get(position-1);
        Intent intent = new Intent(getActivity(), StudentQueryCourseItemInfoActivity.class);
        startActivity(intent);

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
