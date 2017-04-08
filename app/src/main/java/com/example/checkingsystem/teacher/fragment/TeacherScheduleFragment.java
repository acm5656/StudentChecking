package com.example.checkingsystem.teacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.teacher.activity.TeacherCheckingActivity;
import com.example.checkingsystem.teacher.activity.TeacherIndexActivity;

public class TeacherScheduleFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private View view;
    private TextView weekOneClassOne;

    public TeacherScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_schedule, container, false);
        initUI();

        return view;
    }

    private void initUI() {
        weekOneClassOne = (TextView) view.findViewById(R.id.WeekOneClassOne);
        weekOneClassOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.WeekOneClassOne:
                String weekTime = ((TeacherIndexActivity)getActivity()).weekTime;
                Intent intent = new Intent(getActivity(), TeacherCheckingActivity.class);
                String courseID = getCouorseID(weekTime,1,1);
                intent.putExtra("courseID",courseID);
                startActivity(intent);
                break;
        }
    }

    private String getCouorseID(String weekTime, int course, int weekDay) {
        return "1";
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
