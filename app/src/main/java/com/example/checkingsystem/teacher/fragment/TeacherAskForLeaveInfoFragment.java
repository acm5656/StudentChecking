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

import java.util.List;

public class TeacherAskForLeaveInfoFragment extends Fragment {
    private View view;
    private OnFragmentInteractionListener mListener;
    private ListView listView;

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

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
