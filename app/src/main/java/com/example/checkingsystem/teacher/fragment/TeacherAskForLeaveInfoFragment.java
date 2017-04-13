package com.example.checkingsystem.teacher.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.TeacherCheckingStudentItem;
import com.example.checkingsystem.entity.ResultObj;
import com.example.checkingsystem.entity.Student;
import com.example.checkingsystem.net.GetStudentInfoByID;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;
import util.StudentStateInfoItemAdapter;

public class TeacherAskForLeaveInfoFragment extends Fragment {
    public final static int RIGHT = 1;
    public final static int FALSE = 0;
    ObjectMapper objectMapper= new ObjectMapper();
    ResultObj<List<String>> resultObjID;
    ResultObj<List<Student>> resultObjStu;
    ProgressDialog progressDialog;
    Handler handlerQueryID = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case RIGHT :
                    List<Student> listStu = resultObjStu.getData();
                    List<TeacherCheckingStudentItem> list = new ArrayList<>();
                    for(Student stu:listStu)
                    {
                        TeacherCheckingStudentItem studentItem = new TeacherCheckingStudentItem();
                        studentItem.setStudentName(stu.getStudentName());
                        studentItem.setStudentNo(stu.getStudentNo());
                        studentItem.setStudentState("请假");
                        list.add(studentItem);
                    }
                    StudentStateInfoItemAdapter studentStateItemAdapter = new StudentStateInfoItemAdapter(getContext(),R.layout.teacher_checking_student_list_item,list);
                    listView.setAdapter(studentStateItemAdapter);
                    progressDialog.dismiss();
                    break;
                case FALSE:
                    Toast.makeText(getContext(),"操作失败，请稍后再试",Toast.LENGTH_SHORT).show();
                    break;
            }
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

                Message message = new Message();
                message.what = RIGHT;
                handlerQueryID.sendMessage(message);
            }
            else {
                Message message = new Message();
                message.what = FALSE;
                handlerQueryID.sendMessage(message);
            }

        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handlerQueryID.sendMessage(message);
        }
    };
    HttpCallbackListener httpCallbackListenerGetStudentID = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Log.e("get","---------4");
            try {
                resultObjID = objectMapper.readValue(response.getBytes(), new TypeReference<ResultObj<List<String>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("get","---------5");
            if(resultObjID.getMeta().getResult())
            {
                GetStudentInfoByID getStudentInfoByID = new GetStudentInfoByID();
                getStudentInfoByID.teacherGetStudentInfoByID(httpCallbackListenerGetStudentInfo,resultObjID.getData());

            }
        }

        @Override
        public void onError(Exception e) {
            Message message = new Message();
            message.what = FALSE;
            handlerQueryID.sendMessage(message);
        }
    };

    private View view;
    private OnFragmentInteractionListener mListener;
    private ListView listView;

    public TeacherAskForLeaveInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("test","--------------1");
        view = inflater.inflate(R.layout.fragment_teacher_ask_for_leave_info, container, false);
        progressDialog = ProgressDialog.show(getActivity(),"查询请假信息", "请稍等", true, false);
        initItem();


        return view;
    }

    private void initItem() {
        listView = (ListView) view.findViewById(R.id.teacher_ask_for_leave_info_listview);
        Log.e("test","--------------2");
        String courseID = ((TeacherCheckingActivity)getActivity()).courseID;
        String path = HttpUtil.urlIp+ PathUtil.TEACHER_GET_STUDENT_ASK_FOR_LEAVE;
        String data = "courseTimeId="+courseID;
        Log.e("test","--------------3");
        HttpUtil.sendHttpGetRequest(path+"?"+data,httpCallbackListenerGetStudentID);


    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
