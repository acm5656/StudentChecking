package com.example.checkingsystem.assistant.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.example.checkingsystem.adapter.AssistantAgreeForLeaveItemAdapter;
import com.example.checkingsystem.assistant.activity.AssistantAgreeForLeaveActivity;
import com.example.checkingsystem.entity.ClassLeaveShow;
import com.example.checkingsystem.entity.CourseLeave;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.entity.VirtualCourseLeave;
import com.example.checkingsystem.net.AssistantGetLeaveNet;
import com.example.checkingsystem.net.GetPictureNet;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;

/**
 * Created by 竞豪 on 2017/7/8.
 */

/**
 * 通过点击listView中的学生，跳转到AssistantAgreeForLeaveActivity来进行批准操作
 */
public class AssistantAgreeForLeaveFragment extends Fragment {
    public final static int RIGHT = 1;
    public final static int FALSE = 0;
    public final static int CHANGE_ASK_LEAVE_INFO = 0;
    ObjectMapper objectMapper= new ObjectMapper();
    ResultObj<List<String>> resultObjID;

    ResultObj<List<Student>> resultObjStu;
    ProgressDialog progressDialog;

    private View view;
    private ListView listView;
    public static ClassLeaveShow classLeaveShow = null;
    /**
     * listView的适配器
     */
    private AssistantAgreeForLeaveItemAdapter assistantAgreeForLeaveItemAdapter;
    ResultObj<List<VirtualCourseLeave>> resultObj;
    List<ClassLeaveShow> classLeaveShowList;
    Handler noStudethandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getActivity(),"无请假信息",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    };
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case RIGHT:
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    updateUI();
                    break;
                case FALSE:
                    Toast.makeText(getActivity(),"查询失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    HttpCallbackListener httpCallbackListenerGetStudentInfo = new HttpCallbackListener() {

        @Override
        public void onFinish(String response) {
            ResultObj<List<Student>> resultStudentList = null;
            try {
                resultStudentList = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<Student>>>() {});
                if(resultStudentList.getMeta().getResult())
                {
                    List<Student> list = resultStudentList.getData();
                    for(int i = 0 ;i<classLeaveShowList.size();i++)
                    {
                        for(int j = 0 ;j<list.size();j++)
                        {
                            if(classLeaveShowList.get(i).getVirtualCourseLeaveStuId().equals(list.get(j).getStudentId()))
                            {

                                classLeaveShowList.get(i).setStudentName(list.get(j).getStudentName());
                                classLeaveShowList.get(i).setStudentImgUrl(list.get(j).getStudentHeadimageUrl());
                                classLeaveShowList.get(i).setStudentSchoolName(list.get(j).getStudentNo());
                            }
                        }
                    }
                    LoginActivity.classLeaveShowList = classLeaveShowList;
                    for(int i = 0 ;i<classLeaveShowList.size() ;i++)
                    {
                        final GetPictureNet getPictureNet = new GetPictureNet();
                        final int finalI = i;
                        if(classLeaveShowList.get(i).getStudentImgUrl()!=null&&!classLeaveShowList.get(i).getStudentImgUrl().trim().equals(""))
                        {
                            getPictureNet.getPicture(classLeaveShowList.get(i).getStudentImgUrl(), new GetPictureNet.HttpPictureCallbackListener() {
                                @Override
                                public void onFinish(InputStream inputStream) {
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    LoginActivity.classLeaveShowList.get(finalI).setStudentBitmap(bitmap);
                                    Message message = new Message();
                                    message.what = RIGHT;
                                    handler.sendMessage(message);
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        }

                    }
                    Message message = new Message();
                    message.what = RIGHT;
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = FALSE;
                    handler.sendMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handler.sendMessage(message);
        }
    };
    HttpCallbackListener getLeaveHttpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {

            try {
                resultObj = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<VirtualCourseLeave>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resultObj.getMeta().getResult())
            {
                List<VirtualCourseLeave> virtualCourseLeaveList = resultObj.getData();
                GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                List<String> studentIDList = new ArrayList<>();
                classLeaveShowList = new ArrayList<ClassLeaveShow>();

                for(int i = 0 ;i<virtualCourseLeaveList.size() ;i++)
                {
                    ClassLeaveShow classLeaveShow = new ClassLeaveShow(virtualCourseLeaveList.get(i));
                    studentIDList.add(virtualCourseLeaveList.get(i).getVirtualCourseLeaveStuId());
                    classLeaveShowList.add(classLeaveShow);
                }
                if(classLeaveShowList.size()==0)
                {
                    Message message = new Message();
                    message.what = RIGHT;
                    noStudethandler.sendMessage(message);
                }else {
                    getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo, studentIDList);
                }
            }else {
                Message message = new Message();
                message.what = FALSE;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handler.sendMessage(message);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assistant_agree_for_leave, container, false);
        initItem();
        if(classLeaveShowList==null) {
            progressDialog = ProgressDialog.show(getActivity(), "查询请假信息", "请稍等", true, true);
            AssistantGetLeaveNet assistantGetLeaveNet = new AssistantGetLeaveNet();
            assistantGetLeaveNet.assistantGetLeave(LoginActivity.assistantStatic.getAssistantId(),getLeaveHttpCallbackListener);
        }
        updateUI();
        return view;
    }

    private void initItem(){
        listView = (ListView) view.findViewById(R.id.lv_fragment_assistant_agree_for_leave);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classLeaveShow = classLeaveShowList.get(position);
                Intent intent = new Intent(getActivity(), AssistantAgreeForLeaveActivity.class);
                startActivityForResult(intent,CHANGE_ASK_LEAVE_INFO);
            }
        });
    }
    private void updateUI()
    {
        if(classLeaveShowList!=null) {
            assistantAgreeForLeaveItemAdapter = new AssistantAgreeForLeaveItemAdapter(getActivity(), LoginActivity.classLeaveShowList, R.layout.item_assistant_agree_for_leave);
            listView.setAdapter(assistantAgreeForLeaveItemAdapter);
        }
    }

}
