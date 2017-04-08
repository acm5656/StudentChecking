package com.example.checkingsystem.teacher.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;

import java.util.List;

import util.HttpCallbackListener;
import util.HttpUtil;
import util.PathUtil;

public class TeacherAskForLeaveInfoFragment extends Fragment {
    private View view;
    private OnFragmentInteractionListener mListener;
    private ListView listView;
    private HttpCallbackListener httpCallbackListener = new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {

        }

        @Override
        public void onError(Exception e) {

        }
    };

    public TeacherAskForLeaveInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_ask_for_leave_info, container, false);
        initItem();


        return view;
    }

    private void initItem() {
        listView = (ListView) view.findViewById(R.id.teacher_ask_for_leave_info_listview);
        String courseID = ((TeacherCheckingActivity)getActivity()).courseID;
        String path = HttpUtil.urlIp+ PathUtil.TEACHER_GET_STUDENT_ASK_FOR_LEAVE;
//        String data
//        HttpUtil.sendHttpGetRequest();

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
