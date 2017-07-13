package com.example.checkingsystem.assistant.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.AssistantQueryClassItemAdapter;
import com.example.checkingsystem.assistant.activity.AssistantQueryStudentTotalActivity;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.ClassShow;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.net.GetPictureNet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.ChangeTypeUtil;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantInquireFragment extends Fragment implements AdapterView.OnItemClickListener{
    final static int SUCCESS = 1;
    final static int FAIL = 0;
    public static ClassShow classShow;
    PullToRefreshListView listView;
    View view;
    ResultObj<List<Class>> resultObj;
    ObjectMapper objectMapper = new ObjectMapper();
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SUCCESS:
                    updateUI();
                    break;
                case FAIL:
                    Toast.makeText(getActivity(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Class>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                LoginActivity.classShowList = new ArrayList<>();
                for(int i = 0 ;i<resultObj.getData().size();i++)
                {
                    ClassShow classShow = new ClassShow(resultObj.getData().get(i));
                    LoginActivity.classShowList.add(classShow);
                }
                for(int i = 0 ;i<resultObj.getData().size();i++)
                {
                    GetPictureNet getPictureNet = new GetPictureNet();
                    final int finalI = i;
                    getPictureNet.getPicture(LoginActivity.classShowList.get(i).getClassHeadImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                        @Override
                        public void onFinish(InputStream inputStream) {
                            LoginActivity.classShowList.get(finalI).setBitmap(BitmapFactory.decodeStream(inputStream));
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
                message.obj = resultObj.getMeta().getMsg();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assistant_inquire,container,false);
        initView();

        return view;
    }

    private void initView() {
        listView = (PullToRefreshListView)view.findViewById(R.id.lv_fragment_assistant_inquire);
        listView.setOnItemClickListener(this);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true,false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        classShow = LoginActivity.classShowList.get(position-1);
        Intent intent = new Intent(getActivity(), AssistantQueryStudentTotalActivity.class);
        startActivity(intent);
    }
    public void getData()
    {
        String url = HttpUtil.urlIp + PathUtil.ASSISTANT_GET_CLASS_INFO;
        String data = "?assistantId="+ LoginActivity.assistantStatic.getAssistantId();
        HttpUtil.sendHttpGetRequest(url+data,httpCallbackListener);

    }
    public void updateUI()
    {
        AssistantQueryClassItemAdapter assistantQueryClassItemAdapter = new AssistantQueryClassItemAdapter(getActivity(),R.layout.item_assistant_query_class,LoginActivity.classShowList);
        listView.setAdapter(assistantQueryClassItemAdapter);
        listView.onRefreshComplete();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getData();
    }
}
